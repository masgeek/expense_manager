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
import com.lillian.lillysbeauty.MyServicesActivity;
import com.lillian.lillysbeauty.R;
import com.lillian.lillysbeauty.RestService;
import com.lillian.lillysbeauty.SalonServiceListActivity;

import interfaces.IVolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapters.SalonListAdapter;

public class SalonListFragment extends Fragment {
    RequestQueue queue;
    RestService restService;
    ListView list;
    JSONArray salonArray;
    ProgressBar loadingProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_single, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getActivity());
        restService = RestService.getInstance(queue);
        restService.SetEndPoint("v1/salons");
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Available Salons");

        list = (ListView) getView().findViewById(R.id.list);
        loadingProgress = (ProgressBar) getView().findViewById(R.id.loadingIndicator);

        loadingProgress.setVisibility(View.VISIBLE);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if (salonArray != null) {
                    String salonID = GetSalonId(position, salonArray);
                    //launch the activity
                    // Toast toast = Toast.makeText(getContext(), "Item " + (position) + ": Salon id " + salonID, Toast.LENGTH_SHORT);
                    // toast.show();
                    LaunchServicesActivity(null, salonID);
                }
            }
        });

        //set the adapters
        restService.GetJsonArrList(new IVolleyCallback() {
            @Override
            public void onSuccessJsonString(String result) {

            }

            @Override
            public void onSuccessJsonArr(JSONArray result) {
                SalonListAdapter adapter = new SalonListAdapter(getActivity(), result);
                salonArray = result;
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
        //loadingProgress.setVisibility(View.GONE);
    }

    public String GetSalonId(int position, JSONArray salon) {
        String salonID = null;
        try {
            JSONObject jo = (JSONObject) salon.get(position);
            salonID = jo.getString("SALON_ID");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return salonID;
    }

    private void LaunchServicesActivity(String reservation_id, String salon_id) {
        Intent launchNextActivity;
        launchNextActivity = new Intent(getContext().getApplicationContext(), SalonServiceListActivity.class);
        launchNextActivity.putExtra(MyServicesActivity.RESERVATION_ID, reservation_id);
        launchNextActivity.putExtra(MyServicesActivity.SALON_ID, salon_id);
        startActivity(launchNextActivity);
    }
}
