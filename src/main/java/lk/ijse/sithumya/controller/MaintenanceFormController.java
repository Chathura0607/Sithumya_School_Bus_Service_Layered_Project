package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.MaintenanceBO;
import lk.ijse.sithumya.dto.MaintenanceDTO;
import lk.ijse.sithumya.util.Navigation;
import lk.ijse.sithumya.view.tm.MaintenanceTm;
import lombok.Getter;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaintenanceFormController {

    @FXML
    private TableColumn<?, ?> colBusId;

    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colMaintenanceId;

    @FXML
    private TableView<MaintenanceTm> tblMaintenance;

    private MaintenanceBO maintenanceBO = (MaintenanceBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.MAINTENANCE);

    @Getter
    private static MaintenanceFormController controller;

    public MaintenanceFormController() {
        controller = this;
    }

    @FXML
    void btnManageOnAction(ActionEvent event) {
        Navigation.navigateToMaintenanceManageForm();
    }

    public void initialize() {
        setCellValueFactory();
        loadAllMaintenance();
    }

    private void loadAllMaintenance() {
        tblMaintenance.getItems().clear();

        try {
            ArrayList<MaintenanceDTO> allMaintenance = maintenanceBO.getAllMaintenance();

            for (MaintenanceDTO maintenanceDTO: allMaintenance) {
                tblMaintenance.getItems().add(new MaintenanceTm(
                        maintenanceDTO.getMaintenanceId(),
                        maintenanceDTO.getBusId(),
                        maintenanceDTO.getDescription(),
                        maintenanceDTO.getCost(),
                        maintenanceDTO.getDate()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colMaintenanceId.setCellValueFactory(new PropertyValueFactory<>("maintenanceId"));
        colBusId.setCellValueFactory(new PropertyValueFactory<>("busId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
}
