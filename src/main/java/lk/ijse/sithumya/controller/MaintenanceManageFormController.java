package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.BusBO;
import lk.ijse.sithumya.bo.custom.MaintenanceBO;
import lk.ijse.sithumya.dto.MaintenanceDTO;
import lk.ijse.sithumya.util.Regex;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MaintenanceManageFormController {

    @FXML
    private ComboBox<String> cmbBusId;

    @FXML
    private DatePicker dtpDate;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtMaintenanceId;

    private MaintenanceBO maintenanceBO = (MaintenanceBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.MAINTENANCE);
    private BusBO busBO = (BusBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.BUS);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String maintenanceId = txtMaintenanceId.getText();

        try {
            MaintenanceDTO maintenance = maintenanceBO.searchMaintenance(maintenanceId);

            if (maintenance != null) {
                boolean isDeleted = maintenanceBO.deleteMaintenance(maintenanceId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Maintenance Deleted Successfully!").show();
                    clearFields();
                    refreshBusIds();
                    MaintenanceFormController.getController().initialize();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Maintenance Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String maintenanceId = txtMaintenanceId.getText();
        String busId = cmbBusId.getValue();
        String description = txtDescription.getText();
        double cost = Double.parseDouble(txtCost.getText());
        Date date = Date.valueOf(dtpDate.getValue());

        if (isTextValid()) {
            try {
                boolean isSaved = maintenanceBO.saveMaintenance(new MaintenanceDTO(maintenanceId, busId, description, cost, date));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Maintenance Saved Successfully!").show();
                    clearFields();
                    refreshBusIds();
                    MaintenanceFormController.getController().initialize();
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
        String maintenanceId = txtMaintenanceId.getText();

        try {
            MaintenanceDTO maintenance = maintenanceBO.searchMaintenance(maintenanceId);

            if(maintenance != null) {
                fillFields(maintenance);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Maintenance Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String maintenanceId = txtMaintenanceId.getText();
        String busId = cmbBusId.getValue();
        String description = txtDescription.getText();
        double cost = Double.parseDouble(txtCost.getText());
        Date date = Date.valueOf(dtpDate.getValue().toString());

        if (isTextValid()) {
            try {

                boolean isUpdated = maintenanceBO.updateMaintenance(new MaintenanceDTO(maintenanceId, busId, description, cost, date));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Maintenance Update Successfully!").show();
                    clearFields();
                    refreshBusIds();
                    MaintenanceFormController.getController().initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    void dtpDateOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.DATE, dtpDate.getEditor());
    }

    @FXML
    void txtCostOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtCost);
    }

    @FXML
    void txtDescriptionOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtDescription);
    }

    @FXML
    void txtMaintenanceIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.MAINTENANCEId, txtMaintenanceId);
    }

    private void clearFields() {
        txtMaintenanceId.setText("");
        txtCost.setText("");
        txtDescription.setText("");
        dtpDate.setValue(null);
        cmbBusId.getSelectionModel().clearSelection();
    }

    private void fillFields(MaintenanceDTO maintenance) {
        txtMaintenanceId.setText(maintenance.getMaintenanceId());
        txtCost.setText(String.valueOf(maintenance.getCost()));
        txtDescription.setText(maintenance.getDescription());
        cmbBusId.setValue(maintenance.getBusId());

        Date maintenanceDate = maintenance.getDate();
        if (maintenanceDate != null) {
            dtpDate.setValue(maintenanceDate.toLocalDate());
        } else {
            dtpDate.setValue(null);
        }
    }

    public void initialize() {
        refreshBusIds();
    }

    private void refreshBusIds() {
        try {
            List<String> busIds = busBO.getAllBusIds();
            cmbBusId.getItems().clear();
            cmbBusId.getItems().addAll(busIds);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public boolean isTextValid() {
        boolean isDateValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.DATE, dtpDate.getEditor());
        boolean isCostValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtCost);
        boolean isDescriptionValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtDescription);
        boolean isMaintenanceIdValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.MAINTENANCEId, txtMaintenanceId);

        return isDateValid && isCostValid && isDescriptionValid && isMaintenanceIdValid;
    }
}
