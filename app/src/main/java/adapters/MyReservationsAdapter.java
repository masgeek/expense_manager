package adapters;

import android.support.annotation.NonNull;
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
 * Created by RONIN on 7/21/2017.
 */

public class MyReservationsAdapter extends BaseAdapter {

    private final FragmentActivity _activity;
    private final JSONArray _services;

    public MyReservationsAdapter(@NonNull FragmentActivity context, @NonNull JSONArray objects) {
        super();
        this._services = objects;
        this._activity = context;
    }

    @Override
    public int getCount() {
        return _services.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return _services.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = _activity.getLayoutInflater();
        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.fragment_reservation_list, null, true);
        }

        TextView txtSalonName = (TextView) rowView.findViewById(R.id.txtServiceName);
        TextView txtReservationID = (TextView) rowView.findViewById(R.id.txtStatus);
        TextView txtUserID = (TextView) rowView.findViewById(R.id.txtStatus);
        TextView txtReservationDate = (TextView) rowView.findViewById(R.id.txtDescription);
        TextView txtTotalCost = (TextView) rowView.findViewById(R.id.txtTotalCost);
        TextView txtStatus = (TextView) rowView.findViewById(R.id.txtStatus);
        TextView txtReservedServices = (TextView) rowView.findViewById(R.id.txtReservedServices);
        TextView txtComments = (TextView) rowView.findViewById(R.id.txtComments);

        try {
            JSONObject jo = (JSONObject) _services.get(position);

            String status = jo.getString("STATUS_ID");

            txtReservationID.setText(jo.getString("RESERVATION_ID"));
            txtUserID.setText(jo.getString("USER_ID"));
            txtReservationDate.setText(jo.getString("RESERVATION_DATE"));
            txtTotalCost.setText(jo.getString("TOTAL_COST"));
            txtStatus.setText(status);
            txtSalonName.setText(jo.getString("SALON_NAME"));
            txtReservedServices.setText(jo.getString("SERVICES_RESERVED"));
            txtComments.setText(jo.getString("COMMENTS"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowView;
    }
}
