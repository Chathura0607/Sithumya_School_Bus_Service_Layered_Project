package lk.ijse.sithumya.util;

import lk.ijse.sithumya.dbConnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {
    @SuppressWarnings("unchecked")
    public static <T> T sql(String sql, Object... args) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                pstm.setObject((i + 1), args[i]);
            }
        }

        String trimmedSql = (sql != null) ? sql.trim().toUpperCase() : "";
        if (trimmedSql.startsWith("SELECT") || trimmedSql.startsWith("SHOW") || trimmedSql.startsWith("DESCRIBE")) {
            return (T) pstm.executeQuery();
        } else {
            return (T) (Boolean) (pstm.executeUpdate() > 0);
        }
    }
}


