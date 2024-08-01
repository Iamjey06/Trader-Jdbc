package com.joshua.traderDatabaseJdbc.RowMappers.City;

import com.joshua.traderDatabaseJdbc.Entity.City;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FindCitiesLikeMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {

        City city = new City();
        city.setCityName(rs.getString("city_name"));

        return city;
    }
}
