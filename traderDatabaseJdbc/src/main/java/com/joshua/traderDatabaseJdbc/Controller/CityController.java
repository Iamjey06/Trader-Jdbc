package com.joshua.traderDatabaseJdbc.Controller;

import com.joshua.traderDatabaseJdbc.Entity.City;
import com.joshua.traderDatabaseJdbc.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;

@Controller("cityController")
public class CityController {

    @Autowired
    CityService cityService;

    public String insertCityRecord(){

    City city = new City();
    city.setCityName("Gwanda");
    cityService.insertCityRecord(city);
    return "City record inserted";
}


    public String updateCityRecord(){

    City city = new City();
    city.setCityId("HAR");
    city.setCityName("Harare");
    cityService.updateCityRecordById(city);
    return "City record has been updated";
}


    public String deleteCitRecordById(int cityId){

    cityService.deleteCityById(cityId);
    return"City record has been deleted ";
}

    public String insertMultiCityRecords(){

        List<City> cityList = new LinkedList<>();

        City city1 = new City();
        city1.setCityName("Hurungwe");

        City city2 = new City();
        city2.setCityName("Masvingo");

        City city3 = new City();
        city3.setCityName("Hwange");

        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);

        cityService.insertMultiCityRecords(cityList);

        return "Multiple City records inserted";
    }


    public String updateMultiCityRecords(){
        List<City> cityList = new LinkedList<>();

        City city1 = new City();
        city1.setCityId("MUT");
        city1.setCityName("Mutare ");

        City city2 = new City();
        city2.setCityId("HAR");
        city2.setCityName("Harare");

        cityList.add(city1);
        cityList.add(city2);

        cityService.updateMultiCityRecords(cityList);
        return "Multiple city records have been updated";
    }

    public void findAllCities(){

        List<City> myCityList= cityService.findAllCities();

        for(City city : myCityList){

            System.out.println("City Id: "+city.getCityId()+" for the City of "+city.getCityName());
        }
    }

    public void findCitiesLike(String likeValue){

        List<City> myLikeCityList = cityService.findCitiesLike("%"+likeValue+"%");

        for(City city: myLikeCityList){

            System.out.println("City "+city.getCityName());
        }

    }

    public void findCityIdByName(String cityName){
        System.out.println("The city id for city "+cityName+" is " +
                ""+cityService.findCityByName(cityName));
    }


}
