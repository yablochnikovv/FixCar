package com.example.fixcardodatok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button listele, Car;
        ImageButton home;

        listele = findViewById(R.id.listeleButton);
        Car = findViewById(R.id.CarButton);
        home = findViewById(R.id.homeb);


        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, ListOfVehicles.class);
                startActivity(intent);

            }
        });

        Car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, RemoveCar.class);
                startActivity(intent);

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}