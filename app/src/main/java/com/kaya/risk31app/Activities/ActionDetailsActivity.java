package com.kaya.risk31app.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kaya.risk31app.R;
import com.kaya.risk31app.Storage.UserStorage;

public class ActionDetailsActivity extends AppCompatActivity {

    TextView textViewRisk, textAction, textPrazo, textAdmin, textCreatedAt, textTipo;
    Toolbar toolbar;
    ImageView imageView;
    UserStorage userStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_details);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Detalhes da Acção");
        userStorage = UserStorage.getINSTANCE(getSharedPreferences("preferences", MODE_PRIVATE));
        setSupportActionBar(toolbar);

        textViewRisk = findViewById(R.id.action_detail_risk_name);
        textAction = findViewById(R.id.action_detail_action);
        textPrazo = findViewById(R.id.action_detail_prazo);
        textAdmin = findViewById(R.id.action_detail_admin);
        textCreatedAt = findViewById(R.id.action_detail_created_at);
        textTipo = findViewById(R.id.action_detail_tipo);

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

    }
}
