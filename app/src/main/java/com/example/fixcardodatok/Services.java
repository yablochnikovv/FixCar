package com.example.fixcardodatok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Services extends AppCompatActivity {

    Button ButtonPeriodicMaintenance,ButtonRepairServices,ButtonBodyPainting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        ButtonPeriodicMaintenance = findViewById(R.id.buttonPeriodicMaintenance);
        ButtonRepairServices = findViewById(R.id.buttonRepairServices);
        ButtonBodyPainting = findViewById(R.id.buttonBodyPainting);

        ButtonPeriodicMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services.this, PeriodicMaintenance.class);
                startActivity(intent);

            }
        });

        ButtonRepairServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services.this, RepairServices.class);
                startActivity(intent);

            }
        });

        ButtonBodyPainting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services.this, BodyPainting.class);
                startActivity(intent);

            }
        });


    }


}