package com.mugo.expensemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import helpers.SetDate;
import interfaces.IVolleyCallback;

public class AddIncomeActivity extends AppCompatActivity {
    Button btnAddExpense;
    EditText txtDate, Amount, Cheque, Notes, Place;
    Spinner Type;

    String[] spinnerItems;
    String EXPENSE_TYPE;
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        btnAddExpense = (Button) findViewById(R.id.submit);
        Type = (Spinner) findViewById(R.id.Type);
        txtDate = (EditText) findViewById(R.id.Date);
        Amount = (EditText) findViewById(R.id.Amount);
        Cheque = (EditText) findViewById(R.id.Cheque);
        Notes = (EditText) findViewById(R.id.Notes);
        Place = (EditText) findViewById(R.id.Place);
        spinnerItems = getResources().getStringArray(R.array.spinnerItemsIncome);


        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems);
        Type.setAdapter(stringArrayAdapter);

        Type.setPrompt("Select Expense Type");

        queue = Volley.newRequestQueue(AddIncomeActivity.this);


        SetDate fromDate = new SetDate(txtDate, AddIncomeActivity.this);
        Type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EXPENSE_TYPE = spinnerItems[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestService restService = RestService.getInstance(queue);
                HashMap<String, String> map;
                map = new HashMap<String, String>();
                map.put("type", EXPENSE_TYPE);
                map.put("date", txtDate.getText().toString().trim());
                map.put("amount", Amount.getText().toString().trim());
                map.put("cheque", Cheque.getText().toString().trim());
                map.put("note", Notes.getText().toString().trim());
                map.put("place", Place.getText().toString().trim());

                //post it
                restService.SetEndPoint("v1/incomes/add");
                restService.Post(map, new IVolleyCallback() {
                    @Override
                    public void onSuccessJsonString(String result) {
                        try {
                            JSONArray callbackArr = new JSONArray(result);
                            for (int i = 0; i < callbackArr.length(); i++) {
                                JSONObject jo = (JSONObject) callbackArr.get(i);
                                if (jo.has("message")) {
                                    //means we have an error
                                    String message = jo.getString("message");
                                    Toast.makeText(AddIncomeActivity.this, message, Toast.LENGTH_SHORT).show();
                                } else if (jo.has("id")) {
                                    Toast.makeText(AddIncomeActivity.this, "Inceome Submitted", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                            //Log.d("Success.Response", jsonArray.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AddIncomeActivity.this, "Income addition Not successful, please try again", Toast.LENGTH_SHORT).show();
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
                        Log.d("Success.Response", error.toString());
                    }
                });
            }
        });
    }
}