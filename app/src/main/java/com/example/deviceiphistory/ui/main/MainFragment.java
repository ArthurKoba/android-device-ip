package com.example.deviceiphistory.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deviceiphistory.ApiService;
import com.example.deviceiphistory.R;


public class MainFragment extends Fragment {

    TextView actualAddressView;
    Button updateAddressButton;
    TextView lastAddressView;
    ApiService apiService;


    public MainFragment() {
        apiService = ApiService.getInstance();
    }

    public static MainFragment newInstance(Context context) {
        //Вот тут нужно получить ссылку на объект, чтобы его пробросить в конструктор
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initElements(view);
        initDefaultTexts();
        initHandlers();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        clickUpdateAddress();
    }

    @SuppressLint("SetTextI18n")
    public void setActualAddress(String ip) {
        actualAddressView.setText(getString(R.string.actualIpAddress) + " " + ip);
    }

    @SuppressLint("SetTextI18n")
    public void setLastAddress(String ip) {
        lastAddressView.setText(getString(R.string.lastIpAddress) + " " + ip);
    }

    private void initElements(View view) {
        actualAddressView = view.findViewById(R.id.actualIpAddressView);
        lastAddressView = view.findViewById(R.id.lastIpAddressView);
        updateAddressButton = view.findViewById(R.id.updateIpAddress);
    }

    private void initDefaultTexts() {
        setActualAddress(getString(R.string.unknown));
        setLastAddress(getString(R.string.unknown));
    }

    private void initHandlers() {
        updateAddressButton.setOnClickListener(view -> clickUpdateAddress());
    }

    private void clickUpdateAddress() {
        setActualAddress(getString(R.string.loading));
        String actualAddress = apiService.getIp();
        if (actualAddress.length() != 0) {
            setActualAddress(actualAddress);
        } else {
            setActualAddress(getString(R.string.failedGetAddress));
        }

    }

}