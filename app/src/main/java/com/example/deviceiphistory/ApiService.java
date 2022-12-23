package com.example.deviceiphistory;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.util.Log;

import com.example.deviceiphistory.db.DBConst;
import com.example.deviceiphistory.db.DbHelper;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ApiService {
    private static ApiService INSTANCE = null;
    private SQLiteDatabase db;
    private final DbHelper dbHelper;

    private ApiService(Context context) {
        dbHelper = new DbHelper(context);
    }

    public static ApiService newInstance(Context context) {
        if (INSTANCE == null) INSTANCE = new ApiService(context);
        return INSTANCE;
    }

    public static ApiService getInstance(){
        return INSTANCE;
    }

    @SuppressLint("SuspiciousIndentation")
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

    public void openDb() {
        db = dbHelper.getWritableDatabase();
    }

    public void closeDb() {
        db.close();
    }

    public String getLastIp() {
        try {
            openDb();
            List<String> listIp = getHistory();
            for (String ip: listIp) {
                Log.d("Api", "DB ip: " + ip);
            }
            if (!listIp.isEmpty()) {
                return listIp.get(0);
            }
        } finally {
            closeDb();
        }

        return "";
    }

    public void writeNewIp(String ip) {
        if (ip.length() == 0) return;
        Log.d("Api", "write ip to db: " + ip);
        try {
            openDb();
            ContentValues values = new ContentValues();
            values.put(DBConst.COLUMN_NAME_ADDRESS, ip);
            db.insert(DBConst.TABLE_NAME, null, values);
        } finally {
            closeDb();
        }
    }

    public List<String> getHistory() {
        try {
            openDb();
            List<String> listAddresses = new ArrayList<>();
            Cursor cursor = db.query(DBConst.TABLE_NAME,null, null,
                    null, null,null, null);
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String address = cursor.getString(
                        cursor.getColumnIndex(DBConst.COLUMN_NAME_ADDRESS)
                );
                listAddresses.add(address);
            }
            cursor.close();
            return listAddresses;
        } finally {
            closeDb();
        }

    }

    public void clearHistory() {
        try {
            openDb();
            db.execSQL(DBConst.CLEAR_TABLE);
        } finally {
            closeDb();
        }
    }
}
