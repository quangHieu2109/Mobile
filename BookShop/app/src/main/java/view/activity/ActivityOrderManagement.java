package view.activity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.databinding.ActivityOrderManagementBinding;

import com.example.bookshop.R;

import adapter.OrderAdapter;
import loader.OrderLoader;
import view.fragment.FragmentAccount;
import view.fragment.FragmentDiscover;
import view.fragment.FragmentHome;
import view.fragment.FragmentWishlist;

public class ActivityOrderManagement extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    OrderAdapter orderAdapter;
    RecyclerView list_order;
    Button backOrderManegement;
    TextView inforManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);
        EdgeToEdge.enable(this);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        orderAdapter = new OrderAdapter(this);
        list_order = findViewById(R.id.rcv_list_order);
        backOrderManegement = findViewById(R.id.backOrderManegement);
        inforManagement = findViewById(R.id.inforManagement);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        list_order.setLayoutManager(linearLayoutManager);
        list_order.setAdapter(orderAdapter);
        OrderLoader.loadOrder(orderAdapter, 0);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.status0) {
                inforManagement.setText("Order success");
                OrderLoader.loadOrder(orderAdapter, 0);
            }
            if (item.getItemId() == R.id.status1) {
                inforManagement.setText("Are delivering");
                OrderLoader.loadOrder(orderAdapter, 1);            }
            if (item.getItemId() == R.id.status2) {
                inforManagement.setText("Successful delivery");
                OrderLoader.loadOrder(orderAdapter, 2);            }
            if (item.getItemId() == R.id.status3) {
                inforManagement.setText("Order canceled");
                OrderLoader.loadOrder(orderAdapter, 3);            }
            if (item.getItemId() == R.id.status4) {
                inforManagement.setText("Return the order");
                OrderLoader.loadOrder(orderAdapter, 4);            }
//
//
            return true;
        });

    setOnclickListener();
    }
    private void setOnclickListener(){
        backOrderManegement.setOnClickListener(v ->{
            finish();
        });
    }

}