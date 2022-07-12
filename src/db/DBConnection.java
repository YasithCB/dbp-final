package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * author  Yasith C Bandara
 * created 7/12/2022 - 10:12 AM
 * project DBP-Final
 */

public class DBConnection {
    public static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ijse","root","1234");
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        return dbConnection == null? dbConnection= new DBConnection() : dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }
}
