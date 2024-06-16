package view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshop.R;

public class MainApp extends AppCompatActivity {
    Button btnLoginByGoogle,btnRegister,btnLogin, btnGetStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_app);
        btnLogin = findViewById(R.id.login);
        btnGetStarted = findViewById(R.id.getStarted);
        setOnClickLogin();


    }
    public void setOnClickLogin(){
        if(btnLogin != null){
            btnLogin.setOnClickListener(v -> {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            });
        }

        if(btnGetStarted != null){
            btnGetStarted.setOnClickListener(v -> {
                Intent intent = new Intent(this, MyAddressActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            });
        }
    }
}