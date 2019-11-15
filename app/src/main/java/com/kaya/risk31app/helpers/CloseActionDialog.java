package com.kaya.risk31app.helpers;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kaya.risk31app.Activities.ActionDetailsActivity;
import com.kaya.risk31app.Models.Actions;
import com.kaya.risk31app.R;
import com.kaya.risk31app.Storage.AccessToken;
import com.kaya.risk31app.Storage.ApiService;
import com.kaya.risk31app.Storage.RetrofitBuilder;
import com.kaya.risk31app.Storage.TokenManager;
import com.kaya.risk31app.Storage.UserStorage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CloseActionDialog extends AppCompatDialogFragment {
    EditText editTextDescricao;
    private ActtionCloseDialogListerner  listerner;
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
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_close_action_dialog, null);

        builder.setView(view)
        .setTitle("Descrição do Desfecho")
        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               String descricao = editTextDescricao.getText().toString();

                int id = userStorage.getUser().getId();
                service = RetrofitBuilder.createService(ApiService.class);

                tokenManager = TokenManager.getINSTANCE(getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE));
                userStorage = UserStorage.getINSTANCE(getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE));

                serviceAction = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

//               listerner.applyTexts(descricao);
            }
        });

        editTextDescricao = view.findViewById(R.id.action_detail_close_text_id);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        try {
            listerner = (ActtionCloseDialogListerner) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ActionCloseDialog");
        }

    }

    public interface ActtionCloseDialogListerner {
        void applyTexts(String descricao);
    }
}
