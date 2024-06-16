package view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookshop.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import api.vnpay.TypeAction;
import api.vnpay.VNPayCallBack;
import api.vnpay.VNPaySDK;
import model.Book;
import view.fragment.FragmentHome;
import view.fragment.OrderFragment;

public class OrderBookActivity extends AppCompatActivity implements VNPayCallBack {
    FrameLayout fragment_container_order;
    Button btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<Book> book = (List<Book>) getIntent().getSerializableExtra("book");
        List<Integer> quantities = (List<Integer>) getIntent().getSerializableExtra("quantity");
        fragment_container_order = findViewById(R.id.fragment_container_order);
        btn_back = findViewById(R.id.back);
        setClickListener();
        if (savedInstanceState == null) {

            OrderFragment orderFragment = new OrderFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("book", (Serializable) book);
            bundle.putSerializable("quantity", (Serializable) quantities);
            orderFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_order, orderFragment).commit();


        }
    }

    public void setClickListener() {
        btn_back.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void action(TypeAction action) {
        String message = VNPaySDK.getMessage(action);
        if (action == TypeAction.SUCCESS) {
            
        }else{
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}