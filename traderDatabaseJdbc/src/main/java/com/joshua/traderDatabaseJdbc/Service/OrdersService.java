package com.joshua.traderDatabaseJdbc.Service;

import com.joshua.traderDatabaseJdbc.Entity.Orders;
import com.joshua.traderDatabaseJdbc.MainRepositories.OrdersDao;
import com.joshua.traderDatabaseJdbc.ResultSetExtractors.Orders.OrdersInfoExt;
import com.joshua.traderDatabaseJdbc.RowMappers.Orders.OrdersOrderDetailstMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service("ordersService")

public class OrdersService implements OrdersDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertOrdersRecord(Orders orders, String productId, double unitPrice, int quantity,
                                   double discountPerUnit) {

        String orderId = (orders.getCustomerId().substring(0,3)+
                orders.getShipedDate().substring(4)).toUpperCase();

        Object[] objArray = {orderId,orders.getShipedDate(), orders.getCustomerId(), orders.getEmployeeId()};

        String insertSql = "INSERT INTO orders(order_id, shipped_date, customer_id, employee_id)" +
                            "VALUES(?,?,?,?)";
        int rowsInserted = jdbcTemplate.update(insertSql, objArray);
        System.out.println(rowsInserted+" order(s) recorded");

        Object[] procedureObjArray = {orderId, productId, unitPrice, quantity, discountPerUnit};
        String excProcedureInsertOrderDetails = "CALL insert_into_orderDetails(?,?,?,?,?)";
        int rowsAffected = jdbcTemplate.update(excProcedureInsertOrderDetails, procedureObjArray);
        System.out.println(rowsAffected+" rows affected");
    }

    @Override
    public void updateOrderRecords(Orders orders, String productId, double unitPrice, int quantity,
                                   double discountPerUnit) {

        String orderId = (orders.getCustomerId().substring(0,3)+
                orders.getShipedDate().substring(4)).toUpperCase();

        Object[] objArray = {orderId, orders.getShipedDate(), orders.getCustomerId(),
                            orders.getEmployeeId(), orders.getOrderId()};

        String updateSql = "UPDATE orders SET order_id =?, shipped_date=?, customer_id=?," +
                            "employee_id=? " +
                            "WHERE order_id = ?";
        int rowsUpdated = jdbcTemplate.update(updateSql, objArray);
        System.out.println(rowsUpdated+" row updated");

        String getOldOrderDetails = "SELECT * FROM order_details WHERE order_id =?";
        List<Orders> ordersOrderDetails =  jdbcTemplate.query(getOldOrderDetails,
                new OrdersOrderDetailstMapper(), orderId);

        Object[] procedureObjArray = {orderId, productId, unitPrice, quantity, discountPerUnit,
                                    ordersOrderDetails.get(0).getOrderDetails().getQuantity(),
                                    ordersOrderDetails.get(0).getOrderDetails().getProductId()};
        String excProcedureUpdateOrderDetails = "call update_orderDetails(?,?,?,?,?,?,?)";
        int rowsAffected = jdbcTemplate.update(excProcedureUpdateOrderDetails, procedureObjArray);
        System.out.println(rowsAffected+" row affected in order Details");
    }

    @Override
    public void deleteOrdersRecord(String orderId) {

        String getOrderQuantitySql = "SELECT * FROM order_details WHERE order_id=?";
        List<Orders> getOrderQuantityList = jdbcTemplate.query(getOrderQuantitySql,
                new OrdersOrderDetailstMapper(), orderId);

        String productId = getOrderQuantityList.get(0).getOrderDetails().getProductId();
        int oldQuantity = getOrderQuantityList.get(0).getOrderDetails().getQuantity();

        String updateProductsSql = "UPDATE products SET units_in_stock = (units_in_stock + ?)" +
                                    "WHERE product_id =?";
        jdbcTemplate.update(updateProductsSql, oldQuantity, productId);
        

        String deleteSql = "DELETE FROM orders WHERE order_id=?";
        int rowsAffected = jdbcTemplate.update(deleteSql, orderId);
        System.out.println(rowsAffected+" order deleted.");

    }

    @Override
    public void insertMultiOrderRecords(List<Orders> ordersList, List<Object[]> ordersOrderDetailsObjList) {

        List<Object[]> ordersObjList = new LinkedList<>();
        List<Object[]> orderDetailsObjList = new LinkedList<>();

        int count=0;

        for(Orders orders: ordersList){

            String orderId = (orders.getCustomerId().substring(0,3)+
                    orders.getShipedDate().substring(4)).toUpperCase();

           Object[] ordersObjArray = {orderId, orders.getShipedDate(), orders.getCustomerId(),
                                orders.getEmployeeId()};
           Object[] orderDetailsObjArray = {orderId, Arrays.stream(ordersOrderDetailsObjList.get(count)).toList().get(0),
                   Arrays.stream(ordersOrderDetailsObjList.get(count)).toList().get(1),
                   Arrays.stream(ordersOrderDetailsObjList.get(count)).toList().get(2),
                   Arrays.stream(ordersOrderDetailsObjList.get(count)).toList().get(3)};

           ordersObjList.add(ordersObjArray);
           orderDetailsObjList.add(orderDetailsObjArray);
           count++;
        }

        String insertOrdersSql = "INSERT INTO orders(order_id, shipped_date, customer_id, employee_id)" +
                            "VALUES(?,?,?,?)";
        jdbcTemplate.batchUpdate(insertOrdersSql, ordersObjList);
        System.out.println(count+" orders recorded");

        String insertOrderDetailsSql = "call insert_into_orderDetails(?,?,?,?,?)";
        jdbcTemplate.batchUpdate(insertOrderDetailsSql, orderDetailsObjList);
        System.out.println(count+" order details recorded");

    }

    @Override
    public void updateMultiOrderRecords(List<Orders> ordersList, List<Object[]> ordersOrderDetailsObjList) {

        List<Object[]> ordersObjList = new LinkedList<>();
        List<Object[]> orderDetailsObjList = new LinkedList<>();
        int count = 0;

        for(Orders orders: ordersList){

            String getOldOrderDetailsSql = "SELECT * FROM order_details " +
                                            "WHERE order_id=?";
            List<Orders> orderDetailsList = jdbcTemplate.query(getOldOrderDetailsSql,
                    new OrdersOrderDetailstMapper(), orders.getOrderId());

            String oldProductId = orderDetailsList.get(0).getOrderDetails().getProductId();
            int oldQuantity = orderDetailsList.get(0).getOrderDetails().getQuantity();

            String orderId = (orders.getCustomerId().substring(0,3)+
                    orders.getShipedDate().substring(4)).toUpperCase();

            Object[] ordersObjArray = {orderId, orders.getShipedDate(), orders.getCustomerId(),
                                        orders.getEmployeeId(), orders.getOrderId()};
            Object[] orderDetailsObjArray = {orderId, Arrays.stream(ordersOrderDetailsObjList.get(count)).toList().get(0),
                    Arrays.stream(ordersOrderDetailsObjList.get(count)).toList().get(1),
                    Arrays.stream(ordersOrderDetailsObjList.get(count)).toList().get(2),
                    Arrays.stream(ordersOrderDetailsObjList.get(count)).toList().get(3),
                    oldQuantity, oldProductId};

            ordersObjList.add(ordersObjArray);
            orderDetailsObjList.add(orderDetailsObjArray);

            count++;

        }

        String updateOrdersSql = "UPDATE orders SET order_id=?, shipped_date=?, customer_id=?, " +
                                "employee_id=?" +
                                "WHERE order_id=?";
        jdbcTemplate.batchUpdate(updateOrdersSql, ordersObjList);
        System.out.println(count+" orders updated");

        String updateOrderDetailsSql = "CALL update_orderDetails(?,?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(updateOrderDetailsSql, orderDetailsObjList);
        System.out.println(count+" order details updated");

    }

    @Override
    public List<Orders> findAllOrders() {


        String findSql = "SELECT ord.order_id, ord.order_date, ord.shipped_date, ord.customer_id, " +
                        "cu.contact_name, od.unit_price, od.quantity, pr.product_name, ca.category_name " +
                        "FROM orders ord " +
                        "INNER JOIN customers cu " +
                        "ON ord.customer_id = cu.customer_id " +
                        "INNER JOIN order_details od " +
                        "ON ord.order_id = od.order_id " +
                        "INNER JOIN products pr " +
                        "ON od.product_id = pr.product_id " +
                        "INNER JOIN categories ca " +
                        "ON pr.category_id = ca.category_id";
        return jdbcTemplate.query(findSql, new OrdersInfoExt());
    }
}
