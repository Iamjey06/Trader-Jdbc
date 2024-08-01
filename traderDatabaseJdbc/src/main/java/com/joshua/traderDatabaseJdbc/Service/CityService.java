package com.joshua.traderDatabaseJdbc.Service;

import com.joshua.traderDatabaseJdbc.Entity.City;
import com.joshua.traderDatabaseJdbc.MainRepositories.CityDao;
import com.joshua.traderDatabaseJdbc.RowMappers.City.FindAllMapper;
import com.joshua.traderDatabaseJdbc.RowMappers.City.FindCitiesLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Service("cityService")
public class CityService implements CityDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void insertCityRecord(City city) {

        String sql = "INSERT INTO city(city_name) " +
                    "VALUES(?)";
        int rowsInserted = jdbcTemplate.update(sql, city.getCityName() );

        System.out.println( rowsInserted+" rows inserted");
    }

    @Override
    public void updateCityRecordById(City city) {

        Object[] cityArgs = {city.getCityName() ,city.getCityId() };
        String sql = "UPDATE city SET city_name=? WHERE city_id=?";
        jdbcTemplate.update(sql, cityArgs);

        System.out.println("city name with city_id "+city.getCityId()+" has been updated to "+city.getCityName() );

    }

    @Override
    public void deleteCityById(int cityId) {

        String sql = "DELETE FROM city WHERE id=?";
        jdbcTemplate.update(sql, cityId);

        System.out.println("Record with id "+cityId+ " has been deleted");
    }

    @Override
    public void insertMultiCityRecords(List<City> cityList) {

        List<Object[]> cityArgs = new LinkedList<>();
        int count = 0;
        for(City city: cityList){
            Object[] cityObjArgs = { city.getCityName()};
            cityArgs.add(cityObjArgs);
        }

        String sql = "INSERT INTO city(city_name) " +
                    "VALUES(?)";
        int[] rowsInserted = jdbcTemplate.batchUpdate(sql,cityArgs );
        for(int i: rowsInserted){
            count++;
        }

        System.out.println(count+" Rows inserted");

    }

    @Override
    public void updateMultiCityRecords(List<City> cityList) {

        String sql= "UPDATE city SET city_name =? WHERE city_id=?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {

                ps.setString(1,cityList.get(i).getCityName());
                ps.setString(2,cityList.get(i).getCityId());


            }

            @Override
            public int getBatchSize() {
                return cityList.size();
            }
        });

        System.out.println(cityList.size()+" rows have been updated");
    }

    @Override
    public List<City> findAllCities() {

        String sql = "SELECT * FROM city";
        return jdbcTemplate.query(sql, new FindAllMapper());
    }

    @Override
    public String findCityByName(String cityName) {

        String sql = "SELECT city_id, city_name FROM city WHERE city_name=?";
        List<City> cityList = jdbcTemplate.query(sql, new FindAllMapper(), cityName);

        return cityList.get(0).getCityId();
    }

    @Override
    public List<City> findCitiesLike(String likeValue) {

        String sql = "SELECT city_name FROM city WHERE city_name LIKE ?";

        System.out.println("Cities like " +likeValue+" here");
        return jdbcTemplate.query(sql, new FindCitiesLikeMapper(), likeValue);
    }


}
