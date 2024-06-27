package lk.ijse.sithumya.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.BusBO;
import lk.ijse.sithumya.bo.impl.BusBOImpl;
import lk.ijse.sithumya.dto.BusDTO;
import lk.ijse.sithumya.util.Navigation;
import lk.ijse.sithumya.view.tm.BusTm;
import lombok.Getter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusFormController {

    @FXML
    private Pane bodyPane;

    @FXML
    private TableColumn<?, ?> colBusId;

    @FXML
    private TableColumn<?, ?> colDebt;

    @FXML
    private TableColumn<?, ?> colDriverDate;

    @FXML
    private TableColumn<?, ?> colDriverId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<BusTm> tblBus;

    private BusBO busBO  = (BusBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.BUS);

    @Getter
    private static BusFormController controller;

    public BusFormController() {
        controller = this;
    }

    @FXML
    void btnFuelingOnAction(ActionEvent event) {
        bodyPane.getChildren().clear();
        try {
            bodyPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/fueling_record_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnMaintenanceOnAction(ActionEvent event) {
        bodyPane.getChildren().clear();
        try {
            bodyPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/maintenance_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnManageOnAction(ActionEvent event) {
        Navigation.navigateToBusManageForm();
    }

    public void initialize() {
        setCellValueFactory();
        loadAllBuses();
    }

    public void loadAllBuses() {
        tblBus.getItems().clear();

        try {
            ArrayList<BusDTO> allBus = busBO.getAllBus();

            for (BusDTO busDTO : allBus) {
                tblBus.getItems().add(new BusTm(
                        busDTO.getBusId(),
                        busDTO.getName(),
                        busDTO.getDriverId(),
                        busDTO.getDriverAssignedDate(),
                        busDTO.getAmountPayToBeStation()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colBusId.setCellValueFactory(new PropertyValueFactory<>("busId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDriverId.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        colDriverDate.setCellValueFactory(new PropertyValueFactory<>("driverAssignedDate"));
        colDebt.setCellValueFactory(new PropertyValueFactory<>("amountPayToBeStation"));
    }

    private static void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Navigation Error");
        alert.showAndWait();
    }
}
