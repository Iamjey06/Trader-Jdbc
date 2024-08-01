package com.joshua.traderDatabaseJdbc.MainRepositories;

import com.joshua.traderDatabaseJdbc.Entity.Customers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao{

    void insertCustomerRecord(Customers customers);

    void updateCustomerRecordById(Customers customers);

    void deleteCustomerRecordById(String customerId);

    void insertMultiCustomerRecords(List<Customers> customerList);

    void updateMultiCustomerRecords(List<Customers> customerList);

    void deleteMultiCustomerRecords(List<String> customerIds);

    List<Customers> findAllCustomers();

    List<Customers> findCustomersById(String customerId);

    List<Customers> findCustomersByCityName(String cityName);

    List<Customers> findCustomersByContactName(String contactName);

}
