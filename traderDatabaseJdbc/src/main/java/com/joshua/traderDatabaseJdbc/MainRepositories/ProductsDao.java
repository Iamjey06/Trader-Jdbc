package com.joshua.traderDatabaseJdbc.MainRepositories;

import com.joshua.traderDatabaseJdbc.Entity.Products;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsDao {

    void insertProductRecord(Products products);

    void updateProductRecord(Products products);

    void deleteProductsRecordById(String productsId);

    void deleteProductsRecordByCategoryId(int categoryId);

    void deleteProductsRecordBySupplierId(String supplierId);

    void deleteProductsRecordBySupplierName(String supplierName);

    void deleteProductsRecordByCategoryName(String categoryName);

    void insertMultiProductsRecords(List<Products> productsList);

    void updateMultiProductsRecords(List<Products> productsList);

    void deleteMultiProductsRecordsById(List<String> productsIdList);

    void deleteMultiProductsByCategoryName(List<String> productsCategoryNameList);

    void deleteMultiProductsRecordsBySupplierCompanyName(List<String> supplierNameList);

    List<Products> findAllProductsRecords();

    List<Products> findProductsRecordsBySupplierCompanyName(String supplierCompanyName);

    List<Products> findProductsRecordsByCategoryName(String categoryName);

    List<Products> findProductsRecordsByUnitPrice(Double unitPrice);

}
