package com.joshua.traderDatabaseJdbc.RowMappers.Suppliers;

import com.joshua.traderDatabaseJdbc.Entity.City;
import com.joshua.traderDatabaseJdbc.Entity.Suppliers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SupplierCityMapper implements RowMapper<Suppliers> {

    @Override
    public Suppliers mapRow(ResultSet rs, int rowNum) throws SQLException {

        City city = new City();
        city.setCityId(rs.getString("city_id"));

        Suppliers suppliers = new Suppliers();
        suppliers.setCity(city);

        return suppliers;
    }
}
