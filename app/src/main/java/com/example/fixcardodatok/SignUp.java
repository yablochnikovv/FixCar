package com.example.fixcardodatok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {
    TextInputEditText textInputEditTextuserAd, textInputEditTextuserSurname, textInputEditTextSifre, textInputEditTextEmail;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEditTextuserAd = findViewById(R.id.userAd);
        textInputEditTextuserSurname = findViewById(R.id.userSurname);
        textInputEditTextSifre = findViewById(R.id.password);
        textInputEditTextEmail = findViewById(R.id.email);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userAd, userSurname, sifre, email;
                userAd = String.valueOf(textInputEditTextuserAd.getText());
                userSurname = String.valueOf(textInputEditTextuserSurname.getText());
                sifre = String.valueOf(textInputEditTextSifre.getText());
                email = String.valueOf(textInputEditTextEmail.getText());

                if (!userAd.equals("") && !userSurname.equals("") && !sifre.equals("") && !email.equals("")){
                    progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {


                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[4];
                        field[0] = "userAd";
                        field[1] = "userSurname";
                        field[2] = "sifre";
                        field[3] = "email";

                        //Creating array for data
                        String[] data = new String[4];
                        data[0] = userAd;
                        data[1] = userSurname;
                        data[2] = sifre;
                        data[3] = email;

                        PutData putData = new PutData("http://192.168.31.118/LoginRegister/login.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if (result.equals("Реєстрацію успішно завершено!"))
                                {
                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);

                                }
                                else {
                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Обов'язкові поля повинні бути заповнені", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}