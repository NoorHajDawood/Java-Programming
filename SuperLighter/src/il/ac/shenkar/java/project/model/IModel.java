package il.ac.shenkar.java.project.model;

import il.ac.shenkar.java.project.CostsManagerException;
import il.ac.shenkar.java.project.dao.Category;
import il.ac.shenkar.java.project.dao.Cost;

import java.sql.Date;
import java.util.List;

/**
 * Model interface
 */
public interface IModel {
    /**
     * call get categories from DAO
     * @return
     * @throws CostsManagerException
     */
    public List<Category> getCategories() throws CostsManagerException;

    /**
     * call add category from DAO
     * @param name
     * @throws CostsManagerException
     */
    public void addCategory(String name) throws CostsManagerException;

    /**
     * call get costs from DAO
     * @param start
     * @param end
     * @return
     * @throws CostsManagerException
     */
    public List<Cost> getCosts(Date start, Date end) throws CostsManagerException;

    /**
     * call add cost from DAO
     * @param sum
     * @param currency
     * @param categoryName
     * @param description
     * @param date
     * @throws CostsManagerException
     */
    public void addCost(float sum, String currency, String categoryName, String description, Date date) throws CostsManagerException;


}
