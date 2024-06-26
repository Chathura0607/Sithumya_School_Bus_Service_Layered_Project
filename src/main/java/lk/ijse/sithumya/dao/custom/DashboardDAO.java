package lk.ijse.sithumya.dao.custom;

import lk.ijse.sithumya.dao.CurdDAO;

import java.sql.SQLException;
import java.util.List;

public interface DashboardDAO extends CurdDAO {
    int getTotalStudentsCount() throws SQLException, ClassNotFoundException;

    int getTotalBusCount() throws SQLException, ClassNotFoundException;

    int getTotalDriversCount() throws SQLException, ClassNotFoundException;

    String getUserName() throws SQLException, ClassNotFoundException;

    List loadTodayPayments() throws SQLException;
}
