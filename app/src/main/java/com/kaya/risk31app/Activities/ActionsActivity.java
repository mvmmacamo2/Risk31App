package com.kaya.risk31app.Activities;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kaya.risk31app.Adapters.ViewPagerAdapter;
import com.kaya.risk31app.Fragments.AccoesFechadasFragment;
import com.kaya.risk31app.Fragments.ActionsAtrasadasFragment;
import com.kaya.risk31app.Fragments.ActionsFragment;
import com.kaya.risk31app.Fragments.ClosedActionsFragment;
import com.kaya.risk31app.R;
import com.kaya.risk31app.Storage.TokenManager;
import com.kaya.risk31app.Storage.UserStorage;

public class ActionsActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    ImageView userProfileFoto;
    UserStorage userStorage;
    TokenManager tokenManager;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);

        tabLayout = findViewById(R.id.tablayout_id);
        appBarLayout = findViewById(R.id.appbar_id);
        viewPager = findViewById(R.id.viewPager_id);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actions_id);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Actions");
//        username = findViewById(R.id.actions_app_bar_image_name);
//        userProfileFoto = findViewById(R.id.actions_app_bar_image_profile);
        tokenManager = TokenManager.getINSTANCE(getSharedPreferences("preferences", MODE_PRIVATE));
        userStorage = UserStorage.getINSTANCE(getSharedPreferences("preferences", MODE_PRIVATE));

//        username.setText(userStorage.getUser().getName());
//        Glide.with(this).load(userStorage.getUser().getFoto()).circleCrop().into(userProfileFoto);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragments(new ActionsFragment(), "Todas");
        adapter.addFragments(new ActionsAtrasadasFragment(), "Atrasadas");
        adapter.addFragments(new ClosedActionsFragment(), "Fechadas");
        adapter.addFragments(new AccoesFechadasFragment(), "Cumpridas");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
