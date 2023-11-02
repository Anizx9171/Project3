package project.model;

import project.config.Config;

import java.io.Serializable;

public class Order implements Serializable {
    private Long orderId;
    private Long userId;
    private Long productId;
    private String userName;
    private String productName;
    private String phoneNumber;
    private String address;
    private int quantity;
    private double total;

    private StatusOrder orderStatus = StatusOrder.WAITING;

    public Order(Long userId, Long productId, String userName, String productName, String phoneNumber, String address, double total, int quantity) {
        this.orderId = Config.RandomId();
        this.userId = userId;
        this.productId = productId;
        this.userName = userName;
        this.productName = productName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.total = total;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public StatusOrder getOrderStatus() {
        return orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getUserName() {
        return userName;
    }

    public String getProductName() {
        return productName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public double getTotal() {
        return total;
    }

    public void setOrderStatus(StatusOrder orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "orderId: " + orderId +
                ", \nuserId: " + userId +
                ", \nproductId: " + productId +
                ", \nuserName: " + userName +
                ", \nproductName: " + productName +
                ", \nquantity: " + quantity +
                ", \nphoneNumber: " + phoneNumber +
                ", \naddress: " + address +
                ", \ntotal: " + Config.formatVnd().format(total) + "đ" +
                ", \norderStatus: " + orderStatus + "\n";
    }
}