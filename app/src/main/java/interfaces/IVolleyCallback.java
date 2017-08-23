package interfaces;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by RONIN on 7/17/2017.
 */

public interface IVolleyCallback {

    void onSuccessJsonString(String result);

    void onSuccessJsonArr(JSONArray result);

    void onSuccessJsonObject(JSONObject result);

    void onDeleted(String result);

    void onError(VolleyError error);
}
