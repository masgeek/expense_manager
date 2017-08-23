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

public class MyServicesAdapter extends BaseAdapter {

    private final FragmentActivity _activity;
    private final JSONArray _services;

    String reservedServiceId;
    String offeredServiceId;
    String reservationId;
    String reservationDate;
    String reservationTime;
    String serviceAmount;
    String serviceName;
    String salonName;
    String staff;
    String status;

    public MyServicesAdapter(@NonNull FragmentActivity context, @NonNull JSONArray objects) {
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
            rowView = inflater.inflate(R.layout.my_services_content, null, true);
        }

        TextView txtSalonName = (TextView) rowView.findViewById(R.id.txtServiceName);
        TextView txtTotalCost = (TextView) rowView.findViewById(R.id.txtTotalCost);
        TextView txtReservationDate = (TextView) rowView.findViewById(R.id.txtDescription);
        TextView txtReservationTime = (TextView) rowView.findViewById(R.id.txtReservationTime);
        TextView txtStatus = (TextView) rowView.findViewById(R.id.txtStatus);
        TextView txtStaffName = (TextView) rowView.findViewById(R.id.txtStaffName);

        try {
            JSONObject myservicesObject = (JSONObject) _services.get(position);

            reservedServiceId = myservicesObject.getString("RESERVATION_ID");
            offeredServiceId = myservicesObject.getString("OFFERED_SERVICE_ID");
            reservationId = myservicesObject.getString("RESERVATION_ID");
            reservationDate = myservicesObject.getString("RESERVATION_DATE");
            reservationTime = myservicesObject.getString("RESERVATION_TIME");
            serviceAmount = myservicesObject.getString("SERVICE_AMOUNT");
            serviceName = myservicesObject.getString("SERVICE_NAME");
            salonName = myservicesObject.getString("SALON_NAME");
            staff = myservicesObject.getString("STAFF");
            status = myservicesObject.getString("STATUS");

            txtSalonName.setText(salonName);
            txtTotalCost.setText(serviceAmount);
            txtReservationDate.setText(reservationDate);
            txtReservationTime.setText(reservationTime);
            txtStatus.setText(status);
            txtStaffName.setText(staff);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rowView;
    }
}