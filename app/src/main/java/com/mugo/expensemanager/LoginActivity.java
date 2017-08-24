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

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import interfaces.IVolleyCallback;
import session.UserSession;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    UserSession userSession;
    RequestQueue queue;
    Intent launchNextActivity;
    private Button btnLogin, btnRegister;
    private EditText txtName, txtPin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userSession = new UserSession(getApplicationContext());
        queue = Volley.newRequestQueue(LoginActivity.this);

        //lets get the elements
        //(Button) findViewById(R.id.button) ;
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        //get the text fields
        txtName = (EditText) findViewById(R.id.txtName);
        txtPin = (EditText) findViewById(R.id.txtPin);

        //add click listeners
        btnLogin.setOnClickListener(LoginActivity.this);
        btnRegister.setOnClickListener(LoginActivity.this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        if (userSession.isLoggedIn()) {
            Toast.makeText(getApplicationContext(), "Welcome back", Toast.LENGTH_SHORT).show();
            //LoadMainDisplay();
        }
    }

    @Override
    public void onClick(View v) {
        RestService restService = RestService.getInstance(queue);
        switch (v.getId()) {
            case R.id.btnLogin:
                // do your code
                //Toast.makeText(LoginActivity.this, "Login Button Clicked", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.VISIBLE); //show progress bar

                txtName.setEnabled(false);
                txtPin.setEnabled(false);
                String name = txtName.getText().toString();
                String pin = txtPin.getText().toString();

                HashMap<String, String> map;
                map = new HashMap<String, String>();
                map.put("name", name.trim());
                map.put("pin", pin.trim());

                restService.SetEndPoint("v1/users/login");
                restService.Post(map, new IVolleyCallback() {
                    @Override
                    public void onSuccessJsonString(String result) {
                        //Log.d("Success.Response", result);
                        try {
                            JSONObject user = new JSONObject(result);
                            if (user.has("message")) {
                                String message = user.getString("message");
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                            } else {
                                //set the login session in shared preferences
                                userSession.createLoginSession(user);
                                txtPin.setText(null);
                                LoadMainDisplay();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "An error has occurred, please try again", Toast.LENGTH_SHORT).show();
                        }
                        //hide the progress bar again
                        txtName.setEnabled(true);
                        txtPin.setEnabled(true);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onSuccessJsonArr(JSONArray result) {
                    }

                    @Override
                    public void onSuccessJsonObject(JSONObject result) {
                    }

                    @Override
                    public void onDeleted(String result) {

                    }

                    @Override
                    public void onError(VolleyError error) {
                        Log.d("Success.Response", error.toString());
                    }
                });
                break;
            case R.id.btnRegister:
                //open the registration interface
                launchNextActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(launchNextActivity);
                break;
            default:
                break;
        }
    }

    public void LoadMainDisplay() {
        launchNextActivity = new Intent(getApplicationContext(), MainActivity.class);
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(launchNextActivity);
    }
}
