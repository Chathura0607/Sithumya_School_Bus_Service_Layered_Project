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
        if (paymentPlanId == null || paymentPlanId.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a Plan ID to delete!").show();
            return;
        }

        try {
            PaymentPlanDTO paymentPlan = paymentPlanBO.searchPaymentPlan(paymentPlanId);

            if (paymentPlan != null) {
                boolean isDeleted = paymentPlanBO.deletePlan(paymentPlanId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Plan Deleted Successfully!").show();
                    clearFields();
                    refreshPlanIds();
                    generateNewPlanId();
                    if (PaymentPlanFormController.getController() != null) {
                        PaymentPlanFormController.getController().loadAllPlans();
                    }
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
        if (!isTextValid()) {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check all fields!").show();
            return;
        }

        String planId = nextPlanId;
        String planName = txtName.getText();
        int installments;
        try {
            installments = Integer.parseInt(txtNumberOfInstallments.getText().trim());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Installments count must be a valid integer!").show();
            return;
        }

        try {
            boolean isSaved = paymentPlanBO.savePlan(new PaymentPlanDTO(planId, planName, installments));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Plan Saved Successfully!").show();
                clearFields();
                refreshPlanIds();
                generateNewPlanId();
                if (PaymentPlanFormController.getController() != null) {
                    PaymentPlanFormController.getController().loadAllPlans();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String planId = cmbPlanId.getValue();
        if (planId == null || planId.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a Plan ID to search!").show();
            return;
        }

        try {
            PaymentPlanDTO paymentPlan = paymentPlanBO.searchPaymentPlan(planId);

            if (paymentPlan != null) {
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
        if (planId == null || planId.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a Plan ID to update!").show();
            return;
        }

        if (!isTextValid()) {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check all fields!").show();
            return;
        }

        String planName = txtName.getText();
        int installments;
        try {
            installments = Integer.parseInt(txtNumberOfInstallments.getText().trim());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Installments count must be a valid integer!").show();
            return;
        }

        try {
            boolean isUpdated = paymentPlanBO.updatePaymentPlan(new PaymentPlanDTO(planId, planName, installments));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Plan Updated Successfully!").show();
                clearFields();
                refreshPlanIds();
                if (PaymentPlanFormController.getController() != null) {
                    PaymentPlanFormController.getController().loadAllPlans();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
        Regex.resetColor(txtName, txtNumberOfInstallments);
    }

    private void fillFields(PaymentPlanDTO paymentPlan) {
        txtName.setText(paymentPlan.getPlanName());
        txtNumberOfInstallments.setText(String.valueOf(paymentPlan.getNumberOfInstallments()));
        Regex.resetColor(txtName, txtNumberOfInstallments);
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

    public boolean isTextValid() {
        boolean isCountValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.COUNT, txtNumberOfInstallments);
        boolean isNameValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);

        return isCountValid && isNameValid;
    }
}
