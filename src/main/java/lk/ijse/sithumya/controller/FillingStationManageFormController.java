package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.FillingStationBO;
import lk.ijse.sithumya.dto.FillingStationDTO;
import lk.ijse.sithumya.util.Regex;

import java.sql.SQLException;
import java.util.List;

public class FillingStationManageFormController {

    @FXML
    private ComboBox<String> cmbStationId;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtName;

    private String nextStationId;

    private FillingStationBO fillingStationBO = (FillingStationBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.STATION);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String stationId = cmbStationId.getValue();

        try{
            FillingStationDTO fillingStation = fillingStationBO.searchStation(stationId);
            if(fillingStation != null) {
                boolean isDeleted = fillingStationBO.deleteStation(stationId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Station Delete Successfully!").show();
                    clearFields();
                    refreshStationIds();
                    FillingStationFormController.getController().initialize();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Station Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = nextStationId;
        String name = txtName.getText();
        String location = txtLocation.getText();
        String contact = txtContact.getText();

        if (isTextValid()) {

            try {
                boolean isSaved = fillingStationBO.saveStation(new FillingStationDTO(id, name, location, contact));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Station Saved Successfully!").show();
                    clearFields();
                    refreshStationIds();
                    FillingStationFormController.getController().initialize();
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
        String stationId = cmbStationId.getValue();

        try {
            FillingStationDTO fillingStation = fillingStationBO.searchStation(stationId);

            if(fillingStation != null) {
                fillFields(fillingStation);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Station Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String stationId = cmbStationId.getValue();
        String name = txtName.getText();
        String location = txtLocation.getText();
        String contact = txtContact.getText();

        if (isTextValid()) {
            try {
                boolean isUpdated = fillingStationBO.updateStation(new FillingStationDTO(stationId, name, location, contact));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Station Update Successfully!").show();
                    clearFields();
                    refreshStationIds();
                    FillingStationFormController.getController().initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    void txtContactOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.CONTACT, txtContact);
    }

    @FXML
    void txtLocationKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.ADDRESS, txtLocation);
    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);
    }

    private void clearFields() {
        txtName.setText("");
        txtLocation.setText("");
        txtContact.setText("");
        cmbStationId.getSelectionModel().clearSelection();
    }

    private void fillFields(FillingStationDTO fillingStation) {
        txtName.setText(fillingStation.getName());
        txtLocation.setText(fillingStation.getLocation());
        txtContact.setText(fillingStation.getContact());
        cmbStationId.setValue(fillingStation.getId());
    }

    public void initialize() {
        refreshStationIds();
        generateNextStationId();
    }

    private void generateNextStationId() {
        try {
            nextStationId = fillingStationBO.generateNextStationId();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void refreshStationIds() {
        try {
            List<String> stationIds = fillingStationBO.getAllStationIds();
            cmbStationId.getItems().clear();
            cmbStationId.getItems().addAll(stationIds);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public  boolean isTextValid() {
        boolean isContactValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.CONTACT, txtContact);
        boolean isLocationValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.ADDRESS, txtLocation);
        boolean isNameValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);

        return isContactValid && isLocationValid && isNameValid;
    }
}
