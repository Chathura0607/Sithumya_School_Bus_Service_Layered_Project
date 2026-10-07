package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.UserBO;
import lk.ijse.sithumya.dto.UserDTO;
import lk.ijse.sithumya.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private TextField txtUsername;

    private UserBO userBO = (UserBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.USER);

    @FXML
    void linkResetOnAction(ActionEvent event) {
        Navigation.openPasswordResetForm();
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        try {
            UserDTO userDTO = new UserDTO(null, txtUsername.getText(), pwfPassword.getText());
            userBO.checkCredentialAndLogin(userDTO);
        } catch (SQLException | IOException e) {
            showErrorAlert();
        }
    }

    private void showErrorAlert() {
        new Alert(Alert.AlertType.ERROR, "OOPS! Something went wrong").show();
    }
}
