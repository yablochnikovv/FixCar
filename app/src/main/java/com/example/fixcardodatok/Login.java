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

public class Login extends AppCompatActivity {

    TextInputEditText textInputEditTextEmail, textInputEditTextSifre;
    Button buttonLogin, buttonGoToMain;
    TextView textViewSignUp, buttonF;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextEmail = findViewById(R.id.emailLogin);
        textInputEditTextSifre = findViewById(R.id.LoginSifre);
        buttonLogin = findViewById(R.id.LoginButon);
        textViewSignUp = findViewById(R.id.KayitOlText);
        progressBar = findViewById(R.id.progress);
        buttonGoToMain = findViewById(R.id.buttonGoToMain);
        buttonF = findViewById(R.id.button5);

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, sifre;
                email = String.valueOf(textInputEditTextEmail.getText());
                sifre = String.valueOf(textInputEditTextSifre.getText());

                if (!email.equals("") && !sifre.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "sifre";


                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = sifre;

                            PutData putData = new PutData("http://192.168.31.118/LoginRegister/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();

                                    if (result.equals("Успішний вхід в систему!") && (email.equals("admin") && sifre.equals("admin"))) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Admin.class);
                                        startActivity(intent);
                                    } else if (result.equals("Успішний вхід в систему!")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        buttonGoToMain.setVisibility(View.GONE);
                                        buttonGoToMain.setEnabled(false);
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

        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        buttonGoToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}