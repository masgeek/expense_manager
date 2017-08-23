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
import com.lillian.lillysbeauty.R;
import com.lillian.lillysbeauty.RestService;
import com.lillian.lillysbeauty.ServiceSalonListActivity;

import interfaces.IVolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.OfferedServices;
import adapters.ServiceListAdapter;

public class ServiceListFragment extends Fragment {
    public static String SELECTED_SERVICE_ID;
    RequestQueue queue;
    RestService restService;
    ListView list;
    JSONArray serviceArray;

    ProgressBar loadingProgress;
    String endpoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_single, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getActivity());
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Offered Services");

        list = (ListView) getView().findViewById(R.id.list);
        loadingProgress = (ProgressBar) getView().findViewById(R.id.loadingIndicator);

        loadingProgress.setVisibility(View.VISIBLE);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if (serviceArray != null) {
                    String serviceID = GetSalonId(position, serviceArray);
                    LoadActivity(serviceID);
                }
            }
        });

        endpoint = "v1/services";
        LoadServices(endpoint);


    }

    @Override
    public void onResume() {
        super.onResume();
        //loadingProgress.setVisibility(View.VISIBLE);
    }

    public void LoadServices(String endpoint) {
        restService = RestService.getInstance(queue);
        restService.SetEndPoint(endpoint);
        //set the adapters
        restService.GetJsonArrList(new IVolleyCallback() {
            @Override
            public void onSuccessJsonString(String result) {
            }

            @Override
            public void onSuccessJsonArr(JSONArray result) {
                ServiceListAdapter adapter = new ServiceListAdapter(getActivity(), result);
                //list = (ListView) getView().findViewById(R.id.list);
                serviceArray = result;
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

    /**
     * @param position
     * @param service
     * @return @String
     */
    public String GetSalonId(int position, JSONArray service) {
        String service_id = null;
        try {
            JSONObject jo = (JSONObject) service.get(position);
            service_id = jo.getString("SERVICE_ID");
            SELECTED_SERVICE_ID = service_id;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service_id;
    }

    /**
     * @param serviceID
     */
    public void LoadActivity(String serviceID) {
        Intent launchNextActivity;
        launchNextActivity = new Intent(getActivity(), ServiceSalonListActivity.class);
        launchNextActivity.putExtra(OfferedServices.SERVICE_ID_ARG, serviceID);
        //launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(launchNextActivity);
    }
}
