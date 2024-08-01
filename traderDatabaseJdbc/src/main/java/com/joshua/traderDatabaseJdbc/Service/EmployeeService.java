package com.joshua.traderDatabaseJdbc.Service;

import com.joshua.traderDatabaseJdbc.Entity.Employees;
import com.joshua.traderDatabaseJdbc.MainRepositories.EmployeeDao;
import com.joshua.traderDatabaseJdbc.ResultSetExtractors.Employees.EmpCityExt;
import com.joshua.traderDatabaseJdbc.RowMappers.Employees.EmployeeCityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("employeeService")

public class EmployeeService implements EmployeeDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertEmployeeRecord(Employees employees) {

        String getCitySql = "SELECT city_id, city_name FROM city WHERE city_name=?";
        List<Employees> employeesList = jdbcTemplate.query(getCitySql,
                                        new EmployeeCityMapper() ,employees.getCityName());

        String birthDate = employees.getBirthDate();
        StringBuilder birthDateBuilder = new StringBuilder(birthDate);
        birthDateBuilder.insert(4,"-");
        birthDateBuilder.insert(7, "-");

        String hireDate = employees.getHireDate();
        StringBuilder hireDateBuilder = new StringBuilder(hireDate);
        hireDateBuilder.insert(4, "-");
        hireDateBuilder.insert(7, "-");

        Object[] empObj = {employees.getLastName(), employees.getFirstName(), employees.getJobTitle(),
                            birthDateBuilder, hireDateBuilder,
                            employeesList.get(0).getCity().getCityId()};
        String insertSql = "INSERT INTO employees(last_name, first_name, job_title, birth_date, " +
                        "hire_date, city_id )" +
                        "VALUES(?,?,?,?,?,?)";
        int rowsInserted = jdbcTemplate.update(insertSql, empObj);
        System.out.println(rowsInserted+" customers inserted");
    }

    @Override
    public void updateEmployeeRecord(Employees employees) {
        String getCityIdSql = "SELECT city_id, city_name FROM city WHERE city_name=?";
        List<Employees> employeesList = jdbcTemplate.query(getCityIdSql,
                                        new EmployeeCityMapper(), employees.getCityName());
        String birthDate = employees.getBirthDate();
        StringBuilder birthDateBuilder = new StringBuilder(birthDate);
        birthDateBuilder.insert(4,"-");
        birthDateBuilder.insert(7,"-");

        String hireDate = employees.getHireDate();
        StringBuilder hireDateBuilder = new StringBuilder(hireDate);
        hireDateBuilder.insert(4,"-");
        hireDateBuilder.insert(7,"-");

        Object[] empObj = { employees.getLastName(), employees.getFirstName(),
                            employees.getJobTitle(), birthDateBuilder, hireDateBuilder,
                            employeesList.get(0).getCity().getCityId(), employees.getEmployeeId()};
        String updateSql = "UPDATE employees SET last_name=?, first_name=?, job_title=?, " +
                            "birth_date=?, hire_date=?, city_id=? " +
                            "WHERE employee_id=?";
        int rowsUpdated = jdbcTemplate.update(updateSql, empObj);
        System.out.println(rowsUpdated+" rows updated");
    }

    @Override
    public void deleteEmployeeRecordById(String employeeId) {
        String deleteSql = "DELETE FROM employees WHERE employee_id=?";
        jdbcTemplate.update(deleteSql, employeeId);

        System.out.println("Deleted record of employee with id "+employeeId);
    }

    @Override
    public void insertMultiEmployeeRecords(List<Employees> employeesList) {

        List<Object[]> empObjList = new LinkedList<>();
        int count =0;

        for(Employees employees: employeesList){

            String getCityIdSql = "SELECT city_id, city_name FROM city WHERE city_name =?";
            List<Employees> getCityIdEmplList = jdbcTemplate.query(getCityIdSql,
                                            new EmployeeCityMapper(), employees.getCityName() );

            String birthDate = employees.getBirthDate();
            StringBuilder birthDateBuilder = new StringBuilder(birthDate);
            birthDateBuilder.insert(4, "-");
            birthDateBuilder.insert(7,"-");

            String hireDate = employees.getHireDate();
            StringBuilder hireDateBuilder = new StringBuilder(hireDate);
            hireDateBuilder.insert(4,"-");
            hireDateBuilder.insert(7, "-");


            Object[] empObjArray={employees.getLastName(), employees.getFirstName(), employees.getJobTitle(),
                            birthDateBuilder, hireDateBuilder,
                            getCityIdEmplList.get(0).getCity().getCityId()};
            empObjList.add(empObjArray);
            count ++;
        }

        String insertSql= "INSERT INTO employees(last_name,first_name, job_title, birth_date, " +
                            "hire_date, city_id)" +
                            "VALUES(?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(insertSql, empObjList);

        System.out.println(count+ " rows inserted");
    }

    @Override
    public void updateMultiEmployeeRecords(List<Employees> employeesList) {

        List<Object[]> empObjList = new LinkedList<>();
        int count=0;

        for(Employees employees: employeesList){

            String getCityIdSql = "SELECT city_id, city_name FROM city WHERE city_name=?";
            List<Employees> employeeList = jdbcTemplate.query(getCityIdSql,
                                        new EmployeeCityMapper(), employees.getCityName());
            String birthDate = employees.getBirthDate();
            StringBuilder birthDateBuilder = new StringBuilder(birthDate);
            birthDateBuilder.insert(4, "-");
            birthDateBuilder.insert(7, "-");

            String hireDate = employees.getHireDate();
            StringBuilder hireDateBuilder = new StringBuilder(hireDate);
            hireDateBuilder.insert(4, "-");
            hireDateBuilder.insert(7, "-");

            Object[] empObjArray={employees.getLastName(), employees.getFirstName(),
                                employees.getJobTitle(), birthDateBuilder,hireDateBuilder,
                                employeeList.get(0).getCity().getCityId(),employees.getEmployeeId()};
            empObjList.add(empObjArray);

            count++;
        }

        String updateSql = "UPDATE employees SET last_name=?, first_name=?, job_title=?, " +
                            "birth_date=?, hire_date =?, city_id=? " +
                            "WHERE  employee_id=?";
        jdbcTemplate.batchUpdate(updateSql, empObjList);
        System.out.println(count+ " rows updated");

    }

    @Override
    public List<Employees> findEmployeeById(String employeeId) {

        String findSql = "SELECT em.employee_id, em.last_name, em.first_name, em.job_title," +
                "YEAR(CURDATE())- YEAR(em.birth_date) AS Age, YEAR(CURDATE()) - YEAR(em.hire_date) AS Years_at_work," +
                "ci.city_name, ci.city_id " +
                "FROM employees em " +
                "INNER JOIN city ci " +
                "ON em.city_id = ci.city_id " +
                "WHERE em.employee_id =?";

        return jdbcTemplate.query(findSql, new EmpCityExt(), employeeId);
    }

    @Override
    public List<Employees> findEmployeesByCity(String employeeCity) {
        String findSql = "SELECT em.employee_id, em.last_name, em.first_name, em.job_title," +
                "YEAR(CURDATE())- YEAR(em.birth_date) AS Age, YEAR(CURDATE()) - YEAR(em.hire_date) AS Years_at_work," +
                "ci.city_name, ci.city_id " +
                "FROM employees em " +
                "INNER JOIN city ci " +
                "ON em.city_id = ci.city_id " +
                "WHERE ci.city_name=?";

        return jdbcTemplate.query(findSql, new EmpCityExt(), employeeCity);
    }

    @Override
    public List<Employees> findAllEmployees() {

        String findSql = "SELECT em.employee_id, em.last_name,em.first_name, em.job_title, " +
                "YEAR(CURDATE()) - YEAR(em.birth_date) AS Age," +
                "YEAR(CURDATE()) - YEAR(em.hire_date) AS years_at_work, ci.city_id, ci.city_name " +
                "FROM employees em " +
                "INNER JOIN city ci " +
                "ON em.city_id = ci.city_id ";

        return jdbcTemplate.query(findSql, new EmpCityExt());
    }


}
