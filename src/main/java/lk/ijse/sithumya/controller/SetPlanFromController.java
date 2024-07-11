package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.FeeBO;
import lk.ijse.sithumya.bo.custom.PaymentPlanBO;
import lk.ijse.sithumya.bo.custom.StudentBO;
import lk.ijse.sithumya.dto.FeeDTO;

import java.sql.SQLException;
import java.util.List;

public class SetPlanFromController {

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private ComboBox<String> cmbPlanId;

    private FeeBO feeBO = (FeeBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.FEE);
    private StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.STUDENT);
    private PaymentPlanBO paymentPlanBO = (PaymentPlanBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.PLAN);

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String studentId = cmbStudentId.getValue();
        String planId = cmbPlanId.getValue();

        try {
            boolean isUpdated = feeBO.setPlan(new FeeDTO(studentId, planId));
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Plan Set Successfully!").show();
                clearFields();
                refreshPlanIds();
                refreshStudentIds();
                MonthlyFeeFormController.getController().loadAllFees();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        cmbStudentId.getItems().clear();
        cmbPlanId.getItems().clear();
    }

    @FXML
    private void initialize() {
        refreshStudentIds();
        refreshPlanIds();
    }

    private void refreshPlanIds() {
        try {
            List<String> planIds = paymentPlanBO.getAllPlanIds();
            cmbPlanId.getItems().clear();
            cmbPlanId.getItems().addAll(planIds);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void refreshStudentIds() {
        try {
            List<String> studentsIds = studentBO.getAllStudentsIds();
            cmbStudentId.getItems().clear();
            cmbStudentId.getItems().addAll(studentsIds);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
