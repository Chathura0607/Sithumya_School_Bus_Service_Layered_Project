package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.BusBO;
import lk.ijse.sithumya.bo.custom.FillingStationBO;
import lk.ijse.sithumya.bo.custom.FuelingRecordBO;
import lk.ijse.sithumya.dto.FuelingRecordDTO;
import lk.ijse.sithumya.util.Regex;
import lk.ijse.sithumya.view.tm.FuelingRecordTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuelingRecordFormController {

    @FXML
    private ComboBox<String> cmbBusId;

    @FXML
    private ComboBox<String> cmbStationId;

    @FXML
    private TableColumn<?, ?> colRecordId;

    @FXML
    private TableColumn<?, ?> colBusId;

    @FXML
    private TableColumn<?, ?> colStationId;

    @FXML
    private TableColumn<?, ?> colTotalCost;

    @FXML
    private TableColumn<?, ?> colPayment;

    @FXML
    private TableColumn<?, ?> colDebt;

    @FXML
    private TableView<FuelingRecordTm> tblFueling;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtPayment;

    private FuelingRecordBO fuelingRecordBO = (FuelingRecordBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.RECORD);
    private BusBO busBO = (BusBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.BUS);
    private FillingStationBO fillingStationBO = (FillingStationBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.STATION);

    @FXML
    void btnRecordOnAction(ActionEvent event) {
        try {
            String busId = cmbBusId.getValue();
            String stationId = cmbStationId.getValue();
            double totalCost = Double.parseDouble(txtCost.getText());
            double paymentAmount = Double.parseDouble(txtPayment.getText());
            double debtAmount = totalCost - paymentAmount;

            if (isTextValid()) {
                boolean isSaved = fuelingRecordBO.isRecordSaved(new FuelingRecordDTO(busId, stationId, totalCost, paymentAmount, debtAmount));

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Record Update Successfully!").show();
                    initialize();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save record!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtCostOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtCost);
    }

    @FXML
    void txtPaymentOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtPayment);
    }

    private void clearFields() {
        cmbStationId.getSelectionModel().clearSelection();
        cmbBusId.getSelectionModel().clearSelection();
        txtCost.setText("");
        txtPayment.setText("");
    }

    public void initialize() {
        setCellValueFactory();
        loadAllRecords();

        refreshBusIds();
        refreshStationIds();
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

    private void refreshBusIds() {
        try {
            List<String> busIds = busBO.getAllBusIds();
            cmbBusId.getItems().clear();
            cmbBusId.getItems().addAll(busIds);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllRecords() {
        tblFueling.getItems().clear();

        try {
            ArrayList<FuelingRecordDTO> allFuelingRecords = fuelingRecordBO.getAllFuelingRecords();

            for (FuelingRecordDTO fuelingRecordDTO : allFuelingRecords) {
                tblFueling.getItems().add(new FuelingRecordTm(
                        fuelingRecordDTO.getRecordId(),
                        fuelingRecordDTO.getBusId(),
                        fuelingRecordDTO.getStationId(),
                        fuelingRecordDTO.getCost(),
                        fuelingRecordDTO.getPayment(),
                        fuelingRecordDTO.getDebt()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colRecordId.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        colBusId.setCellValueFactory(new PropertyValueFactory<>("busId"));
        colStationId.setCellValueFactory(new PropertyValueFactory<>("stationId"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colDebt.setCellValueFactory(new PropertyValueFactory<>("debt"));
    }

    public boolean isTextValid() {
        boolean isCostValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtCost);
        boolean isPaymentValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtPayment);

        return isCostValid && isPaymentValid;
    }
}
