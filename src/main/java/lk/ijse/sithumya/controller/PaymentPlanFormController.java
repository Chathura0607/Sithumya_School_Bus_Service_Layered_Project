package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.PaymentPlanBO;
import lk.ijse.sithumya.dto.PaymentPlanDTO;
import lk.ijse.sithumya.util.Navigation;
import lk.ijse.sithumya.view.tm.PaymentPlanTm;
import lombok.Getter;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentPlanFormController {

    @FXML
    private TableColumn<?, ?> colNumberOfInstallments;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPlanId;

    @FXML
    private TableView<PaymentPlanTm> tblPlans;

    private PaymentPlanBO paymentPlanBO = (PaymentPlanBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.PLAN);

    @Getter
    private static PaymentPlanFormController controller;

    public PaymentPlanFormController() {
        controller = this;
    }

    @FXML
    void btnManageOnAction(ActionEvent event) {
        Navigation.navigateToPaymentPlanManageForm();
    }

    public void initialize() throws SQLException {
        setCellValueFactory();
        loadAllPlans();
    }

    public void loadAllPlans() {
        tblPlans.getItems().clear();

        try {
            ArrayList<PaymentPlanDTO> allPlans = paymentPlanBO.getAllPaymentPlans();

            for (PaymentPlanDTO paymentPlanDTO : allPlans) {
                tblPlans.getItems().add(new PaymentPlanTm(
                        paymentPlanDTO.getPlanId(),
                        paymentPlanDTO.getPlanName(),
                        paymentPlanDTO.getNumberOfInstallments()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colPlanId.setCellValueFactory(new PropertyValueFactory<>("planId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("planName"));
        colNumberOfInstallments.setCellValueFactory(new PropertyValueFactory<>("numberOfInstallments"));
    }
}

