package model;

import android.accounts.Account;

/**
 * Created by RONIN on 8/22/2017.
 */

public class Payments {

    public String ACCOUNT_REF,
            PAYMENT_ID,
            RESERVATION_ID,
            BOOKING_AMOUNT,
            FINAL_AMOUNT,
            DATE_PAID,
            PAYMENT_REF,
            PAYMENT_STATUS,
            BALANCE,
            MPESA_REF,
            COMMENTS,
            STATUS,
            SALON_TEL,
            OWNER_ID;

    public void setACCOUNT_REF(String ACCOUNT_REF) {
        this.ACCOUNT_REF = ACCOUNT_REF;
    }

    public void setBALANCE(String BALANCE) {
        this.BALANCE = BALANCE;
    }

    public void setBOOKING_AMOUNT(String BOOKING_AMOUNT) {
        this.BOOKING_AMOUNT = BOOKING_AMOUNT;
    }

    public void setCOMMENTS(String COMMENTS) {
        this.COMMENTS = COMMENTS;
    }

    public void setDATE_PAID(String DATE_PAID) {
        this.DATE_PAID = DATE_PAID;
    }

    public void setFINAL_AMOUNT(String FINAL_AMOUNT) {
        this.FINAL_AMOUNT = FINAL_AMOUNT;
    }

    public void setMPESA_REF(String MPESA_REF) {
        this.MPESA_REF = MPESA_REF;
    }

    public void setOWNER_ID(String OWNER_ID) {
        this.OWNER_ID = OWNER_ID;
    }

    public void setPAYMENT_ID(String PAYMENT_ID) {
        this.PAYMENT_ID = PAYMENT_ID;
    }

    public void setPAYMENT_REF(String PAYMENT_REF) {
        this.PAYMENT_REF = PAYMENT_REF;
    }

    public void setPAYMENT_STATUS(String PAYMENT_STATUS) {
        this.PAYMENT_STATUS = PAYMENT_STATUS;
    }

    public void setRESERVATION_ID(String RESERVATION_ID) {
        this.RESERVATION_ID = RESERVATION_ID;
    }

    public void setSALON_TEL(String SALON_TEL) {
        this.SALON_TEL = SALON_TEL;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
}
