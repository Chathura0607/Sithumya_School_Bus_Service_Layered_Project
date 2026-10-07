package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.SalaryBO;
import lk.ijse.sithumya.dto.DriverDTO;
import lk.ijse.sithumya.util.Navigation;
import lk.ijse.sithumya.view.tm.DriverSalaryTm;
import lombok.Getter;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryFormController {

    @FXML
    private TableColumn<?, ?> colDriverId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPayment;

    @FXML
    private TableColumn<?, ?> colPaymentDue;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<DriverSalaryTm> tblSalary;

    private SalaryBO salaryBO = (SalaryBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.SALARY);

    @Getter
    private static SalaryFormController controller;

    public SalaryFormController() {
        controller = this;
    }

    @FXML
    void btnManageOnAction(ActionEvent event) {
        Navigation.navigateToSalaryManageForm();
    }

    public void initialize() {
        setCellValueFactory();
        loadAllDriversWithSalary();
    }

    private void loadAllDriversWithSalary() {
        tblSalary.getItems().clear();

        try {
            ArrayList<DriverDTO> allDrivers = salaryBO.getAllDriversWithSalary();

            for (DriverDTO driverDTO : allDrivers) {
                tblSalary.getItems().add(new DriverSalaryTm(
                        driverDTO.getDriverId(),
                        driverDTO.getName(),
                        driverDTO.getSalary(),
                        driverDTO.getPayment(),
                        driverDTO.getPaymentDue()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colDriverId.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colPaymentDue.setCellValueFactory(new PropertyValueFactory<>("paymentDue"));
    }
}
