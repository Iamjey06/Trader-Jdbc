package com.joshua.traderDatabaseJdbc.RowMappers.Employees;

import com.joshua.traderDatabaseJdbc.Entity.City;
import com.joshua.traderDatabaseJdbc.Entity.Employees;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeCityMapper implements RowMapper<Employees> {
    @Override
    public Employees mapRow(ResultSet rs, int rowNum) throws SQLException {

        City city = new City();
        city.setCityId(rs.getString("city_id"));
        city.setCityName(rs.getString("city_name"));

        Employees employees = new Employees();
        employees.setCity(city);

        return employees;
    }
}
