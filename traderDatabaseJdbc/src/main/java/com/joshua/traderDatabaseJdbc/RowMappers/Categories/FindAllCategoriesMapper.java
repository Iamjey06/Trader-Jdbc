package com.joshua.traderDatabaseJdbc.RowMappers.Categories;

import com.joshua.traderDatabaseJdbc.Entity.Categories;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FindAllCategoriesMapper implements RowMapper<Categories> {

    @Override
    public Categories mapRow(ResultSet rs, int rowNum) throws SQLException {

        Categories categories = new Categories();
        categories.setCategoryId(rs.getInt("category_id"));
        categories.setCategoryName(rs.getString("category_name"));


        return categories;
    }
}
