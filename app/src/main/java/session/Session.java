package session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.lillian.lillysbeauty.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by RONIN on 7/18/2017.
 */

public class Session {
    // Sharedpref file name
    private static final String PREF_NAME = "SALON";
    private static final String ACCOUNT_TYPE_SALON_ADMIN = "BUSINESS";
    private static final String ACCOUNT_TYPE_NORMAL = "CUSTOMER";
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    private SharedPreferences pref;

    public Session(Context ctx) {
        this._context = ctx;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(JSONObject user) {
        try {
            editor.putString("user_id", user.getString("USER_ID"));
            editor.putString("surname", user.getString("SURNAME"));
            editor.putString("email", user.getString("EMAIL"));
            editor.putString("account", user.getString("ACCOUNT_TYPE"));
            editor.putBoolean("isLoggedIn", true);
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUserID() {
        return pref.getString("user_id", null);
    }

    public String getUserName() {
        return pref.getString("surname", null);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean("isLoggedIn", false);
    }

    public boolean isSalonAdmin() {
        String account = pref.getString("account", null);
        return account.equals(ACCOUNT_TYPE_SALON_ADMIN);
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Get the logged in session data
     *
     * @return
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put("USER_ID", pref.getString("user_id", null));
        user.put("EMAIL", pref.getString("email", null));
        user.put("SURNAME", pref.getString("surname", null));
        // return user
        return user;
    }
}
