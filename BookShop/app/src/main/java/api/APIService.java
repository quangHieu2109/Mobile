package api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import model.Address;
import model.District;
import model.Province;
import model.Ward;
import model.Wishlist;
import request.AddressRequest;
import request.InfoShipRequest;
import request.LoginRequest;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    APIService viettheoAPI = new Retrofit.Builder()
            .baseUrl("https://partner.viettelpost.vn/v2/categories/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);
    APIService getInfoShip = new Retrofit.Builder()
            .baseUrl("https://partner.viettelpost.vn/v2/order/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);
    @POST("user/login")
    Call<Login> login(@Body LoginRequest loginRequest);

    @GET("product/getSimilarProduct")
    Call<AApi<List<BookResponse>>> getSimilarBook(@Query("productId") long id);

    @GET("product/getAllProduct")
    Call<AApi<List<BookResponse>>> getAllBook();

    @POST("product/addWishList/productId={productId}")
    Call<AApi<Wishlist>> addWishList(@Header("Authorization") String token, @Path("productId") int id);

    @GET("product/getProductByName")
    Call<AApi<List<BookResponse>>> searchBook(@Query("name") String name);

    @GET("product/getWishList")
    Call<AApi<List<BookResponse>>> getWishList(@Header("Authorization") String token);

    @GET("Product/getPerchased")
    Call<AApi<List<BookResponse>>> getPerchased(@Header("Authorization") String token);
    @GET("product/getTopSell")
    Call<AApi<List<BookResponse>>> getTopSeller();
    @GET("product/getByOrderRating")
    Call<AApi<List<BookResponse>>> getTopCharts();
    @GET("product/getReleases")
    Call<AApi<List<BookResponse>>> getTopRelease();
    @GET("product/getRecommendByOrderRating/productId={productId}")
    Call<AApi<List<BookResponse>>> getRecommended(@Path("productId") long id);
    @DELETE("Product/deleteWishList/productId={productId}")
    Call<AApi<String>> deleteWishList(@Header("Authorization") String token, @Path("productId") int id);
    @GET("Product/getById/productId={productId}")
    Call<AApi<BookResponse>> getBookById(@Header("Authorization") String token,@Path("productId") int id);
    @GET("listProvinceById?provinceId=")
    Call<ViettheoResponse<Province>> getAllProvince(@Header("token") String token);
    @GET("listProvinceById")
    Call<ViettheoResponse<Province>> getProvince(@Header("token") String token, @Query("provinceId") int id);
    @GET("listDistrict")
    Call<ViettheoResponse<District>> getDistrict(@Header("token") String token, @Query("provinceId") int id);
    @GET("listWards")
    Call<ViettheoResponse<Ward>> getWard(@Header("token") String token, @Query("districtId") int id);
    @GET("Address/getAddress")
    Call<AApi<List<Address>>> getAddress(@Header("Authorization") String token);

    @POST("Address/addAddress")
    Call<AApi<Address>> addAddress(@Header("Authorization") String token, @Body AddressRequest addressRequest);
    @POST("getPriceAll")
    Call<List<InfoShip>> getInfoShip(@Header("token") String token, @Body InfoShipRequest infoShipRequest);
}
