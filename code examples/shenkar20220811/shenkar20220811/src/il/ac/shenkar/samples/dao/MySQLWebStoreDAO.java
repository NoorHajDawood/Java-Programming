package il.ac.shenkar.samples.dao;

import java.sql.*;
import java.util.*;

public class MySQLWebStoreDAO implements IWebStoreDAO {

    private String driver = "com.mysql.jdbc.Driver";
    private String connectionString = "jdbc:mysql://localhost:8889/a";

    public MySQLWebStoreDAO() throws ClassNotFoundException {
        Class.forName(driver);
    }

    @Override
    public boolean addProduct(Product product) throws WebStoreDBException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Product> getProducts() throws WebStoreDBException {

        Connection connection = null;
        ResultSet rs = null;
        Statement statement = null;
        List<Product> list = new LinkedList<>();

        try {

            connection = DriverManager.getConnection(connectionString,"a","b");

            statement = connection.createStatement();

            rs = statement.executeQuery("SELECT id,price FROM products ORDER BY id");

            while(rs.next())
            {
                list.add(new Product(rs.getInt("id"), rs.getDouble("price")));
            }

        } catch(SQLException e) {
            throw new WebStoreDBException("problem with getting all products",e);
        }
        finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }
}
