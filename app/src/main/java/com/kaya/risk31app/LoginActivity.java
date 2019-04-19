package com.kaya.risk31app;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.bumptech.glide.Glide;
import com.kaya.risk31app.Activities.HomeActivity;
import com.kaya.risk31app.Service.UserProfileService;
import com.kaya.risk31app.Storage.AccessToken;
import com.kaya.risk31app.Storage.ApiError;
import com.kaya.risk31app.Storage.ApiService;
import com.kaya.risk31app.Storage.RetrofitBuilder;
import com.kaya.risk31app.Storage.TokenManager;
import com.kaya.risk31app.Storage.UserStorage;
import com.kaya.risk31app.Storage.Utils;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    Button btnLogin;
    EditText editEMail, editPassword;
    ProgressBar loginProgressBar;

    ApiService service;
    ApiService service2;
    TokenManager tokenManager;
    AwesomeValidation awesomeValidation;
    Call<AccessToken> call;
    Call<UserProfileService> callUser;
    UserStorage userStorage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        variaveis();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void variaveis() {
        btnLogin = findViewById(R.id.login_button);
        editEMail = findViewById(R.id.login_email);
        editPassword = findViewById(R.id.login_password);
        loginProgressBar = findViewById(R.id.login_progressBar);
        tokenManager = TokenManager.getINSTANCE(getSharedPreferences("preferences", MODE_PRIVATE));
        userStorage = UserStorage.getINSTANCE(getSharedPreferences("preferences", MODE_PRIVATE));
        service = RetrofitBuilder.createService(ApiService.class);
        service2 = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);
        awesomeValidation = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);

    }


    private void loginUser() {

        String email = editEMail.getText().toString();
        String password = editPassword.getText().toString();

        loginProgressBar.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.INVISIBLE);
        call = service.login(email, password);

        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                Log.w(TAG, "onResponse: " + response);
               if (response.isSuccessful()) {

                   loginProgressBar.setVisibility(View.INVISIBLE);
                   btnLogin.setVisibility(View.VISIBLE);
                   tokenManager.saveToken(response.body());
                   saveUser();
                   callHomeActivity();

               } else {
                   if (response.code() == 422) {
                       loginProgressBar.setVisibility(View.INVISIBLE);
                       btnLogin.setVisibility(View.VISIBLE);
                       handleErros(response.errorBody());
                   }
                   if (response.code() == 401) {
                       ApiError apiError = Utils.converErrors(response.errorBody());
                       showMessage(apiError.getMessage());
                       Toast.makeText(LoginActivity.this, apiError.getMessage(), Toast.LENGTH_LONG).show();
                       loginProgressBar.setVisibility(View.INVISIBLE);
                       btnLogin.setVisibility(View.VISIBLE);
                   }

               }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.w(TAG, "on Failure: " + t.getMessage());
                showMessage(t.getMessage());
                loginProgressBar.setVisibility(View.INVISIBLE);
                btnLogin.setVisibility(View.VISIBLE);
            }
        });


    }

    private void saveUser() {
      callUser = service2.getUserProfile();

      callUser.enqueue(new Callback<UserProfileService>() {
          @Override
          public void onResponse(Call<UserProfileService> call, Response<UserProfileService> response) {
              if (response.isSuccessful()) {
                  userStorage.saveUserProfile(response.body());
                  showMessage("Bem vindo brada");
//                  getPerfil();
              }
              userStorage.saveUserProfile(response.body());
          }

          @Override
          public void onFailure(Call<UserProfileService> call, Throwable t) {
             showMessage("ERROR" + t.getMessage());
          }
      });


    }

    private void callHomeActivity() {
        Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }

    private void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void handleErros(ResponseBody response) {
        ApiError apiError = Utils.converErrors(response);
        for (Map.Entry<String, List<String>> error : apiError.getErrors().entrySet()) {

            if (error.getKey().equals("email")) {
                editEMail.setError(error.getValue().get(0));
            }

            if (error.getKey().equals("password")) {
                editPassword.setError(error.getValue().get(0));
            }

        }
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
        if (tokenManager.getToken().getAccessToken() != null) {

            Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(homeActivity);
            finish();
        }
    }

    private void getPerfil() {
        userStorage.getUser();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUserName = headerView.findViewById(R.id.nav_nome);
        TextView navUserEmail = headerView.findViewById(R.id.nav_email);
        ImageView navUserFoto = headerView.findViewById(R.id.nav_foto);

        navUserName.setText(userStorage.getUser().getName());
        navUserEmail.setText(userStorage.getUser().getEmail());

        Glide.with(this).load(userStorage.getUser().getFoto()).circleCrop()
                .into(navUserFoto);
    }

}
