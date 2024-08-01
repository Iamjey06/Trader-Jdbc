package com.joshua.traderDatabaseJdbc.RowMappers.Products;

import com.joshua.traderDatabaseJdbc.Entity.Categories;
import com.joshua.traderDatabaseJdbc.Entity.Products;
import com.joshua.traderDatabaseJdbc.Entity.Suppliers;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductsCategoryMapper implements RowMapper<Products> {


    @Override
    public Products mapRow(ResultSet rs, int rowNum) throws SQLException {

        Categories categories = new Categories();
        categories.setCategoryId(rs.getInt("category_id"));

        Products products = new Products();
        products.setCategories(categories);
        return products;
    }
}
