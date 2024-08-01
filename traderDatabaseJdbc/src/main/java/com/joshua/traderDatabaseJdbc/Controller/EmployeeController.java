package com.joshua.traderDatabaseJdbc.Controller;

import com.joshua.traderDatabaseJdbc.Entity.Employees;
import com.joshua.traderDatabaseJdbc.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;

@Controller("employeeController")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    public String insertEmployeeRecord(){

        Employees employees = new Employees();
        employees.setLastName("Luke");
        employees.setFirstName("Brian");
        employees.setJobTitle("Cleaner");
        employees.setBirthDate("20020306");
        employees.setHireDate("20200701");
        employees.setCityName("Harare");

        employeeService.insertEmployeeRecord(employees);
        return "Employee record inserted";
    }

    public String updateEmployeeRecord(){
        Employees employees = new Employees();
        employees.setEmployeeId("B2002EA");
        employees.setLastName("Luke");
        employees.setFirstName("Brian");
        employees.setJobTitle("Cleaner");
        employees.setBirthDate("20020306");
        employees.setHireDate("20200701");
        employees.setCityName("Masvingo");

        employeeService.updateEmployeeRecord(employees);
        return "Employee record updated";
    }

    public String deleteEmployeeRecordById(String employeeId){
        employeeService.deleteEmployeeRecordById(employeeId);
        return "Employee record deleted";
    }

    public void insertMultiEmployeeRecords(){
        List<Employees> employeesList = new LinkedList<>();
        
        Employees employee1 = new Employees();
        employee1.setLastName("Brian");
        employee1.setFirstName("Lordwell");
        employee1.setJobTitle("Cleaner");
        employee1.setBirthDate("20020306");
        employee1.setHireDate("20200701");
        employee1.setCityName("Hurungwe");

        Employees employee2 = new Employees();
        employee2.setLastName("Chaks");
        employee2.setFirstName("Kondini");
        employee2.setJobTitle("Receptonist");
        employee2.setBirthDate("19991226");
        employee2.setHireDate("20200701");
        employee2.setCityName("Gwanda");

        employeesList.add(employee2);
        employeesList.add(employee1);

        employeeService.insertMultiEmployeeRecords(employeesList);
    }

    public void updateMultiEmployeeRecords(){

        List<Employees> employeesList = new LinkedList<>();

        Employees employee1 = new Employees();
        employee1.setEmployeeId("B2002EA");
        employee1.setLastName("Luke");
        employee1.setFirstName("Brian");
        employee1.setJobTitle("Cleaner");
        employee1.setBirthDate("20020306");
        employee1.setHireDate("20200701");
        employee1.setCityName("Gwanda");

        Employees employee2 = new Employees();
        employee2.setEmployeeId("K1999CE");
        employee2.setLastName("Chaks");
        employee2.setFirstName("Kondini");
        employee2.setJobTitle("Receptonist");
        employee2.setBirthDate("19991226");
        employee2.setHireDate("20200701");
        employee2.setCityName("Masvingo");

        employeesList.add(employee2);
        employeesList.add(employee1);
        employeeService.updateMultiEmployeeRecords(employeesList);

    }

    public void findEmployeeById(String employeeId){

        List<Employees> employeeList= employeeService.findEmployeeById(employeeId);

        for(Employees employee: employeeList){

            System.out.println("EmployeeId... "+employee.getEmployeeId()+" ..LastName... "
                    +employee.getLastName()+" ..FirstName... "+employee.getFirstName()
                    +" ..JobTitle... "+employee.getJobTitle()+" ..Age..."+
                    employee.getAge()+" ..YearsAtWork..."+employee.getYearsAtWork()
                    +" ..CityName..."+ employee.getCity().getCityName());
        }

        System.out.println(" employees with Id "+ employeeId+" returned");

    }

    public void findAllEmployees(){

        List<Employees> employeesList = employeeService.findAllEmployees();
        int count =0;

        for(Employees employee: employeesList){

            System.out.println("EmployeeId... "+employee.getEmployeeId()+" ..LastName... "
                    +employee.getLastName()+" ..FirstName... "+employee.getFirstName()+" ..Age... "
                    +employee.getAge()+" ..YearsAtWork... "+employee.getYearsAtWork()+" ..CityName..."
                    +employee.getCity().getCityName());

            count++;
        }

        System.out.println(count+" employees returned");
    }

public void findEmployeesByCity(String employeeCity){

        List<Employees> employeesList = employeeService.findEmployeesByCity(employeeCity);
    int count =0;

    for(Employees employee: employeesList){

        System.out.println("EmployeeId... "+employee.getEmployeeId()+" ..LastName... "
                +employee.getLastName()+" ..FirstName... "+employee.getFirstName()+" ..Age... "
                +employee.getAge()+" ..YearsAtWork... "+employee.getYearsAtWork()+" ..CityName..."
                +employee.getCity().getCityName());

        count++;
    }
    System.out.println(count+" employees returned");
}


}
