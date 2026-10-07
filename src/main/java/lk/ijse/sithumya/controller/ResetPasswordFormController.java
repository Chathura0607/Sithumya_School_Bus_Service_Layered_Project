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
        String enteredCode = txtVerificationCode.getText();

        if (enteredCode.equals(verificationCode)) {
            try {
                UserDTO userDTO = new UserDTO(txtUserID.getText(), txtUsername.getText(), pwfPassword.getText());
                userBO.resetPassword(userDTO);
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Password reset successfully.").show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "OOPS! Something went wrong").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid verification code.").show();
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
