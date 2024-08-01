package com.joshua.traderDatabaseJdbc.ResultSetExtractors.Employees;

import com.joshua.traderDatabaseJdbc.Entity.City;
import com.joshua.traderDatabaseJdbc.Entity.Employees;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmpCityExt implements ResultSetExtractor<List<Employees>> {
    @Override
    public List<Employees> extractData(ResultSet rs) throws SQLException, DataAccessException {

        List<Employees> employeesList = new LinkedList<>();
        while(rs.next()){

            City city = new City();
            city.setCityId(rs.getString("city_id"));
            city.setCityName(rs.getString("city_name"));

            Employees employees = new Employees();
            employees.setEmployeeId(rs.getString("employee_id"));
            employees.setLastName(rs.getString("last_name"));
            employees.setFirstName(rs.getString("first_name"));
            employees.setJobTitle(rs.getString("job_title"));
            employees.setAge(rs.getInt("Age"));
            employees.setYearsAtWork(rs.getInt("years_at_work"));
            employees.setCity(city);
            employeesList.add(employees);
        }


        return employeesList;
    }
}
