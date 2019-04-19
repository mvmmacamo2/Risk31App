package com.kaya.risk31app.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kaya.risk31app.R;
import com.kaya.risk31app.Storage.TokenManager;
import com.kaya.risk31app.Storage.UserStorage;

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

        tnome = fragmentView.findViewById(R.id.perfil_nome);
        tnivel = fragmentView.findViewById(R.id.perfil_nivel);
        tcelular = fragmentView.findViewById(R.id.perfil_celular);
        tendereco = fragmentView.findViewById(R.id.perfil_endereco);
        temail = fragmentView.findViewById(R.id.perfil_email);
        imageViewFoto = fragmentView.findViewById(R.id.perfil_foto);

        getPerfil();

        return  fragmentView;
//        return inflater.inflate(R.layout.fragment_perfil, container, false);
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void getPerfil() {
        tnivel.setText(userStorage.getUser().getNivel());
        tnome.setText(userStorage.getUser().getName());
        tcelular.setText(userStorage.getUser().getCelular());
        temail.setText(userStorage.getUser().getEmail());
        tendereco.setText(userStorage.getUser().getEndereco());
        Glide.with(getActivity()).load(userStorage.getUser().getFoto()).circleCrop()
                .into(imageViewFoto);
    }

    private void showLogMessage(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
