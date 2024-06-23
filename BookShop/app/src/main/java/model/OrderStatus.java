package model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class OrderStatus {
    private int status;
    private String statusName;

    public OrderStatus(int status, String statusName) {
        this.status = status;
        this.statusName = statusName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    public static OrderStatus getStatus(int status){
        OrderStatus  result = null;
        switch (status){
            case 0:
                result=(new OrderStatus(0, "Order Success"));

                break;
            case 1:
                result=(new OrderStatus(1, "Are delivering"));

                break;
            case 2:
                result=(new OrderStatus(2, "Successful delivery"));

                break;
            case 3:
                result=(new OrderStatus(3, "Order canceled"));
                break;
            case 4:
                result=(new OrderStatus(4, "Return the order"));
                break;
        }
        return result ;
    }
    public static List<OrderStatus> generatedOrderStatus(int status){
        List<OrderStatus> result = new ArrayList<>();
        switch (status){
            case 0:
                result.add(new OrderStatus(0, "Order Success"));
                result.add(new OrderStatus(1, "Are delivering"));
                result.add(new OrderStatus(3, "Order canceled"));
                break;
            case 1:
                result.add(new OrderStatus(1, "Are delivering"));
                result.add(new OrderStatus(2, "Successful delivery"));
                result.add(new OrderStatus(3, "Order canceled"));
                break;
            case 2:
                result.add(new OrderStatus(2, "Successful delivery"));
                result.add(new OrderStatus(4, "Return the order"));
                break;
            case 3:
                result.add(new OrderStatus(3, "Order canceled"));
                break;
            case 4:
                result.add(new OrderStatus(4, "Return the order"));
                break;
        }
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return this.statusName;
    }
}
