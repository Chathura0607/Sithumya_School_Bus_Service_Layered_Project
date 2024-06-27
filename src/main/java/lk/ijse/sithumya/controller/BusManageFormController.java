package lk.ijse.sithumya.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.BusBO;
import lk.ijse.sithumya.bo.custom.DriverBO;
import lk.ijse.sithumya.dto.BusDTO;
import lk.ijse.sithumya.util.Regex;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class BusManageFormController {

    @FXML
    private ComboBox<String> cmbBusId;

    @FXML
    private JFXComboBox<String> cmbDriverId;

    @FXML
    private DatePicker dtpDate;

    @FXML
    private TextField txtName;

    private String nextBusId;

    private BusBO busBO = (BusBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.BUS);
    private DriverBO driverBO = (DriverBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.DRIVER);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String busId = cmbBusId.getValue();

        try{
            BusDTO bus = busBO.searchBus(busId);
            if(bus != null) {
                boolean isDeleted = busBO.deleteBus(busId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Bus Delete Successfully!").show();
                    clearFields();
                    refreshBusIds();
                    BusFormController.getController().loadAllBuses();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Bus Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String busId = nextBusId;
        String name = txtName.getText();
        String driverId = cmbDriverId.getValue();
        Date date = Date.valueOf(dtpDate.getValue());

        if (isTextValid()) {
            try {
                boolean isSaved = busBO.saveBus(new BusDTO(busId, name, driverId, date));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Bus Saved Successfully!").show();
                    clearFields();
                    refreshBusIds();
                    BusFormController.getController().loadAllBuses();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String busId = cmbBusId.getValue();

        try {
            BusDTO bus = busBO.searchBus(busId);

            if(bus != null) {
                fillFields(bus);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Bus Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String busId = cmbBusId.getValue();
        String name = txtName.getText();
        String driverId = cmbDriverId.getValue();
        Date date = Date.valueOf(dtpDate.getValue());

        if (isTextValid()) {

            try {
                boolean isUpdated = busBO.updateBus(new BusDTO(busId, name, driverId, date));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Bus Update Successfully!").show();
                    clearFields();
                    refreshBusIds();
                    BusFormController.getController().loadAllBuses();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    void dtpDateOnAction(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.DATE, dtpDate.getEditor());
    }

    @FXML
    void txtNameOnAction(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);
    }

    public void initialize() {
        try {
            /*List<String> busIds = busBO.getAllBusIds();
            cmbBusId.getItems().addAll(busIds);*/
            refreshBusIds();

            List<String> driverIds = driverBO.getAllDriverIds();
            cmbDriverId.getItems().addAll(driverIds);

            nextBusId = generateNextBusId();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void refreshBusIds() {
        try {
            List<String> busIds = busBO.getAllBusIds();
            cmbBusId.getItems().clear();
            cmbBusId.getItems().addAll(busIds);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtName.setText("");
        cmbDriverId.getSelectionModel().clearSelection();
        cmbBusId.getSelectionModel().clearSelection();
        dtpDate.setValue(null);
    }

    private void fillFields(BusDTO bus) {
        txtName.setText(bus.getName());
        cmbDriverId.setValue(bus.getDriverId());

        java.sql.Date assignedDate = bus.getDriverAssignedDate();
        if (assignedDate != null) {
            dtpDate.setValue(assignedDate.toLocalDate());
        } else {
            dtpDate.setValue(null);
        }
    }

    private String generateNextBusId() throws SQLException, ClassNotFoundException {
        return nextBusId = busBO.generateNextBusId();
    }

    public boolean isTextValid() {
        boolean isNameValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);
        boolean isDateValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.DATE, dtpDate.getEditor());

        return isNameValid && isDateValid;
    }
}
