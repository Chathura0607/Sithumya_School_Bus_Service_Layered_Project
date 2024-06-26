package lk.ijse.sithumya.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.TodayPaymentsDTO;
import lk.ijse.sithumya.dto.tm.TodayPaymentsTm;

import java.sql.SQLException;
import java.util.List;

public interface DashboardBO extends SuperBO {
    int getTotalStudentsCount() throws SQLException, ClassNotFoundException;

    int getTotalBusCount() throws SQLException, ClassNotFoundException;

    int getTotalDriversCount() throws SQLException, ClassNotFoundException;

    String getUserName() throws SQLException, ClassNotFoundException;

    //ObservableList<TodayPaymentsTm> loadTodayPayments() throws SQLException;
    List<TodayPaymentsDTO> loadTodayPayments() throws SQLException;
}
