package com.joshua.traderDatabaseJdbc.Service;

import com.joshua.traderDatabaseJdbc.Entity.Categories;
import com.joshua.traderDatabaseJdbc.Entity.Products;
import com.joshua.traderDatabaseJdbc.Entity.Suppliers;
import com.joshua.traderDatabaseJdbc.MainRepositories.ProductsDao;
import com.joshua.traderDatabaseJdbc.ResultSetExtractors.Products.ProductsSupplierCategoryExt;
import com.joshua.traderDatabaseJdbc.RowMappers.Products.ProductsCategoryMapper;
import com.joshua.traderDatabaseJdbc.RowMappers.Products.ProductsSupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("productsService")
public class ProductsService implements ProductsDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertProductRecord(Products products) {

        String getSupplierIdSql= "SELECT supplier_id FROM suppliers " +
                                    "WHERE contact_name=?";
        List<Products> productSupplierList = jdbcTemplate.query(getSupplierIdSql,
                new ProductsSupplierMapper(), products.getSupplierName());
        String supplierId = productSupplierList.get(0).getSuppliers().getSupplierId();

        String getCategoryIdSql= "SELECT category_id FROM categories " +
                                    "WHERE category_name=?";
        List<Products> productCategoryList= jdbcTemplate.query(getCategoryIdSql,
                new ProductsCategoryMapper(), products.getCategoryName());
        int categoryId = productCategoryList.get(0).getCategories().getCategoryId();

        String productId = (products.getProductName().substring(products.getProductName().length()-2,
                products.getProductName().length())+ supplierId.substring(0,4) + categoryId).toUpperCase();

        Object[] insertProductsObj = {productId, products.getProductName(), products.getUnitPrice(),
                products.getUnitsInStock(), supplierId, categoryId};

       String insertSql = "INSERT INTO products(product_id, product_name, unit_price," +
               "units_in_stock, supplier_id, category_id) " +
               "VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(insertSql, insertProductsObj);
        System.out.println("Product record inserted");
    }

    @Override
    public void updateProductRecord(Products products) {

        String getSupplierIdSql= "SELECT supplier_id FROM suppliers " +
                                "WHERE contact_name=?";
        List<Products> productsSupplierList=jdbcTemplate.query(getSupplierIdSql,
                        new ProductsSupplierMapper(), products.getSupplierName());

        String getCategoryIdSql = "SELECT category_id FROM categories " +
                                "WHERE category_name=?";
        List<Products> productsCategoryList= jdbcTemplate.query(getCategoryIdSql,
                        new ProductsCategoryMapper(), products.getCategoryName());

        Object[] updateObj = {products.getProductName(), products.getUnitPrice(), products.getUnitsInStock(),
                productsSupplierList.get(0).getSuppliers().getSupplierId(),
                productsCategoryList.get(0).getCategories().getCategoryId(), products.getProductsId()};

        String updateSql = "UPDATE products SET product_name=?, unit_price=?, units_in_stock=?," +
                        "supplier_id=?, category_id=? " +
                        "WHERE product_id=?";

        jdbcTemplate.update(updateSql, updateObj);
        System.out.println("Product record updated.");
    }

    @Override
    public void deleteProductsRecordById(String productsId) {
        String deleteSql = "DELETE FROM products WHERE product_id=?";
        jdbcTemplate.update(deleteSql, productsId);
        System.out.println("Products record deleted.");
    }

    @Override
    public void deleteProductsRecordByCategoryId(int categoryId) {

        String deleteSql = "DELETE FROM products WHERE category_id =?";
        jdbcTemplate.update(deleteSql, categoryId);
        System.out.println("Products record deleted");
    }

    @Override
    public void deleteProductsRecordBySupplierId(String supplierId) {

        String deleteSql = "DELETE FROM products WHERE supplier_id=?";
        jdbcTemplate.update(deleteSql, supplierId);
        System.out.println("Products record deleted");
    }

    @Override
    public void deleteProductsRecordBySupplierName(String supplierName) {

        String getSupplierIdSql = "SELECT supplier_id FROM suppliers " +
                                "WHERE contact_name = ?";
        List<Products> productsSupplierList = jdbcTemplate.query(getSupplierIdSql,
                new ProductsSupplierMapper(), supplierName);

        String deleteSql = "DELETE FROM products WHERE contact_name=?";
        jdbcTemplate.update(deleteSql, productsSupplierList.get(0).getSuppliers().getSupplierId());
        System.out.println("Products record deleted");
    }

    @Override
    public void deleteProductsRecordByCategoryName(String categoryName) {

        String getCategoryIdSql = "SELECT category_id FROM categories " +
                                    "WHERE category_name=?";
        List<Products> productsCategoryList = jdbcTemplate.query(getCategoryIdSql,
                new ProductsCategoryMapper(), categoryName);

        String deleteSql= "DELETE FROM products WHERE category_id =?";
        jdbcTemplate.update(deleteSql, productsCategoryList.get(0).getCategories().getCategoryId());
        System.out.println("Products record deleted");
    }

    @Override
    public void insertMultiProductsRecords(List<Products> productsList) {

        List<Object[]> productsObjList = new LinkedList<>();
        int count=0;

        for(Products products: productsList){

            String getSupplierIdSql = "SELECT supplier_id FROM suppliers " +
                                    "WHERE contact_name=?";
            List<Products> productsSupplierList= jdbcTemplate.query(getSupplierIdSql,
                        new ProductsSupplierMapper(), products.getSupplierName());
            String supplierId = productsSupplierList.get(0).getSuppliers().getSupplierId();

            String getCategoryIdSql = "SELECT category_id FROM categories " +
                                    "WHERE category_name=?";
            List<Products> productsCategoryList = jdbcTemplate.query(getCategoryIdSql,
                    new ProductsCategoryMapper(), products.getCategoryName());
            int categoryId = productsCategoryList.get(0).getCategories().getCategoryId();

            String productId =(products.getProductName().substring(products.getProductName().length()-2,
                    products.getProductName().length())+ supplierId.substring(0,4) + categoryId).toUpperCase();

            Object[] productsObjArray = {productId, products.getProductName(), products.getUnitPrice(),
                    products.getUnitsInStock(), supplierId, categoryId};
            productsObjList.add(productsObjArray);

            count ++;
        }

        String insertSql = "INSERT INTO products(product_id, product_name, unit_price, units_in_stock, " +
                            "supplier_id, category_id)" +
                            "VALUES(?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(insertSql, productsObjList);
        System.out.println(count+" products records inserted");
    }

    @Override
    public void updateMultiProductsRecords(List<Products> productsList) {

        List<Object[]> productsObjList = new LinkedList<>();
        int count=0;

        for(Products products: productsList){

            String getSupplierSql = "SELECT supplier_id FROM suppliers " +
                                    "WHERE contact_name =?";
            List<Products> productsSupplierList = jdbcTemplate.query(getSupplierSql,
                    new ProductsSupplierMapper(), products.getSupplierName());

            String getCategorySql = "SELECT category_id FROM categories " +
                                    "WHERE category_name =?";
            List<Products> productsCategoryList = jdbcTemplate.query(getCategorySql,
                    new ProductsCategoryMapper(), products.getCategoryName());

            Object[] productsObjArray = {products.getProductName(), products.getUnitPrice(),
                     products.getUnitsInStock(),productsSupplierList.get(0).getSuppliers().getSupplierId(),
                     productsCategoryList.get(0).getCategories().getCategoryId(),products.getProductsId()};

            productsObjList.add(productsObjArray);

            count++;
        }

        String updateSql = "UPDATE products SET product_name=?, unit_price=?, units_in_stock=?, supplier_id=?," +
                            "category_id=? " +
                            "WHERE product_id =?";
        jdbcTemplate.batchUpdate(updateSql, productsObjList);
        System.out.println(count+" records updated");

    }

    @Override
    public void deleteMultiProductsRecordsById(List<String> productsIdList) {

        List<Object[]> productsIdObjList = new LinkedList<>();
        int count=0;

        for(String id: productsIdList){

            Object[] productsIdObj ={id};
            productsIdObjList.add(productsIdObj);

            count++;
        }

        String deleteSql = "DELETE FROM products WHERE product_id=?";
        jdbcTemplate.batchUpdate(deleteSql, productsIdObjList);
        System.out.println(count+" records deleted");

    }

    @Override
    public void deleteMultiProductsByCategoryName(List<String> productsCategoryNameList) {

        List<Object[]> productCategoryNameObjList = new LinkedList<>();
        int count=0;

        for(String categoryName: productsCategoryNameList){

            String getProductCategory = "SELECT category_id FROM categories " +
                                        "WHERE category_name=?";
            List<Products> productsCategoryList= jdbcTemplate.query(getProductCategory,
                    new ProductsCategoryMapper(), categoryName);

            Object[] productCategoryNameObj= {productsCategoryList.get(0).getCategories().getCategoryId()};

            productCategoryNameObjList.add(productCategoryNameObj);
            count ++;

        }

        String deleteSql = "DELETE FROM products WHERE category_id=?";
        jdbcTemplate.batchUpdate(deleteSql, productCategoryNameObjList);

        System.out.println(count+" records deleted");

    }





















    @Override
    public void deleteMultiProductsRecordsBySupplierCompanyName(List<String> supplierNameList) {

        List<Object[]> productsObjList = new LinkedList<>();
        int count=0;

        for(String supplierName: supplierNameList) {

            String getSupplierSql =" SELECT supplier_id FROM suppliers WHERE company_name=?";
            List<Products> productsSupplierList = jdbcTemplate.query(getSupplierSql,
                    new ProductsSupplierMapper(), supplierName);

            Object[] productsObjArray = {productsSupplierList.get(0).getSuppliers().getSupplierId()};
            productsObjList.add(productsObjArray);
            count++;
        }

        String deleteSql = "DELETE FROM products WHERE supplier_id =?";
        jdbcTemplate.batchUpdate(deleteSql, productsObjList);

        System.out.println(count+" products records deleted");

    }

    @Override
    public List<Products> findAllProductsRecords() {

        String findSql = "SELECT pr.product_id, pr.product_name, pr.unit_price, pr.units_in_stock, " +
                "su.company_name, su.contact_name, su.phone, ca.category_name " +
                "FROM products pr " +
                "INNER JOIN suppliers su " +
                "ON pr.supplier_id = su.supplier_id " +
                "INNER JOIN categories ca " +
                "ON pr.category_id = ca.category_id ";
        return jdbcTemplate.query(findSql, new ProductsSupplierCategoryExt());
    }

    @Override
    public List<Products> findProductsRecordsBySupplierCompanyName(String supplierCompanyName) {

        String findSql = "SELECT pr.product_id, pr.product_name, pr.unit_price, pr.units_in_stock, " +
                "su.company_name, su.contact_name, su.phone, ca.category_name " +
                "FROM products pr " +
                "INNER JOIN suppliers su " +
                "ON pr.supplier_id = su.supplier_id " +
                "INNER JOIN categories ca " +
                "ON pr.category_id = ca.category_id " +
                "WHERE su.company_name=? " +
                "ORDER BY pr.unit_price ASC";

        return jdbcTemplate.query(findSql, new ProductsSupplierCategoryExt(), supplierCompanyName);
    }

    @Override
    public List<Products> findProductsRecordsByCategoryName(String categoryName) {

        String findSql = "SELECT pr.product_id, pr.product_name, pr.unit_price, pr.units_in_stock, " +
                "su.company_name, su.contact_name, su.phone, ca.category_name " +
                "FROM products pr " +
                "INNER JOIN suppliers su " +
                "ON pr.supplier_id = su.supplier_id " +
                "INNER JOIN categories ca " +
                "ON pr.category_id = ca.category_id " +
                "WHERE ca.category_name=? " +
                "ORDER BY pr.unit_price DESC";
        return jdbcTemplate.query(findSql, new ProductsSupplierCategoryExt(), categoryName);
    }

    @Override
    public List<Products> findProductsRecordsByUnitPrice(Double unitPrice) {

        String findSql = "SELECT pr.product_id, pr.product_name, pr.unit_price, pr.units_in_stock, " +
                "su.company_name, su.contact_name, su.phone, ca.category_name " +
                "FROM products pr " +
                "INNER JOIN suppliers su " +
                "ON pr.supplier_id = su.supplier_id " +
                "INNER JOIN categories ca " +
                "ON pr.category_id = ca.category_id " +
                "WHERE pr.unit_price=? ";

        return jdbcTemplate.query(findSql, new ProductsSupplierCategoryExt(), unitPrice);
    }


}
