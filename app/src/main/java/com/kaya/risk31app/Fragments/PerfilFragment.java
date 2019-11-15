package com.kaya.risk31app.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kaya.risk31app.R;
import com.kaya.risk31app.Storage.TokenManager;
import com.kaya.risk31app.Storage.UserStorage;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // MINHAS VARIAVEIS
    TokenManager tokenManager;
    UserStorage userStorage;
    SharedPreferences preferences;
    TextView tnome, temail, tendereco, tnivel, tcelular;
    ImageView imageViewFoto;
    EditText editNome, editEmail, editCelular, editEndereco, editNivel;
    Button btnUpdate;
    ProgressBar progressBar;
    File file;
    Uri uri;
    Intent CamIntent, GalIntent, CropIntent;
    final int RequestPermissionCode=1;
    DisplayMetrics displayMetrics;
    int width, height;


    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_perfil, container, false);

        preferences = this.getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        tokenManager = TokenManager.getINSTANCE(preferences);
        userStorage = UserStorage.getINSTANCE(preferences);


        editNome = fragmentView.findViewById(R.id.perfil_edit_name_id);
        editCelular = fragmentView.findViewById(R.id.perfil_edit_celular_id);
        editEmail = fragmentView.findViewById(R.id.perfil_edit_email_id);
        editNivel = fragmentView.findViewById(R.id.perfil_edit_nivel_id);
        editEndereco = fragmentView.findViewById(R.id.perfil_edit_endereco_id);
        btnUpdate = fragmentView.findViewById(R.id.button_perfil_update_id);
        progressBar = fragmentView.findViewById(R.id.progressBarPerfilUpdateId);

        editEmail.setFocusable(false);
        editNivel.setFocusable(false);

//        editEmail.setEnabled(false);
//        editEmail.setCursorVisible(false);
//        editEmail.setKeyListener(null);
//        editEmail.setBackgroundColor(Color.TRANSPARENT);

        imageViewFoto = fragmentView.findViewById(R.id.perfil_foto);

        getPerfil();

        updateUser();

        return  fragmentView;
    }

    private void updateUser() {

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               btnUpdate.setVisibility(View.INVISIBLE);
               progressBar.setVisibility(View.VISIBLE);
            }
        });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void getPerfil() {
        editNivel.setText(userStorage.getUser().getNivel());
        editNome.setText(userStorage.getUser().getName());
        editCelular.setText(userStorage.getUser().getCelular());
        editEmail.setText(userStorage.getUser().getEmail());
        editEndereco.setText(userStorage.getUser().getEndereco());


        Glide.with(getActivity()).load(userStorage.getUser().getFoto()).circleCrop()
                .into(imageViewFoto);
    }

    private void showLogMessage(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
