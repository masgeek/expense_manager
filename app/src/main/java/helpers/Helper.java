package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by RONIN on 7/17/2017.
 */
public class Helper {
    static Date date1;
    //public static final String BASE_URL ="http://192.168.0.19/spa/api/";
    public static final String BASE_URL = "http://expense.tsobu.co.ke/api/";

    public static Date StringToDate(String dateString) {

        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date1;
    }
}
