package com.joshua.traderDatabaseJdbc.Controller;

import com.joshua.traderDatabaseJdbc.Entity.Categories;
import com.joshua.traderDatabaseJdbc.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;

@Controller("categoriesController")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;
    public void insertCategoryRecord(){

        Categories categories = new Categories();
        categories.setCategoryId(3);
        categories.setCategoryName("Food");

        categoriesService.insertCategoryRecord(categories);

    }

    public void updateCategoryRecord(int oldCategoryId){

        Categories categories = new Categories();
        categories.setCategoryId(3);
        categories.setCategoryName("Food");

        categoriesService.updateCategoryRecord(categories, oldCategoryId);
    }

    public void deleteCategoryRecordById(int categoryId){
        categoriesService.deleteCategoryRecordById(categoryId);

    }

    public void insertMultiCategoryRecords(){

        List<Categories> categoriesList = new LinkedList<>();

        Categories categories1 = new Categories();
        categories1.setCategoryId(2);
        categories1.setCategoryName("Shirts");

        Categories categories2 = new Categories();
        categories2.setCategoryId(1);
        categories2.setCategoryName("shorts");

        categoriesList.add(categories1);
        categoriesList.add(categories2);

        categoriesService.insertMultiCategoryRecords(categoriesList);
    }

    public void updateMultiCategoriesRecords(){

        List<Categories> categoriesList= new LinkedList<>();
        List<Integer> oldCategoryList = new LinkedList<>();

        Categories categories1= new Categories();
        categories1.setCategoryId(4);
        categories1.setCategoryName("Shirts");

        Categories categories2 = new Categories();
        categories2.setCategoryId(1);
        categories2.setCategoryName("Cloth");

        categoriesList.add(categories1);
        categoriesList.add(categories2);

        oldCategoryList.add(2);
        oldCategoryList.add(1);

        categoriesService.updateMultiCategoriesRecords(categoriesList, oldCategoryList);
    }


    public void findAllCategories(){

        List<Categories> categoriesList = categoriesService.findAllCategories();
        int count=0;
        for(Categories categories: categoriesList){

            System.out.println("CategoryId... "+categories.getCategoryId()+" ..CategoryName... " +
                    categories.getCategoryName());
            count++;
        }

        System.out.println(count+" categories returned");
    }


    public void findCategoryByName(String categoryName){

        List<Categories> categoriesList = categoriesService.findCategoryByName(categoryName);

        for(Categories categories: categoriesList){

            System.out.println("CategoryId... "+categories.getCategoryId()+" ..CategoryName... " +
                    categories.getCategoryName());
        }
        System.out.println("Categories with name "+categoryName +" returned");
    }

    public void findCategoryById(int categoryId){

        List<Categories> categoriesList = categoriesService.findCategoryById(categoryId);

        System.out.println("CategoryId... "+categoriesList.get(0).getCategoryId()+" ..CategoryName..."
                    +categoriesList.get(0).getCategoryName());
    }



}
