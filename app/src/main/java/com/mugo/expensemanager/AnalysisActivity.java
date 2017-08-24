package com.mugo.expensemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONObject;

import helpers.ObjectBuilder;
import interfaces.IVolleyCallback;

public class AnalysisActivity extends AppCompatActivity {

    RequestQueue queue;
    RestService restService;
    GraphView incomeGraph, expensesGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        incomeGraph = (GraphView) findViewById(R.id.incomeGraph);
        expensesGraph = (GraphView) findViewById(R.id.expensesGraph);

        queue = Volley.newRequestQueue(AnalysisActivity.this);

        BuildExpensesGraph();
        BuildIncomeGraph();
    }

    public void BuildExpensesGraph() {
        restService = RestService.getInstance(queue);
        restService.SetEndPoint(String.format("v1/expenses/graph"));

        //set the adapters
        restService.GetJsonArrList(new IVolleyCallback() {
            @Override
            public void onSuccessJsonString(String result) {

            }

            @Override
            public void onSuccessJsonArr(JSONArray result) {
                LineGraphSeries<DataPoint> datapoint = ObjectBuilder.BuildLineGraphSeries(result, false);

                expensesGraph.addSeries(datapoint);

                datapoint.setTitle("Expenses Analysis Over Time");
                expensesGraph.getLegendRenderer().setVisible(true);
                expensesGraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
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

    public void BuildIncomeGraph() {
        restService = RestService.getInstance(queue);
        restService.SetEndPoint(String.format("v1/incomes/graph"));

        //set the adapters
        restService.GetJsonArrList(new IVolleyCallback() {
            @Override
            public void onSuccessJsonString(String result) {

            }

            @Override
            public void onSuccessJsonArr(JSONArray result) {
                LineGraphSeries<DataPoint> datapoint = ObjectBuilder.BuildLineGraphSeries(result, false);

                incomeGraph.addSeries(datapoint);

                datapoint.setTitle("Income Analysis Over Time");
                incomeGraph.getLegendRenderer().setVisible(true);
                incomeGraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

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
