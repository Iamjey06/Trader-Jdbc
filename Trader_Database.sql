CREATE DATABASE trader_db;
USE trader_db;

CREATE TABLE city(
			city_id CHAR(3) GENERATED ALWAYS AS (UPPER(SUBSTRING(city_name, 1, 3))) STORED,
			city_name VARCHAR(20) NOT NULL,
            PRIMARY KEY(city_id)
            );
            
DROP TABLE city;

CREATE TABLE employees(
			employee_id VARCHAR(8) GENERATED ALWAYS AS (UPPER(CONCAT(SUBSTRING(first_name, 1, 1), 
				YEAR(birth_date), SUBSTRING(job_title, 3,2)))) STORED,
				last_name VARCHAR(20) NOT NULL,
				first_name VARCHAR(20) NOT NULL,
				job_title VARCHAR(20) NOT NULL,
				birth_date DATE,
				hire_date DATE,
				city_id CHAR(3),
				PRIMARY KEY(employee_id),
                FOREIGN KEY(city_id) REFERENCES city(city_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE
			);
            
DROP TABLE employees;

CREATE TABLE customers(
			customer_id VARCHAR(8),
			company_name VARCHAR(20) NOT NULL DEFAULT "no company name",
			contact_name VARCHAR(20) NOT NULL,
			address VARCHAR(20),
			phone VARCHAR(10),
			city_id CHAR(3),
			PRIMARY KEY(customer_id),
			FOREIGN KEY(city_id) REFERENCES city(city_id)
            ON UPDATE CASCADE
            ON DELETE CASCADE
			);
         
DROP TABLE customers;

CREATE TABLE suppliers(
			supplier_id VARCHAR(8),
			company_name VARCHAR(20) NOT NULL DEFAULT "no compmany name",
			contact_name VARCHAR(20) NOT NULL,
			address VARCHAR(20),
			phone VARCHAR(10) CHECK (LENGTH(phone) = 10),
			city_id CHAR(3) NOT NULL,
			PRIMARY KEY(supplier_id),
			FOREIGN KEY(city_id) REFERENCES city(city_id) 
            ON UPDATE CASCADE
            ON DELETE CASCADE
			);
            
DROP TABLE suppliers;


CREATE TABLE categories(
			category_id INT AUTO_INCREMENT,
			category_name VARCHAR(20),
			PRIMARY KEY(category_id));

DROP TABLE categories;


 
CREATE TABLE products(
				product_id CHAR(8),
				product_name VARCHAR(20) NOT NULL,
				unit_price DECIMAL(10,2) NOT NULL,
				units_in_stock INT,
				supplier_id VARCHAR(8),
				category_id INT,
				PRIMARY KEY(product_id),
				FOREIGN KEY(category_id) REFERENCES categories(category_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE,
                FOREIGN KEY(supplier_id) REFERENCES suppliers(supplier_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE
				);      
                
                SHOW CREATE TABLE suppliers;
DROP TABLE product;

USE TRADER_DB;

CREATE TABLE orders(
				order_id VARCHAR(8),
				order_date TIMESTAMP DEFAULT NOW(),
				shipped_date DATE,
				customer_id VARCHAR(8),
				employee_id VARCHAR(8),
				PRIMARY KEY(order_id),
				FOREIGN KEY(customer_id) REFERENCES customers(customer_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE,
				FOREIGN KEY(employee_id) REFERENCES employees(employee_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE
				);

                
CREATE TABLE order_details(
				order_id VARCHAR(8),
				unit_price DECIMAL(10,2),
				quantity INT,
				discount_per_unit DECIMAL(4,2),
				product_id VARCHAR(8),
				PRIMARY KEY(order_id),
                FOREIGN KEY(order_id) REFERENCES orders(order_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE,
				FOREIGN KEY(product_id) REFERENCES products(product_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE
				);
                
drop table order_details;



DELIMITER $$
CREATE TRIGGER update_products_on_update_suppliers
BEFORE UPDATE
ON suppliers
FOR EACH ROW
BEGIN
		UPDATE products
		SET supplier_id = NEW.supplier_id
        WHERE supplier_id = OLD.supplier_id;
END $$
DELIMITER ;

DROP TRIGGER update_products_on_update_suppliers;
            

DELIMITER $$
CREATE PROCEDURE insert_into_orderDetails( orders_id VARCHAR(8), products_id VARCHAR(8) , price DECIMAL(10,2),
											units_sold INT, item_discount DECIMAL(5,2))
BEGIN

	INSERT INTO order_details(order_id, unit_price, quantity, discount_per_unit, product_id)
    VALUES(orders_id, price, units_sold, item_discount, products_id);
	
    UPDATE products
    SET units_in_stock = units_in_stock - units_sold
    WHERE product_id = products_id;
END $$
DELIMITER ;

drop procedure insert_into_orderDetails;


DELIMITER $$
CREATE PROCEDURE update_orderDetails( orders_id VARCHAR(8), products_id VARCHAR(8) , price DECIMAL(10,2),
											units_sold INT, item_discount DECIMAL(5,2), old_quantity INT,
                                            old_product_id VARCHAR(8))
BEGIN 

	UPDATE products SET units_in_stock = (units_in_stock + old_quantity)
	WHERE product_id= old_product_id;

	UPDATE order_details
    SET unit_price= price, quantity = units_sold, discount_per_unit = item_discount,
		product_id = products_id
	WHERE order_id = orders_id;
    
    UPDATE products SET units_in_stock = (units_in_stock - units_sold)
    WHERE product_id = products_id;
END $$
DELIMITER ;

drop procedure update_orderDetails;
	
use trader_db;