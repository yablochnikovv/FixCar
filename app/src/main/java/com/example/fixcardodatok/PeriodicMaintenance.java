package com.example.fixcardodatok;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class PeriodicMaintenance extends AppCompatActivity {


    Button buttonRepairAl;

    Spinner spinnerRepair;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodic_maintenance);


        buttonRepairAl = (Button) findViewById(R.id.buttonRepairAl);

        spinnerRepair = findViewById(R.id.spinnerRepair);



        buttonRepairAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Repair;

                Repair = spinnerRepair.getSelectedItem().toString();


                if (!Repair.equals("")) {
                    //progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[1];
                            field[0] = "RepairTuru";


                            //Creating array for data
                            String[] data = new String[1];
                            data[0] = Repair;


                            PutData putData = new PutData("http://192.168.31.118/LoginRegister/Services.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Сервісний запис успішно виконано!")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Services.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Обов'язкові поля повинні бути заповнені", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}