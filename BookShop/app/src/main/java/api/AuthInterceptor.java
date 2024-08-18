package api;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class AuthInterceptor implements Interceptor {
    private Chain chainGloble;
    private Request request;
    private boolean success = false;

    @Override
    public Response intercept(Chain chain) throws IOException {
        request = chain.request();
        chainGloble = chain;
        Response response = chain.proceed(request);
        if (response.code() == 401) { // Access token hết hạn
            synchronized (this) {
                // Gọi method refresh token
                if (Login.getRefreshToken().length() > 0) {

                    final CountDownLatch latch = new CountDownLatch(1);
                    APIService.apiService.refreshTOken(Login.getRefreshToken()).enqueue(new Callback<Login>() {
                        @Override
                        public void onResponse(Call<Login> call, retrofit2.Response<Login> response) {

                                Login.setToken(response.body().getData().getAccessToken());
                                Login.setRefreshToken(response.body().getData().getRefreshToken());
                                success = true;
                                // Thực hiện các bước tiếp theo

                            latch.countDown();
                        }

                        @Override
                        public void onFailure(Call<Login> call, Throwable t) {
                            // Xử lý lỗi
                            latch.countDown();
                        }
                    });

                    try {
                        latch.await(); // Đợi cho đến khi latch đếm về 0
                        if (success) {
                            Log.d("SUCCESS", chain.request().header("Authorization"));
                            Request originalRequest = chain.request();
                            String token = Login.getToken(); // Giả sử bạn có phương thức này để lấy token

                            Request newRequest = originalRequest.newBuilder()
                                    .header("Authorization", "Bearer " + token)
                                    .build();

                            return chain.proceed(newRequest);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
// Thực hiện các bước tiếp theo
                }
            }
        }

        return response;
    }
}
