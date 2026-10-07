package lk.ijse.sithumya.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import lk.ijse.sithumya.dbConnection.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionUtil {

    private static Connection getConnection() throws SQLException {
        return DbConnection.getInstance().getConnection();
    }

    public static void startTransaction() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.setAutoCommit(false);
        }
    }

    public static void endTransaction() {
        try {
            Connection con = getConnection();
            if (con != null && !con.getAutoCommit()) {
                con.commit();
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            Platform.runLater(() -> new Alert(Alert.AlertType.ERROR, "Transaction commit error: " + e.getMessage()).show());
        }
    }

    public static void rollBack() {
        try {
            Connection con = getConnection();
            if (con != null && !con.getAutoCommit()) {
                con.rollback();
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            Platform.runLater(() -> new Alert(Alert.AlertType.ERROR, "Transaction rollback error: " + e.getMessage()).show());
        }
    }
}

