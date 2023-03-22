package il.ac.shenkar.java.project.model;

import il.ac.shenkar.java.project.CostsManagerException;
import il.ac.shenkar.java.project.dao.Category;
import il.ac.shenkar.java.project.dao.Cost;
import il.ac.shenkar.java.project.dao.MySQLCostsManagerDAO;

import java.sql.Date;
import java.util.List;

/**
 * Logic of the mvvm
 */
public class Model implements IModel {
    /**
     * DAO reference
     */
    private MySQLCostsManagerDAO costsManagerDB;

    /**
     * deafult constructor to initialize the DAO reference
     * @throws ClassNotFoundException
     */
    public Model() throws ClassNotFoundException{
        costsManagerDB = new MySQLCostsManagerDAO();
    }

    /**
     * call get categories from DAO
     * @return
     * @throws CostsManagerException
     */
    public List<Category> getCategories() throws CostsManagerException {
        return costsManagerDB.getCategories();
    }
    /**
     * call add category from DAO
     * @param name
     * @throws CostsManagerException
     */
    public void addCategory(String name) throws CostsManagerException{
        Category newCate = new Category(name);
        costsManagerDB.addCategory(newCate);
    }
    /**
     * call get costs from DAO
     * @param start
     * @param end
     * @return
     * @throws CostsManagerException
     */
    public List<Cost> getCosts(Date start , Date end) throws CostsManagerException{
        return costsManagerDB.getCosts(start, end);
    }
    /**
     * call add cost from DAO
     * @param sum
     * @param currency
     * @param categoryName
     * @param description
     * @param date
     * @throws CostsManagerException
     */
    public void addCost(float sum, String currency, String categoryName, String description, Date date) throws CostsManagerException{
        Cost newCost = new Cost(costsManagerDB.getCategoryByName(categoryName).getId(),sum, currency, description,date);
        costsManagerDB.addCost(newCost);
    }
}
