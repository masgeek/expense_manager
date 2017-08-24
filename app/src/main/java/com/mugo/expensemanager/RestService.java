package com.mugo.expensemanager;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import helpers.Helper;
import interfaces.IVolleyCallback;

/**
 * Created by RONIN on 7/17/2017.
 */

public class RestService {
    private static RestService myObj;
    private final String base_url = Helper.BASE_URL;
    StringRequest postRequest;
    private String url;
    private JsonObjectRequest jsonObjectRequest;
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue queue;

    private RestService(RequestQueue _queue) {
        queue = _queue;
    }

    public static RestService getInstance(RequestQueue _queue) {
        if (myObj == null) {
            myObj = new RestService(_queue);
        }
        return myObj;
    }

    public String SetEndPoint(String endpoint) {
        url = String.format("%s%s", base_url, endpoint);

        return url;
    }

    public void Post(final HashMap<String, String> postData, final IVolleyCallback callback) {

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                callback.onSuccessJsonString(result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return postData;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    public void Put(final HashMap<String, String> putData, final IVolleyCallback callback) {
        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        callback.onSuccessJsonString(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        callback.onError(error);
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() {
                return putData;
            }

        };

        queue.add(putRequest);
    }

    public void GetJsonObjList(final IVolleyCallback callback) {
        // prepare the Request
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccessJsonObject(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Log.d("Error.Response", error.toString());
                        callback.onError(error);
                    }
                }
        );

// add it to the RequestQueue

        queue.add(jsonObjectRequest);
    }

    public void GetJsonArrList(final IVolleyCallback callback) {
        // prepare the Request
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        callback.onSuccessJsonArr(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.d("Error.Response", error.toString());
                        callback.onError(error);
                    }
                }
        );
// add it to the RequestQueue
        queue.add(jsonArrayRequest);
    }

    public void Delete(final IVolleyCallback callback) {
        StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        callback.onDeleted(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error.
                        callback.onError(error);
                    }
                }
        );
        queue.add(dr);
    }
}