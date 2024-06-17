package model;

public class Ward {
    private int WARDS_ID;
    private String WARDS_NAME;
    private int DISTRICT_ID;

    public Ward(int WARDS_ID, String WARDS_NAME, int DISTRICT_ID) {

        this.WARDS_ID = WARDS_ID;
        this.WARDS_NAME = WARDS_NAME;
        this.DISTRICT_ID = DISTRICT_ID;
    }

    public int getWARDS_ID() {
        return WARDS_ID;
    }

    public void setWARDS_ID(int WARDS_ID) {
        this.WARDS_ID = WARDS_ID;
    }

    public String getWARDS_NAME() {
        return WARDS_NAME;
    }

    public void setWARDS_NAME(String WARDS_NAME) {
        this.WARDS_NAME = WARDS_NAME;
    }

    public int getDISTRICT_ID() {
        return DISTRICT_ID;
    }

    public void setDISTRICT_ID(int DISTRICT_ID) {
        this.DISTRICT_ID = DISTRICT_ID;
    }

    @Override
    public String toString() {
        return WARDS_NAME;
    }
}

