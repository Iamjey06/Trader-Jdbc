package com.joshua.traderDatabaseJdbc.ResultSetExtractors.Products;

import com.joshua.traderDatabaseJdbc.Entity.Categories;
import com.joshua.traderDatabaseJdbc.Entity.Products;
import com.joshua.traderDatabaseJdbc.Entity.Suppliers;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductsSupplierCategoryExt implements ResultSetExtractor<List<Products>> {
    @Override
    public List<Products> extractData(ResultSet rs) throws SQLException, DataAccessException {

        List<Products> productsList = new LinkedList<>();

        while(rs.next()){

            Suppliers suppliers = new Suppliers();
            suppliers.setCompanyName(rs.getString("company_name"));
            suppliers.setContactName(rs.getString("contact_name"));
            suppliers.setPhone(rs.getString("phone"));

            Categories categories = new Categories();
            categories.setCategoryName(rs.getString("category_name"));

            Products products = new Products();
            products.setProductsId(rs.getString("product_id"));
            products.setProductName(rs.getString("product_name"));
            products.setUnitPrice((rs.getDouble("unit_price")));
            products.setUnitsInStock(rs.getInt("units_in_stock"));
            products.setSuppliers(suppliers);
            products.setCategories(categories);

            productsList.add(products);
        }
        return productsList;
    }
}
