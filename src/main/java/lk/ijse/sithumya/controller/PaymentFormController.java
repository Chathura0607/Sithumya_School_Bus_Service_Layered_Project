package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.PaymentBO;
import lk.ijse.sithumya.bo.custom.PaymentPlanBO;
import lk.ijse.sithumya.util.SqlUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentFormController {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private Pane bodyPane;

    @FXML
    private Label lblIncome;

    @FXML
    private Label lblPlanCount;

    private PaymentPlanBO paymentPlanBO = (PaymentPlanBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.PLAN);
    private PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.PAYMENT);

    @FXML
    void btnMonthlyFeeOnAction(ActionEvent event) {
        bodyPane.getChildren().clear();
        try {
            bodyPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/monthly_fee_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnPaymentPlanOnAction(ActionEvent event) {
        bodyPane.getChildren().clear();
        try {
            bodyPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/payment_plan_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnPenaltyOnAction(ActionEvent event) {
        bodyPane.getChildren().clear();
        try {
            bodyPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/penalty_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public void initialize() {
        try {
            populateChart(barChart);
            lblPlanCount.setText(String.valueOf(getTotalPlanCount()));
            lblIncome.setText(String.valueOf(getTotalPaymentAmount()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double getTotalPaymentAmount() {
        try {
            return paymentBO.getTotalPaymentAmount();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return 0.00;
    }

    private int getTotalPlanCount() {
        try {
            return paymentPlanBO.getTotalPlanCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return 0;
    }

    private void populateChart(BarChart<String, Number> barChart) throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT s.Name AS Student_Name, SUM(p.Amount) AS Total_Payment " +
                "FROM Student s " +
                "LEFT JOIN Payment p ON s.Student_ID = p.Student_ID " +
                "GROUP BY s.Name");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        while (resultSet.next()) {
            String studentName = resultSet.getString("Student_Name");
            double totalPayment = resultSet.getDouble("Total_Payment");
            series.getData().add(new XYChart.Data<>(studentName, totalPayment));
        }
        barChart.getData().add(series);
        for (Node n : barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #E0A383;");
        }
    }

    private static void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Navigation Error");
        alert.showAndWait();
    }
}
