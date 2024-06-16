package model;

import java.io.Serializable;

public class Address implements Serializable {
    private long id;
    private long userId;
    private String houseNumber;
    private String province;
    private String district;
    private String ward;

    public Address(long id, long userId, String houseNumber, String province, String district, String ward) {
        this.id = id;
        this.userId = userId;
        this.houseNumber = houseNumber;
        this.province = province;
        this.district = district;
        this.ward = ward;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Số "+houseNumber+" - "+ward+" - "+district+" - "+province;
    }

}
