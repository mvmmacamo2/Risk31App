package com.kaya.risk31app.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaya.risk31app.R;

public class AccoesFechadasFragment extends Fragment {
    View view;
    public AccoesFechadasFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       view = inflater.inflate(R.layout.fragment_accoes_fechadas, container, false);
        return view;
    }
}
