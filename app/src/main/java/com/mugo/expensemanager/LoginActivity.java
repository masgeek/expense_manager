package com.mugo.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    //Session session;
    //RequestQueue queue;
    Intent launchNextActivity;
    private Button btnLogin, btnRegister;
    private EditText txtEmail, txtPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //lets get the elements
        //(Button) findViewById(R.id.button) ;
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        //get the text fields
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        //add click listeners
        btnLogin.setOnClickListener(LoginActivity.this);
        btnRegister.setOnClickListener(LoginActivity.this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        //initate the login here
    }
}
