package com.joshua.traderDatabaseJdbc.MainRepositories;

import com.joshua.traderDatabaseJdbc.Entity.Categories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesDao {

    void insertCategoryRecord(Categories categories);

    void updateCategoryRecord(Categories categories, int oldCategoryId);

    void deleteCategoryRecordById(int categoryId);

    void insertMultiCategoryRecords(List<Categories> categoriesList);

    void updateMultiCategoriesRecords(List<Categories> categoriesList, List<Integer> oldCategoryIds);

    List<Categories> findAllCategories();

    List<Categories> findCategoryByName(String categoryName);

    List<Categories> findCategoryById(int categoryId);
}
