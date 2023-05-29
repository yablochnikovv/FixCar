package com.example.fixcardodatok;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Products extends AppCompatActivity {

    private TextView urun1TextView, urun2TextView, urun3TextView, urun4TextView, urun5TextView, fiyat1Textview,
            fiyat2Textview, fiyat3Textview, fiyat4Textview, fiyat5Textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // Запуск AsyncTask для отримання даних
        new FetchDataTask().execute();
    }

    private class FetchDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonStr = null;

            try {
                // URL-адреса PHP-файлу, з якого йдуть дані
                URL url = new URL("http://192.168.31.118/LoginRegister/products.php");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder builder = new StringBuilder();
                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }

                if (builder.length() == 0) {
                    return null;
                }

                jsonStr = builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return jsonStr;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray jsonArray = new JSONArray(s);

                // Вивести перші дві адреси в TextViews
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                urun1TextView.setText(jsonObject1.getString("urun_ad"));

                JSONObject jsonObject2 = jsonArray.getJSONObject(1);
                urun2TextView.setText(jsonObject2.getString("urun_ad"));

                JSONObject jsonObject3 = jsonArray.getJSONObject(2);
                urun3TextView.setText(jsonObject3.getString("urun_ad"));

                JSONObject jsonObject4 = jsonArray.getJSONObject(3);
                urun4TextView.setText(jsonObject4.getString("urun_ad"));

                JSONObject jsonObject5 = jsonArray.getJSONObject(4);
                urun5TextView.setText(jsonObject5.getString("urun_ad"));


                JSONObject jsonObject6 = jsonArray.getJSONObject(0);
                fiyat1Textview.setText(jsonObject5.getString("fiyat"));

                JSONObject jsonObject7 = jsonArray.getJSONObject(1);
                fiyat2Textview.setText(jsonObject6.getString("fiyat"));

                JSONObject jsonObject8 = jsonArray.getJSONObject(2);
                fiyat3Textview.setText(jsonObject7.getString("fiyat"));

                JSONObject jsonObject9 = jsonArray.getJSONObject(3);
                fiyat4Textview.setText(jsonObject8.getString("fiyat"));

                JSONObject jsonObject10 = jsonArray.getJSONObject(4);
                fiyat5Textview.setText(jsonObject9.getString("fiyat"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}