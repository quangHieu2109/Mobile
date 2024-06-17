package view.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshop.R;

import java.util.List;

import api.AApi;
import api.APIService;
import api.InfoShip;
import api.Login;
import api.ViettheoResponse;
import model.Address;
import model.District;
import model.Province;
import request.InfoShipRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InfoShipActivity extends AppCompatActivity {
    Button back;
    RadioGroup listInfoShip;
    Context context;
    String token = "F72D14C609C4C693ECDA34653EBCF032";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_info_ship);
        back = findViewById(R.id.backInfoShip);
        listInfoShip = findViewById(R.id.listInfoShip);
        context = this;
        Address address = (Address) getIntent().getExtras().getSerializable("address");
        APIService.viettheoAPI.getAllProvince(token).enqueue(new Callback<ViettheoResponse<Province>>() {
            @Override
            public void onResponse(Call<ViettheoResponse<Province>> call, Response<ViettheoResponse<Province>> response) {
//                    System.out.println(address);
                List<Province> provinces = response.body().getData();
//                    System.out.println(provinces);
                int provinceId=1;
                for (Province province : provinces) {
                    if (province.getPROVINCE_NAME().equalsIgnoreCase(address.getProvince())) {
//                            System.out.println(123123);
                        provinceId = province.getPROVINCE_ID();
                        break;
                    }
                }
                APIService.viettheoAPI.getDistrict(token, provinceId).enqueue(new Callback<ViettheoResponse<District>>() {

                    @Override
                    public void onResponse(Call<ViettheoResponse<District>> call, Response<ViettheoResponse<District>> response) {

                        List<District> districts = response.body().getData();
                        int districtId=1;
                        for (District district : districts) {
                            if (district.getDISTRICT_NAME().equalsIgnoreCase(address.getDistrict())) {
                                districtId = district.getDISTRICT_ID();
                                break;
                            }
                        }
                        InfoShipRequest infoShipRequest = new InfoShipRequest(districts.get(0).getPROVINCE_ID(), districtId);
                        Call<List<InfoShip>> getInfoShip = APIService.getInfoShip.getInfoShip(token, infoShipRequest);
                        getInfoShip.enqueue(new Callback<List<InfoShip>>() {
                            @Override
                            public void onResponse(Call<List<InfoShip>> call, Response<List<InfoShip>> response) {
                                List<InfoShip> infoShips = response.body();
                                listInfoShip.setGravity(Gravity.CENTER);
                                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                                        RadioGroup.LayoutParams.MATCH_PARENT,
                                        RadioGroup.LayoutParams.WRAP_CONTENT
                                );
                                int i = 0;
                                for (InfoShip infoShip : infoShips) {
                                    RadioButton radioButton = new RadioButton(context);
                                    if (i++ == 0) {
                                        radioButton.setSelected(true);
                                    }
                                    radioButton.setGravity(Gravity.CENTER);
                                    radioButton.setTextColor(getResources().getColor(R.color.black));
                                    radioButton.setBackgroundColor(getResources().getColor(R.color.DDD));
                                    radioButton.setText(infoShip.toString());
                                    radioButton.setTag(infoShip);
                                    radioButton.setPadding(20,20,20,20);
                                    params.width = (int) (listInfoShip.getWidth() * 0.7); // 60% chiều rộng của RadioButton
                                    params.setMargins(5, 10, 5, 10);
                                    radioButton.setLayoutParams(params);

//                    radioButton.setMa;
                                    listInfoShip.addView(radioButton);
                                }

                            }

                            @Override
                            public void onFailure(Call<List<InfoShip>> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<ViettheoResponse<District>> call, Throwable t) {

                    }
                });


            }

            @Override
            public void onFailure(Call<ViettheoResponse<Province>> call, Throwable t) {

            }
        });
//        Toast.makeText(this, infoShips.toString(), Toast.LENGTH_LONG).show();


        setOnClickListener();
    }

    private void setOnClickListener() {
        back.setOnClickListener(v -> {
            int selected = listInfoShip.getCheckedRadioButtonId();
            Bundle resultBundle;
            InfoShip infoShip = null;
            if (selected != -1) {
                RadioButton radioButton = findViewById(selected);
                infoShip = (InfoShip) radioButton.getTag();
                resultBundle = new Bundle();
                resultBundle.putSerializable("infoShip", infoShip);
                setResult(RESULT_OK, new Intent().putExtras(resultBundle));
            } else {
                setResult(RESULT_CANCELED);
            }
//            Log.d("MyActivity", "Showing Toast: " + address.toString());
//            Toast.makeText(this, infoShip.toString(), Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
