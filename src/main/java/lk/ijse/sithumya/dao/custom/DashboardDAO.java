package lk.ijse.sithumya.dao.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.sithumya.dao.CurdDAO;

import java.sql.SQLException;

public interface DashboardDAO extends CurdDAO<Object> {
    int getTotalStudentsCount() throws SQLException, ClassNotFoundException;

    int getTotalBusCount() throws SQLException, ClassNotFoundException;

    int getTotalDriversCount() throws SQLException, ClassNotFoundException;

    String getUserName() throws SQLException, ClassNotFoundException;

    XYChart.Series<String, Number> getChartData() throws SQLException;
}
