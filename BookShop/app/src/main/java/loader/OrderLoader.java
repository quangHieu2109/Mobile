package loader;

import java.util.List;

import adapter.OrderAdapter;
import api.AApi;
import api.APIService;
import api.Login;
import api.OrderResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderLoader {
    public static void loadOrder(OrderAdapter adapter, int status){
        APIService.apiService.getOrdersByStatus("Bearer "+ Login.getToken(), status).enqueue(new Callback<AApi<List<OrderResponse>>>() {
            @Override
            public void onResponse(Call<AApi<List<OrderResponse>>> call, Response<AApi<List<OrderResponse>>> response) {
                adapter.setData(response.body().getData());
            }

            @Override
            public void onFailure(Call<AApi<List<OrderResponse>>> call, Throwable t) {

            }
        });
    }
}
