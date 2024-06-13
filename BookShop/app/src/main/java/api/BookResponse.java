package api;

import java.io.Serializable;

import model.Book;

public class BookResponse implements Serializable {
    Book product;
    double rating;

    public BookResponse(Book product, int rating) {
        this.product = product;
        this.rating = rating;
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
