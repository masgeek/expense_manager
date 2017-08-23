package model;

/**
 * Created by RONIN on 7/17/2017.
 */

public class User {
    public int USER_ID, ACCOUNT_STATUS, ACCOUNT_TYPE_ID;
    public String SURNAME, OTHER_NAMES, EMAIL, MOBILE_NO, PASSWORD;

    //getters
    public int getUSERID() {
        return USER_ID;
    }

    public int getACCOUNTSTATUS() {
        return ACCOUNT_STATUS;
    }

    public int getACCOUNTTYPE() {
        return ACCOUNT_TYPE_ID;
    }

    public String getSURNAME() {
        return SURNAME;
    }

    public String getOTHERNAMES() {
        return OTHER_NAMES;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getMOBILENO() {
        return MOBILE_NO;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    //setters
    public void setUSERID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public void setACCOUNTSTATUS(int ACCOUNT_STATUS) {
        this.ACCOUNT_STATUS = ACCOUNT_STATUS;
    }

    public void setACCOUNTTYPE(int ACCOUNT_TYPE_ID) {
        this.ACCOUNT_TYPE_ID = ACCOUNT_TYPE_ID;
    }

    public void setSURNAME(String SURNAME) {
        this.SURNAME = SURNAME;
    }

    public void setOTHERNAMES(String OTHER_NAMES) {
        this.OTHER_NAMES = OTHER_NAMES;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setMOBILENO(String MOBILE_NO) {
        this.MOBILE_NO = MOBILE_NO;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
