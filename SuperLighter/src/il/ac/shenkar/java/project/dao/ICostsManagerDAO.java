package il.ac.shenkar.java.project.dao;

import il.ac.shenkar.java.project.CostsManagerException;

import java.sql.Date;
import java.util.List;

/**
 * interface of the costs manager DAO
 */
public interface ICostsManagerDAO {
    /**
     * add a new category to the database
     * @param category
     * @throws CostsManagerException
     */
    public void addCategory(Category category) throws CostsManagerException;

    /**
     * get all categories in the database
     * @return
     * @throws CostsManagerException
     */
    public List<Category> getCategories() throws CostsManagerException;

    /**
     * return a specific category in the database by name
     * @param name
     * @return
     * @throws CostsManagerException
     */
    public Category getCategoryByName(String name) throws CostsManagerException;

    /**
     * add a new cost to the database
     * @param cost
     * @throws CostsManagerException
     */
    public void addCost(Cost cost) throws CostsManagerException;

    /**
     * get all costs in the database from start date till end date
     * @param start
     * @param end
     * @return
     * @throws CostsManagerException
     */
    public List<Cost> getCosts(Date start, Date end) throws CostsManagerException;
}
