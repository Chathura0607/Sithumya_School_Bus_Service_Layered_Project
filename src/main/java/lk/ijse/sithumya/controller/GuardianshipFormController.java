package lk.ijse.sithumya.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.GuardianshipBO;
import lk.ijse.sithumya.dto.GuardianshipDTO;
import lk.ijse.sithumya.view.tm.GuardianshipTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuardianshipFormController {

    /*@FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colGuardianId;

    @FXML
    private TableColumn<?, ?> colGuardianName;

    @FXML
    private TableColumn<?, ?> colRelation;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableView<GuardianshipTm> tblGuardian;

    private GuardianshipBO guardianshipBO = (GuardianshipBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.GUARDIANSHIP);

    public void initialize() {
        setCellValueFactory();
        loadAllGuardians();
    }

    private void loadAllGuardians() {
        tblGuardian.getItems().clear();

        try {
            ArrayList<GuardianshipDTO> guardianList = model.getAllGuardians();

            for (GuardianshipTm guardian : guardianList) {
                tmList.add(new GuardianshipTm(
                        guardian.getStudentId(),
                        guardian.getStudentName(),
                        guardian.getGuardianId(),
                        guardian.getGuardianName(),
                        guardian.getEmergencyContact(),
                        guardian.getRelation()
                ));
            }
            tblGuardian.setItems(tmList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colGuardianId.setCellValueFactory(new PropertyValueFactory<>("guardianId"));
        colGuardianName.setCellValueFactory(new PropertyValueFactory<>("guardianName"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("emergencyContact"));
        colRelation.setCellValueFactory(new PropertyValueFactory<>("relation"));
    }*/
}
