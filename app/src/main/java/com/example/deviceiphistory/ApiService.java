package com.example.deviceiphistory;

import android.os.StrictMode;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiService {
    String baseUrl = "https://api.ipify.org/?format=json";

    public static final ApiService INSTANCE = new ApiService();

    private ApiService() {}

    public static ApiService getInstance(){
        return INSTANCE;
    }

    private String request() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(baseUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder buffer = new StringBuilder();
            String line = "";
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
}
