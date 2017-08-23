package helpers;

import android.text.TextUtils;
import android.util.Patterns;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by RONIN on 7/22/2017.
 */

public final class DataValidator {

    /**
     * The + is optional
     */
    public static String phoneNumberRegex = "^[+]?[0-9]{10,13}$";


    /**
     * @param objectString
     * @return
     */
    public static boolean isValidJsonObject(String objectString) {
        try {
            new JSONObject(objectString);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }

    /**
     * @param arrayString
     * @return
     */
    public final static boolean isValidJsonArray(String arrayString) {
        try {
            new JSONArray(arrayString);
        } catch (JSONException ex1) {
            return false;
        }
        return true;
    }

    /**
     * @param email
     * @return
     */
    public final static boolean isValidEmail(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    /**
     * @param phoneNumber
     * @return
     */
    public final static boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return false;
        } else {
            return phoneNumber.length() >= 10 && Patterns.PHONE.matcher(phoneNumber).matches();
        }
    }

}
