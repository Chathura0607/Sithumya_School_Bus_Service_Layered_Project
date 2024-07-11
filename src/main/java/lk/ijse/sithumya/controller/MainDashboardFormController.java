package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.DashboardBO;
import lk.ijse.sithumya.dto.TodayPaymentsDTO;
import lk.ijse.sithumya.view.tm.TodayPaymentsTm;
import lk.ijse.sithumya.util.Navigation;
import lk.ijse.sithumya.util.SqlUtil;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainDashboardFormController {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPlanName;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private Label lblBusCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDriverCount;

    @FXML
    private Label lblStudentCount;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblUser;

    @FXML
    private TableView tblTodayPayments;

    private DashboardBO dashboardBO = (DashboardBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.DASHBOARD);

    @FXML
    void btnSendOnAction(ActionEvent event) {
        Navigation.navigateToSendTimeForm();
    }

    @FXML
    private void initialize() throws SQLException {
        loadTodayPayments();
        setCellValueFactory();

        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        lblTime.setText(time.format(DateTimeFormatter.ofPattern("hh:mm a")));
        lblDate.setText(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        lblStudentCount.setText(String.valueOf(getTotalStudentsCount()));
        lblBusCount.setText(String.valueOf(getTotalBusCount()));
        lblDriverCount.setText(String.valueOf(getTotalDriversCount()));
        lblUser.setText(getUsername());

        try {
            populateChart(barChart);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateChart(BarChart<String, Number> barChart) throws SQLException {
        ResultSet resultSet = SqlUtil.sql("SELECT School_Name, COUNT(*) AS Count FROM Student GROUP BY School_Name");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        while (resultSet.next()) {
            String propertyType = resultSet.getString("School_Name");
            int count = resultSet.getInt("Count");
            series.getData().add(new XYChart.Data<>(propertyType, count));
        }
        barChart.getData().add(series);
        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #E0A383;");
        }
    }

    private void setCellValueFactory(){
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colPlanName.setCellValueFactory(new PropertyValueFactory<>("paymentPlanName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));
    }

    private void loadTodayPayments() {
        tblTodayPayments.getItems().clear();

        try {
            ArrayList<TodayPaymentsDTO> loadTodayPayments = dashboardBO.loadTodayPayments();

            for (TodayPaymentsDTO todayPayments : loadTodayPayments) {
                tblTodayPayments.getItems().add(new TodayPaymentsTm(
                        todayPayments.getPaymentId(),
                        todayPayments.getStudentName(),
                        todayPayments.getPaymentPlanName(),
                        todayPayments.getPaymentAmount()
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private int getTotalStudentsCount() {
        try {
            return dashboardBO.getTotalStudentsCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return 0;
    }

    private int getTotalBusCount() {
        try {
            return dashboardBO.getTotalBusCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return 0;
    }

    private int getTotalDriversCount() {
        try {
            return dashboardBO.getTotalDriversCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return 0;
    }

    private String getUsername() {
        try {
            return dashboardBO.getUserName();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return null;
    }
}
