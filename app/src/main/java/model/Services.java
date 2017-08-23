package model;

/**
 * Created by RONIN on 7/20/2017.
 */

public class Services {

    public String SERVICE_ID, SERVICE_COST, SERVICE_DESCRIPTION, SERVICE_NAME;

    public Services() {
        this.SERVICE_ID = null;
        this.SERVICE_COST = null;
        this.SERVICE_DESCRIPTION = null;
        this.SERVICE_NAME = null;
    }

    public void setSERVICE_ID(String SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

    public void setSERVICE_NAME(String SERVICE_NAME) {
        this.SERVICE_NAME = SERVICE_NAME;
    }

    public void setDESCRIPTION(String SERVICE_DESCRIPTION) {
        this.SERVICE_DESCRIPTION = SERVICE_DESCRIPTION;
    }
}
