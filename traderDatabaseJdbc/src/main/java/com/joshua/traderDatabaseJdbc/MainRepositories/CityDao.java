package com.joshua.traderDatabaseJdbc.MainRepositories;

import com.joshua.traderDatabaseJdbc.Entity.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityDao {

    void insertCityRecord(City city);

    void updateCityRecordById(City city);

    void deleteCityById(int cityId);

    void insertMultiCityRecords(List<City> cityList);

    void updateMultiCityRecords(List<City> cityList);

    List<City> findAllCities();

    String findCityByName(String cityName);

    List<City> findCitiesLike(String likeValue);


}
