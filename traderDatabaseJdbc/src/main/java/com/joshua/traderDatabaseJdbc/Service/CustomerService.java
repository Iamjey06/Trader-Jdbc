package com.joshua.traderDatabaseJdbc.Service;

import com.joshua.traderDatabaseJdbc.Entity.Customers;
import com.joshua.traderDatabaseJdbc.MainRepositories.CustomerDao;
import com.joshua.traderDatabaseJdbc.ResultSetExtractors.Customers.CustomerJoiningCityExt;
import com.joshua.traderDatabaseJdbc.RowMappers.Customers.CustomerCityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("customerService")
public class CustomerService implements CustomerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertCustomerRecord(Customers customers) {
        String getCityIdSql = "SELECT city_id FROM city WHERE city_name=?";
        List<Customers> customerCityList = jdbcTemplate.query(getCityIdSql,
                                            new CustomerCityMapper(), customers.getCityName());
        String cityId = customerCityList.get(0).getCity().getCityId();

        String customerId = (customers.getContactName().substring(2,4)+
                            customers.getPhone().substring(6,9)+ cityId).toUpperCase();

        Object[] customerArgs = {customerId, customers.getCompanyName(), customers.getContactName(), customers.getAddress(),
                                    customers.getPhone(), cityId};

        String insertSql = "INSERT INTO customers(customer_id, company_name, contact_name, " +
                        "address, phone, city_id)" +
                        "VALUES(?,?,?,?,?,?)";
        int rowsInserted = jdbcTemplate.update(insertSql, customerArgs);

        System.out.println(rowsInserted+" customer(s) inserted into database");
    }

    @Override
    public void updateCustomerRecordById(Customers customers) {

        String getCityIdSql = "SELECT city_id, city_name FROM city WHERE city_name=?";
        List<Customers> customerCity = jdbcTemplate.query(getCityIdSql, new CustomerCityMapper(), customers.getCityName());

        Object[] customerArgs = {customers.getCompanyName(), customers.getContactName(),
                                customers.getAddress(), customers.getPhone(),
                                customerCity.get(0).getCity().getCityId(),customers.getCustomerId()};

        String sql = "UPDATE customers SET company_name=?, contact_name=?, address=?, phone =?," +
                        " city_id=? WHERE customer_id=?";

        int rowsUpdated = jdbcTemplate.update(sql, customerArgs );

        System.out.println(rowsUpdated +" rows updated.");
    }

    @Override
    public void deleteCustomerRecordById(String customerId) {

        String sql = "DELETE FROM customers WHERE customer_id=?";
        jdbcTemplate.update(sql, customerId);
    }

    @Override
    public void insertMultiCustomerRecords(List<Customers> customerList) {

        List<Object[]> customerObjList = new LinkedList<>();
        int count = 0;



        for(Customers customers: customerList){
            String getCityIdSql = "SELECT city_id, city_name FROM city WHERE city_name=?";
            List<Customers> customerCityId= jdbcTemplate.query(getCityIdSql,
                    new CustomerCityMapper(), customers.getCityName());
            String cityId = customerCityId.get(0).getCity().getCityId();

            String customerId = (customers.getContactName().substring(2,4)+
                    customers.getPhone().substring(6,9)+ cityId).toUpperCase();

            Object[] customerObjArray ={customerId,customers.getCompanyName(), customers.getContactName(),
                                        customers.getAddress(), customers.getPhone(),
                                        cityId};

            customerObjList.add(customerObjArray);
            count++;
        }
        String insertSql = "INSERT INTO customers(customer_id, company_name, contact_name, address, phone, city_id) " +
                            "VALUES(?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(insertSql, customerObjList);

        System.out.println(count+" customer records inserted");
    }

    @Override
    public void updateMultiCustomerRecords(List<Customers> customerList) {

        List<Object[]> customerObjList = new LinkedList<>();
        int count = 0;

        for(Customers customers: customerList) {
            String getCityIdSql = "SELECT city_id FROM city WHERE city_name=?";
            List<Customers> customerCity = jdbcTemplate.query(getCityIdSql,
                                            new CustomerCityMapper(), customers.getCityName());

           Object[] customerObj = {customers.getCompanyName(), customers.getContactName(),
                                   customers.getAddress(), customers.getPhone(),
                                   customerCity.get(0).getCity().getCityId(), customers.getCustomerId() };

           customerObjList.add(customerObj);
           count++;
        }

        String updateSql = "UPDATE customers SET company_name=?, contact_name=?, address=?," +
                "phone=?, city_id=? WHERE customer_id=?";
        jdbcTemplate.batchUpdate(updateSql,customerObjList);

        System.out.println(count+" rows have been updated");
    }

    @Override
    public void deleteMultiCustomerRecords(List<String> customerIds) {

        List<Object[]> customerIdsList = new LinkedList<>();
        int count=0;
        for(String ids: customerIds){

            Object[] customerIdsObj = {ids};
            customerIdsList.add(customerIdsObj);
            count++;
        }

        String deleteSql = "DELETE FROM customers WHERE customer_id=?";
        jdbcTemplate.batchUpdate(deleteSql, customerIdsList);
        System.out.println(count+" customer records deleted");
    }

    @Override
    public List<Customers> findAllCustomers() {

        String findSql = "SELECT cu.customer_id, cu.company_name, cu.contact_name, cu.address," +
                                "cu.phone, ci.city_id, ci.city_name" +
                            " FROM customers cu INNER JOIN city ci " +
                            "ON cu.city_id = ci.city_id";


        return jdbcTemplate.query(findSql, new CustomerJoiningCityExt());
    }

    @Override
    public List<Customers> findCustomersById(String customerId) {


        String findSql = "SELECT cu.customer_id, cu.company_name, cu.contact_name, cu.address," +
                        " cu.phone, ci.city_id, ci.city_name " +
                        "From customers cu " +
                        " INNER JOIN city ci " +
                        " ON cu.city_id = ci.city_id" +
                        " WHERE cu.customer_id =?";

        return jdbcTemplate.query(findSql, new CustomerJoiningCityExt(), customerId);
    }

    @Override
    public List<Customers> findCustomersByCityName(String cityName) {

        String findSql = "SELECT cu.customer_id, cu.company_name, cu.contact_name, cu.address," +
                        "cu.phone, ci.city_id, ci.city_name " +
                        "FROM customers cu " +
                        "INNER JOIN city ci " +
                        "ON cu.city_id = ci.city_id " +
                        "WHERE ci.city_name=?";
        return jdbcTemplate.query(findSql, new CustomerJoiningCityExt(), cityName);
    }

    @Override
    public List<Customers> findCustomersByContactName(String contactName) {

        String findSql = "SELECT cu.customer_id, cu.company_name, cu.contact_name, cu.address," +
                "cu.phone, ci.city_id, ci.city_name " +
                "FROM customers cu " +
                "INNER JOIN city ci " +
                "ON cu.city_id = ci.city_id " +
                "WHERE cu.contact_name=?";
        return jdbcTemplate.query(findSql, new CustomerJoiningCityExt(), contactName);
    }


}
