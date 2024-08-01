package com.joshua.traderDatabaseJdbc.Service;

import com.joshua.traderDatabaseJdbc.Entity.Suppliers;
import com.joshua.traderDatabaseJdbc.MainRepositories.SupplierDao;
import com.joshua.traderDatabaseJdbc.ResultSetExtractors.Suppliers.SupplierCityExt;
import com.joshua.traderDatabaseJdbc.RowMappers.Suppliers.SupplierCityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("supplierService")
public class SupplierService implements SupplierDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void insertSupplierRecord(Suppliers suppliers) {

        String getCityId = "SELECT city_id FROM city WHERE city_name =?";
        List<Suppliers> supplierCity = jdbcTemplate.query(getCityId,
                new SupplierCityMapper(), suppliers.getCityName());

        String supplierId = (suppliers.getContactName().substring(3,5)+
                suppliers.getPhone().substring(6,9)+ suppliers.getCompanyName().substring(0,3)).toUpperCase();

        Object[] supplierObjArray ={supplierId, suppliers.getCompanyName(), suppliers.getContactName(),
                                    suppliers.getAddress(), suppliers.getPhone(),
                                    supplierCity.get(0).getCity().getCityId()};

        String insertSql = "INSERT INTO suppliers(supplier_id, company_name, contact_name, address, phone, city_id)" +
                            "VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(insertSql, supplierObjArray);

        System.out.println("Supplier Record inserted");
    }

    @Override
    public void updateSupplierRecord(Suppliers suppliers) {

        String getCitySql = "SELECT city_id FROM city WHERE city_name =?";
        List<Suppliers> supplierCity = jdbcTemplate.query(getCitySql,
                new SupplierCityMapper(), suppliers.getCityName());

        String supplierId = (suppliers.getContactName().substring(3,5)+
                suppliers.getPhone().substring(6,9)+ suppliers.getCompanyName().substring(0,3)).toUpperCase();

        Object[] supplierObjArray = {supplierId, suppliers.getCompanyName(), suppliers.getContactName(),
                suppliers.getAddress(), suppliers.getPhone(),
                supplierCity.get(0).getCity().getCityId(), suppliers.getSupplierId() };

        String updateSql = "UPDATE suppliers SET supplier_id=?, company_name=?, contact_name=?, address=?, phone=?, " +
                            "city_id=?" +
                            "WHERE supplier_id=?";
        jdbcTemplate.update(updateSql, supplierObjArray);
        System.out.println("Supplier record updated");
    }

    @Override
    public void deleteSupplierRecordById(String supplierId) {

        String deleteSql = "DELETE FROM suppliers WHERE supplier_id=?";
        int recordsDeleted = jdbcTemplate.update(deleteSql, supplierId);

        System.out.println(recordsDeleted+" records deleted");
    }

    @Override
    public void deleteSupplierRecordByCompanyName(String companyName) {

        String deleteSql = "DELETE FROM suppliers WHERE company_name=?";
        int recordsDeleted = jdbcTemplate.update(deleteSql, companyName);

        System.out.println(recordsDeleted+" supplier record(s) deleted");
    }

    @Override
    public void insertMultiSupplierRecords(List<Suppliers> suppliersList) {

        List<Object[]> supplierObjList = new LinkedList<>();
        int count=0;

        for(Suppliers supplier: suppliersList){

            String getCitySql = "SELECT city_id FROM city WHERE city_name=?";
            List<Suppliers> supplierCityList= jdbcTemplate.query(getCitySql,
                    new SupplierCityMapper(), supplier.getCityName());
            String supplierCityId = supplierCityList.get(0).getCity().getCityId();

            String supplierId = (supplier.getContactName().substring(3,5)+
                    supplier.getPhone().substring(6,9)+ supplier.getCompanyName().substring(0,3)).toUpperCase();

            Object[] objArray = {supplierId, supplier.getCompanyName(), supplier.getContactName(),
                    supplier.getAddress(), supplier.getPhone(), supplierCityId};

            supplierObjList.add(objArray);
            count++;
        }

        String insertSql = "INSERT INTO suppliers(supplier_id, company_name, contact_name, address, " +
                            "phone, city_id )" +
                            "VALUES(?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(insertSql, supplierObjList);
        System.out.println(count+" supplier records inserted");

    }

    @Override
    public void updateMultiSupplierRecords(List<Suppliers> suppliersList) {

        List<Object[]> supplierObjList = new LinkedList<>();
        int count =0;

        for(Suppliers suppliers: suppliersList){

            String getSupplierCity = "SELECT city_id FROM city WHERE city_name=?";
            List<Suppliers> supplierCityList = jdbcTemplate.query(getSupplierCity,
                    new SupplierCityMapper(), suppliers.getCityName());
            String cityId = supplierCityList.get(0).getCity().getCityId();

            String supplierId = (suppliers.getContactName().substring(3,5)+
                    suppliers.getPhone().substring(6,9)+ suppliers.getCompanyName().substring(0,3)).toUpperCase();

            Object[] objArray = {supplierId, suppliers.getCompanyName(),
                    suppliers.getContactName(), suppliers.getAddress(), suppliers.getPhone(), cityId,
                    suppliers.getSupplierId()};

            supplierObjList.add(objArray);
            count++;
        }

        String updateSql ="UPDATE suppliers SET supplier_id=?, company_name=?, contact_name=?," +
                        "address=?, phone=?, city_id=?" +
                        "WHERE supplier_id=?";
        jdbcTemplate.batchUpdate(updateSql, supplierObjList);
        System.out.println(count+" supplier records updated");
    }

    @Override
    public List<Suppliers> findAllSuppliers() {

        String findSql = "SELECT su.supplier_id, su.company_name, su.contact_name," +
                "su.address, su.phone, ci.city_name " +
                "FROM suppliers su " +
                "INNER JOIN city ci " +
                "ON su.city_id= ci.city_id";
        return jdbcTemplate.query(findSql, new SupplierCityExt());
    }

    @Override
    public List<Suppliers> findSupplierByCityName(String cityName) {

        String findSql ="SELECT su.supplier_id, su.company_name, su.contact_name," +
                "su.address, su.phone, ci.city_name " +
                "FROM suppliers su " +
                "INNER JOIN city ci " +
                "ON su.city_id= ci.city_id " +
                "WHERE ci.city_name=?";

        return jdbcTemplate.query(findSql, new SupplierCityExt(), cityName);
    }

    @Override
    public List<Suppliers> findSupplierByCompanyName(String companyName) {

        String findSql ="SELECT su.supplier_id, su.company_name, su.contact_name," +
                "su.address, su.phone, ci.city_name " +
                "FROM suppliers su " +
                "INNER JOIN city ci " +
                "ON su.city_id= ci.city_id " +
                "WHERE su.company_name=?";

        return jdbcTemplate.query(findSql, new SupplierCityExt(), companyName);
    }


}
