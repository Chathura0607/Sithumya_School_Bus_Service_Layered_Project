package lk.ijse.sithumya.dao.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.sithumya.dao.CurdDAO;
import lk.ijse.sithumya.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends CurdDAO<Payment> {
    double getTotalPaymentAmount() throws SQLException;

    XYChart.Series<String, Number> getChartData() throws SQLException;
}
