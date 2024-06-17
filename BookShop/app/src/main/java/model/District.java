package model;

public class District {
    private int DISTRICT_ID;
    private String DISTRICT_VALUE;
    private String DISTRICT_NAME;
    private int PROVINCE_ID;

    public District(int DISTRICT_ID, String DISTRICT_VALUE, String DISTRICT_NAME, int PROVINCE_ID) {
        this.DISTRICT_ID = DISTRICT_ID;
        this.DISTRICT_VALUE = DISTRICT_VALUE;
        this.DISTRICT_NAME = DISTRICT_NAME;
        this.PROVINCE_ID = PROVINCE_ID;
    }

    public int getDISTRICT_ID() {
        return DISTRICT_ID;
    }

    public void setDISTRICT_ID(int DISTRICT_ID) {
        this.DISTRICT_ID = DISTRICT_ID;
    }

    public String getDISTRICT_VALUE() {
        return DISTRICT_VALUE;
    }

    public void setDISTRICT_VALUE(String DISTRICT_VALUE) {
        this.DISTRICT_VALUE = DISTRICT_VALUE;
    }

    public String getDISTRICT_NAME() {
        return DISTRICT_NAME;
    }

    public void setDISTRICT_NAME(String DISTRICT_NAME) {
        this.DISTRICT_NAME = DISTRICT_NAME;
    }

    public int getPROVINCE_ID() {
        return PROVINCE_ID;
    }

    public void setPROVINCE_ID(int PROVINCE_ID) {
        this.PROVINCE_ID = PROVINCE_ID;
    }

    @Override
    public String toString() {
        return DISTRICT_NAME ;
    }
}
