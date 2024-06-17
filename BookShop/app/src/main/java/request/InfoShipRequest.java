package request;

public class InfoShipRequest {
    private String SENDER_PROVINCE;
    private String SENDER_DISTRICT;
    private String RECEIVER_PROVINCE;
    private String RECEIVER_DISTRICT;
    private String PRODUCT_TYPE;
    private double PRODUCT_WEIGHT;
    private int  MONEY_COLLECTION;
    private int  PRODUCT_PRICE;
    private int  TYPE;

    public InfoShipRequest(int RECEIVER_PROVINCE, int RECEIVER_DISTRICT) {
        this.RECEIVER_PROVINCE = RECEIVER_PROVINCE+"";
        this.RECEIVER_DISTRICT = RECEIVER_DISTRICT+"";
        this.SENDER_PROVINCE="2";
        this.SENDER_DISTRICT = "1231";
        this.PRODUCT_TYPE ="HH";
        this.PRODUCT_WEIGHT=0.5;
        this.PRODUCT_PRICE =0;
        this.MONEY_COLLECTION=0;
        this.TYPE=1;
    }
}
