package lk.ijse.sithumya.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.sithumya.view.tm.PenaltyDetailsTm;

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

    public void initialize() {
        setCellValueFactory();
        loadAllPenalties();
    }

    public void loadAllPenalties() {
        /*tblPenalty.getItems().clear();

        try {
            List<PenaltyDetailsTm> penaltyList = model.getAllPenalties();

            for (PenaltyDetailsTm penalty : penaltyList) {
                tmList.add(new PenaltyDetailsTm(
                        penalty.getPenaltyId(),
                        penalty.getFeeId(),
                        penalty.getPenaltyAmount(),
                        penalty.getStudentId(),
                        penalty.getStudentName()
                ));
            }
            tblPenalty.setItems(tmList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    private void setCellValueFactory() {
        colPenaltyId.setCellValueFactory(new PropertyValueFactory<>("penaltyId"));
        colFeeId.setCellValueFactory(new PropertyValueFactory<>("feeId"));
        colPenalty.setCellValueFactory(new PropertyValueFactory<>("penaltyAmount"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
    }
}
