package loader;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Random;

import adapter.BookAdapter;
import api.AApi;
import api.APIService;
import api.BookResponse;
import api.Login;
import model.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookLoader {


    public static void loadBook(BookAdapter bookAdapter, BookType bookType, @Nullable Book book) {
        switch (bookType) {
            case SIMILAR:
                callGetBookSimilar(bookAdapter, book);
                break;
            case WISHLIST:
                callGetBookWishlist(bookAdapter);
                break;
            case PURCHASED:
                callGetBookPurchased(bookAdapter);
                break;
            case TOP_SELLER:
                callGetTopSeller(bookAdapter);
                break;
            case TOP_RELEASES:
                callGetTopRelease(bookAdapter);
                break;
            case RECOMMENDED:
                callGetRecommended(bookAdapter);
                break;
            case CHARTS:
                callGetTopCharts(bookAdapter);
                break;
        }



    }

    private static void callGetRecommended(BookAdapter bookAdapter) {
        APIService.apiService.getRecommended(1).enqueue(new Callback<AApi<List<BookResponse>>>() {
            @Override
            public void onResponse(Call<AApi<List<BookResponse>>> call, Response<AApi<List<BookResponse>>> response) {
                if (response.isSuccessful()) {
                    Log.d("BookLoader", "onResponse: " + response.code());
                    bookAdapter.setData(response.body().getData());
                } else {
                    Log.d("BookLoader", "onResponse: " + response.message());
                    bookAdapter.setData(null);
                }
            }

            @Override
            public void onFailure(Call<AApi<List<BookResponse>>> call, Throwable t) {

                Log.d("BookLoader", "onFailure: " + t.getMessage());
                bookAdapter.setData(null);
            }

        });
    }

    private static void callGetTopRelease(BookAdapter bookAdapter) {
        APIService.apiService.getTopRelease().enqueue(new Callback<AApi<List<BookResponse>>>() {
            @Override
            public void onResponse(Call<AApi<List<BookResponse>>> call, Response<AApi<List<BookResponse>>> response) {
                if (response.isSuccessful()) {
                    Log.d("BookLoader", "onResponse: " + response.code());
                    bookAdapter.setData(response.body().getData());
                } else {
                    Log.d("BookLoader", "onResponse: " + response.message());
                    bookAdapter.setData(null);
                }
            }

            @Override
            public void onFailure(Call<AApi<List<BookResponse>>> call, Throwable t) {

                Log.d("BookLoader", "onFailure: " + t.getMessage());
                bookAdapter.setData(null);
            }

        });
    }

    private static void callGetTopCharts(BookAdapter bookAdapter) {
        APIService.apiService.getTopCharts().enqueue(new Callback<AApi<List<BookResponse>>>() {
            @Override
            public void onResponse(Call<AApi<List<BookResponse>>> call, Response<AApi<List<BookResponse>>> response) {
                if (response.isSuccessful()) {
                    Log.d("BookLoader", "onResponse: " + response.code());
                    bookAdapter.setData(response.body().getData());
                } else {
                    Log.d("BookLoader", "onResponse: " + response.message());
                    bookAdapter.setData(null);
                }
            }

            @Override
            public void onFailure(Call<AApi<List<BookResponse>>> call, Throwable t) {

                Log.d("BookLoader", "onFailure: " + t.getMessage());
                bookAdapter.setData(null);
            }

        });
    }

    private static void callGetTopSeller(BookAdapter bookAdapter) {
        APIService.apiService.getTopSeller().enqueue(new Callback<AApi<List<BookResponse>>>() {
            @Override
            public void onResponse(Call<AApi<List<BookResponse>>> call, Response<AApi<List<BookResponse>>> response) {
                if (response.isSuccessful()) {
                    Log.d("BookLoader", "onResponse: " + response.code());
                    bookAdapter.setData(response.body().getData());
                } else {
                    Log.d("BookLoader", "onResponse: " + response.message());
                    bookAdapter.setData(null);
                }
            }

            @Override
            public void onFailure(Call<AApi<List<BookResponse>>> call, Throwable t) {

                Log.d("BookLoader", "onFailure: " + t.getMessage());
                bookAdapter.setData(null);
            }

        });
    }

    private static void callGetBookPurchased(BookAdapter bookAdapter) {
        APIService.apiService.getPerchased("Bearer "+Login.getToken()).enqueue(new Callback<AApi<List<BookResponse>>>() {
            @Override
            public void onResponse(Call<AApi<List<BookResponse>>> call, Response<AApi<List<BookResponse>>> response) {
                if (response.isSuccessful()) {
                    Log.d("BookLoader", "onResponse: " + response.code());
                    bookAdapter.setData(response.body().getData());
                } else {
                    Log.d("BookLoader", "onResponse: " + response.message());
                    bookAdapter.setData(null);
                }
            }

            @Override
            public void onFailure(Call<AApi<List<BookResponse>>> call, Throwable t) {

                Log.d("BookLoader", "onFailure: " + t.getMessage());
                bookAdapter.setData(null);
            }

        });
    }

    private static void callGetBookWishlist(BookAdapter bookAdapter) {
        APIService.apiService.getWishList("Bearer " + Login.getToken()).enqueue(new Callback<AApi<List<BookResponse>>>() {
            @Override
            public void onResponse(Call<AApi<List<BookResponse>>> call, Response<AApi<List<BookResponse>>> response) {
                if (response.isSuccessful()) {
                    Log.d("BookLoader", "onResponse: " + response.code());
                    bookAdapter.setData(response.body().getData());
                } else {
                    Log.d("BookLoader", "onResponse: " + response.message());
                    bookAdapter.setData(null);
                }
            }

            @Override
            public void onFailure(Call<AApi<List<BookResponse>>> call, Throwable t) {

                Log.d("BookLoader", "onFailure: " + t.getMessage());
                bookAdapter.setData(null);
            }

        });
    }

    private static void callGetBookSimilar(BookAdapter bookAdapter, Book book) {
        APIService.apiService.getSimilarBook(book.getId()).enqueue(new Callback<AApi<List<BookResponse>>>() {
            @Override
            public void onResponse(Call<AApi<List<BookResponse>>> call, Response<AApi<List<BookResponse>>> response) {
                if (response.isSuccessful()) {
                    bookAdapter.setData(response.body().getData());
                } else {
                    bookAdapter.setData(null);
                }
            }

            @Override
            public void onFailure(Call<AApi<List<BookResponse>>> call, Throwable t) {
                bookAdapter.setData(null);
            }

        });

    }
}
