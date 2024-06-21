package view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookshop.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import view.fragment.FragmentSigin;

public class LoginActivity extends AppCompatActivity {
    Button btnBackMain;
    Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        context = this;
        setContentView(R.layout.activity_login);
        btnBackMain = findViewById(R.id.back);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new FragmentSigin()).addToBackStack("fragmentSigin")
                    .commit();
        }
        setOnClickBackMain();
    }

    private void setOnClickBackMain() {

        if (btnBackMain != null) {
            btnBackMain.setOnClickListener(v -> {
                if (getSupportFragmentManager().getFragments().get(0) instanceof FragmentSigin) {
                    finish();
                } else {
                    getSupportFragmentManager().popBackStack();
                }

            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Toast.makeText(this, "Welcome " , Toast.LENGTH_LONG ).show();
        // Kết quả từ luồng đăng nhập

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
//            Toast.makeText(this, "Welcome 123123" , Toast.LENGTH_LONG ).show();
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Đăng nhập thành công, hiển thị giao diện đã xác thực
            Toast.makeText(this, "Welcome " + account.getEmail(), Toast.LENGTH_SHORT).show();
        } catch (ApiException e) {
            // Đăng nhập không thành công, xử lý lỗi
            Toast toast = Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
            toast.show();

            Log.w("TAG", "signInResult:failed code=" + e);
        }
    }
}