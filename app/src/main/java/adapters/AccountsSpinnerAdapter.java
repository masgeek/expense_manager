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

import model.AccountTypes;

/**
 * Created by RONIN on 7/18/2017.
 */

public class AccountsSpinnerAdapter extends BaseAdapter {

    private final Activity _activity;
    private final AccountTypes[] _accountTypes;


    public AccountsSpinnerAdapter(@NonNull Activity context, @NonNull AccountTypes[] objects) {
        super();
        this._accountTypes = objects;
        this._activity = context;
    }

    @Override
    public int getCount() {
        return _accountTypes.length;
    }

    @Override
    public AccountTypes getItem(int position) {
        return _accountTypes[position];
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

        names.setText(_accountTypes[position].ACCOUNT_NAME);
        return rowView;
    }
}
