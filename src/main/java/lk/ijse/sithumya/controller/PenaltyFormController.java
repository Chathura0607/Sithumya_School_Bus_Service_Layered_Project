package lk.ijse.sithumya.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.PenaltyBO;
import lk.ijse.sithumya.dto.PenaltyDetailsDTO;
import lk.ijse.sithumya.view.tm.PenaltyDetailsTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class PenaltyFormController {

    @FXML
    private TableColumn<?, ?> colFeeId;

    @FXML
    private TableColumn<?, ?> colPenalty;

    @FXML
    private TableColumn<?, ?> colPenaltyId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableView<PenaltyDetailsTm> tblPenalty;

    private PenaltyBO penaltyBO = (PenaltyBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.PENALTY);

    public void initialize() {
        setCellValueFactory();
        loadAllPenalties();
    }

    public void loadAllPenalties() {
        tblPenalty.getItems().clear();

        try {
            ArrayList<PenaltyDetailsDTO> loadAllPenalties = penaltyBO.getAllPenalties();

            for (PenaltyDetailsDTO penalty : loadAllPenalties) {
                tblPenalty.getItems().add(new PenaltyDetailsTm(
                        penalty.getPenaltyId(),
                        penalty.getFeeId(),
                        penalty.getPenaltyAmount(),
                        penalty.getStudentId(),
                        penalty.getStudentName()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colPenaltyId.setCellValueFactory(new PropertyValueFactory<>("penaltyId"));
        colFeeId.setCellValueFactory(new PropertyValueFactory<>("feeId"));
        colPenalty.setCellValueFactory(new PropertyValueFactory<>("penaltyAmount"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
    }
}
