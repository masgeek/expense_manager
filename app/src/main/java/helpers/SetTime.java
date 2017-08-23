package helpers;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lillian.lillysbeauty.ReservationActivity;

import java.util.Calendar;

/**
 * Created by RONIN on 7/21/2017.
 */

public class SetTime implements View.OnFocusChangeListener, TimePickerDialog.OnTimeSetListener, View.OnClickListener {


    TimePickerDialog timePicker;
    private EditText editText;
    private Calendar myCalendar;
    private Context ctx;

    public SetTime(EditText editText, Context ctx) {
        this.editText = editText;
        this.editText.setOnFocusChangeListener(this);
        this.myCalendar = Calendar.getInstance();
        this.ctx = ctx;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String validHour = String.valueOf(hourOfDay);
        String validMinute = String.valueOf(minute);
        String selectedDate = ReservationActivity.SELECTED_DAY;


        int ch = myCalendar.get(Calendar.HOUR_OF_DAY);
        int mnt = myCalendar.get(Calendar.MINUTE);

        if ((hourOfDay <= ch) && (minute <= mnt)) {
            Toast.makeText(ctx.getApplicationContext(), "Wrong hour", Toast.LENGTH_SHORT).show();
        } else {}

            if (hourOfDay < 10) {
                validHour = "0" + (hourOfDay);
            }
            if (minute < 10) {
                validMinute = "0" + minute;
            }
            this.editText.setText(validHour + ":" + validMinute);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            ShowTimeDialog();
        }
    }

    @Override
    public void onClick(View v) {
        ShowTimeDialog();
    }

    private void ShowTimeDialog() {
        int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = myCalendar.get(Calendar.MINUTE);
        timePicker = new TimePickerDialog(ctx, this, hour, minute, true);
        timePicker.show();
    }
}
