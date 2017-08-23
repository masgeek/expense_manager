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

/**
 * Created by RONIN on 7/18/2017.
 */

public class SalonListAdapter extends BaseAdapter {

    private final FragmentActivity _activity;
    private final JSONArray salon;

    public SalonListAdapter(@NonNull FragmentActivity context, @NonNull JSONArray objects) {
        //super(context, R.layout.fragment_salon_list, objects);
        super();
        this.salon = objects;
        this._activity = context;
    }
/*
    public SalonListAdapter(@NonNull FragmentActivity activity, @NonNull JSONArray[] salonObject) {
        super(activity, R.layout.fragment_salon_list, salonObject);
        this.salon = salonObject;
        this._activity = activity;
    }
*/

    @Override
    public int getCount() {
        return salon.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return salon.getJSONObject(position);
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
            rowView = inflater.inflate(R.layout.fragment_salon_list, null, true);
        }

        TextView txtSalonName = (TextView) rowView.findViewById(R.id.txtServiceName);
        TextView txtLocation = (TextView) rowView.findViewById(R.id.txtSalonLocation);
        TextView txtDescription = (TextView) rowView.findViewById(R.id.txtDescription);

        try {
            JSONObject jo = (JSONObject) salon.get(position);
            txtSalonName.setText(jo.getString("SALON_NAME"));
            txtLocation.setText(jo.getString("SALON_LOCATION"));
            txtDescription.setText(jo.getString("DESCRIPTION"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowView;
    }
}
