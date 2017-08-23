package adapters;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lillian.lillysbeauty.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceListAdapter extends BaseAdapter {

    private final FragmentActivity _activity;
    private final JSONArray service;

    public ServiceListAdapter(@NonNull FragmentActivity context, @NonNull JSONArray objects) {
        super();
        this.service = objects;
        this._activity = context;
    }

    @Override
    public int getCount() {
        return service.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return service.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = _activity.getLayoutInflater();
        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.fragment_service_list, null, true);
        }

        TextView txtServiceName = (TextView) rowView.findViewById(R.id.txtServiceName);
        TextView txtDescription = (TextView) rowView.findViewById(R.id.txtDescription);

        try {
            JSONObject jo = (JSONObject) service.get(position);
            txtServiceName.setText(jo.getString("SERVICE_NAME"));
            txtDescription.setText(jo.getString("DESCRIPTION"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowView;
    }
}
