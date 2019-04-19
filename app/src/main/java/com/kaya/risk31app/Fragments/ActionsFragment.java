package com.kaya.risk31app.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kaya.risk31app.Adapters.ActionsAdapter;
import com.kaya.risk31app.R;
import com.kaya.risk31app.Service.ActionsResponse;
import com.kaya.risk31app.Storage.ApiService;
import com.kaya.risk31app.Storage.RetrofitBuilder;
import com.kaya.risk31app.Storage.TokenManager;
import com.kaya.risk31app.Storage.UserStorage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActionsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerView;
    ActionsAdapter actionsAdapter;
    Call<ActionsResponse> call;
    ApiService apiService;
    TokenManager tokenManager;
    SharedPreferences preferences;
    UserStorage userStorage;
    TextView btnAction;
    ProgressBar progressBar;


    public ActionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActionsFragment newInstance(String param1, String param2) {
        ActionsFragment fragment = new ActionsFragment();
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

        View fragmentView = inflater.inflate(R.layout.fragment_actions, container, false);
        recyclerView = fragmentView.findViewById(R.id.actions_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        preferences = this.getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        tokenManager = TokenManager.getINSTANCE(preferences);
        userStorage = UserStorage.getINSTANCE(preferences);
        apiService = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);
        return fragmentView;
//        return inflater.inflate(R.layout.fragment_actions, container, false);
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

    @Override
    public void onStart() {
        super.onStart();
       int id =userStorage.getUser().getId();
        call = apiService.getActions(id);

//        View inflatedView = getLayoutInflater().inflate(R.layout.row_myactions_item, null);
////        final ProgressBar progress = inflatedView.findViewById(R.id.progressBarMyActions_id);
//       progressBar  = inflatedView.findViewById(R.id.progressBarMyActions_id);




        call.enqueue(new Callback<ActionsResponse>() {
            @Override
            public void onResponse(Call<ActionsResponse> call, Response<ActionsResponse> response) {
                if (response.isSuccessful()) {
                    actionsAdapter = new ActionsAdapter(getContext(), response.body().getData());
                    recyclerView.setAdapter(actionsAdapter);
//                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ActionsResponse> call, Throwable t) {
                showLogMessage(t.getMessage());
            }
        });
    }

    private void showLogMessage(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void detalhes() {
      btnAction.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(getContext(), "Clicado", Toast.LENGTH_SHORT).show();
          }
      });
    }
}
