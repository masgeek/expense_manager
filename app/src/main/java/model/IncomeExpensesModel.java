package model;

import android.accounts.Account;

/**
 * Created by RONIN on 8/22/2017.
 */

public class IncomeExpensesModel {

    public String id, type, amount, place, note, cheque, date;
    public String totalIncome,totalExpenses,difference;

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotalExpenses(String totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }
}
