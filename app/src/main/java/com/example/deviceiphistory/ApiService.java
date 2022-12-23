package com.example.deviceiphistory;

import android.content.Context;
import android.os.StrictMode;

import com.example.deviceiphistory.db.DbManager;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiService {
    private final DbManager manager;
    private static ApiService INSTANCE = null;


    private ApiService(Context context) {
        manager = new DbManager(context);
    }

    public static ApiService newInstance(Context context) {
        if (INSTANCE == null) INSTANCE = new ApiService(context);
        return INSTANCE;
    }

    public static ApiService getInstance(){
        return INSTANCE;
    }

    private String request() {
        String baseUrl = "https://api.ipify.org/?format=json";
        HttpURLConnection connection = null;
        try {
            URL url = new URL(baseUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder buffer = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null)
                buffer.append(line).append("\n");
                reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (connection != null) connection.disconnect();
        }
        return "";
    }

    public String getIp() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String response = request();
        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString("ip");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getLastIp() {
        manager.openDb();
        manager.closeDb();
        return "";
    }
}
