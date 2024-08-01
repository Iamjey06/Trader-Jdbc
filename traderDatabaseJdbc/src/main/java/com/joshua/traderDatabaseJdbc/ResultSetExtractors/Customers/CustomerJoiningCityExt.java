package com.joshua.traderDatabaseJdbc.ResultSetExtractors.Customers;

import com.joshua.traderDatabaseJdbc.Entity.City;
import com.joshua.traderDatabaseJdbc.Entity.Customers;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CustomerJoiningCityExt implements ResultSetExtractor<List<Customers>> {
    @Override
    public List<Customers> extractData(ResultSet rs) throws SQLException, DataAccessException {

        List<Customers> customersList = new LinkedList<>();

        while(rs.next()) {
            City city = new City();
            city.setCityId(rs.getString("city_id"));
            city.setCityName(rs.getString("city_name"));

            Customers customers = new Customers();
            customers.setCustomerId(rs.getString("customer_id"));
            customers.setCompanyName(rs.getString("company_name"));
            customers.setContactName(rs.getString("contact_name"));
            customers.setAddress(rs.getString("address"));
            customers.setPhone(rs.getString("phone"));
            customers.setCity(city);

            customersList.add(customers);
        }

        return customersList;
    }
}
