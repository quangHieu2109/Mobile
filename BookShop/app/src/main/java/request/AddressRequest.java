package request;

public class AddressRequest {
    private String houseNumber;
    private String province;
    private String district;
    private String ward;

    public AddressRequest(String houseNumber, String province, String district, String ward) {
        this.houseNumber = houseNumber;
        this.province = province;
        this.district = district;
        this.ward = ward;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }
}
