package view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookshop.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import api.AApi;
import api.APIService;
import model.AddCartItemResponse;
import api.InfoShip;
import api.Login;
import api.vnpay.TypeAction;
import api.vnpay.VNPayCallBack;
import api.vnpay.VNPaySDK;
import model.Address;
import model.Book;
import request.CreateOrderRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.fragment.OrderFragment;

public class OrderBookActivity extends AppCompatActivity implements VNPayCallBack {
    FrameLayout fragment_container_order;
    Button btn_back;
    ImageButton showAddresses;
    Context context;
    TextView method_ship, price_ship, date_ship;
    final int GET_ADDRESS=123, GET_INFOSHIP =124;
    List<Book> book;
    List<Integer> quantities;
    OrderFragment orderFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_book);
        context = this;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn_back = findViewById(R.id.back);
        setClickListener();
        book = (List<Book>) getIntent().getSerializableExtra("book");
        quantities = (List<Integer>) getIntent().getSerializableExtra("quantity");
        if (savedInstanceState == null) {

            orderFragment = new OrderFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("book", (Serializable) book);
            bundle.putSerializable("quantity", (Serializable) quantities);
            orderFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_order, orderFragment).commit();
        }

        fragment_container_order = findViewById(R.id.fragment_container_order);
        showAddresses = fragment_container_order.findViewById(R.id.showAddresses);
        method_ship = fragment_container_order.findViewById(R.id.method_ship);
        price_ship = fragment_container_order.findViewById(R.id.price_ship);
        date_ship = fragment_container_order.findViewById(R.id.date_ship);
    }

    public void setClickListener() {
        btn_back.setOnClickListener(v -> {
            finish();
        });
//        showAddresses.setOnClickListener(v ->{
//            Intent intent = new Intent(context, MyAddressActivity.class);
//            startActivity(intent);
//        });
    }
    @Override
    public void action(TypeAction action) {
        String message = VNPaySDK.getMessage(action);
        if (action == TypeAction.SUCCESS) {
            List<Long> cartItemIds = addCartItems();
            if (cartItemIds != null) {
                CreateOrderRequest createOrderRequest = new CreateOrderRequest(0, orderFragment.getPriceShip(), cartItemIds);
                APIService.apiService.createOrder("Bearer "+Login.getToken(),createOrderRequest).enqueue(new Callback<AApi<Object>>() {
                    @Override
                    public void onResponse(Call<AApi<Object>> call, Response<AApi<Object>> response) {
                        if (response.body().isStatus()) {
                           Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("OrderBookActivity", "onResponse: " + response.body().getMessage());
                           finish();
                        } else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<AApi<Object>> call, Throwable t) {

                    }
                });

            } else {
                Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                finish();

            }


            
        }else{
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        }

    }
    public List<Long> addCartItems() {
        final boolean[] addCartItem = {true};
        List<Long> cartItemIds = new ArrayList<>();
        for (int i = 0; i < book.size(); i++) {
            if (addCartItem[0] == false) {
                break;
            }
            APIService.apiService.addCartItemById("Bearer " + Login.getToken(), (int) book.get(i).getId(), quantities.get(i)).enqueue(new Callback<AApi<AddCartItemResponse>>() {
                @Override
                public void onResponse(Call<AApi<AddCartItemResponse>> call, Response<AApi<AddCartItemResponse>> response) {
                    if (response.body().isStatus()) {
                        cartItemIds.add(response.body().getData().getCartId());
                    } else {
                        addCartItem[0] = false;
                    }
                }

                @Override
                public void onFailure(Call<AApi<AddCartItemResponse>> call, Throwable t) {
                    addCartItem[0] = false;
                }
            });
        }
        if (addCartItem[0]) {
            return cartItemIds;
        } else {
            return null;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GET_ADDRESS && resultCode == RESULT_OK && data != null) {
            Address address = (Address) data.getExtras().getSerializable("address");
            // Xử lý đối tượng Address ở đây

            TextView deliveryAddress = fragment_container_order.findViewById(R.id.deleveryAddress);
            deliveryAddress.setTag(address);
            deliveryAddress.setText(address.toString());
        } else if ( resultCode == RESULT_OK && data != null) {
//            Toast.makeText(this, "123123123", Toast.LENGTH_LONG).show();
            InfoShip infoShip = (InfoShip) data.getExtras().getSerializable("infoShip");
//            Toast.makeText(this, infoShip.toString(), Toast.LENGTH_LONG).show();
//
//            // Xử lý đối tượng infoShip ở đây
           TextView methodShip =(TextView) fragment_container_order.findViewById(R.id.method_ship);
            methodShip.setText(infoShip.getTEN_DICHVU());
            TextView price_ship =(TextView) fragment_container_order.findViewById(R.id.price_ship);

            TextView date_ship =(TextView) fragment_container_order.findViewById(R.id.date_ship);
            TextView fee_ship = fragment_container_order.findViewById(R.id.fee_ship);
            price_ship.setText((int)infoShip.getGIA_CUOC() + "");
            fee_ship.setText((int)infoShip.getGIA_CUOC() + "");
            date_ship.setText(infoShip.getTHOI_GIAN());
            setData();
        }
    }

    private void setData() {
        TextView price_of_products = fragment_container_order.findViewById(R.id.price_of_products);
        TextView fee_ship = fragment_container_order.findViewById(R.id.fee_ship);
        TextView total_amount = fragment_container_order.findViewById(R.id.total_amount);
        TextView total = fragment_container_order.findViewById(R.id.total);
        int price_of_product = Integer.valueOf(price_of_products.getText().toString());

        int totalP = (int)price_of_product + Integer.valueOf(fee_ship.getText().toString());
        total_amount.setText(totalP + "");
        total.setText(totalP + "");

    }


}