package com.mugo.expensemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {

    RequestQueue queue;
    RestService restService;
    HashMap<String, String> postMap = null;
    String endpoint = null;

    Intent launchNextActivity;
    private Button btnLogin, btnRegister;
    private EditText txtName, txtPin;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        queue = Volley.newRequestQueue(RegisterActivity.this);
        restService = RestService.getInstance(queue);
        //lets get the element
        btnRegister = (Button) findViewById(R.id.btnRegister);

        //get the text fields
        txtName = (EditText) findViewById(R.id.txtName);
        txtPin = (EditText) findViewById(R.id.txtPin);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                postMap = new HashMap<String, String>();
                postMap.put("name", txtName.getText().toString());
                postMap.put("pin", txtPin.getText().toString());

                endpoint = "v1/users/register";
                RegisterUser(endpoint, postMap);

            }
        });
    }

    private void RegisterUser(String endpoint, HashMap<String, String> postData) {
        restService.SetEndPoint(endpoint);
        restService.Post(postData, new IVolleyCallback() {
            @Override
            public void onSuccessJsonString(String result) {
                //process the response
                try {
                    JSONArray callbackArr = new JSONArray(result);
                    for (int i = 0; i < callbackArr.length(); i++) {
                        JSONObject jo = (JSONObject) callbackArr.get(i);
                        if (jo.has("message")) {
                            //means we have an error
                            String message = jo.getString("message");
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        } else if (jo.has("id")) {
                            //session.createLoginSession(jo); //let us disable the automatic login
                            txtPin.setText(null);
                            Toast.makeText(RegisterActivity.this, "Registration Successful.", Toast.LENGTH_LONG).show();
                            LoadLoginDisplay();
                        }
                    }
                    //Log.d("Success.Response", jsonArray.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Unable to register, please try again", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Unable to register, please try again", Toast.LENGTH_SHORT).show();
                }
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
                Log.d("Error:Response", error.toString());
                Toast.makeText(RegisterActivity.this, "Unable to register, please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void LoadLoginDisplay() {
        Intent launchNextActivity = new Intent(getApplicationContext(), LoginActivity.class);
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); //prevent history
        //launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(launchNextActivity);
    }
}
