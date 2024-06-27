package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.StudentBO;
import lk.ijse.sithumya.dto.StudentDTO;
import lk.ijse.sithumya.util.Navigation;
import lk.ijse.sithumya.view.tm.StudentTm;
import lombok.Getter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentFormController {

    @FXML
    private Pane bodyPane;

    @FXML
    private TableColumn<?, ?> colBusId;

    @FXML
    private TableColumn<?, ?> colDateOfBirth;

    @FXML
    private TableColumn<?, ?> colDistance;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSchool;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private Label lblStudentCount;

    @FXML
    private Pane lblStudents;

    @FXML
    private PieChart pieChartStudent;

    @FXML
    private TableView<StudentTm> tblStudent;

    private StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.STUDENT);

    @Getter
    private static StudentFormController controller;

    public StudentFormController() {
        controller = this;
    }

    public void initialize() {
        lblStudentCount.setText(String.valueOf(getTotalStudentsCount()));
        setCellValueFactory();
        loadAllStudents();
    }

    private int getTotalStudentsCount() {
        try {
            return studentBO.getTotalStudentsCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return 0;
    }

    public void loadAllStudents() {
        tblStudent.getItems().clear();

        try {
            ArrayList<StudentDTO> allStudents = studentBO.getAllStudents();

            for (StudentDTO studentDTO : allStudents) {
                tblStudent.getItems().add(new StudentTm(
                        studentDTO.getStudentId(),
                        studentDTO.getName(),
                        studentDTO.getDateOfBirth(),
                        studentDTO.getSchool(),
                        studentDTO.getDistance(),
                        studentDTO.getBusId()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));
        colSchool.setCellValueFactory(new PropertyValueFactory("schoolName"));
        colDistance.setCellValueFactory(new PropertyValueFactory("distanceToSchool"));
        colBusId.setCellValueFactory(new PropertyValueFactory("busId"));
    }

    @FXML
    void btnGuardianOnAction(ActionEvent event) {
        bodyPane.getChildren().clear();
        try {
            bodyPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/guardianship_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnManageOnAction(ActionEvent event) {
        Navigation.navigateToStudentManageForm();
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        bodyPane.getChildren().clear();
        try {
            bodyPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/monthly_fee_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    private static void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Navigation Error");
        alert.showAndWait();
    }
}
