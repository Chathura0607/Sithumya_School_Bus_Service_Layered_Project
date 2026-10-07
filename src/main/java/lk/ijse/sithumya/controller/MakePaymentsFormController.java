package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.PaymentBO;
import lk.ijse.sithumya.bo.custom.StudentBO;
import lk.ijse.sithumya.dbConnection.DbConnection;
import lk.ijse.sithumya.dto.PaymentDTO;
import lk.ijse.sithumya.util.Regex;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakePaymentsFormController {

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private DatePicker dtpPaymentDate;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtPlan;

    private final StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.STUDENT);
    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.PAYMENT);

    @FXML
    void btnPayOnAction(ActionEvent event) {
        try {
            PaymentDTO payment = createPaymentFromForm();
            if (payment != null && isPaymentValid(payment)) {
                boolean isPaymentSuccessful = paymentBO.savePayment(payment);
                if (isPaymentSuccessful) {
                    new Alert(Alert.AlertType.INFORMATION, "Payment successful! Payment has been successfully recorded.").show();
                    clearForm();
                    if (FeePaymentFormController.getController() != null) {
                        FeePaymentFormController.getController().loadAllPayments();
                    }
                    if (MonthlyFeeFormController.getController() != null) {
                        MonthlyFeeFormController.getController().loadAllFees();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Payment failed: Failed to record the payment.").show();
                }
            }
        } catch (SQLException | NumberFormatException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Payment error: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnPrintBillOnAction(ActionEvent event) {
        String studentId = cmbStudentId.getValue();
        if (studentId == null || studentId.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a Student ID first to print the bill!").show();
            return;
        }

        try {
            java.io.InputStream reportStream = getClass().getResourceAsStream("/reports/Sithumya_Payment_Report.jrxml");
            if (reportStream == null) {
                new Alert(Alert.AlertType.ERROR, "Payment Report template not found in resources!").show();
                return;
            }

            JasperDesign jasperDesign = JRXmlLoader.load(reportStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Student_ID", studentId);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate report: " + e.getMessage()).show();
        }
    }

    public void initialize() {
        refreshStudentIds();
        setCurrentPlanListener();
    }

    private void setCurrentPlanListener() {
        cmbStudentId.setOnAction(event -> updateCurrentPlan());
    }

    private void updateCurrentPlan() {
        String selectedStudentId = cmbStudentId.getValue();
        if (selectedStudentId != null) {
            try {
                String currentPlanId = studentBO.getCurrentPaymentPlan(selectedStudentId);
                txtPlan.setText(currentPlanId);
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
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

    private PaymentDTO createPaymentFromForm() {
        if (cmbStudentId.getValue() == null || cmbStudentId.getValue().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a Student ID!").show();
            return null;
        }
        if (dtpPaymentDate.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a payment date!").show();
            return null;
        }

        if (isTextValid()) {
            String studentId = cmbStudentId.getValue();
            String paymentPlanId = txtPlan.getText();
            try {
                double amount = Double.parseDouble(txtAmount.getText().trim());
                Date paymentDate = Date.valueOf(dtpPaymentDate.getValue());
                return new PaymentDTO(studentId, paymentPlanId, amount, paymentDate, "YES");
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Please enter a valid payment amount!").show();
                return null;
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please check all fields and ensure valid inputs!").show();
        }
        return null;
    }

    private boolean isPaymentValid(PaymentDTO payment) throws SQLException {
        double remainingFeeAmount = paymentBO.getRemainingFeeAmount(payment.getStudentId());
        if (payment.getAmount() > remainingFeeAmount) {
            new Alert(Alert.AlertType.ERROR, "Invalid payment: Payment amount (Rs. " + payment.getAmount() + ") exceeds the remaining fee amount (Rs. " + remainingFeeAmount + ")!").show();
            return false;
        }
        return true;
    }

    private void clearForm() {
        cmbStudentId.getSelectionModel().clearSelection();
        txtPlan.clear();
        txtAmount.clear();
        dtpPaymentDate.setValue(null);
        dtpPaymentDate.getEditor().clear();
        Regex.resetColor(txtPlan, txtAmount, dtpPaymentDate.getEditor());
    }

    @FXML
    void txtDateOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.DATE, dtpPaymentDate.getEditor());
    }

    @FXML
    void txtPaymentOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtAmount);
    }

    @FXML
    void txtPaymentPlanOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.PLANId, txtPlan);
    }

    public boolean isTextValid() {
        boolean isPaymentValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.COST, txtAmount);
        boolean isDateValid = dtpPaymentDate.getValue() != null || Regex.setTextColor(lk.ijse.sithumya.util.TextField.DATE, dtpPaymentDate.getEditor());
        boolean isPlanValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.PLANId, txtPlan);

        return isPaymentValid && isDateValid && isPlanValid;
    }
}
