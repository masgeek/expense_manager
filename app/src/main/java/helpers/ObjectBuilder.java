package helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.OfferedServices;
import model.Expenses;

/**
 * Created by RONIN on 7/22/2017.
 */

public class ObjectBuilder {

    public static String RESERVATION_ID = null;

    public static List<OfferedServices> BuildServiceObject(JSONArray result) {
        List<OfferedServices> offeredServicesList = new ArrayList<>();
        try {
            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = (JSONObject) result.get(i);
                OfferedServices item = new OfferedServices();
                item.setOFFERED_SERVICE_ID(jo.getString("OFFERED_SERVICE_ID"));
                item.setSALON_ID(jo.getString("SALON_ID"));
                item.setSALON_NAME(jo.getString("SALON_NAME"));
                item.setSERVICE_ID(jo.getString("SERVICE_ID"));
                item.setSERVICE_COST(jo.getString("SERVICE_COST"));
                item.setSERVICE_NAME(jo.getString("SERVICE_NAME"));
                item.setDESCRIPTION(jo.getString("SERVICE_DESCRIPTION"));
                item.setRESERVATION_ID(RESERVATION_ID);

                offeredServicesList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return offeredServicesList;
    }

    public static List<Expenses> BuildExpensesObject(JSONArray result) {
        List<Expenses> paymentHiistoryList = new ArrayList<>();
        try {
            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = (JSONObject) result.get(i);
                Expenses item = new Expenses();
                item.setAmount(jo.getString("amount"));
                item.setType(jo.getString("type"));
                paymentHiistoryList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paymentHiistoryList;
    }
}
