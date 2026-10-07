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
import lk.ijse.sithumya.dto.ScheduleDTO;
import lk.ijse.sithumya.util.Regex;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SendBusScheduleFormController {
    @FXML
    private ComboBox<String> cmbBusId;

    @FXML
    private DatePicker dtpDate;

    @FXML
    private TextField txtArrivalTime;

    @FXML
    private TextField txtReturnTime;

    private BusBO busBO = (BusBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.BUS);

    @FXML
    void btnSendArrivalTimeOnAction(ActionEvent event) {
        String busId = cmbBusId.getValue();
        LocalDate date = dtpDate.getValue();

        if (busId == null || busId.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a Bus ID.").show();
            return;
        }

        if (date == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a Date.").show();
            return;
        }

        if (isArrivalTimeValid()) {
            try {
                String timeText = txtArrivalTime.getText().trim();
                LocalTime arrivalTime = parseFlexibleTime(timeText);
                if (arrivalTime == null) {
                    new Alert(Alert.AlertType.ERROR, "Invalid time format. Please use HH:mm or HH:mm:ss (e.g. 07:30:00)").show();
                    return;
                }

                boolean isSaved = busBO.saveBusArrivalTime(new ScheduleDTO(busId, date, arrivalTime));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Bus arrival time saved and notification emails dispatched!").show();
                    txtArrivalTime.clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to record bus arrival time.").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error saving schedule: " + e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid arrival time. Example: 07:30:00").show();
        }
    }

    @FXML
    void btnSendReturnTimeOnAction(ActionEvent event) {
        String busId = cmbBusId.getValue();
        LocalDate date = dtpDate.getValue();

        if (busId == null || busId.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a Bus ID.").show();
            return;
        }

        if (date == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a Date.").show();
            return;
        }

        if (isReturnTimeValid()) {
            try {
                String timeText = txtReturnTime.getText().trim();
                LocalTime returnTime = parseFlexibleTime(timeText);
                if (returnTime == null) {
                    new Alert(Alert.AlertType.ERROR, "Invalid time format. Please use HH:mm or HH:mm:ss (e.g. 14:00:00)").show();
                    return;
                }

                boolean isSaved = busBO.saveBusReturnTime(new ScheduleDTO(busId, date, returnTime));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Bus return time saved and notification emails dispatched!").show();
                    txtReturnTime.clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to record bus return time.").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error saving schedule: " + e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid return time. Example: 14:00:00").show();
        }
    }

    private LocalTime parseFlexibleTime(String timeText) {
        try {
            if (timeText.length() == 5) {
                timeText = timeText + ":00";
            }
            return LocalTime.parse(timeText);
        } catch (Exception e) {
            return null;
        }
    }

    @FXML
    void txtArrivalOnKeyReleased(KeyEvent event) {
        isArrivalTimeValid();
    }

    @FXML
    void txtReturnOnKeyReleased(KeyEvent event) {
        isReturnTimeValid();
    }

    public void initialize() {
        try {
            List<String> busIds = busBO.getAllBusIds();
            cmbBusId.getItems().addAll(busIds);
            dtpDate.setValue(LocalDate.now());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isArrivalTimeValid() {
        return Regex.setTextColor(lk.ijse.sithumya.util.TextField.TIME, txtArrivalTime);
    }

    public boolean isReturnTimeValid() {
        return Regex.setTextColor(lk.ijse.sithumya.util.TextField.TIME, txtReturnTime);
    }
}

