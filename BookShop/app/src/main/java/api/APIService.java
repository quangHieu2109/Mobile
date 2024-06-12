package api;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    APIService apiService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5276/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService.class);
    @POST("login")
    Call<Login> login(@Body LoginRequest loginRequest);
}
