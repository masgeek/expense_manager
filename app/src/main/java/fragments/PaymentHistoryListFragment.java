package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lillian.lillysbeauty.PaymentActivity;
import com.lillian.lillysbeauty.PaymentListActivity;
import com.lillian.lillysbeauty.R;
import com.lillian.lillysbeauty.RestService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapters.MyReservationsAdapter;
import interfaces.IVolleyCallback;
import model.Reservations;
import session.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentHistoryListFragment extends Fragment {

    String userID;
    RequestQueue queue;
    Session session;
    RestService restService;
    ListView list;
    JSONArray bookedServicesArray;

    ProgressBar loadingProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.list_single, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        queue = Volley.newRequestQueue(getActivity());
        restService = RestService.getInstance(queue);
        session = new Session(getActivity().getApplicationContext());
        userID = session.getUserID();


        //reservations/1/my-reservations
        String myReservedServicesEndpoint = String.format("v1/reservations/%s/my-reservations", userID);

        restService.SetEndPoint(myReservedServicesEndpoint);

        getActivity().setTitle("Payment History");

        list = (ListView) getView().findViewById(R.id.list);
        loadingProgress = (ProgressBar) getView().findViewById(R.id.loadingIndicator);

        loadingProgress.setVisibility(View.VISIBLE);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if (bookedServicesArray != null) {
                    String reservationID = GetFieldValue("RESERVATION_ID", position, bookedServicesArray);
                    LaunchServicesActivity(reservationID);
                }
            }
        });
        restService.GetJsonArrList(new IVolleyCallback() {
            @Override
            public void onSuccessJsonString(String result) {
            }

            @Override
            public void onSuccessJsonArr(JSONArray result) {
                Log.d("Success.Response", result.toString());
                MyReservationsAdapter adapter = new MyReservationsAdapter(getActivity(), result);
                bookedServicesArray = result;
                list.setAdapter(adapter);
                loadingProgress.setVisibility(View.GONE);
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
                loadingProgress.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //loadingProgress.setVisibility(View.VISIBLE);
    }

    public String GetFieldValue(String field_name, int position, JSONArray service) {
        String fieldValue = null;
        try {
            JSONObject jo = (JSONObject) service.get(position);
            fieldValue = jo.getString(field_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fieldValue;
    }

    private void LaunchServicesActivity(String reservation_id ) {
        Intent launchNextActivity;
        launchNextActivity = new Intent(getContext().getApplicationContext(), PaymentListActivity.class);
        launchNextActivity.putExtra(Reservations.RESERVATION_ID_ARG, reservation_id);
        startActivity(launchNextActivity);
    }
}
