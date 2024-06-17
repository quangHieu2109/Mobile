package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import api.APIService;
import api.InfoShip;
import api.ViettheoResponse;
import request.InfoShipRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    int provinceId =1;
    int districtId =1;
    List<InfoShip> result = new ArrayList<>();
    Address address;
    String token = "F72D14C609C4C693ECDA34653EBCF032";
    List<Province> provinces;
    List<District> districts;
    @Override
    public String toString() {
        return "Số "+houseNumber+" - "+ward+" - "+district+" - "+province;
    }
    public List<InfoShip> getInfoShip(){

        address = this;

            APIService.viettheoAPI.getAllProvince(token).enqueue(new Callback<ViettheoResponse<Province>>() {
                @Override
                public void onResponse(Call<ViettheoResponse<Province>> call, Response<ViettheoResponse<Province>> response) {
                     provinces = response.body().getData();
                    for(Province province: provinces){
                        if(province.getPROVINCE_NAME().equalsIgnoreCase(address.province)){
                            provinceId = province.getPROVINCE_ID();
                            break;
                        }
                    }
                }

                @Override
                public void onFailure(Call<ViettheoResponse<Province>> call, Throwable t) {

                }
            });

              APIService.viettheoAPI.getDistrict(token, provinceId).enqueue(new Callback<ViettheoResponse<District>>() {
                 @Override
                 public void onResponse(Call<ViettheoResponse<District>> call, Response<ViettheoResponse<District>> response) {
                     districts = response.body().getData();
                 }

                 @Override
                 public void onFailure(Call<ViettheoResponse<District>> call, Throwable t) {

                 }
             });
            for(District district: districts){
                if(district.getDISTRICT_NAME().equalsIgnoreCase(address.district)){
                    districtId = district.getDISTRICT_ID();
                    break;
                }
            }


        InfoShipRequest infoShipRequest = new InfoShipRequest(provinceId, districtId);
        Call<List<InfoShip>> getInfoShip = APIService.getInfoShip.getInfoShip(token, infoShipRequest);
        getInfoShip.enqueue(new Callback<List<InfoShip>>() {
            @Override
            public void onResponse(Call<List<InfoShip>> call, Response<List<InfoShip>> response) {
                result=response.body();
            }

            @Override
            public void onFailure(Call<List<InfoShip>> call, Throwable t) {

            }
        });
        return result;
    }

}
