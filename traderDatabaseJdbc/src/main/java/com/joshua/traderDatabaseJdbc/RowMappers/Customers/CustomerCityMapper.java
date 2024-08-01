package com.joshua.traderDatabaseJdbc.RowMappers.Customers;

import com.joshua.traderDatabaseJdbc.Entity.City;
import com.joshua.traderDatabaseJdbc.Entity.Customers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerCityMapper implements RowMapper<Customers> {
    @Override
    public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
            City city = new City();
            city.setCityId(rs.getString("city_id"));

            Customers customers = new Customers();
            customers.setCity(city);

        return customers;
    }
}
