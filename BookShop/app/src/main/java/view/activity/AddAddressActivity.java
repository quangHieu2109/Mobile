package view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookshop.R;

import java.util.ArrayList;
import java.util.List;

import api.AApi;
import api.APIService;
import api.Login;
import api.ViettheoResponse;
import model.Address;
import model.District;
import model.Province;
import model.Ward;
import request.AddressRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressActivity extends AppCompatActivity {
    private final String token = "F72D14C609C4C693ECDA34653EBCF032";
    Spinner province, district, ward;
    TextView province_name, district_name, ward_name;
    Button btnAddAddress, backAddAddress;
    EditText houseNumberEdt;
    boolean result = false;

    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_address);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addAddress), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        context = this;
        province = findViewById(R.id.province);
        province.setPrompt("Chọn Tỉnh/Thành phố!");
        province.setBackgroundResource(R.drawable.spinner_background);

        district = findViewById(R.id.district);
        district.setPrompt("Chọn Quận/Huyện!");
        district.setBackgroundResource(R.drawable.spinner_background);
        ward = findViewById(R.id.ward);
        ward.setPrompt("Chọn Phương/Xã!");
        ward.setBackgroundResource(R.drawable.spinner_background);
        province_name = findViewById(R.id.province_name);
        district_name = findViewById(R.id.district_name);
        ward_name = findViewById(R.id.ward_name);
        btnAddAddress = findViewById(R.id.btnAddAddress);
        houseNumberEdt = findViewById(R.id.houseNumber);
        backAddAddress = findViewById(R.id.backAddAddress);
        Call<ViettheoResponse<Province>> call = APIService.viettheoAPI.getAllProvince(token);

        call.enqueue(new Callback<ViettheoResponse<Province>>() {
            @Override
            public void onResponse(Call<ViettheoResponse<Province>> call, Response<ViettheoResponse<Province>> response) {
                List<Province> provinces = response.body().getData();
                ArrayAdapter<Province> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, provinces);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                province.setAdapter(adapter);
                province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                        Province province1Selected = provinces.get(position);
//                        province_name.setText(province1Selected.toString());
                        View v = province.getSelectedView();
                        ((TextView)v).setTextColor(getResources().getColor(R.color.white));
                        int provinceId = province1Selected.getPROVINCE_ID();
                        Call<ViettheoResponse<District>> callDis = APIService.viettheoAPI.getDistrict(token, provinceId);
                        callDis.enqueue(new Callback<ViettheoResponse<District>>() {
                            @Override
                            public void onResponse(Call<ViettheoResponse<District>> call, Response<ViettheoResponse<District>> response) {
                                List<District> districts = response.body().getData();
                                    ArrayAdapter<District> adapterDist = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, districts);
                                    adapterDist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    district.setAdapter(adapterDist);
                                    district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                            District districtSelected = districts.get(position);
//                                            district_name.setText(districtSelected.toString());
                                            int districtId = districtSelected.getDISTRICT_ID();
                                            View v = district.getSelectedView();
                                            ((TextView)v).setTextColor(getResources().getColor(R.color.white));
                                            Call<ViettheoResponse<Ward>> callWard = APIService.viettheoAPI.getWard(token, districtId);
                                            callWard.enqueue(new Callback<ViettheoResponse<Ward>>() {
                                                @Override
                                                public void onResponse(Call<ViettheoResponse<Ward>> call, Response<ViettheoResponse<Ward>> response) {
                                                    List<Ward> wards = response.body().getData();

                                                    if (wards != null) {
                                                        ArrayAdapter<Ward> districtArrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, wards);
                                                        adapterDist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                        ward.setAdapter(districtArrayAdapter);
                                                        ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                Ward wardSelected = wards.get(i);
                                                                View v = ward.getSelectedView();
                                                                ((TextView)v).setTextColor(getResources().getColor(R.color.white));
//                                                                ward_name.setText(wardSelected.toString());
                                                            }

                                                            @Override
                                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                                            }
                                                        });
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ViettheoResponse<Ward>> call, Throwable t) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });

                            }

                            @Override
                            public void onFailure(Call<ViettheoResponse<District>> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }


            @Override
            public void onFailure(Call<ViettheoResponse<Province>> call, Throwable t) {

            }
        });
        setOnClickListener();
    }
    private void setOnClickListener(){
        backAddAddress.setOnClickListener(v ->{
            if(result){
                setResult(RESULT_OK);
            }else{
                setResult(RESULT_CANCELED);
            }
            finish();
        });

        btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String houseNumber = houseNumberEdt.getText().toString();
                String provinceName = ((Province) province.getSelectedItem()).getPROVINCE_NAME();
                String districtName = ((District) district.getSelectedItem()).getDISTRICT_NAME();
                String wardName = ((Ward) ward.getSelectedItem()).getWARDS_NAME();

                if(houseNumber.length() >0){
                    AddressRequest addressRequest = new AddressRequest(houseNumber, provinceName, districtName, wardName);
                    Call<AApi<Address>> call = APIService.apiService.addAddress("Bearer "+ Login.getToken(),addressRequest);
                    call.enqueue(new Callback<AApi<Address>>() {
                        @Override
                        public void onResponse(Call<AApi<Address>> call, Response<AApi<Address>> response) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogSuccess);
                            builder.setTitle("Success")
                                    .setMessage("Thêm địa chỉ thành công")
                                    .show();
                            result = true;
                        }

                        @Override
                        public void onFailure(Call<AApi<Address>> call, Throwable t) {

                        }
                    });
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogError);
                    builder.setTitle("Error")
                            .setMessage("Vui lòng nhập số nhà!")
                            .show();
                }
            }
        });
    }
}
