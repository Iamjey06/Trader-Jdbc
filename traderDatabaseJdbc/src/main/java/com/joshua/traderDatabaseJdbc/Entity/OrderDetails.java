package com.joshua.traderDatabaseJdbc.Entity;

import org.springframework.stereotype.Component;

@Component("orderDetails")
public class OrderDetails {

    private String orderId;
    private double unitPrice;

    private int quantity;
    private double discountPerUnit;
    private String productId;

    private Products products;

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscountPerUnit() {
        return discountPerUnit;
    }

    public void setDiscountPerUnit(double discountPerUnit) {
        this.discountPerUnit = discountPerUnit;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId='" + orderId + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", discountPerUnit=" + discountPerUnit +
                ", productId='" + productId + '\'' +
                '}';
    }
}
