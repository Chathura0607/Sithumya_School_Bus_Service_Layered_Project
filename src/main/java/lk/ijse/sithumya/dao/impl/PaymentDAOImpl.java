package lk.ijse.sithumya.dao.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.sithumya.dao.custom.PaymentDAO;
import lk.ijse.sithumya.entity.Payment;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPayments = new ArrayList<>();
        ResultSet resultSet = SqlUtil.sql("SELECT * from Payment");
        while (resultSet.next()) {
            Payment payment = new Payment(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDate(5),
                    resultSet.getString(6)
            );
            allPayments.add(payment);
        }
        return allPayments;
    }

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.sql("INSERT INTO Payment (Payment_ID, Student_ID, Payment_Plan_ID, Amount, Payment_Date, Is_Completed) VALUES (?, ?, ?, ?, ?, ?)",
                entity.getPaymentId(),
                entity.getStudentId(),
                entity.getPaymentPlanId(),
                entity.getAmount(),
                entity.getPaymentDate(),
                entity.getIsCompleted());
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public double getTotalPaymentAmount() throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT SUM(Amount) AS TotalAmount FROM Payment;");
        return resultSet.next() ? resultSet.getDouble("TotalAmount") : 0.00;
    }

    @Override
    public XYChart.Series<String, Number> getChartData() throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT s.Name AS Student_Name, SUM(p.Amount) AS Total_Payment " +
                "FROM Student s " +
                "LEFT JOIN Payment p ON s.Student_ID = p.Student_ID " +
                "GROUP BY s.Name");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        while (resultSet.next()) {
            String propertyType = resultSet.getString("Student_Name");
            double count = resultSet.getDouble("Total_Payment");
            series.getData().add(new XYChart.Data<>(propertyType, count));
        }
        return series;
    }
}
