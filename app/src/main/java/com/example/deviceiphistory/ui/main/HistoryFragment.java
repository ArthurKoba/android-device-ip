package com.example.deviceiphistory.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.deviceiphistory.ApiService;
import com.example.deviceiphistory.R;


public class HistoryFragment extends Fragment {
    Button getHistoryButton;
    Button clearHistoryButton;
    ListView historyListView;
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
        getHistoryButton = view.findViewById(R.id.getHistory);
        clearHistoryButton = view.findViewById(R.id.clearHistory);
        historyListView = view.findViewById(R.id.historyList);
        getHistoryButton.setOnClickListener(v -> getHistory());
        clearHistoryButton.setOnClickListener(v -> clearHistory());
        return view;
    }

    private void getHistory() {
        Log.d("History", "click get");

    }

    private void clearHistory() {
        Log.d("History", "click clear");
    }



}