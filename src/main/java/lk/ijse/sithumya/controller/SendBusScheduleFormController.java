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
        LocalTime arrivalTime = LocalTime.parse(txtArrivalTime.getText());

        if (isTextValid()) {
            try {
                busBO.saveBusArrivalTime(new ScheduleDTO(busId, date, arrivalTime));
                new Alert(Alert.AlertType.CONFIRMATION, "Bus arrival time saved and emails sent successfully!").show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }  else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    void btnSendReturnTimeOnAction(ActionEvent event) {
        String busId = cmbBusId.getValue();
        LocalDate date = dtpDate.getValue();
        LocalTime returnTime = LocalTime.parse(txtReturnTime.getText());

        if (isTextValid()) {
            try {
                busBO.saveBusReturnTime(new ScheduleDTO(busId, date, returnTime));
                new Alert(Alert.AlertType.CONFIRMATION, "Bus return time saved and emails sent successfully!").show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    void txtArrivalOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.TIME, txtArrivalTime);
    }

    @FXML
    void txtReturnOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.TIME, txtReturnTime);
    }

    public void initialize() {
        try {
            List<String> busIds = busBO.getAllBusIds();
            cmbBusId.getItems().addAll(busIds);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isTextValid() {
        boolean isArrivalTimeValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.TIME, txtArrivalTime);
        boolean isReturnTimeValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.TIME, txtReturnTime);

        return isArrivalTimeValid && isReturnTimeValid;
    }
}
