package view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.EdgeToEdge;

import com.example.bookshop.R;

import api.AApi;
import api.APIService;
import request.RegisterRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.activity.ChangeInforActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, email, fullname;
    Button buttonBack, buttonRegister;
    private boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9_]{4,20}$";
        return username.matches(regex);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6 && password.length() <= 20;
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidFullname(String fullname) {
        String regex = "^[a-zA-Z][a-zA-Z\\s]*[a-zA-Z]$";
        return fullname.matches(regex);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Ánh xạ các view từ layout XML
        username = findViewById(R.id.regName);
        password = findViewById(R.id.regPass);
        email = findViewById(R.id.regMail);
        fullname = findViewById(R.id.regFullname);
        buttonBack = findViewById(R.id.button_back);
        buttonRegister = findViewById(R.id.btn_createAcc);

        // Xử lý sự kiện khi click vào nút "Register"
        buttonRegister.setOnClickListener(v -> {

            String enteredUsername = username.getText().toString();
            String enteredPassword = password.getText().toString();
            String enteredEmail = email.getText().toString();
            String enteredFullname = fullname.getText().toString();

            if (!isValidUsername(enteredUsername)) {
                Toast.makeText(RegisterActivity.this, "Username phải từ 4 đến 20 ký tự và không chứa ký tự đặc biệt", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra điều kiện password
            if (!isValidPassword(enteredPassword)) {
                Toast.makeText(RegisterActivity.this, "Password phải từ 6 đến 20 ký tự", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra điều kiện email
            if (!isValidEmail(enteredEmail)) {
                Toast.makeText(RegisterActivity.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValidFullname(enteredFullname)) {
                Toast.makeText(RegisterActivity.this, "Fullname không được chứa ký tự đặc biệt hoặc số", Toast.LENGTH_SHORT).show();
                return;
            }
            APIService.apiService.register(new RegisterRequest(enteredUsername,enteredPassword,enteredEmail,enteredFullname)).enqueue(new Callback<AApi<Object>>() {
                @Override
                public void onResponse(Call<AApi<Object>> call, Response<AApi<Object>> response) {
                    if (response.isSuccessful()) {
                        runOnUiThread(() -> {
                            Toast.makeText(RegisterActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        });
                    } else {
                        runOnUiThread(() -> {
                            Toast.makeText(RegisterActivity.this, "Email hoặc username đã tồn tại", Toast.LENGTH_SHORT).show();

                        });
                    }
                }
                @Override
                public void onFailure(Call<AApi<Object>> call, Throwable t) {
                    runOnUiThread(() -> {
                        Toast.makeText(RegisterActivity.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                    });
                }
            });
        });

        buttonBack.setOnClickListener(v -> {
           finish();
        });
    }
}
