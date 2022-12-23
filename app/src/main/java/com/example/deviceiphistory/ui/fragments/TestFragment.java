package com.example.deviceiphistory.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deviceiphistory.ApiService;
import com.example.deviceiphistory.R;

public class TestFragment extends Fragment {

    TextView testText;
    Button testButton;
    EditText inputText;
    ApiService apiService;

    public TestFragment() {
        apiService = ApiService.getInstance();
    }

    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        testText = view.findViewById(R.id.testText);
        testButton = view.findViewById(R.id.testButton);
        inputText = view.findViewById(R.id.testInput);
        testButton.setOnClickListener(v -> click());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Events", "focus to test");
    }

    private void click() {
        String input = inputText.getText().toString();
        if (input.length() == 0) return;
        Log.d("Test", input);
        apiService.writeNewIp(input);
        apiService.getHistory();

    }
}