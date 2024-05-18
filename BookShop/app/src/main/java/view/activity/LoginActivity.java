package view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookshop.R;

import view.fragment.FragmentSigin;

public class LoginActivity extends AppCompatActivity {
    Button btnBackMain;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
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
}