package project.model;

import project.config.Config;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable {
    private Long productId;
    private String productName;
    private Long categoryId;
    private String description;
    private double unitPrice;
    private int stock;
    private boolean status;

    public Product(String productName, Long categoryId, String description, double unitPrice, int stock, boolean status) {
        this.productId = Config.RandomId();
        this.productName = productName;
        this.categoryId = categoryId;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.status = status;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getStock() {
        return stock;
    }
    public boolean isStatus() {
        return status;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "productId: " + productId +
                "\n, productName: '" + productName +
                "\n, categoryId: " + categoryId +
                "\n, description: '" + description +
                "\n, unitPrice: " + Config.formatVnd().format(unitPrice) + "vnd" +
                "\n, stock: " + stock +
                "\n, status=" + status + "\n";
    }
}
