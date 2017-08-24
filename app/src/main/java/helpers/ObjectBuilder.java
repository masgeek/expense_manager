package helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.IncomeExpensesModel;
import model.OfferedServices;

/**
 * Created by RONIN on 7/22/2017.
 */

public class ObjectBuilder {

    public static String RESERVATION_ID = null;


    public static List<IncomeExpensesModel> BuildExpensesObject(JSONArray result) {
        List<IncomeExpensesModel> paymentHiistoryList = new ArrayList<>();
        try {
            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = (JSONObject) result.get(i);
                IncomeExpensesModel item = new IncomeExpensesModel();
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

    public static List<IncomeExpensesModel> BuildExpensesVSIncomeObject(JSONArray result) {
        List<IncomeExpensesModel> paymentHistoryList = new ArrayList<>();
        try {
            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = (JSONObject) result.get(i);
                IncomeExpensesModel item = new IncomeExpensesModel();
                item.setTotalIncome(jo.getString("total_income"));
                item.setTotalExpenses(jo.getString("total_expenses"));
                item.setDifference(jo.getString("difference"));
                paymentHistoryList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paymentHistoryList;
    }
}
