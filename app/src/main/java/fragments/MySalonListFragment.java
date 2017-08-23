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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lillian.lillysbeauty.AddSalonActivity;
import com.lillian.lillysbeauty.R;
import com.lillian.lillysbeauty.RestService;

import interfaces.IVolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import adapters.MySalonListAdapter;
import model.Salons;
import session.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySalonListFragment extends Fragment {

    RequestQueue queue;
    RestService restService;
    JSONArray mySalonArray;
    MySalonListAdapter mySalonListAdapter;
    HashMap<String, String> postMap = null;
    String endpoint = null;
    String ownerID;
    EditText salonName, salonTel, salonEmail, salonLocation, salonWebsite, salonDescription, salonImage;
    Button btnAddSalon;
    ListView list;
    ProgressBar loadingProgress;
    private Session session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_single, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        session = new Session(getActivity().getApplicationContext());
        queue = Volley.newRequestQueue(getActivity());

        getActivity().setTitle("My Salons");
        ownerID = session.getUserID();
        endpoint = "v1/salons/" + ownerID + "/my-salons";


        list = (ListView) getView().findViewById(R.id.list);
        loadingProgress = (ProgressBar) getView().findViewById(R.id.loadingIndicator);

        loadingProgress.setVisibility(View.VISIBLE);
        LoadSalons(endpoint);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                loadingProgress.setVisibility(View.GONE);
                if (mySalonArray != null) {
                    JSONObject salonObject = GetSalonJsonObject(position, mySalonArray);

                    LaunchEditSalon(salonObject);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //loadingProgress.setVisibility(View.VISIBLE);
    }

    private void LoadSalons(String endpoint) {
        restService = RestService.getInstance(queue);
        restService.SetEndPoint(endpoint);

        restService.GetJsonArrList(new IVolleyCallback() {
            @Override
            public void onSuccessJsonString(String result) {
            }

            @Override
            public void onSuccessJsonArr(JSONArray result) {
                mySalonArray = result;
                mySalonListAdapter = new MySalonListAdapter(getActivity(), mySalonArray);
                list.setAdapter(mySalonListAdapter);
                //Log.d("Success:Response", result.toString());
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
                Log.d("Error:Response", error.toString());
                loadingProgress.setVisibility(View.GONE);
            }
        });
    }

    private JSONObject GetSalonJsonObject(int position, JSONArray service) {
        try {
            JSONObject jo = (JSONObject) service.get(position);
            return jo;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void LaunchEditSalon(JSONObject salon_object) {
        try {
            Intent launchNextActivity;
            launchNextActivity = new Intent(getContext().getApplicationContext(), AddSalonActivity.class);

            launchNextActivity.putExtra(Salons.SALON_ID_ARG, salon_object.getString("SALON_ID"));
            launchNextActivity.putExtra(Salons.SALON_NAME_ARG, salon_object.getString("SALON_NAME"));
            launchNextActivity.putExtra(Salons.SALON_LOCATION_ARG, salon_object.getString("SALON_LOCATION"));
            launchNextActivity.putExtra(Salons.SALON_TEL_ARG, salon_object.getString("SALON_TEL"));
            launchNextActivity.putExtra(Salons.SALON_EMAIL_ARG, salon_object.getString("SALON_EMAIL"));
            launchNextActivity.putExtra(Salons.SALON_WEBSITE_ARG, salon_object.getString("SALON_WEBSITE"));
            launchNextActivity.putExtra(Salons.SALON_OWNER_ID_ARG, salon_object.getString("OWNER_ID"));
            launchNextActivity.putExtra(Salons.SALON_DESCRIPTION_ARG, salon_object.getString("DESCRIPTION"));
            AddSalonActivity.EDIT_SALON = true;
            startActivity(launchNextActivity);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
