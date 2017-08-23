package helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.OfferedServices;
import model.Payments;

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

    public static List<Payments> BuildPaymentObject(JSONArray result) {
        List<Payments> paymentHiistoryList = new ArrayList<>();
        try {
            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = (JSONObject) result.get(i);
                Payments item = new Payments();
                item.setACCOUNT_REF(jo.getString("ACCOUNT_REF"));
                item.setBALANCE(jo.getString("BALANCE"));
                item.setBOOKING_AMOUNT(jo.getString("BOOKING_AMOUNT"));
                item.setCOMMENTS(jo.getString("COMMENTS"));
                item.setDATE_PAID(jo.getString("DATE_PAID"));
                item.setFINAL_AMOUNT(jo.getString("FINAL_AMOUNT"));
                item.setMPESA_REF(jo.getString("MPESA_REF"));
                item.setOWNER_ID(jo.getString("OWNER_ID"));
                item.setPAYMENT_ID(jo.getString("PAYMENT_ID"));
                item.setPAYMENT_REF(jo.getString("PAYMENT_REF"));
                item.setPAYMENT_STATUS(jo.getString("PAYMENT_STATUS"));
                item.setRESERVATION_ID(jo.getString("RESERVATION_ID"));
                item.setSALON_TEL(jo.getString("SALON_TEL"));
                item.setSTATUS(jo.getString("STATUS"));
                item.setRESERVATION_ID(jo.getString("STATUS"));

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
