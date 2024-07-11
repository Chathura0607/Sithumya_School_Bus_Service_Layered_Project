package lk.ijse.sithumya.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private Connection connection;

    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/school_bus_service_management_system",
                "root",
                "Chathu0607"
        );
        //System.out.println(connection.toString());
    }

    public static DbConnection getInstance() throws SQLException {
        return (dbConnection == null) ? dbConnection = new DbConnection() : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
