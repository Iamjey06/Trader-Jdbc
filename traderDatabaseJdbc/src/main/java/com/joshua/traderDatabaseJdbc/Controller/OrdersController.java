package com.joshua.traderDatabaseJdbc.Controller;

import com.joshua.traderDatabaseJdbc.Entity.Orders;
import com.joshua.traderDatabaseJdbc.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;

@Controller("ordersController")
public class OrdersController{


    @Autowired
    OrdersService ordersService;

    public void insertOrdersRecord(){

        Orders orders = new Orders();
        orders.setCustomerId("LU902HWA");
        orders.setEmployeeId("L2002EA");
        orders.setShipedDate("20240714");

        ordersService.insertOrdersRecord(orders, "KEIO261", 6.00, 3,0);

    }

    public void updateOrdersRecord(){

        Orders orders = new Orders();
        orders.setOrderId("LU90714");
        orders.setCustomerId("LU902HWA");
        orders.setEmployeeId("L2002EA");
        orders.setShipedDate("20240715");

        ordersService.updateOrderRecords(orders, "KEIO261", 6.00, 5,0);
    }

    public void deleteOrdersRecord(String orderId){
        ordersService.deleteOrdersRecord(orderId);
    }

    public void insertMultiOrderRecords(){

        List<Orders> ordersList = new LinkedList<>();
        List<Object[]> orderDetailsObjList = new LinkedList<>();

        Orders orders = new Orders();
        orders.setCustomerId("LU902HWA");
        orders.setEmployeeId("L2002EA");
        orders.setShipedDate("20240712");

        Object[] ordersOrderDetails = {"KEIO262", 10.50, 4, 0};

        Orders order1 = new Orders();
        order1.setCustomerId("RT900GWA");
        order1.setEmployeeId("B2002EA");
        order1.setShipedDate("20230714");

        Object[] order1OrderDetais = {"NOIO264", 12, 2, 0};

        Orders order2 = new Orders();
        order2.setCustomerId("SH265HAR");
        order2.setEmployeeId("P2002EA");
        order2.setShipedDate("20240714");

        Object[] order2OrderDetails = {"KEIO261", 6, 1, 0};

        ordersList.add(orders);
        ordersList.add(order1);
        ordersList.add(order2);

        orderDetailsObjList.add(ordersOrderDetails);
        orderDetailsObjList.add(order1OrderDetais);
        orderDetailsObjList.add(order2OrderDetails);

        ordersService.insertMultiOrderRecords(ordersList, orderDetailsObjList );
    }

    public void updateMultiOrderRecords(){

        List<Orders> ordersList = new LinkedList<>();
        List<Object[]> orderDetailsObjList = new LinkedList<>();

        Orders orders = new Orders();
        orders.setOrderId("RT90714");
        orders.setShipedDate("20230714");
        orders.setCustomerId("RT900GWA");
        orders.setEmployeeId("B2002EA");

        Object[] ordersDetailsObj = {"NOIO264",12 , 5, 0};

        Orders order1 = new Orders();
        order1.setOrderId("SH20714");
        order1.setShipedDate("20231218");
        order1.setCustomerId("SH265HAR");
        order1.setEmployeeId("P2002EA");

        Object[] order1DetailsObj = {"NOIO264", 12, 3, 0};

        ordersList.add(orders);
        ordersList.add(order1);

        orderDetailsObjList.add(ordersDetailsObj);
        orderDetailsObjList.add(order1DetailsObj);

        ordersService.updateMultiOrderRecords(ordersList, orderDetailsObjList);
    }

    public void findAllOrders(){

        List<Orders> ordersList = ordersService.findAllOrders();
        int count =0;

        for(Orders orders: ordersList){

            System.out.println("OrderId... "+ orders.getOrderId()+" ..ProductName... "
                +orders.getOrderDetails().getProducts().getProductName()+" ..Category... "
                +orders.getOrderDetails().getProducts().getCategories().getCategoryName()+" ..Quantity... "
                +orders.getOrderDetails().getQuantity()+" ..UnitPrice... "+orders.getOrderDetails().getUnitPrice()
                +" ..OrderDate... "+orders.getOrderDate()
                +" ..ShippedDate... "+orders.getShipedDate()+" ..CustomerId... "+orders.getCustomerId()
                +" ..CustomerName... "+orders.getCustomers().getContactName()+" ..");
            count++;
        }

        System.out.println(count+" orders returned");
    }

}
