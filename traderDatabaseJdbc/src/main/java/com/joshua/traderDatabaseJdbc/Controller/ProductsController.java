package com.joshua.traderDatabaseJdbc.Controller;

import com.joshua.traderDatabaseJdbc.Entity.Products;
import com.joshua.traderDatabaseJdbc.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;

@Controller("productsController")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    public void insertProductsRecord(){

        Products products = new Products();
        products.setProductName("Nike");
        products.setUnitsInStock(30);
        products.setUnitPrice(10.5);
        products.setSupplierName("Kabios Kabira");
        products.setCategoryName("Shirts");

        productsService.insertProductRecord(products);
    }


    public void updateProductsRecord(){

        Products products = new Products();
        products.setProductsId("AGIT421");
        products.setProductName("Nike");
        products.setUnitPrice(10.5);
        products.setUnitsInStock(35);
        products.setSupplierName("Keith Piyaso");
        products.setCategoryName("shirts");

        productsService.updateProductRecord(products);
    }
    
    public void deleteProductsRecordById(String productId){
        productsService.deleteProductsRecordById(productId);
    }

    public void deleteProductsRecordBYCategoryId(int categoryId){
        productsService.deleteProductsRecordByCategoryId(categoryId);
    }

    public void deleteProductsRecordBYSupplierId(String supplierId){
        productsService.deleteProductsRecordBySupplierId(supplierId);
    }

    public void deleteProductsRecordBYSupplierName(String supplierName){
        productsService.deleteProductsRecordBySupplierName(supplierName);
    }

    public void deleteProductsRecordBYCategoryName(String categoryName){
        productsService.deleteProductsRecordByCategoryName(categoryName);
    }
    
    public void insertMultiProductsRecords(){

        List<Products> productsList = new LinkedList<>();
        
        Products products = new Products();
        products.setProductName("Chino");
        products.setUnitPrice(12);
        products.setUnitsInStock(26);
        products.setSupplierName("Kabios Kabira");
        products.setCategoryName("jean trousers");

        Products products1 = new Products();
        products1.setProductName("Nike");
        products1.setUnitPrice(6);
        products1.setUnitsInStock(35);
        products1.setSupplierName("Kabios Kabira");
        products1.setCategoryName("shorts");

        productsList.add(products);
        productsList.add(products1);

        productsService.insertMultiProductsRecords(productsList);
        
    }

    public void updateMultiProductsRecords(){

        List<Products> productsList = new LinkedList<>();

        Products products = new Products();
        products.setProductsId("INIT422");
        products.setProductName("Chino");
        products.setUnitPrice(12);
        products.setUnitsInStock(26);
        products.setSupplierName("Lolo koko");
        products.setCategoryName("jean trousers");

        Products products1 = new Products();
        products1.setProductsId("IKIT424");
        products1.setProductName("Nike");
        products1.setUnitPrice(6);
        products1.setUnitsInStock(35);
        products1.setSupplierName("Keith Piyaso");
        products1.setCategoryName("shirts");

        productsList.add(products);
        productsList.add(products1);

        productsService.updateMultiProductsRecords(productsList);
    }

    public void deleteMultiProductsRecords(){

        List<String> productsIdList = new LinkedList<>();

        String id1 ="IKIT424";
        String id2 ="IKLO425";

        productsIdList.add(id1);
        productsIdList.add(id2);

        productsService.deleteMultiProductsRecordsById(productsIdList);
    }

    public void deleteMultiProductsRecordsByCategoryName(){

        List<String> productCategoryNameList = new LinkedList<>();

        String productsCategoryName1 ="jean trousers";
        String productsCategoryName2 ="shirts";

        productCategoryNameList.add(productsCategoryName1);
        productCategoryNameList.add(productsCategoryName2);

        productsService.deleteMultiProductsByCategoryName(productCategoryNameList);
    }


    public void deleteMultiProductsRecordsBySupplierCompanyName(){

        List<String> supplierCompanyNameList = new LinkedList<>();

        String name1 = "Kondwas";
        String name2 = "Bosvo ltd";

        supplierCompanyNameList.add(name1);
        supplierCompanyNameList.add(name2);

        productsService.deleteMultiProductsRecordsBySupplierCompanyName(supplierCompanyNameList);

    }

    public void findAllProductsRecords(){

        List<Products> productsList = productsService.findAllProductsRecords();

        for(Products products: productsList){

            System.out.println("ProductId... "+products.getProductsId()+" ..ProductName... "
            +products.getProductName()+" ..UnitPrice..."+products.getUnitPrice()+" ..UnitsInStock... "
            +products.getUnitsInStock()+" ..SupplierCompany... "+products.getSuppliers().getCompanyName()
            +" ..SupplierContactName... "+products.getSuppliers().getContactName()+" ..SupplierPhone... "
            +products.getSuppliers().getPhone()+" ..CategoryName... "+products.getCategories().getCategoryName());

        }
    }


    public void findProductsBySupplierCompanyName(String supplierCompanyName){

        List<Products> productsList = productsService.findProductsRecordsBySupplierCompanyName(supplierCompanyName);

        for(Products products: productsList){

            System.out.println("ProductId... "+products.getProductsId()+" ..ProductName... "
            +products.getProductName()+" ..UnitPrice..."+products.getUnitPrice()+" ..UnitsInStock... "
            +products.getUnitsInStock()+" ..SupplierCompany... "+products.getSuppliers().getCompanyName()
            +" ..SupplierContactName... "+products.getSuppliers().getContactName()+" ..SupplierPhone... "
            +products.getSuppliers().getPhone()+" ..CategoryName... "+products.getCategories().getCategoryName());
        }

    }


    public void findProductsByCategoryName(String categoryName){

        List<Products> productsList = productsService.findProductsRecordsByCategoryName(categoryName);

        for(Products products: productsList){

            System.out.println("ProductId... "+products.getProductsId()+" ..ProductName... "
                    +products.getProductName()+" ..UnitPrice..."+products.getUnitPrice()+" ..UnitsInStock... "
                  +products.getUnitsInStock()+" ..SupplierCompany... "+products.getSuppliers().getCompanyName()
                    +" ..SupplierContactName... "+products.getSuppliers().getContactName()+" ..SupplierPhone... "
            +products.getSuppliers().getPhone()+" ..CategoryName... "+products.getCategories().getCategoryName());
}

}


    public void findProductsRecordsByUnitPrice(Double unitPrice){

        List<Products> productsList = productsService.findProductsRecordsByUnitPrice(unitPrice);

        for(Products products: productsList){

            System.out.println("ProductId... "+products.getProductsId()+" ..ProductName... "
                    +products.getProductName()+" ..UnitPrice..."+products.getUnitPrice()+" ..UnitsInStock... "
                    +products.getUnitsInStock()+" ..SupplierCompany... "+products.getSuppliers().getCompanyName()
                    +" ..SupplierContactName... "+products.getSuppliers().getContactName()+" ..SupplierPhone... "
                    +products.getSuppliers().getPhone()+" ..CategoryName... "+products.getCategories().getCategoryName());
        }

    }


}