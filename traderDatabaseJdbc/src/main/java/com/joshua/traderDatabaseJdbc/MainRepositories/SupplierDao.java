package com.joshua.traderDatabaseJdbc.MainRepositories;

import com.joshua.traderDatabaseJdbc.Entity.Suppliers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierDao {

    void insertSupplierRecord(Suppliers suppliers);

    void updateSupplierRecord(Suppliers suppliers);

    void deleteSupplierRecordById(String supplierId);

    void deleteSupplierRecordByCompanyName(String companyName);

    void insertMultiSupplierRecords(List<Suppliers> suppliersList);

    void updateMultiSupplierRecords(List<Suppliers> suppliersList);

    List<Suppliers> findAllSuppliers();

    List<Suppliers> findSupplierByCityName(String cityName);

    List<Suppliers> findSupplierByCompanyName(String companyName);


}
