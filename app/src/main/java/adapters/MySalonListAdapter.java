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

public class MySalonListAdapter extends BaseAdapter {

    private final FragmentActivity _activity;
    private final JSONArray _salon;

    TextView txtSalonName;
    TextView txtSalonTel;
    TextView txtLocation;
    TextView txtDescription;

    public MySalonListAdapter(@NonNull FragmentActivity context, @NonNull JSONArray objects) {
        //super(context, R.layout.fragment_salon_list, objects);
        super();
        this._salon = objects;
        this._activity = context;
    }
    @Override
    public int getCount() {
        return _salon.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return _salon.getJSONObject(position);
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
            rowView = inflater.inflate(R.layout.fragment_my_salon_list, null, true);
        }

        txtSalonName = (TextView) rowView.findViewById(R.id.txtSalonName);
        txtSalonTel = (TextView) rowView.findViewById(R.id.txtSalonTel);
        txtLocation = (TextView) rowView.findViewById(R.id.txtSalonLocation);
        txtDescription = (TextView) rowView.findViewById(R.id.txtSalonDescription);

        try {
            JSONObject jo = (JSONObject) _salon.get(position);
            txtSalonName.setText(jo.getString("SALON_NAME"));
            txtSalonTel.setText(jo.getString("SALON_TEL"));
            txtLocation.setText(jo.getString("SALON_LOCATION"));
            txtDescription.setText(jo.getString("DESCRIPTION"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowView;
    }
}
