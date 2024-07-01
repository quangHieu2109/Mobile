package api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import model.AddCartItemResponse;
import model.Address;
import model.District;
import model.OrderResponse;
import model.Province;
import model.User;
import model.Ward;
import model.Wishlist;
import request.AccuracyRequest;
import request.AddressRequest;
import request.ChangeInforRequest;
import request.ChangePasswordOTP;
import request.CreateOrderRequest;
import request.InfoShipRequest;
import request.LoginRequest;
import request.RegisterRequest;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl("http://103.118.29.65/")
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
    @GET("user/getInfor")
    Call<AApi<User>> getInfor(@Header("Authorization") String token);

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
    @POST("Order/createOrder")
    Call<AApi<Object>> createOrder(@Header("Authorization") String token, @Body CreateOrderRequest createOrderRequest);
    @POST("Cart/addCartItemPId={productId}")
    Call<AApi<AddCartItemResponse>> addCartItemById(@Header("Authorization") String token, @Path("productId") int id, @Query("quantity") int quantity);
    @POST("User/loginGoogleUser/token={token}")
    Call<AApi<String>> loginGoogle(@Path("token") String token);
    @POST("User/sendOTP/email={email}")
    Call<AApi<Object>> sendOTP(@Path("email") String email);
    @POST("User/accuracyOTP")
    Call<AApi<Object>> accuracyOTP(@Body AccuracyRequest accuracyRequest);
    @POST("User/changePasswordByOTP")
    Call<AApi<Object>> changePasswordOTP(@Body ChangePasswordOTP changePasswordOTP);
    @POST("User/changePassword")
    Call<AApi<Object>> changePassword(@Header("Authorization") String token, @Query("password") String password);
    @POST("User/changeInfor")
    Call<AApi<Object>> changeInfor(@Header("Authorization") String token, @Body ChangeInforRequest changeInforRequest);
    @POST("User/register")
    Call<AApi<Object>> register( @Body RegisterRequest registerRequest);
    @GET("Order/getOrderDetailByStatus/status={status}")
    Call<AApi<List<OrderResponse>>> getOrdersByStatus(@Header("Authorization") String token, @Path("status") int status);
    @PUT("Order/updateStatus/orderId={orderId}&status={status}")
    Call<AApi<Object>> updateOrderStatus(@Header("Authorization") String token, @Path("orderId") long orderId, @Path("status") int status);
    @GET("Order/getOrderDetailById/orderId={orderId}")
    Call<AApi<OrderResponse>> getOrdersByOrderId(@Header("Authorization") String token, @Path("orderId") long orderId);
}
