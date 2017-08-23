package model;

/**
 * Created by RONIN on 7/20/2017.
 */

public class OfferedServices {

    public static final String SERVICE_ID_ARG = "SERVICE_ID";
    public static final String OFFERED_SERVICE_ID_ARG = "OFFERED_SERVICE_ID";
    public static final String RESERVED_SERVICE_ID_ARG = "RESERVATION_ID";
    public static final String SELECTED_SERVICES_ARG = "SELECTED_SERVICES";
    public static final String SERVICE_NAME_ARG = "SERVICE_NAME";
    public static final String SERVICE_COST_ARG = "SERVICE_COST";
    public static final String SALON_NAME_ARG = "SALON_NAME";

    public static final String SERVICE_DESCRIPTION_ARG = "SERVICE_DESCRIPTION";


    public String OFFERED_SERVICE_ID, SERVICE_ID, SALON_ID, SERVICE_COST, SERVICE_DESCRIPTION, RESERVATION_ID;
    public String SERVICE_NAME, SALON_NAME;

    public void setSALON_ID(String SALON_ID) {
        this.SALON_ID = SALON_ID;
    }

    public void setSERVICE_ID(String SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

    public void setSALON_NAME(String SALON_NAME) {
        this.SALON_NAME = SALON_NAME;
    }

    public void setOFFERED_SERVICE_ID(String OFFERED_SERVICE_ID) {
        this.OFFERED_SERVICE_ID = OFFERED_SERVICE_ID;
    }

    public void setSERVICE_NAME(String SERVICE_NAME) {
        this.SERVICE_NAME = SERVICE_NAME;
    }

    public void setDESCRIPTION(String SERVICE_DESCRIPTION) {
        this.SERVICE_DESCRIPTION = SERVICE_DESCRIPTION;
    }

    public void setRESERVATION_ID(String RESERVATION_ID) {
        this.RESERVATION_ID = RESERVATION_ID;
    }

    public void setSERVICE_COST(String SERVICE_COST) {
        this.SERVICE_COST = SERVICE_COST;
    }
}
