package helpers;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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

    public static LineGraphSeries<DataPoint> BuildLineGraphSeries(JSONArray result, boolean useDatePoint) {
        DataPoint[] point = BuildDataPoints(result, useDatePoint);
        LineGraphSeries<DataPoint> incomeSeries = new LineGraphSeries<>(point);
        return incomeSeries;
    }

    /**
     * @param result
     * @return
     */
    private static DataPoint[] BuildDataPoints(JSONArray result, boolean useDate) {
        DataPoint[] dataPoints = new DataPoint[result.length()];

        try {
            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = (JSONObject) result.get(i);
                // dataPoints[i] = new DataPoint(jo.getInt("point"), jo.getInt("value"));
                if (useDate) {
                    dataPoints[i] = new DataPoint(Helper.StringToDate(jo.getString("date")), jo.getInt("value"));
                } else {
                    dataPoints[i] = new DataPoint(jo.getInt("point"), jo.getInt("value"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataPoints;
    }
}
