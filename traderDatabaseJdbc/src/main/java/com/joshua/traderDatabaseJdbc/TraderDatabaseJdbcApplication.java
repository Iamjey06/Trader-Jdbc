package com.joshua.traderDatabaseJdbc;

import com.joshua.traderDatabaseJdbc.Controller.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TraderDatabaseJdbcApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TraderDatabaseJdbcApplication.class, args);

		CityController cityController = context.getBean(CityController.class);
		CustomerController customerController = context.getBean(CustomerController.class);
		EmployeeController employeeController = context.getBean(EmployeeController.class);
		CategoriesController categoriesController= context.getBean(CategoriesController.class);
		ProductsController productsController= context.getBean(ProductsController.class);
		SupplierController supplierController = context.getBean(SupplierController.class);
		OrdersController ordersController = context.getBean(OrdersController.class);

		//cityController.insertCityRecord();

		//customerController.insertMultiCustomerRecords();

		//employeeController.insertMultiEmployeeRecords();

		//categoriesController.insertCategoryRecord();

		//productsController.insertMultiProductsRecords();

		//supplierController.findSupplierByCompanyName("HighGoal");

		ordersController.findAllOrders();
	}

}
