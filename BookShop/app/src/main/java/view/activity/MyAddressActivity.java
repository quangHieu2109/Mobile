package view.activity;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshop.R;

import java.util.List;

import api.AApi;
import api.APIService;
import api.Login;
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_address);
        addAddress = findViewById(R.id.btnAddAddress);
        back = findViewById(R.id.backMyAdress);
        listAddress = findViewById(R.id.listAdress);
        context = this;
        getAddress();
        setOnClickListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            getAddress();
        }
    }

    private void setOnClickListener(){
        addAddress.setOnClickListener(v ->{
            Intent intent = new Intent(this, AddAddressActivity.class);
            startActivityForResult(intent, 125);
        });
        back.setOnClickListener(v -> {
            int selected = listAddress.getCheckedRadioButtonId();
            Bundle resultBundle;
            Address address = null;
            if (selected != -1) {
                RadioButton radioButton = findViewById(selected);
                 address = (Address) radioButton.getTag();
                resultBundle = new Bundle();
                resultBundle.putSerializable("address", address);
                setResult(RESULT_OK, new Intent().putExtras(resultBundle));
            } else {
                setResult(RESULT_CANCELED);
            }
//            Log.d("MyActivity", "Showing Toast: " + address.toString());
//            Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();
            finish();
        });
    }
    private void getAddress(){
        listAddress.removeAllViews();
        Call<AApi<List<Address>>> call = APIService.apiService.getAddress("Bearer "+ Login.getToken());
        call.enqueue(new Callback<AApi<List<Address>>>() {
            @Override
            public void onResponse(Call<AApi<List<Address>>> call, Response<AApi<List<Address>>> response) {
                List<Address> addresses = response.body().getData();
                if(addresses.size() >0){

                    listAddress.setGravity(Gravity.CENTER);
                    RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                            RadioGroup.LayoutParams.MATCH_PARENT,
                            RadioGroup.LayoutParams.WRAP_CONTENT
                    );
                    int i=0;
                    for(Address address: addresses){
                        RadioButton radioButton = new RadioButton(context);
                        if(i++ ==0){
                            radioButton.setSelected(true);
                        }
                        radioButton.setTextColor(getResources().getColor(R.color.black));
                        radioButton.setBackgroundColor(getResources().getColor(R.color.DDD));
                        radioButton.setText(address.toString());
                        radioButton.setTag(address);
                        radioButton.setPadding(20,20,20,20);
                        params.width = (int) (listAddress.getWidth() * 0.7); // 60% chiều rộng của RadioButton
                        params.setMargins(5, 10, 5,10);
                        radioButton.setLayoutParams(params);

//                    radioButton.setMa;
                        listAddress.addView(radioButton);
                    }
                }
            }

            @Override
            public void onFailure(Call<AApi<List<Address>>> call, Throwable t) {

            }
        });
    }
}
