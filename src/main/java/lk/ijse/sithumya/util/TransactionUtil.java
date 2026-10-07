package lk.ijse.sithumya.util;

import javafx.scene.control.Alert;
import lk.ijse.sithumya.dbConnection.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionUtil {
    public static Connection connection;

    static {
        try {
            connection = DbConnection.getInstance().getConnection();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public static void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public static void endTransaction() {
        try {
            connection.commit();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public static void rollBack() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
