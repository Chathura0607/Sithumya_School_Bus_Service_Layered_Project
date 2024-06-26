package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.TodayPaymentsDTO;

import java.sql.SQLException;
import java.util.List;

public interface DashboardBO extends SuperBO {
    int getTotalStudentsCount() throws SQLException, ClassNotFoundException;

    int getTotalBusCount() throws SQLException, ClassNotFoundException;

    int getTotalDriversCount() throws SQLException, ClassNotFoundException;

    String getUserName() throws SQLException, ClassNotFoundException;

    List<TodayPaymentsDTO> loadTodayPayments() throws SQLException;
}
