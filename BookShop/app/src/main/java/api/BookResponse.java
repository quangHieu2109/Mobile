package api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import model.Book;

public class BookResponse implements Serializable {
    Book product;
    double rating;
    @SerializedName("wishlist")
    boolean isWishlist;

    public BookResponse(Book product, int rating, boolean isWishlist) {
        this.product = product;
        this.rating = rating;
        this.isWishlist = isWishlist;
    }
    public void setWishlist(boolean wishlist) {
        isWishlist = wishlist;
    }
    public boolean isWishlist() {
        return isWishlist;
    }
    public Book getProduct() {
        return product;
    }

    public void setProduct(Book product) {
        this.product = product;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
