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
import lk.ijse.sithumya.bo.custom.FeeBO;
import lk.ijse.sithumya.dto.FeeDTO;
import lk.ijse.sithumya.util.Navigation;
import lk.ijse.sithumya.view.tm.FeeTm;
import lombok.Getter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MonthlyFeeFormController {

    @FXML
    private Pane bodyPane;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colFeeId;

    @FXML
    private TableColumn<?, ?> colMonthlyFee;

    @FXML
    private TableColumn<?, ?> colPlanId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableView<FeeTm> tblFee;

    private FeeBO feeBO = (FeeBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.FEE);

    @Getter
    private static MonthlyFeeFormController controller;

    public MonthlyFeeFormController() {
        controller = this;
    }

    @FXML
    void btnSetPlanOnAction(ActionEvent event) {
        Navigation.navigateToSetPaymentPlanForm();
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        bodyPane.getChildren().clear();
        try {
            bodyPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/fee_payment_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public void initialize() throws SQLException {
        setCellValueFactory();
        loadAllFees();
    }

    public void loadAllFees() {
        tblFee.getItems().clear();

        try {
            ArrayList<FeeDTO> allFees = feeBO.getAllFees();

            for (FeeDTO feeDTO : allFees) {
                tblFee.getItems().add(new FeeTm(
                        feeDTO.getFeeId(),
                        feeDTO.getStudentId(),
                        feeDTO.getPlanId(),
                        feeDTO.getMonthlyFee(),
                        feeDTO.getDiscount(),
                        feeDTO.getTotalAmount()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colFeeId.setCellValueFactory(new PropertyValueFactory<>("feeId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colPlanId.setCellValueFactory(new PropertyValueFactory<>("planId"));
        colMonthlyFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
    }

    private static void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Navigation Error");
        alert.showAndWait();
    }
}
