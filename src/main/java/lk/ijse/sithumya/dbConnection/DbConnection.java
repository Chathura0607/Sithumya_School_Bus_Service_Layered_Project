package lk.ijse.sithumya.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private Connection connection;

    private DbConnection() throws SQLException {
        String url = System.getProperty("db.url", "jdbc:mysql://localhost:3306/school_bus_service_management_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        String user = System.getProperty("db.user", "root");
        String password = System.getProperty("db.password", "Chathu0607");
        connection = DriverManager.getConnection(url, user, password);
    }

    public static DbConnection getInstance() throws SQLException {
        if (dbConnection == null || dbConnection.getConnection() == null || dbConnection.getConnection().isClosed()) {
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}

