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
import lk.ijse.sithumya.bo.custom.FeeBO;
import lk.ijse.sithumya.bo.custom.PaymentPlanBO;
import lk.ijse.sithumya.bo.custom.StudentBO;
import lk.ijse.sithumya.dto.FeeDTO;
import lk.ijse.sithumya.dto.StudentDTO;
import lk.ijse.sithumya.util.Regex;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StudentManageFormController {

    @FXML
    private ComboBox<String> cmbBusId;
    @FXML
    private ComboBox<String> cmbPlanId;
    @FXML
    private ComboBox<String> cmbStudentId;
    @FXML
    private DatePicker dtpDate;
    @FXML
    private TextField txtDistance;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSchool;

    private String nextStudentId;

    private StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.STUDENT);
    private FeeBO feeBO = (FeeBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.FEE);
    private BusBO busBO = (BusBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.BUS);
    private PaymentPlanBO paymentPlanBO = (PaymentPlanBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.PLAN);

    @FXML
    private void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        String studentId = cmbStudentId.getValue();
        if (studentId != null && !studentId.isEmpty()) {
            try {
                if (studentBO.deleteStudentAndFee(studentId)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted Successfully!").show();
                    clearFields();
                    initialize();
                    StudentFormController.getController().initialize();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Student Not Found!").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a Student ID.").show();
        }
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        if (isTextValid()) {
            try {
                StudentDTO studentDTO = createStudentDTOFromForm();
                FeeDTO feeDTO = createFeeDTOFromStudent(studentDTO);

                if (studentBO.saveStudentAndFee(studentDTO, feeDTO)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student and fee saved successfully!").show();
                    initialize();
                    clearFields();
                    StudentFormController.getController().initialize();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save student and fee!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    private void btnSearchOnAction(ActionEvent event) {
        String studentId = cmbStudentId.getValue();
        if (studentId != null && !studentId.isEmpty()) {
            try {
                StudentDTO studentDTO = studentBO.searchStudent(studentId);
                if (studentDTO != null) {
                    fillFields(studentDTO);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Student Not Found!").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a Student ID.").show();
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        String studentId = cmbStudentId.getValue();
        if (studentId == null || studentId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a Student ID.").show();
            return;
        }

        if (isTextValid()) {
            try {
                StudentDTO studentDTO = createStudentDTOFromForm();
                studentDTO.setStudentId(studentId);
                FeeDTO feeDTO = createFeeDTOFromStudent(studentDTO);

                if (studentBO.updateStudentAndFee(studentDTO, feeDTO)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student and fee updated successfully!").show();
                    initialize();
                    clearFields();
                    StudentFormController.getController().initialize();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update student and fee!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update student and fee: " + e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input found. Please check!").show();
        }
    }

    @FXML
    private void dtpDateOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.DATE, dtpDate.getEditor());
    }

    @FXML
    private void txtDistanceOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.DISTANCE, txtDistance);
    }

    @FXML
    private void txtNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName);
    }

    @FXML
    private void txtSchoolOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtSchool);
    }

    private void clearFields() {
        txtName.clear();
        txtSchool.clear();
        txtDistance.clear();
        dtpDate.setValue(null);
        cmbBusId.getSelectionModel().clearSelection();
        cmbPlanId.getSelectionModel().clearSelection();
        cmbStudentId.getSelectionModel().clearSelection();
    }

    private void fillFields(StudentDTO studentDTO) {
        txtName.setText(studentDTO.getName());
        dtpDate.setValue(studentDTO.getDateOfBirth() != null ? studentDTO.getDateOfBirth().toLocalDate() : null);
        txtSchool.setText(studentDTO.getSchool());
        txtDistance.setText(String.valueOf(studentDTO.getDistance()));
        cmbBusId.setValue(studentDTO.getBusId());
        cmbPlanId.setValue(studentDTO.getPlanId());
    }

    private double calculateMonthlyFee(double distanceToSchool) {
        double baseFeePerKilometer = 150.00;
        return distanceToSchool * baseFeePerKilometer;
    }

    private FeeDTO createFeeDTOFromStudent(StudentDTO studentDTO) {
        double monthlyFee = calculateMonthlyFee(studentDTO.getDistance());
        LocalDate dueDate = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        FeeDTO feeDTO = new FeeDTO();
        feeDTO.setPlanId(studentDTO.getPlanId());
        feeDTO.setStudentId(studentDTO.getStudentId());
        feeDTO.setMonthlyFee(monthlyFee);
        feeDTO.setDiscount(0.0);
        feeDTO.setTotalAmount(monthlyFee);
        feeDTO.setDueDate(Date.valueOf(dueDate));

        return feeDTO;
    }

    private boolean isTextValid() {
        return Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtName) &&
                Regex.setTextColor(lk.ijse.sithumya.util.TextField.DATE, dtpDate.getEditor()) &&
                Regex.setTextColor(lk.ijse.sithumya.util.TextField.NAME, txtSchool) &&
                Regex.setTextColor(lk.ijse.sithumya.util.TextField.DISTANCE, txtDistance);
    }

    public void initialize() {
        generateNewStudentId();
        refreshStudentIds();
        refreshBusIds();
        refreshPaymentPlanIds();
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

    private void refreshBusIds() {
        try {
            List<String> busIds = busBO.getAllBusIds();
            cmbBusId.getItems().clear();
            cmbBusId.getItems().addAll(busIds);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void refreshPaymentPlanIds() {
        try {
            List<String> paymentPlanIds = paymentPlanBO.getAllPlanIds();
            cmbPlanId.getItems().clear();
            cmbPlanId.getItems().addAll(paymentPlanIds);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void generateNewStudentId() {
        try {
            nextStudentId = studentBO.generateNextStudentId();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private StudentDTO createStudentDTOFromForm() {
        String studentId = nextStudentId;
        String name = txtName.getText();
        Date dateOfBirth = Date.valueOf(dtpDate.getValue());
        String school = txtSchool.getText();
        double distance = Double.parseDouble(txtDistance.getText());
        String busId = cmbBusId.getValue();
        String planId = cmbPlanId.getValue();

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(studentId);
        studentDTO.setName(name);
        studentDTO.setDateOfBirth(dateOfBirth);
        studentDTO.setSchool(school);
        studentDTO.setDistance(distance);
        studentDTO.setBusId(busId);
        studentDTO.setPlanId(planId);

        return studentDTO;
    }
}
