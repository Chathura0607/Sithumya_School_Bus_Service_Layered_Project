package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.PaymentPlanBO;
import lk.ijse.sithumya.dto.PaymentPlanDTO;
import lk.ijse.sithumya.util.Regex;

import java.sql.SQLException;
import java.util.List;

public class PaymentPlanManageFormController {

    @FXML
    private ComboBox<String> cmbPlanId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumberOfInstallments;

    private String nextPlanId;

    private PaymentPlanBO paymentPlanBO = (PaymentPlanBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.PLAN);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String paymentPlanId = cmbPlanId.getValue();

        try {
            PaymentPlanDTO paymentPlan = paymentPlanBO.searchPaymentPlan(paymentPlanId);

            if (paymentPlan != null) {
                boolean isDeleted = paymentPlanBO.deletePlan(paymentPlanId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Plan Deleted Successfully!").show();
                    clearFields();
                    refreshPlanIds();
                    PaymentPlanFormController.getController().initialize();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Plan Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String planId = nextPlanId;
        String planName = txtName.getText();
        int installments = Integer.parseInt(txtNumberOfInstallments.getText());

        if (isTextValid()) {
            try {
                boolean isSaved = paymentPlanBO.savePlan(new PaymentPlanDTO(planId, planName, installments));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Plan Saved Successfully!").show();
                    clearFields();
                    refreshPlanIds();
                    PaymentPlanFormController.getController().initialize();
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
        String planId = cmbPlanId.getValue();

        try {
            PaymentPlanDTO paymentPlan = paymentPlanBO.searchPaymentPlan(planId);

            if(paymentPlan != null) {
                fillFields(paymentPlan);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Plan Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String planId = cmbPlanId.getValue();
        String planName = txtName.getText();
        int installments = Integer.parseInt(txtNumberOfInstallments.getText());

        if (isTextValid()) {
            try {
                boolean isUpdated = paymentPlanBO.updatePaymentPlan(new PaymentPlanDTO(planId, planName, installments));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment Plan Update Successfully!").show();
                    clearFields();
                    refreshPlanIds();
                    PaymentPlanFormController.getController().initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    void txtInstallmentsCountOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.COUNT, txtNumberOfInstallments);
    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);
    }

    private void clearFields() {
        txtName.setText("");
        txtNumberOfInstallments.setText("");
    }

    private void fillFields(PaymentPlanDTO paymentPlan) {
        txtName.setText(paymentPlan.getPlanName());
        txtNumberOfInstallments.setText(String.valueOf(paymentPlan.getNumberOfInstallments()));
    }

    public void initialize() {
        refreshPlanIds();
        generateNewPlanId();
    }

    private void generateNewPlanId() {
        try {
            nextPlanId = paymentPlanBO.generateNextPlanId();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void refreshPlanIds() {
        try {
            List<String> planIds = paymentPlanBO.getAllPlanIds();
            cmbPlanId.getItems().clear();
            cmbPlanId.getItems().addAll(planIds);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private String generateNextPlanId(String lastPlanId) {
        if (lastPlanId == null) {
            return "P001";
        } else {
            int lastIdNumeric = Integer.parseInt(lastPlanId.substring(1));

            int nextIdNumeric = lastIdNumeric + 1;

            return String.format("P%03d", nextIdNumeric);
        }
    }

    public boolean isTextValid() {
        boolean isCountValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.COUNT, txtNumberOfInstallments);
        boolean isNameValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);

        return isCountValid && isNameValid;
    }
}
