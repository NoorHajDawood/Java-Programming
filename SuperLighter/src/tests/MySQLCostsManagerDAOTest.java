package tests;

import il.ac.shenkar.java.project.CostsManagerException;
import il.ac.shenkar.java.project.dao.Category;
import il.ac.shenkar.java.project.dao.Cost;
import il.ac.shenkar.java.project.dao.ICostsManagerDAO;
import il.ac.shenkar.java.project.dao.MySQLCostsManagerDAO;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MySQLCostsManagerDAOTest {

    static ICostsManagerDAO dao;

    @BeforeAll
    static void setUp() {
        try {
            dao = new MySQLCostsManagerDAO();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addCategory() {
        // category name to test
        String categoryName = "TestCategory";
        try {
            // add category to the database
            dao.addCategory(new Category(categoryName));
        } catch (CostsManagerException e) {
            try {
                // make sure error thrown because category already exists
                assertEquals(dao.getCategoryByName(categoryName).getName(), categoryName);
            } catch (CostsManagerException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    void getCategories() {
        try {
            // test that the list is not empty
            assertTrue(dao.getCategories().size() > 0);
        } catch (CostsManagerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getCategoryByName() {
        // category name to test
        String categoryName = "TestCategory";
        try {
            Category category = dao.getCategoryByName(categoryName);
            assertEquals(category.getName(), categoryName);
        } catch (CostsManagerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addCost() {
        try {
            dao.addCost(new Cost(11, 10,"NIS","description",new Date(2022, 8, 18)));
        } catch (CostsManagerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getCosts() {
        try {
            // test that the list is not empty
            assertTrue(dao.getCosts(new Date(2022, 8, 17), new Date(2022, 8, 19)).size() > 0);
        } catch (CostsManagerException e) {
            e.printStackTrace();
        }
    }
}