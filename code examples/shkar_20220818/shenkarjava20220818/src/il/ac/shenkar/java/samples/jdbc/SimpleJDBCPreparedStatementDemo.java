package il.ac.shenkar.java.samples.jdbc;

import java.sql.*;

public class SimpleJDBCPreparedStatementDemo {

    public static String driver = "com.mysql.jdbc.Driver";
    public static String connectionString = "jdbc:mysql://localhost:8889/a";

    public static void main(String args[]) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            connection = DriverManager.getConnection(connectionString,"a","b");
            statement = connection.prepareStatement("INSERT INTO products VALUES(?,?)");
            statement.setInt(1,4400212);
            statement.setDouble(2,2.5);
            statement.addBatch();
            statement.setInt(1,4400218);
            statement.setDouble(2,2.8);
            statement.addBatch();
            int vec[] = statement.executeBatch();
            for(int num : vec) {
                System.out.println(num);
            }

        }
        catch(SQLException | ClassNotFoundException e) {


            e.printStackTrace();

        }
        finally {

            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }


    }




}
