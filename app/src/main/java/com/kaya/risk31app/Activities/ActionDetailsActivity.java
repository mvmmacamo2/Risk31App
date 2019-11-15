package com.kaya.risk31app.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kaya.risk31app.Models.Actions;
import com.kaya.risk31app.R;
import com.kaya.risk31app.Storage.AccessToken;
import com.kaya.risk31app.Storage.ApiService;
import com.kaya.risk31app.Storage.RetrofitBuilder;
import com.kaya.risk31app.Storage.TokenManager;
import com.kaya.risk31app.Storage.UserStorage;
import com.kaya.risk31app.helpers.CloseActionDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActionDetailsActivity extends AppCompatActivity implements CloseActionDialog.ActtionCloseDialogListerner {

    TextView textViewRisk, textAction, textPrazo, textAdmin, textCreatedAt, textTipo;
    Toolbar toolbar;
    ImageView imageView;
    UserStorage userStorage;
    Button buttonFechar;
    ApiService service;
    ApiService serviceAction;
    Call<AccessToken> callToken;
    Call<Actions> callAction;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_details);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Detalhes da Acção");
//        userStorage = UserStorage.getINSTANCE(getSharedPreferences("preferences", MODE_PRIVATE));
        service = RetrofitBuilder.createService(ApiService.class);
//        tokenManager = TokenManager.getINSTANCE(preferences);
//        userStorage = UserStorage.getINSTANCE(preferences);
        tokenManager = TokenManager.getINSTANCE(getSharedPreferences("preferences", MODE_PRIVATE));
        userStorage = UserStorage.getINSTANCE(getSharedPreferences("preferences", MODE_PRIVATE));

        serviceAction = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);
        setSupportActionBar(toolbar);

        textViewRisk = findViewById(R.id.action_detail_risk_name);
        textAction = findViewById(R.id.action_detail_action);
        textPrazo = findViewById(R.id.action_detail_prazo);
        textAdmin = findViewById(R.id.action_detail_admin);
        textCreatedAt = findViewById(R.id.action_detail_created_at);
        textTipo = findViewById(R.id.action_detail_tipo);
        buttonFechar = findViewById(R.id.button_close_id);

        String risk = getIntent().getExtras().getString("risk");
        String action = getIntent().getExtras().getString("action");
        String prazo = getIntent().getExtras().getString("prazo");
        String admin = getIntent().getExtras().getString("admin");
        String created_at = getIntent().getExtras().getString("created_at");
        String tipo = getIntent().getExtras().getString("tipo");

        textViewRisk.setText(risk);
        textTipo.setText(tipo);
        textAdmin.setText(admin);
        textPrazo.setText(prazo);
        textCreatedAt.setText(created_at);
        textAction.setText(action);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


         openCloseDialog();


    }

    private void openCloseDialog() {

        buttonFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CloseActionDialog closeActionDialog = new CloseActionDialog();
                closeActionDialog.show(getSupportFragmentManager(), "Close Action Description");
            }
        });
    }

    @Override
    public void applyTexts(String descricao) {
        int id = userStorage.getUser().getId();
        if (descricao.isEmpty()) {

        }else {
            callAction = serviceAction.saveCloseAction(id, descricao);

            callAction.enqueue(new Callback<Actions>() {
                @Override
                public void onResponse(Call<Actions> call, Response<Actions> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ActionDetailsActivity.this, response.body().getEstado(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Actions> call, Throwable t) {
                    Toast.makeText(ActionDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
            Toast.makeText(this, descricao + "id" + userStorage.getUser().getId(), Toast.LENGTH_SHORT).show();
        }


    }
}
