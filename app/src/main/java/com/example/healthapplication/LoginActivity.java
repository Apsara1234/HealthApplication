package com.example.healthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapplication.bll.LoginBLL;
import com.example.healthapplication.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {
    TextView tvRegister;
    EditText etEmail,etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        etEmail=findViewById(R.id.etemail);
        etPassword=findViewById(R.id.etpassword);
        btnLogin=findViewById(R.id.btnlogin);
        tvRegister= findViewById(R.id.tvregister1);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });

    }

    private void login(){
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();

        LoginBLL loginBLL=new LoginBLL();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(email,password)){
            Intent intent=new Intent(LoginActivity.this, MainActivity2.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "email and password is incorrect", Toast.LENGTH_LONG).show();

            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(50);
            etEmail.requestFocus();

        }
    }

}