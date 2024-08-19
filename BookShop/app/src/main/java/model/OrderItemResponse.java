package model;

import java.sql.Timestamp;

import model.Book;

public class OrderItemResponse {
    private long id;
    private long orderId;
    private Book product;
    private double price;
    private int discount;
    private int quantity;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public OrderItemResponse(long id, long orderId, Book product, double price, int discount, int quantity, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.orderId = orderId;
        this.product = product;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Book getProduct() {
        return product;
    }

    public void setProduct(Book product) {
        this.product = product;
    }

    public double getPrice() {
        return quantity * (1 - discount/100)* product.getPrice();

    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
