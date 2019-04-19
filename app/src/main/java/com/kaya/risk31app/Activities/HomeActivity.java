package com.kaya.risk31app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kaya.risk31app.Fragments.ActionsAtrasadasFragment;
import com.kaya.risk31app.Fragments.ActionsFragment;
import com.kaya.risk31app.Fragments.ClosedActionsFragment;
import com.kaya.risk31app.Fragments.HomeFragment;
import com.kaya.risk31app.Fragments.PerfilFragment;
import com.kaya.risk31app.LoginActivity;
import com.kaya.risk31app.R;
import com.kaya.risk31app.Service.UserProfileService;
import com.kaya.risk31app.Service.UserResponse;
import com.kaya.risk31app.Storage.ApiService;
import com.kaya.risk31app.Storage.RetrofitBuilder;
import com.kaya.risk31app.Storage.TokenManager;
import com.kaya.risk31app.Storage.UserStorage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView navName, navEmail;
    ImageView navFoto;
    ApiService service;
    TokenManager tokenManager;
    Call<UserResponse> call;
    UserStorage userStorage;
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        getSupportActionBar().setTitle("Home");
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        variaveis();

//        getPerfil();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HomeActivity.this, "Delay funciona", Toast.LENGTH_SHORT).show();
                getPerfil();
            }
        }, 1000);

//        TabLayout tabLayout = findViewById(R.id.tab_layout);
//        ViewPager viewPager = findViewById(R.id.view_pager);
//
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//
//        viewPagerAdapter.addFragments(new ActionsFragment(), "Accoes");
//        viewPagerAdapter.addFragments(new HomeFragment(), "Home");
//
//        viewPager.setAdapter(viewPagerAdapter);
//        tabLayout.setupWithViewPager(viewPager);

    }

    private void variaveis() {
        navEmail = findViewById(R.id.nav_email);
        navFoto = findViewById(R.id.nav_foto);
        navName = findViewById(R.id.nav_nome);
        tokenManager = TokenManager.getINSTANCE(getSharedPreferences("preferences", MODE_PRIVATE));
        userStorage = UserStorage.getINSTANCE(getSharedPreferences("preferences", MODE_PRIVATE));
        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

        getPerfil();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
          getSupportActionBar().setTitle("Home");
          getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        } else if (id == R.id.nav_actions) {
//            getSupportActionBar().setTitle("Acções");
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ActionsFragment()).commit();
            Intent actionActivity = new Intent(getApplicationContext(), ActionsActivity.class);
            startActivity(actionActivity);

        } else if (id == R.id.nav_closed_actions) {
            getSupportActionBar().setTitle("Acções Fechadas");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ClosedActionsFragment()).commit();

        } else if (id == R.id.nav_atrasadas_actions) {
            getSupportActionBar().setTitle("Acções Atrasadas");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ActionsAtrasadasFragment()).commit();

        } else if (id == R.id.nav_perfil) {
            getSupportActionBar().setTitle("Perfil");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new PerfilFragment()).commit();

        } else if (id == R.id.nav_exit) {
            tokenManager.deleteToken();
            userStorage.deleteUserProfile();
            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (call != null) {
            call.cancel();
            call = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (userStorage.getUser() != null) {
            getPerfil();
        }
         getPerfil();
    }

    private void getPerfil() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUserName = headerView.findViewById(R.id.nav_nome);
        TextView navUserEmail = headerView.findViewById(R.id.nav_email);
        ImageView navUserFoto = headerView.findViewById(R.id.nav_foto);

        navUserName.setText(userStorage.getUser().getName());
        navUserEmail.setText(userStorage.getUser().getEmail());
        Glide.with(this).load(userStorage.getUser().getFoto()).circleCrop()
                .into(navUserFoto);
//        Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();
    }


class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments;
    ArrayList<String> titles;

    ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragments = new ArrayList<>();
        this.titles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragments(Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}

}
