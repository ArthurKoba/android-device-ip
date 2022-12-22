package com.example.deviceiphistory.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deviceiphistory.ApiService;
import com.example.deviceiphistory.R;


public class HistoryFragment extends Fragment {
    ApiService apiService;

    public static HistoryFragment newInstance(Context context) {
        return new HistoryFragment();
    }

    public HistoryFragment() {
//        apiService = ApiService.getInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
//        initElements(view);
//        initDefaultTexts();
//        initHandlers();
        return view;
    }

}