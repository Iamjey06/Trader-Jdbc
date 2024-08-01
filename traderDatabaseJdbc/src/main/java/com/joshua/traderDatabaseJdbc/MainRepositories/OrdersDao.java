package com.joshua.traderDatabaseJdbc.MainRepositories;

import com.joshua.traderDatabaseJdbc.Entity.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao {

    void insertOrdersRecord(Orders orders, String productId, double unit_price,
                            int quantity, double discountPerUnit);

    void updateOrderRecords(Orders orders, String productId, double unitPrice,
                            int quantity, double discountPerUnit);

    void deleteOrdersRecord(String orderId);

    void insertMultiOrderRecords(List<Orders> ordersList, List<Object[]> ordersOrderDetailsObjList);

    void updateMultiOrderRecords(List<Orders> ordersList, List<Object[]> ordersOrderDetailsObjList);

    List<Orders> findAllOrders();


}
