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
import lk.ijse.sithumya.sendMail.EmailService;
import lk.ijse.sithumya.util.TransactionUtil;

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

        try {
            TransactionUtil.startTransaction();

            boolean isSaved = busBO.saveBusArrivalTime(busId, date.toString(), arrivalTime.toString());

            if (isSaved) {
                EmailService.sendBusArrivalEmail(busId, arrivalTime.toString());
                TransactionUtil.endTransaction();
                new Alert(Alert.AlertType.CONFIRMATION, "Bus arrival time saved and emails sent successfully!").show();
            } else {
                TransactionUtil.rollBack();
                new Alert(Alert.AlertType.ERROR, "Failed to save bus arrival time!").show();
            }
        } catch (SQLException e) {
            TransactionUtil.rollBack();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            TransactionUtil.endTransaction();
        }
    }

    @FXML
    void btnSendReturnTimeOnAction(ActionEvent event) {
        String busId = cmbBusId.getValue();
        LocalDate date = dtpDate.getValue();
        LocalTime returnTime = LocalTime.parse(txtReturnTime.getText());

        try {
            TransactionUtil.startTransaction();

            boolean isSaved = busBO.saveBusReturnTime(busId, date.toString(), returnTime.toString());

            if (isSaved) {
                EmailService.sendBusReturnEmail(busId, returnTime.toString());
                TransactionUtil.endTransaction();
                new Alert(Alert.AlertType.CONFIRMATION, "Bus return time saved and emails sent successfully!").show();
            } else {
                TransactionUtil.rollBack();
                new Alert(Alert.AlertType.ERROR, "Failed to save bus return time!").show();
            }
        } catch (SQLException e) {
            TransactionUtil.rollBack();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            TransactionUtil.endTransaction();
        }
    }

    @FXML
    void txtArrivalOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtReturnOnKeyReleased(KeyEvent event) {

    }

    public void initialize() {
        try {
            List<String> busIds = busBO.getAllBusIds();
            cmbBusId.getItems().addAll(busIds);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}