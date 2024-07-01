package view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import api.AApi;
import api.APIService;
import api.Login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.fragment.FragmentAccount;

import com.example.bookshop.R;

public class ChangepasswordActivity extends AppCompatActivity {
    EditText newPass, newPass2;
    Button buttonChangePass, buttonBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Initialize views
        newPass = findViewById(R.id.newPassword);
        newPass2 = findViewById(R.id.newPassword2);
        buttonChangePass = findViewById(R.id.btn_change_pass);
        buttonBack = findViewById(R.id.btn_back);

        // Handle change password button click
        buttonChangePass.setOnClickListener(v -> {
            String newPassword = newPass.getText().toString();
            String newPassword2 = newPass2.getText().toString();

            if (newPassword.equals(newPassword2)) {
                String accessToken = "Bearer " + Login.getToken();
                APIService.apiService.changePassword(accessToken, newPassword).enqueue(new Callback<AApi<Object>>() {
                    @Override
                    public void onResponse(Call<AApi<Object>> call, Response<AApi<Object>> response) {
                            runOnUiThread(() -> {
                                Toast.makeText(ChangepasswordActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            });
                    }

                    @Override
                    public void onFailure(Call<AApi<Object>> call, Throwable t) {
                        runOnUiThread(() -> {
                            Toast.makeText(ChangepasswordActivity.this, "Thay đổi mật khẩu thất bại: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }
                });

            } else {
                Toast.makeText(ChangepasswordActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            }
        });


        buttonBack.setOnClickListener(v -> {
            finish();
        });
    }
}
