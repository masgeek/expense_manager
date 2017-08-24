package com.mugo.expensemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import helpers.SetDate;

public class ReminderActivity extends AppCompatActivity {

    EditText txtDate, txtComment;
    Button btnSaveComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        txtDate = (EditText) findViewById(R.id.txtDate);
        txtComment = (EditText) findViewById(R.id.txtComments);
        btnSaveComment = (Button) findViewById(R.id.btnSave);

        setTitle("Add Reminder");

        SetDate fromDate = new SetDate(txtDate, ReminderActivity.this);
        btnSaveComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Reminder Added Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
}
