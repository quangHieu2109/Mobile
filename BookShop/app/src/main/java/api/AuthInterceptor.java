package api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response.code() == 401) { // Access token hết hạn
            synchronized (this) {
                // Gọi method refresh token
           if(Login.getRefreshToken().length() >0){
              retrofit2.Response<Login> refreshRespone = APIService.apiService.refreshTOken(Login.getRefreshToken()).execute();
              if(refreshRespone.isSuccessful()){
                  Login.setToken(refreshRespone.body().getData().getAccessToken());
                  return chain.proceed(request);
              }else{
                  return response;
              }
           }
           }
        }

        return response;
    }
}
