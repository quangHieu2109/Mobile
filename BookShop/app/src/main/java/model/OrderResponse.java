package model;

import java.sql.Timestamp;
import java.util.List;

public class OrderResponse {
    private long id;
    private User user;
    private int status;
    private int deliveryMethod;
    private double deliveryPrice;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<OrderItemResponse> items;

    public OrderResponse(long id, User user, int status, int deliveryMethod, double deliveryPrice, Timestamp createdAt, Timestamp updatedAt, List<OrderItemResponse> items) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.deliveryMethod = deliveryMethod;
        this.deliveryPrice = deliveryPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(int deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
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

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }
    public double getTotalPrice(){
        double result =0;
        for(OrderItemResponse orderItemResponse: this.items){
            result += orderItemResponse.getPrice();
        }

        return result;
    }
}
