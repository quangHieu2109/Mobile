package view.activity;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshop.R;

import java.util.List;

import api.AApi;
import api.APIService;
import model.Address;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyAddressActivity extends AppCompatActivity {
    Button back, addAddress;
    RadioGroup listAddress;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_address);
        addAddress = findViewById(R.id.btnAddAddress);
        back = findViewById(R.id.backMyAdress);
        listAddress = findViewById(R.id.listAdress);
        context = this;
        Call<AApi<List<Address>>> call = APIService.apiService.getAddress("Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IkR1bm4gTWNwaGVyc29uIiwiZW1haWwiOiJkdW5ubWNwaGVyc29uQHJlY3Jpc3lzLmNvbSIsInN1YiI6ImR1bm5tY3BoZXJzb25AcmVjcmlzeXMuY29tIiwianRpIjoiNzgyYzZhMWUtYzQ4My00ZjMyLTg0MmQtZjZjNzM5NTdmMTQ3Iiwicm9sZSI6IkFETUlOIiwiSWQiOiIxIiwibmJmIjoxNzE4NTM1NDMzLCJleHAiOjE3MTg1NTM0MzMsImlhdCI6MTcxODUzNTQzM30.1hNVKuM5SgTE-8bkRDCM6pTz_ejDPHOrJ-eAze4gh9g");
        call.enqueue(new Callback<AApi<List<Address>>>() {
            @Override
            public void onResponse(Call<AApi<List<Address>>> call, Response<AApi<List<Address>>> response) {
                List<Address> addresses = response.body().getData();
                listAddress.setGravity(Gravity.CENTER);
                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                        RadioGroup.LayoutParams.MATCH_PARENT,
                        RadioGroup.LayoutParams.WRAP_CONTENT
                );
                for(Address address: addresses){
                    RadioButton radioButton = new RadioButton(context);
                    radioButton.setTextColor(getResources().getColor(R.color.black));
                    radioButton.setBackgroundColor(getResources().getColor(R.color.DDD));
                    radioButton.setText(address.toString());
                    params.width = (int) (listAddress.getWidth() * 0.7); // 60% chiều rộng của RadioButton
                    params.setMargins(5, 10, 5,10);
                    radioButton.setLayoutParams(params);

//                    radioButton.setMa;
                    listAddress.addView(radioButton);
                }
            }

            @Override
            public void onFailure(Call<AApi<List<Address>>> call, Throwable t) {

            }
        });
        setOnClickListener();
    }
    private void setOnClickListener(){
        addAddress.setOnClickListener(v ->{
            Intent intent = new Intent(this, AddAddressActivity.class);
            startActivity(intent);
        });
        back.setOnClickListener(v ->{
            finish();
        });
    }
}
