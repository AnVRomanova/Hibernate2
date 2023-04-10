package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util2 {
    private static final String DB_DRIVER ="com.mysql.cj.jdbc.Driver";
    private static final String DB_URL ="jdbc:mysql://localhost:3306/mydbbase";
    private static final String DB_USERNAME ="root";
    private static final String DB_PASSWORD ="1234";
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;

        try {
            Class.forName(DB_DRIVER).newInstance();
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("OK");
        } catch (SQLException e) {
            throw e;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


        return connection;
    }

}
