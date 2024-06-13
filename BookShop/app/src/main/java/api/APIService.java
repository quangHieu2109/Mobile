package api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import model.Book;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5276/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);
    @POST("user/login")
    Call<Login> login(@Body LoginRequest loginRequest);

    @GET("product/getSimilarProduct")
    Call<List<BookResponse>> getSimilarBook(@Query("productId") long id);

    @GET("product/getAllProduct")
    Call<List<BookResponse>> getAllBook();

    @POST("product/addWishList/productId={productId}")
    Call<AApi<Wishlist>> addWishList(@Header("Authorization") String token, @Path("productId") int id);

    @GET("product/getProductByName")
    Call<List<BookResponse>> searchBook(@Query("name") String name);

    @GET("product/getWishList")
    Call<List<BookResponse>> getWishList(@Header("Authorization") String token);

    @GET("Product/getPerchased")
    Call<List<BookResponse>> getPerchased(@Header("Authorization") String token);
    @GET("product/getTopSell")
    Call<List<BookResponse>> getTopSeller();
    @GET("product/getByOrderRating")
    Call<List<BookResponse>> getTopCharts();
    @GET("product/getReleases")
    Call<List<BookResponse>> getTopRelease();
    @GET("product/getRecommendByOrderRating/productId={productId}")
    Call<List<BookResponse>> getRecommended(@Path("productId") long id);
}
