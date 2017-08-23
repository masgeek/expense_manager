package adapters;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lillian.lillysbeauty.R;

import model.Services;

/**
 * Created by RONIN on 7/18/2017.
 */

public class ServicesSpinnerAdapter extends BaseAdapter {

    private final Activity _activity;
    private final Services[] _salon;

    TextView txtSalonName;
    TextView txtSalonTel;
    TextView txtLocation;
    TextView txtDescription;

    public ServicesSpinnerAdapter(@NonNull Activity context, @NonNull Services[] objects) {
        super();
        this._salon = objects;
        this._activity = context;
    }

    @Override
    public int getCount() {
        return _salon.length;
    }

    @Override
    public Services getItem(int position) {
        return _salon[position];
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
            rowView = inflater.inflate(R.layout.custom_spinner, null, true);
        }

        TextView names = (TextView) rowView.findViewById(R.id.textValue);

        names.setText(_salon[position].SERVICE_NAME);
        return rowView;
    }
}
