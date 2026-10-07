package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.PaymentBO;
import lk.ijse.sithumya.dto.PaymentDTO;
import lk.ijse.sithumya.util.Navigation;
import lk.ijse.sithumya.view.tm.PaymentsTm;
import lombok.Getter;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeePaymentFormController {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colIsCompleted;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPlanId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableView<PaymentsTm> tblPayments;

    private PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.PAYMENT);

    @Getter
    private static FeePaymentFormController controller;

    public FeePaymentFormController() {
        controller = this;
    }

    @FXML
    void btnManageOnAction(ActionEvent event) {
        Navigation.navigateToPaymentForm();
    }

    public void initialize() {
        setCellValueFactory();
        loadAllPayments();
    }

    public void loadAllPayments() {
        tblPayments.getItems().clear();

        try {
            ArrayList<PaymentDTO> allPayments = paymentBO.getAllPayments();

            for (PaymentDTO paymentDTO : allPayments) {
                tblPayments.getItems().add(new PaymentsTm(
                        paymentDTO.getPaymentId(),
                        paymentDTO.getPaymentPlanId(),
                        paymentDTO.getStudentId(),
                        paymentDTO.getAmount(),
                        paymentDTO.getPaymentDate(),
                        paymentDTO.getIsCompleted()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colIsCompleted.setCellValueFactory(new PropertyValueFactory<>("isComplete"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colPlanId.setCellValueFactory(new PropertyValueFactory<>("planId"));
    }
}
