package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.DriverBO;
import lk.ijse.sithumya.bo.custom.SalaryBO;
import lk.ijse.sithumya.dto.DriverDTO;
import lk.ijse.sithumya.util.Regex;

import java.sql.SQLException;
import java.util.List;

public class SalaryManageFormController {

    @FXML
    private ComboBox<String> cmbDriverId;

    @FXML
    private TextField txtMonthlySalary;

    @FXML
    private TextField txtPayment;

    private SalaryBO salaryBO = (SalaryBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.SALARY);
    private DriverBO driverBO = (DriverBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.DRIVER);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String driverId = cmbDriverId.getValue();

        try {
            DriverDTO driver = salaryBO.salarySearchByDriverId(driverId);

            if(driver != null) {
                fillFields(driver);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Driver Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String driverId = cmbDriverId.getValue();
        double salary = Double.parseDouble(txtMonthlySalary.getText());
        double payment = Double.parseDouble(txtPayment.getText());
        double paymentDue = salary - payment;

        if (isTextValid()) {
            try {
                boolean isUpdated = salaryBO.updateSalary(new DriverDTO(driverId, salary, payment, paymentDue));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Salary Update Successfully!").show();
                    clearFields();
                    SalaryFormController.getController().initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    void txtPaymentOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtPayment);
    }

    @FXML
    void txtSalaryOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtMonthlySalary);
    }

    private void clearFields() {
        cmbDriverId.getSelectionModel().clearSelection();
        txtMonthlySalary.setText("");
        txtPayment.setText("");
    }

    private void fillFields(DriverDTO driver) {
        cmbDriverId.setValue(driver.getDriverId());
        txtMonthlySalary.setText(String.valueOf(driver.getSalary()));
        txtPayment.setText(String.valueOf(driver.getPayment()));
    }

    public void initialize() {
        refreshDriverIds();
    }

    private void refreshDriverIds() {
        try {
            List<String> driverIds = driverBO.getAllDriverIds();
            cmbDriverId.getItems().clear();
            cmbDriverId.getItems().addAll(driverIds);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public boolean isTextValid() {
        boolean isPaymentValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtPayment);
        boolean isSalaryValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtMonthlySalary);

        return isPaymentValid && isSalaryValid;
    }
}
