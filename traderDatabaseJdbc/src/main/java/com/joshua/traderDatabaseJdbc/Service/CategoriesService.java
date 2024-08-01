package com.joshua.traderDatabaseJdbc.Service;

import com.joshua.traderDatabaseJdbc.Entity.Categories;
import com.joshua.traderDatabaseJdbc.MainRepositories.CategoriesDao;
import com.joshua.traderDatabaseJdbc.RowMappers.Categories.FindAllCategoriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("categoriesService")
public class CategoriesService implements CategoriesDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertCategoryRecord(Categories categories) {

      String insertSql = "INSERT INTO categories(category_id, category_name) " +
              "VALUES(?,?)";
        jdbcTemplate.update(insertSql, categories.getCategoryId(), categories.getCategoryName());

        System.out.println("Category record inserted");
    }

    @Override
    public void updateCategoryRecord(Categories categories, int oldCategoryId) {

        Object[] updateObj = {categories.getCategoryId(), categories.getCategoryName(),
                                oldCategoryId};
        String updateSql = "UPDATE categories SET category_id=?, category_name=?" +
                        "WHERE category_id=?";
        jdbcTemplate.update(updateSql, updateObj);

        System.out.println("Category record updated");
    }

    @Override
    public void deleteCategoryRecordById(int categoryId) {
        String deleteSql = "DELETE FROM categories WHERE category_id =?";
        int rowsDeleted= jdbcTemplate.update(deleteSql, categoryId);
        System.out.println(rowsDeleted+" recordss deleted");
    }

    @Override
    public void insertMultiCategoryRecords(List<Categories> categoriesList) {

        List<Object[]> categoriesObjList = new LinkedList<>();
        int count=0;

        for(Categories categories: categoriesList){

            Object[] categoriesObjArray={categories.getCategoryId(), categories.getCategoryName()};
            categoriesObjList.add(categoriesObjArray);
            count++;
        }

        String insertSql = "INSERT INTO categories(category_id, category_name) " +
                            "VALUES(?,?)";
        jdbcTemplate.batchUpdate(insertSql, categoriesObjList);


        System.out.println(count+" categories added to records");
    }

    @Override
    public void updateMultiCategoriesRecords(List<Categories> categoriesList,
                                             List<Integer> oldCategoryIds) {

        List<Object[]> categoriesObjList= new LinkedList<>();
        int count=0;

        for(Categories categories: categoriesList){
            Object[] categoriesObjArray= {categories.getCategoryId(),
                    categories.getCategoryName(), oldCategoryIds.get(count)};
            categoriesObjList.add(categoriesObjArray);
            count++;
        }

        String updateSql = "UPDATE categories SET category_id=?, category_name=?" +
                            "WHERE category_id =?";
        jdbcTemplate.batchUpdate(updateSql, categoriesObjList);
        System.out.println(count+" categories records updated");
    }

    @Override
    public List<Categories> findAllCategories() {

        String findSql = "SELECT * FROM categories";


        return jdbcTemplate.query(findSql, new FindAllCategoriesMapper());
    }

    @Override
    public List<Categories> findCategoryByName(String categoryName) {

       String findSql = "SELECT * FROM categories " +
                        "WHERE category_name=?";
        return jdbcTemplate.query(findSql, new FindAllCategoriesMapper(), categoryName);
    }

    @Override
    public List<Categories> findCategoryById(int categoryId) {

        String findSql = "SELECT * FROM categories " +
                        "WHERE category_id =?";
        return jdbcTemplate.query(findSql, new FindAllCategoriesMapper(), categoryId);
    }


}




