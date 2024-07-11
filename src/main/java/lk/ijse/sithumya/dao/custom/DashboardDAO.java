package lk.ijse.sithumya.dao.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.sithumya.dao.CurdDAO;
import lk.ijse.sithumya.dto.TodayPaymentsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DashboardDAO extends CurdDAO {
    int getTotalStudentsCount() throws SQLException, ClassNotFoundException;

    int getTotalBusCount() throws SQLException, ClassNotFoundException;

    int getTotalDriversCount() throws SQLException, ClassNotFoundException;

    String getUserName() throws SQLException, ClassNotFoundException;

    XYChart.Series<String, Number> getChartData() throws SQLException;
}
