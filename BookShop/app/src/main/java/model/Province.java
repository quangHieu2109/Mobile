package model;

public class Province {
    private int PROVINCE_ID;
    private String PROVINCE_CODE;
    private String PROVINCE_NAME;

    public Province(int PROVINCE_ID, String PROVINCE_CODE, String PROVINCE_NAME) {
        this.PROVINCE_ID = PROVINCE_ID;
        this.PROVINCE_CODE = PROVINCE_CODE;
        this.PROVINCE_NAME = PROVINCE_NAME;
    }

    public int getPROVINCE_ID() {
        return PROVINCE_ID;
    }

    public void setPROVINCE_ID(int PROVINCE_ID) {
        this.PROVINCE_ID = PROVINCE_ID;
    }

    public String getPROVINCE_CODE() {
        return PROVINCE_CODE;
    }

    public void setPROVINCE_CODE(String PROVINCE_CODE) {
        this.PROVINCE_CODE = PROVINCE_CODE;
    }

    public String getPROVINCE_NAME() {
        return PROVINCE_NAME;
    }

    public void setPROVINCE_NAME(String PROVINCE_NAME) {
        this.PROVINCE_NAME = PROVINCE_NAME;
    }

    @Override
    public String toString() {
        return PROVINCE_NAME;
    }
}
