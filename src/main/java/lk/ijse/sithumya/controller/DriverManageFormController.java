package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.DriverBO;
import lk.ijse.sithumya.dto.DriverDTO;
import lk.ijse.sithumya.util.Regex;

import java.sql.SQLException;

public class DriverManageFormController {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    private DriverBO driverBO = (DriverBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.DRIVER);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String driverId = txtId.getText();

        try{
            DriverDTO driver = driverBO.searchDriver(driverId);
            if(driver != null) {
                boolean isDeleted = driverBO.deleteDriver(driverId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Driver Delete Successfully!").show();
                    clearFields();
                    DriverFormController.getController().initialize();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Driver Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String driverId = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();

        if (isTextValid()) {
            try {
                boolean isSaved = driverBO.saveDriver(new DriverDTO(driverId, name, address, email, contact));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Driver Saved Successfully!").show();
                    clearFields();
                    DriverFormController.getController().initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String driverId = txtId.getText();

        try {
            DriverDTO driver = driverBO.searchDriver(driverId);

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
        String driverId = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();

        if (isTextValid()) {
            try {
                boolean isUpdated = driverBO.updateDriver(new DriverDTO(driverId, name, address, email, contact));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Driver Update Successfully!").show();
                    clearFields();
                    DriverFormController.getController().initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    void txtAddressOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.ADDRESS, txtAddress);
    }

    @FXML
    void txtContactOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.CONTACT, txtContact);
    }

    @FXML
    void txtDriverIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.NIC, txtId);
    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.EMAIL, txtEmail);
    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);
    }

    private void fillFields(DriverDTO driver) {
        txtId.setText(driver.getDriverId());
        txtName.setText(driver.getName());
        txtAddress.setText(driver.getAddress());
        txtEmail.setText(driver.getEmail());
        txtContact.setText(driver.getContactNumber());
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtContact.setText("");
    }

    public boolean isTextValid() {
        boolean isIdValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.NIC, txtId);
        boolean isNameValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);
        boolean isEmailValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.EMAIL, txtEmail);
        boolean isAddressValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.ADDRESS, txtAddress);
        boolean isContactValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.CONTACT, txtContact);

        return isIdValid && isNameValid && isEmailValid && isAddressValid && isContactValid;
    }
}
