package com.joshua.traderDatabaseJdbc.RowMappers.Products;

import com.joshua.traderDatabaseJdbc.Entity.Products;
import com.joshua.traderDatabaseJdbc.Entity.Suppliers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductsSupplierMapper implements RowMapper<Products> {
    @Override
    public Products mapRow(ResultSet rs, int rowNum) throws SQLException {

        Suppliers suppliers= new Suppliers();
        suppliers.setSupplierId(rs.getString("supplier_id"));

        Products products = new Products();
        products.setSuppliers(suppliers);

        return products;
    }
}
