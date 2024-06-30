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
import lk.ijse.sithumya.bo.custom.FillingStationBO;
import lk.ijse.sithumya.dto.FillingStationDTO;
import lk.ijse.sithumya.util.Navigation;
import lk.ijse.sithumya.view.tm.FillingStationTm;
import lombok.Getter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FillingStationFormController {

    @FXML
    private Pane bodyPane;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStationId;

    @FXML
    private TableView<FillingStationTm> tblStation;

    private FillingStationBO fillingStationBO = (FillingStationBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.STATION);

    @Getter
    private static FillingStationFormController controller;

    public FillingStationFormController() {
        controller = this;
    }

    @FXML
    void btnManageOnAction(ActionEvent event) {
        Navigation.navigateToStationManageForm();
    }

    @FXML
    void btnViewFuelDetailsOnAction(ActionEvent event) {
        bodyPane.getChildren().clear();
        try {
            bodyPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/fueling_record_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public void initialize() {
        setCellFactoryValues();
        loadAllFillingStations();
    }

    private void loadAllFillingStations() {
        tblStation.getItems().clear();

        try {
            ArrayList<FillingStationDTO> allStations = fillingStationBO.getAllStations();

            for (FillingStationDTO fillingStationDTO : allStations) {
                tblStation.getItems().add(new FillingStationTm(
                        fillingStationDTO.getId(),
                        fillingStationDTO.getName(),
                        fillingStationDTO.getLocation(),
                        fillingStationDTO.getContact()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellFactoryValues() {
        colStationId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private static void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Navigation Error");
        alert.showAndWait();
    }
}
