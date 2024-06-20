package request;

import java.util.List;

public class CreateOrderRequest {
    private int deliveryMethod;
    private long deliveryPrice;
    private List<Long> cartItemIds;

    public CreateOrderRequest(int deliveryMethod, long deliveryPrice, List<Long> cartItemIds) {
        this.deliveryMethod = deliveryMethod;
        this.deliveryPrice = deliveryPrice;
        this.cartItemIds = cartItemIds;
    }

    public int getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(int deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public long getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(long deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public List<Long> getCartItemIds() {
        return cartItemIds;
    }

    public void setCartItemIds(List<Long> cartItemIds) {
        this.cartItemIds = cartItemIds;
    }
}
