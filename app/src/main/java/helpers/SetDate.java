package helpers;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.lillian.lillysbeauty.ReservationActivity;

import java.util.Calendar;

/**
 * Created by RONIN on 7/21/2017.
 */

public class SetDate implements View.OnFocusChangeListener, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    DatePickerDialog datePicker;
    private EditText editText;
    private Calendar myCalendar;
    private Context ctx;

    public SetDate(EditText editText, Context ctx) {
        this.editText = editText;
        this.editText.setOnFocusChangeListener(this);
        this.myCalendar = Calendar.getInstance();
        this.ctx = ctx;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            ShowDateDialog();
        }
    }

    @Override
    public void onClick(View v) {
        ShowDateDialog();
    }

    private void ShowDateDialog() {
        int mYear = myCalendar.get(Calendar.YEAR); // current year
        int mMonth = myCalendar.get(Calendar.MONTH); // current month
        int mDay = myCalendar.get(Calendar.DAY_OF_MONTH); // current day
        datePicker = new DatePickerDialog(ctx, this, mYear, mMonth, mDay);

        //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000); //prevent past date selection
        datePicker.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String validMonth = String.valueOf(month + 1);
        String validDay = String.valueOf(dayOfMonth);
        if (month < 10) {
            validMonth = "0" + (month + 1);
        }
        if (dayOfMonth < 10) {
            validDay = "0" + dayOfMonth;
        }
        String dateString = year + "-" + validMonth + "-" + validDay;
        editText.setText(dateString);
        ReservationActivity.SELECTED_DAY = validDay;
        ReservationActivity.SELECTED_MONTH = validMonth;
        ReservationActivity.SELECTED_YEAR = year;

    }
}
