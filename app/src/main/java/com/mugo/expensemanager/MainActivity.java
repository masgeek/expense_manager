package com.mugo.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import session.UserSession;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextMessage;
    Button addExpense, addIncome, viewExpense, viewIncome, expenseVsIncome, reminder, analysis, calculator, logout;
    Intent launchNextActivity;

    private UserSession session;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new UserSession(getApplicationContext());

        mTextMessage = (TextView) findViewById(R.id.message);

        addExpense = (Button) findViewById(R.id.btnAddExpense);
        addIncome = (Button) findViewById(R.id.btnAddIncome);
        viewExpense = (Button) findViewById(R.id.btnViewExpense);
        viewIncome = (Button) findViewById(R.id.btnViewIncome);
        expenseVsIncome = (Button) findViewById(R.id.btnExpenseVsIncome);
        reminder = (Button) findViewById(R.id.btnReminder);
        analysis = (Button) findViewById(R.id.btnAnalysis);
        calculator = (Button) findViewById(R.id.btnCalculator);
        logout = (Button) findViewById(R.id.btnLogout);


        addExpense.setOnClickListener(MainActivity.this);
        addIncome.setOnClickListener(MainActivity.this);
        viewExpense.setOnClickListener(MainActivity.this);
        viewIncome.setOnClickListener(MainActivity.this);
        expenseVsIncome.setOnClickListener(MainActivity.this);
        reminder.setOnClickListener(MainActivity.this);
        analysis.setOnClickListener(MainActivity.this);
        calculator.setOnClickListener(MainActivity.this);
        logout.setOnClickListener(MainActivity.this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddExpense:
                launchNextActivity = new Intent(getApplicationContext(), AddExpenseActivity.class);
                startActivity(launchNextActivity);
                break;
            case R.id.btnAddIncome:
                launchNextActivity = new Intent(getApplicationContext(), AddIncomeActivity.class);
                startActivity(launchNextActivity);
                break;
            case R.id.btnViewIncome:
                launchNextActivity = new Intent(getApplicationContext(), IncomeReportActivity.class);
                startActivity(launchNextActivity);
                break;
            case R.id.btnViewExpense:
                launchNextActivity = new Intent(getApplicationContext(), ExpenseReportActivity.class);
                startActivity(launchNextActivity);
                break;
            case R.id.btnExpenseVsIncome:
                break;
            case R.id.btnReminder:
                break;
            case R.id.btnAnalysis:
                break;
            case R.id.btnCalculator:
                Intent i = new Intent();
                i.setClassName("com.android.calculator2",
                        "com.android.calculator2.Calculator");
                startActivity(i);
                break;
            case R.id.btnLogout:
                session.logoutUser(); //logout user and redirect them to login view
                break;
            default:
                break;
        }
    }
}
