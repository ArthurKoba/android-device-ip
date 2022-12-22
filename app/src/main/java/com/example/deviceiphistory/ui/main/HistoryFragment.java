package com.example.deviceiphistory.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deviceiphistory.ApiService;
import com.example.deviceiphistory.R;

public class HistoryFragment extends Fragment {

    Context context;
    ApiService service;

    private HistoryViewModel mViewModel;

    public static HistoryFragment newInstance(Context context) {
        return new HistoryFragment(context);
    }

    public HistoryFragment(Context context) {
//        this.service = service;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel
    }

}