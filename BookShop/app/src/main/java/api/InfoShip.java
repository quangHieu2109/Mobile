package api;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class InfoShip implements Serializable {
    private String MA_DV_CHINH;
    private String TEN_DICHVU;
    private double GIA_CUOC;
    private String THOI_GIAN;
    private int EXCHANGE_WEIGHT;
    private List<ExtraService> EXTRA_SERVICE;

    public InfoShip(String MA_DV_CHINH, String TEN_DICHVU, double GIA_CUOC, String THOI_GIAN, int EXCHANGE_WEIGHT, List<ExtraService> EXTRA_SERVICE) {
        this.MA_DV_CHINH = MA_DV_CHINH;
        this.TEN_DICHVU = TEN_DICHVU;
        this.GIA_CUOC = GIA_CUOC;
        this.THOI_GIAN = THOI_GIAN;
        this.EXCHANGE_WEIGHT = EXCHANGE_WEIGHT;
        this.EXTRA_SERVICE = EXTRA_SERVICE;
    }

    public String getMA_DV_CHINH() {
        return MA_DV_CHINH;
    }

    public void setMA_DV_CHINH(String MA_DV_CHINH) {
        this.MA_DV_CHINH = MA_DV_CHINH;
    }

    public String getTEN_DICHVU() {
        return TEN_DICHVU;
    }

    public void setTEN_DICHVU(String TEN_DICHVU) {
        this.TEN_DICHVU = TEN_DICHVU;
    }

    public double getGIA_CUOC() {
        return GIA_CUOC;
    }

    public void setGIA_CUOC(double GIA_CUOC) {
        this.GIA_CUOC = GIA_CUOC;
    }

    public String getTHOI_GIAN() {
        return THOI_GIAN;
    }

    public void setTHOI_GIAN(String THOI_GIAN) {
        this.THOI_GIAN = THOI_GIAN;
    }

    public int getEXCHANGE_WEIGHT() {
        return EXCHANGE_WEIGHT;
    }

    public void setEXCHANGE_WEIGHT(int EXCHANGE_WEIGHT) {
        this.EXCHANGE_WEIGHT = EXCHANGE_WEIGHT;
    }

    public List<ExtraService> getEXTRA_SERVICE() {
        return EXTRA_SERVICE;
    }

    public void setEXTRA_SERVICE(List<ExtraService> EXTRA_SERVICE) {
        this.EXTRA_SERVICE = EXTRA_SERVICE;
    }


    public String toString() {
        return "Service name: "+this.TEN_DICHVU+"\n"
                +"Unit price: "+this.GIA_CUOC+"\n"
                +"Delivery time: "+this.THOI_GIAN;
    }
}
 class ExtraService implements Serializable{
    private String SERVICE_CODE;
    private String SERVICE_NAME;
    private String DESCRIPTION;

     public ExtraService(String SERVICE_CODE, String SERVICE_NAME, String DESCRIPTION) {
         this.SERVICE_CODE = SERVICE_CODE;
         this.SERVICE_NAME = SERVICE_NAME;
         this.DESCRIPTION = DESCRIPTION;
     }

     @Override
     public String toString() {
         return "ExtraService{" +
                 "SERVICE_CODE='" + SERVICE_CODE + '\'' +
                 ", SERVICE_NAME='" + SERVICE_NAME + '\'' +
                 ", DESCRIPTION='" + DESCRIPTION + '\'' +
                 '}';
     }
 }