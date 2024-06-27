package lk.ijse.sithumya.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.sithumya.util.Navigation;

import java.io.IOException;

public class PageController {

    @FXML
    private AnchorPane dashboard;

    @FXML
    private Pane pagePane;

    public void initialize() {
        btnDashboardOnAction();
    }

    @FXML
    void btnBusOnAction() {
        pagePane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/bus_form.fxml"));
            pagePane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnDashboardOnAction() {
        pagePane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_form.fxml"));
            pagePane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnDriverOnAction() {
        pagePane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/driver_form.fxml"));
            pagePane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnFillingOnAction() {
        pagePane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/filling_station_form.fxml"));
            pagePane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnGuardianOnAction() {
        pagePane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/guardian_form.fxml"));
            pagePane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnInventoryOnAction() {
        pagePane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/inventory_form.fxml"));
            pagePane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnLogoutOnAction() {
        Navigation.navigateToLoginForm();
    }

    @FXML
    void btnPaymentOnAction() {
        pagePane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/payment_form.fxml"));
            pagePane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    @FXML
    void btnStudentOnAction() {
        pagePane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/student_form.fxml"));
            pagePane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    private static void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Navigation Error");
        alert.showAndWait();
    }

    public static void Animation(AnchorPane anchorPane) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(350), anchorPane);
        transition.setFromX(-anchorPane.getPrefWidth());
        transition.setToX(0);
        transition.play();
    }
}
