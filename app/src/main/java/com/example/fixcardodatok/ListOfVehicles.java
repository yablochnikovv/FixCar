package com.example.fixcardodatok;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
public class ListOfVehicles extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofvehicles);

        listView = findViewById(R.id.list_view);

        new FetchData().execute();
    }

    private class FetchData extends AsyncTask<Void, Void, Void> {

        ArrayList<String> data = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://192.168.31.118/LoginRegister/MyCars.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();
                while (line != null) {
                    line = bufferedReader.readLine();
                    stringBuilder.append(line);
                }
                String response = stringBuilder.toString();

                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String marka = jsonObject.getString("marka");
                    String user = jsonObject.getString("user_id");
                    String user2 = jsonObject.getString("userAd");
                    String user3 = jsonObject.getString("userSurname");
                    String model = jsonObject.getString("model");
                    String year = jsonObject.getString("year");
                    String plaka = jsonObject.getString("plaka");
                    String kilometre = jsonObject.getString("kilometre");
                    String yakit = jsonObject.getString("yakit");

                    String item = "id: "+ user + " " + "Ad Surname: " + user2 + " " + user3 + "\n" +"Araç Bilgileri:" + " " + marka + " " + model + " (" + year + ") - " + plaka + " " + " \n" + kilometre + " km - " + yakit;
                    data.add(item);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(ListOfVehicles.this, android.R.layout.simple_list_item_1, data);
            listView.setAdapter(adapter);
        }
    }
}