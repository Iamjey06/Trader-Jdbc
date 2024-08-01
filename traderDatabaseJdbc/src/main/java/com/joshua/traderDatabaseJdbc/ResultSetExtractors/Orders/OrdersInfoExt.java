package com.joshua.traderDatabaseJdbc.ResultSetExtractors.Orders;

import com.joshua.traderDatabaseJdbc.Entity.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrdersInfoExt implements ResultSetExtractor<List<Orders>> {
    @Override
    public List<Orders> extractData(ResultSet rs) throws SQLException, DataAccessException {

        List<Orders> orderInfoList = new LinkedList<>();

        while(rs.next()){

            Categories categories = new Categories();
            categories.setCategoryName(rs.getString("category_name"));

            Products products = new Products();
            products.setProductName(rs.getString("product_name"));
            products.setCategories(categories);

            Customers customers = new Customers();
            customers.setContactName(rs.getString("contact_name"));

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setQuantity(rs.getInt("quantity"));
            orderDetails.setUnitPrice(rs.getDouble("unit_price"));
            orderDetails.setProducts(products);

            Orders orders = new Orders();
            orders.setOrderId(rs.getString("order_id"));
            orders.setOrderDate(rs.getString("order_date"));
            orders.setShipedDate(rs.getString("shipped_date"));
            orders.setCustomerId(rs.getString("customer_id"));
            orders.setCustomers(customers);
            orders.setOrderDetails(orderDetails);

            orderInfoList.add(orders);
        }
        return orderInfoList;
    }
}
