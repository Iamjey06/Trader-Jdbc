package com.joshua.traderDatabaseJdbc.ResultSetExtractors.Suppliers;

import com.joshua.traderDatabaseJdbc.Entity.City;
import com.joshua.traderDatabaseJdbc.Entity.Suppliers;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SupplierCityExt implements ResultSetExtractor<List<Suppliers>> {
    @Override
    public List<Suppliers> extractData(ResultSet rs) throws SQLException, DataAccessException {

        List<Suppliers> suppliersList = new LinkedList<>();

        while(rs.next()){
            City city = new City();
            city.setCityName(rs.getString("city_name"));

            Suppliers suppliers = new Suppliers();
            suppliers.setSupplierId(rs.getString("supplier_id"));
            suppliers.setCompanyName(rs.getString("company_name"));
            suppliers.setContactName(rs.getString("contact_name"));
            suppliers.setAddress(rs.getString("address"));
            suppliers.setPhone(rs.getString("phone"));
            suppliers.setCity(city);

            suppliersList.add(suppliers);
        }


        return suppliersList;
    }
}
