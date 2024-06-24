package view.activity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookshop.R;

import adapter.OrderItemAdapter;
import api.AApi;
import api.APIService;
import api.Login;
import api.OrderResponse;
import model.OrderStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityOrderDetail extends AppCompatActivity {
    OrderItemAdapter orderItemAdapter;
    TextView orderId, customer, orderAt, totalPrice, status;
    RecyclerView rcv_list_order_item;
    Button backOrderDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        EdgeToEdge.enable(this);

        orderItemAdapter = new OrderItemAdapter(this);
        rcv_list_order_item = findViewById(R.id.rcv_list_order_item);
        orderId = findViewById(R.id.orderId);
        customer = findViewById(R.id.customer);
        totalPrice = findViewById(R.id.totalPrice);
        orderAt = findViewById(R.id.orderAt);
        status = findViewById(R.id.status);
        backOrderDetail = findViewById(R.id.backOrderDetail);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_list_order_item.setLayoutManager(linearLayoutManager);
        rcv_list_order_item.setAdapter(orderItemAdapter);
        long orderIdItent = getIntent().getExtras().getLong("orderId");
        APIService.apiService.getOrdersByOrderId("Bearer "+ Login.getToken(), orderIdItent)
                .enqueue(new Callback<AApi<OrderResponse>>() {
                    @Override
                    public void onResponse(Call<AApi<OrderResponse>> call, Response<AApi<OrderResponse>> response) {
                        OrderResponse orderResponse = response.body().getData();
                        orderItemAdapter.setData(orderResponse.getItems());
                        orderId.setText(orderResponse.getId()+"");
                        customer.setText(orderResponse.getUser().getFullName());
                        totalPrice.setText((int)orderResponse.getTotalPrice()+"");
                        orderAt.setText(orderResponse.getCreatedAt().toString());
                        status.setText(OrderStatus.getStatus(orderResponse.getStatus()).toString());
                    }

                    @Override
                    public void onFailure(Call<AApi<OrderResponse>> call, Throwable t) {

                    }
                });
        backOrderDetail.setOnClickListener(v ->{
            finish();
        });
    }
}