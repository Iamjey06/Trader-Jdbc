package com.joshua.traderDatabaseJdbc.Entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component("orders")
public class Orders {

    private String orderId;
    private String orderDate;
    private String shipedDate;
    private String customerId;
    private String employeeId;

    private OrderDetails orderDetails;

    private Customers customers;

    private Employees employees;

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipedDate() {
        return shipedDate;
    }

    public void setShipedDate(String shipedDate) {
        this.shipedDate = shipedDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", shipedDate=" + shipedDate +
                ", customerId='" + customerId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}
