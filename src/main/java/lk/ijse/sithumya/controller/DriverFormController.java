package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.DriverBO;
import lk.ijse.sithumya.dto.DriverDTO;
import lk.ijse.sithumya.util.Navigation;
import lk.ijse.sithumya.view.tm.DriverTm;
import lombok.Getter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverFormController {

    @FXML
    private Pane bodyPane;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDriverId;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<DriverTm> tblDriver;

    private DriverBO driverBO = (DriverBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.DRIVER);

    @Getter
    private static DriverFormController controller;

    public DriverFormController() {
        controller = this;
    }

    @FXML
    void btnManageOnAction(ActionEvent event) {
        Navigation.navigateToDriverManageForm();
    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) {
        bodyPane.getChildren().clear();
        try {
            bodyPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/salary_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public void initialize() {
        setCellValueFactory();
        loadAllDrivers();
    }

    public void loadAllDrivers() {
        tblDriver.getItems().clear();

        try {
            ArrayList<DriverDTO> allDrivers = driverBO.getAllDrivers();

            for (DriverDTO driverDTO : allDrivers) {
                tblDriver.getItems().add(new DriverTm(
                        driverDTO.getDriverId(),
                        driverDTO.getName(),
                        driverDTO.getAddress(),
                        driverDTO.getEmail(),
                        driverDTO.getContactNumber()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colDriverId.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
    }

    private static void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Navigation Error");
        alert.showAndWait();
    }
}
