package il.ac.shenkar.java.project.dao;

import il.ac.shenkar.java.project.CostsManagerException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * database access object implemented for MySQL
 */
public class MySQLCostsManagerDAO implements ICostsManagerDAO {
    /**
     * name of the MySQL driver
     */
    private String driver = "com.mysql.jdbc.Driver";
    /**
     * connection string to the database
     */
    private String connectionString = "jdbc:mysql://localhost:3306/costsmanager";

    /**
     * constructor to make sure that the MySQL driver is imported to the project
     * @throws ClassNotFoundException
     */
    public MySQLCostsManagerDAO() throws ClassNotFoundException {
        Class.forName(driver);
    }

    /**
     * add a new category to the database
     * @param category
     * @throws CostsManagerException
     */
    @Override
    public void addCategory(Category category) throws CostsManagerException {
        // connect to the database and make a prepared statement to insert a category
        try(Connection connection =DriverManager.getConnection(connectionString, "a", "b");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO categories (name) VALUES(?)")){
            // make sure that the category doesn't already exist
            if(getCategoryByName(category.getName()) != null) {
                throw new CostsManagerException("Category already exists");
            }
            // import the name of category from function arguments
            statement.setString(1, category.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CostsManagerException("Error connecting to database", e);
        }
    }

    /**
     * get all categories in the database
     * @return
     * @throws CostsManagerException
     */
    @Override
    public List<Category> getCategories() throws CostsManagerException {
        // a list to hold the result
        List<Category> list = new LinkedList<Category>();
        // connect to the database and make a prepared statement to get all categories
        try(Connection connection =DriverManager.getConnection(connectionString, "a", "b");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories ORDER BY id");
            ResultSet rs = statement.executeQuery()){
            // iterate on the result set and insert all to the result list
            while(rs.next()){
                list.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new CostsManagerException("Error connecting to database", e);
        }
        return list;
    }

    /**
     * return a specific category in the database by name
     * @param name
     * @return
     * @throws CostsManagerException
     */
    @Override
    public Category getCategoryByName(String name) throws CostsManagerException {
        // category result
        Category category = null;
        ResultSet rs = null;
        // connect to the database and make a prepared statement to get a category by name
        try(Connection connection =DriverManager.getConnection(connectionString, "a", "b");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories WHERE name = ?")){
            // set name of the category from the function arguments
            statement.setString(1, name);
            rs = statement.executeQuery();
            // set the return result
            if(rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new CostsManagerException("Error connecting to database", e);
        } finally {
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return category;
    }

    /**
     * add a new cost to the database
     * @param cost
     * @throws CostsManagerException
     */
    @Override
    public void addCost(Cost cost) throws CostsManagerException {
        // connect to the database and make a prepared statement to insert a cost
        try(Connection connection = DriverManager.getConnection(connectionString, "a", "b");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO costs (category_id, sum, currency, description, date) VALUES(?, ?, ?, ?, ?)")){
            // set statement parameters from the function arguments
            statement.setInt(1, cost.getCategoryId());
            statement.setFloat(2, cost.getSum());
            statement.setString(3, cost.getCurrency());
            statement.setString(4, cost.getDescription());
            statement.setDate(5, cost.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CostsManagerException("Error connecting to database", e);
        }
    }

    /**
     * get all costs in the database from start date till end date
     * @param start
     * @param end
     * @return
     * @throws CostsManagerException
     */
    @Override
    public List<Cost> getCosts(Date start, Date end) throws CostsManagerException {
        ResultSet rs = null;
        // query string of the prepared statemnt
        String query = "SELECT costs.*, categories.name as category_name FROM costs JOIN categories ON costs.category_id = categories.id WHERE costs.date >= ? AND costs.date <= ?";
        // result set
        List<Cost> list = new LinkedList<Cost>();
        // connect to the database and make a prepared statement to get all costs
        try(Connection connection =DriverManager.getConnection(connectionString, "a", "b");
            PreparedStatement statement = connection.prepareStatement(query)){
            // set start and end date from the function arguments
            statement.setDate(1, start);
            statement.setDate(2, end);
            rs = statement.executeQuery();
            while(rs.next()){
                list.add(new Cost(rs.getInt("id"), rs.getInt("category_id"), rs.getString("category_name"), rs.getFloat("sum"), rs.getString("currency"), rs.getString("description"), rs.getDate("date")));
            }
        } catch (SQLException e) {
            throw new CostsManagerException("Error connecting to database", e);
        }
        return list;
    }
}
