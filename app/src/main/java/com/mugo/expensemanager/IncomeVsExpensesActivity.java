package com.mugo.expensemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import adapters.ExpensesSummaryAdapter;
import adapters.IncomeVsExpensesAdapter;
import helpers.ObjectBuilder;
import interfaces.IVolleyCallback;
import model.IncomeExpensesModel;

public class IncomeVsExpensesActivity extends AppCompatActivity {

    IncomeVsExpensesAdapter paymentHistoryAdapter;
    ListView listView;
    RecyclerView recyclerView;

    private RequestQueue queue;
    private RestService restService;
    private String reservation_id;
    private String salon_id;
    private String service_name;
    private String service_cost;
    private String service_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_list);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        setTitle("Expenses Vs Income");
        recyclerView = (RecyclerView) findViewById(R.id.payment_history);
        queue = Volley.newRequestQueue(IncomeVsExpensesActivity.this);
        GetPaymentHistory();
    }

    private void GetPaymentHistory() {
        restService = RestService.getInstance(queue);
        restService.SetEndPoint(String.format("v1/expenses/all"));

        //set the adapters
        restService.GetJsonArrList(new IVolleyCallback() {
            @Override
            public void onSuccessJsonString(String result) {

            }

            @Override
            public void onSuccessJsonArr(JSONArray result) {
                List<IncomeExpensesModel> services = ObjectBuilder.BuildExpensesVSIncomeObject(result);
                paymentHistoryAdapter = new IncomeVsExpensesAdapter(IncomeVsExpensesActivity.this, services);
                recyclerView.setAdapter(paymentHistoryAdapter);
                Log.d("Success.Response", result.toString());
            }

            @Override
            public void onSuccessJsonObject(JSONObject result) {
            }

            @Override
            public void onDeleted(String result) {

            }

            @Override
            public void onError(VolleyError error) {

                Log.d("Error.Response", error.toString());
            }
        });
    }
}
