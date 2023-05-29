package com.example.fixcardodatok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ButtonCarInformation, ButtonIletisim, ButtonServices, ButtonProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonIletisim = findViewById(R.id.button4);
        ButtonCarInformation = findViewById(R.id.button1);
        ButtonServices = findViewById(R.id.button2);
        ButtonProducts = findViewById(R.id.button3);



        ButtonIletisim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactDetails.class);
                startActivity(intent);

            }
        });

        ButtonCarInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Appointment.class);
                startActivity(intent);

            }
        });

        ButtonServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Services.class);
                startActivity(intent);

            }
        });

        ButtonProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Products.class);
                startActivity(intent);
            }
        });

    }

}