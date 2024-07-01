package view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshop.R;

import api.AApi;
import api.APIService;
import api.Login;
import request.ChangeInforRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeInforActivity extends AppCompatActivity {
    EditText fullname, phoneNumber, email;
    Button buttonBack, buttonChangeInfor;
    RadioGroup radioGroupGender;
    RadioButton radioMale, radioFemale;
    int gender;
    public boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10) {
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public boolean isValidName(String name) {

        return name.matches("[a-zA-Z ]+");
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_infor);

        // Initialize views
        fullname = findViewById(R.id.newName);
        phoneNumber = findViewById(R.id.newPhoneNum);
        email = findViewById(R.id.newEmail);
        buttonChangeInfor = findViewById(R.id.btn_change_infor);
        buttonBack = findViewById(R.id.button_back);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);

        // Handle radio button selection
        radioGroupGender.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioMale) {
                gender = 0; // Assume 0 for male
            } else if (checkedId == R.id.radioFemale) {
                gender = 1; // Assume 1 for female
            }
        });

        buttonChangeInfor.setOnClickListener(v -> {
            String newFullname = fullname.getText().toString();
            String newPhoneNumber = phoneNumber.getText().toString();
            String newEmail = email.getText().toString();

            if (!isValidName(newFullname)) {
                Toast.makeText(ChangeInforActivity.this, "Tên không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidPhoneNumber(newPhoneNumber)) {
                Toast.makeText(ChangeInforActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidEmail(newEmail)) {
                Toast.makeText(ChangeInforActivity.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            String accessToken = "Bearer " + Login.getToken();
            APIService.apiService.changeInfor(accessToken, new ChangeInforRequest(newFullname, newPhoneNumber, newEmail, gender))
                    .enqueue(new Callback<AApi<Object>>() {
                        @Override
                        public void onResponse(Call<AApi<Object>> call, Response<AApi<Object>> response) {
                                runOnUiThread(() -> {
                                    Toast.makeText(ChangeInforActivity.this, "Thay đổi thông tin thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                });
                        }
                        @Override
                        public void onFailure(Call<AApi<Object>> call, Throwable t) {
                            runOnUiThread(() -> {
                                Toast.makeText(ChangeInforActivity.this, "Thay đổi thông tin thất bại: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                        }
                    });
        });

        // Handle back button click
        buttonBack.setOnClickListener(v -> {
            finish();
        });
    }
}
