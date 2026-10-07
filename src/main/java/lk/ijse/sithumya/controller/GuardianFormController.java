package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.GuardianBO;
import lk.ijse.sithumya.dto.GuardianDTO;
import lk.ijse.sithumya.util.Navigation;
import lk.ijse.sithumya.view.tm.GuardianTm;
import lombok.Getter;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuardianFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colGuardianId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<GuardianTm> tblGuardian;

    @Getter
    private static GuardianFormController controller;

    private GuardianBO guardianBO = (GuardianBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.GUARDIAN);

    public GuardianFormController() {
        controller = this;
    }

    @FXML
    void btnManageOnAction(ActionEvent event) {
        Navigation.navigateToGuardianManageForm();
    }

    public void initialize() {
        setCellValueFactory();
        loadAllGuardians();
    }

    private void loadAllGuardians() {
        tblGuardian.getItems().clear();

        try {
            ArrayList<GuardianDTO> allGuardians = guardianBO.getAllGuardians();

            for (GuardianDTO guardianDTO : allGuardians) {
                tblGuardian.getItems().add(new GuardianTm(
                        guardianDTO.getGuardianId(),
                        guardianDTO.getName(),
                        guardianDTO.getContact(),
                        guardianDTO.getEmail(),
                        guardianDTO.getAddress()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colGuardianId.setCellValueFactory(new PropertyValueFactory("guardianId"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colContact.setCellValueFactory(new PropertyValueFactory("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
    }
}
