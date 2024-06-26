package lk.ijse.sithumya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import lk.ijse.sithumya.util.Navigation;

import java.io.IOException;

public class PageController {

    @FXML
    private Pane pagePane;

    public void initialize() {
        pagePane.getChildren().clear();
        try {
            pagePane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnBusOnAction(ActionEvent event) {
        pagePane.getChildren().clear();
        try {
            pagePane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/bus_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        pagePane.getChildren().clear();
        try {
            pagePane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnDriverOnAction(ActionEvent event) {
        pagePane.getChildren().clear();
        try {
            pagePane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/driver_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnFillingOnAction(ActionEvent event) {
        pagePane.getChildren().clear();
        try {
            pagePane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/filling_station_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnGuardianOnAction(ActionEvent event) {
        pagePane.getChildren().clear();
        try {
            pagePane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/guardian_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) {
        pagePane.getChildren().clear();
        try {
            pagePane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/inventory_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Navigation.navigateToLoginForm();
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        pagePane.getChildren().clear();
        try {
            pagePane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/payment_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) {
        pagePane.getChildren().clear();
        try {
            pagePane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/student_form.fxml")));
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    private static void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Navigation Error");
        alert.showAndWait();
    }
}
