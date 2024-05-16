package view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookshop.R;

public class MainApp extends AppCompatActivity {
    Button btnLoginByGoogle,btnRegister,btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_app);
        btnLogin = findViewById(R.id.login);
        setOnClickLogin();
    }
    public void setOnClickLogin(){
        if(btnLogin != null){
            btnLogin.setOnClickListener(v -> {
                startActivity(new Intent(this, LoginActivity.class));
            });
        }
    }
}