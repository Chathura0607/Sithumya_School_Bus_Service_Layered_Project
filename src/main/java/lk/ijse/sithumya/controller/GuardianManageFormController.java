package lk.ijse.sithumya.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.GuardianBO;
import lk.ijse.sithumya.bo.custom.GuardianshipBO;
import lk.ijse.sithumya.bo.custom.StudentBO;
import lk.ijse.sithumya.dto.GuardianDTO;
import lk.ijse.sithumya.util.Regex;

import java.sql.SQLException;
import java.util.List;

public class GuardianManageFormController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrimaryNumber;

    @FXML
    private TextField txtRelation;

    private GuardianBO guardianBO = (GuardianBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.GUARDIAN);
    private GuardianshipBO guardianshipBO = (GuardianshipBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.GUARDIANSHIP);
    private StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.STUDENT);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String guardianId = txtId.getText();

        try {
            GuardianDTO guardian = guardianBO.searchGuardian(guardianId);
            if (guardian != null) {
                boolean isDeleted = guardianBO.deleteGuardian(guardianId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Guardian Delete Successfully!").show();
                    clearFields();
                    GuardianFormController.getController().initialize();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Guardian Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String guardianId = txtId.getText();
        String name = txtName.getText();
        String relation = txtRelation.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String studentId = String.valueOf(cmbStudentId.getValue());
        String emergencyContact = txtPrimaryNumber.getText();

        if (studentId == null || studentId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a Student ID.").show();
            return;
        }

        if (isTextValid()) {
            try {
                GuardianDTO guardian = new GuardianDTO(guardianId, name, relation, contact, email, address);
                boolean isSaved = guardianBO.saveGuardianWithGuardianship(guardian, studentId, emergencyContact);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Guardian and Guardianship Saved Successfully!").show();
                    clearFields();
                    GuardianFormController.getController().initialize();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save guardian and guardianship!").show();
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
        String guardianId = txtId.getText();

        try {
            GuardianDTO guardian = guardianBO.searchGuardian(guardianId);

            if (guardian != null) {
                fillFields(guardian);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Guardian Not Found!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String guardianId = txtId.getText();
        String name = txtName.getText();
        String relation = txtRelation.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String studentId = String.valueOf(cmbStudentId.getValue());
        String emergencyContact = txtPrimaryNumber.getText();

        if (isTextValid()) {
            GuardianDTO guardian = new GuardianDTO(guardianId, name, relation, contact, email, address);

            try {
                boolean isUpdated = guardianBO.updateGuardianWithGuardianship(guardian, studentId, emergencyContact);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Guardian and Guardianship Updated Successfully!").show();
                    clearFields();
                    GuardianFormController.getController().initialize();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update guardian and guardianship!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    void cmbStudentIdOnAction(ActionEvent event) {
        String selectedStudentId = String.valueOf(cmbStudentId.getValue());
        if (selectedStudentId != null) {
            try {
                String emergencyContact = guardianshipBO.getEmergencyContact(selectedStudentId);
                if (emergencyContact != null) {
                    txtPrimaryNumber.setText(emergencyContact);
                } else {
                    txtPrimaryNumber.setText("No Emergency Contact Found");
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void txtAddressOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.ADDRESS, txtAddress);
    }

    @FXML
    void txtContactOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.CONTACT, txtContact);
    }

    @FXML
    void txtGuardianIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.NIC, txtId);
    }

    @FXML
    void txtMailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.EMAIL, txtEmail);
    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);
    }

    @FXML
    void txtPrimaryNumberOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.CONTACT, txtPrimaryNumber);
    }

    @FXML
    void txtRelationOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtRelation);
    }


    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtRelation.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtPrimaryNumber.setText("");
        cmbStudentId.getSelectionModel().clearSelection();
    }

    private void fillFields(GuardianDTO guardian) {
        txtId.setText(guardian.getGuardianId());
        txtName.setText(guardian.getName());
        txtRelation.setText(guardian.getRelation());
        txtContact.setText(guardian.getContact());
        txtEmail.setText(guardian.getEmail());
        txtAddress.setText(guardian.getAddress());
    }

    public void initialize() {
        refreshStudentIds();
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

    public boolean isTextValid() {
        boolean isIdValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.NIC, txtId);
        boolean isAddressValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.ADDRESS, txtAddress);
        boolean isContactValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.CONTACT, txtContact);
        boolean isEmailValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.EMAIL, txtEmail);
        boolean isNameValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);
        boolean isRelationValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtRelation);
        boolean isNumberValid = Regex.setTextColor(lk.ijse.sithumya.util.TextField.CONTACT, txtPrimaryNumber);

        return isIdValid && isAddressValid && isContactValid && isEmailValid && isNameValid && isRelationValid && isNumberValid;
    }
}
