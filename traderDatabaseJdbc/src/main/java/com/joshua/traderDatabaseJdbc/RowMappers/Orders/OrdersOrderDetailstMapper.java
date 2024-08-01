package com.joshua.traderDatabaseJdbc.RowMappers.Orders;

import com.joshua.traderDatabaseJdbc.Entity.OrderDetails;
import com.joshua.traderDatabaseJdbc.Entity.Orders;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersOrderDetailstMapper implements RowMapper<Orders>{
    @Override
    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {


        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(rs.getString("order_id"));
        orderDetails.setUnitPrice(rs.getDouble("unit_price"));
        orderDetails.setQuantity(rs.getInt("quantity"));
        orderDetails.setDiscountPerUnit(rs.getDouble("discount_per_unit"));
        orderDetails.setProductId(rs.getString("product_id"));

        Orders orders = new Orders();
        orders.setOrderDetails(orderDetails);

        return orders;
    }
}
