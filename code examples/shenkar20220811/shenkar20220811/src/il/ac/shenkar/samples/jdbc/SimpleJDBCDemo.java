package il.ac.shenkar.samples.jdbc;

import java.sql.*;

public class SimpleJDBCDemo {

    public static String driver = "com.mysql.jdbc.Driver";
    public static String connectionString = "jdbc:mysql://localhost:8889/a";

    public static void main(String args[]) {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            connection = DriverManager.getConnection(connectionString,"a","b");
            statement = connection.createStatement();
            System.out.println(statement.executeUpdate("insert into products values (100212,2.5)"));
            System.out.println(statement.executeUpdate("insert into products values (100213,42.5)"));
            System.out.println(statement.executeUpdate("insert into products values (100214,25.5)"));
            System.out.println(statement.executeUpdate("insert into products values (100215,122.5)"));

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
