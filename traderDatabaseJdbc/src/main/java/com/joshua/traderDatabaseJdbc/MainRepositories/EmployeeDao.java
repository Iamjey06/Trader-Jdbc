package com.joshua.traderDatabaseJdbc.MainRepositories;

import com.joshua.traderDatabaseJdbc.Entity.Employees;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao{

    void insertEmployeeRecord(Employees employees);

    void updateEmployeeRecord(Employees employees);

    void deleteEmployeeRecordById(String employeeId);

    void insertMultiEmployeeRecords(List<Employees> employeesList);

    void updateMultiEmployeeRecords(List<Employees> employeesList);

    List<Employees> findEmployeeById(String employeeId);



    List<Employees> findEmployeesByCity(String emplpoyeeCity);

    List<Employees> findAllEmployees();
}
