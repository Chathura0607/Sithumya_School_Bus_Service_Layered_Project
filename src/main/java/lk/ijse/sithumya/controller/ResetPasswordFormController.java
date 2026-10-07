package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.UserBO;
import lk.ijse.sithumya.dto.UserDTO;
import lk.ijse.sithumya.util.GenerateCode;
import lk.ijse.sithumya.util.Navigation;

import java.sql.SQLException;

public class ResetPasswordFormController {

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private TextField txtUserID;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtVerificationCode;

    private String verificationCode;

    private UserBO userBO = (UserBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.USER);

    @FXML
    void btnGetCodeOnAction(ActionEvent event) {
        verificationCode = GenerateCode.generateCode();
        boolean emailSent = userBO.sendVerificationCodeByEmail(verificationCode);

        if (emailSent) {
            new Alert(Alert.AlertType.INFORMATION, "Verification code sent successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to send verification code. Please try again.").show();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        String enteredCode = txtVerificationCode.getText() != null ? txtVerificationCode.getText().trim() : "";

        if (verificationCode == null || verificationCode.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please request a verification code first by clicking 'Get Code'!").show();
            return;
        }

        if (enteredCode.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter the verification code!").show();
            return;
        }

        if (txtUserID.getText().trim().isEmpty() || txtUsername.getText().trim().isEmpty() || pwfPassword.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill in User ID, Username, and New Password!").show();
            return;
        }

        if (enteredCode.equals(verificationCode.trim())) {
            try {
                UserDTO userDTO = new UserDTO(txtUserID.getText().trim(), txtUsername.getText().trim(), pwfPassword.getText().trim());
                userBO.resetPassword(userDTO);
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Password reset successfully! You can now log in with your new password.").show();
                Navigation.navigateToLoginForm();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Password reset failed: " + e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid verification code. Please check your email and try again!").show();
        }
    }

    @FXML
    void linkLoginOnAction(ActionEvent event) {
        Navigation.navigateToLoginForm();
    }

    private void clearFields() {
        txtUserID.clear();
        txtUsername.clear();
        pwfPassword.clear();
        txtVerificationCode.clear();
    }
}
