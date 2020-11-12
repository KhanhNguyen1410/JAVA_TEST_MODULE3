package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/testmodule3?useSSL=false";
    private static String jdbcUsername="root";
    private static String jdbcPassword = "MySQL1410";

    public static Connection getConnect(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
}
}
