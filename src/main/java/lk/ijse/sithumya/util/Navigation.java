package lk.ijse.sithumya.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;

public class Navigation {

    @Setter
    private static Stage primaryStage;

    public static void navigateToDashboard() {
        try {
            // Load both FXML files into their respective Parent objects
            Parent mainForm = FXMLLoader.load(Navigation.class.getResource("/view/dashboard_form.fxml"));

            // Create a BorderPane to hold both forms
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(mainForm); // Set mainform at the center or choose another position

            // Set up the scene with the borderPane containing both nodes
            Scene scene = new Scene(borderPane);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Dashboard Form");
            primaryStage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void openPasswordResetForm() {
        try {
            AnchorPane rootNode = FXMLLoader.load(Navigation.class.getResource("/view/reset_password_form.fxml"));
            Scene scene = new Scene(rootNode);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Password Reset Form");
            primaryStage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToLoginForm() {
        try {
            AnchorPane rootNode = FXMLLoader.load(Navigation.class.getResource("/view/login_form.fxml"));
            Scene scene = new Scene(rootNode);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Login Form");
            primaryStage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToStudentManageForm() {
        try {
            Parent rootNode = FXMLLoader.load(Navigation.class.getResource("/view/student_manage_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Student Manage Form");
            stage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToDriverManageForm() {
        try {
            Parent rootNode = FXMLLoader.load(Navigation.class.getResource("/view/driver_manage_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Driver Manage Form");
            stage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToBusManageForm() {
        try {
            Parent rootNode = FXMLLoader.load(Navigation.class.getResource("/view/bus_manage_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Bus Manage Form");
            stage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToStationManageForm() {
        try {
            Parent rootNode = FXMLLoader.load(Navigation.class.getResource("/view/filling_station_manage_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Station Manage Form");
            stage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToSalaryManageForm() {
        try {
            Parent rootNode = FXMLLoader.load(Navigation.class.getResource("/view/salary_manage_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Salary Manage Form");
            stage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToGuardianManageForm() {
        try {
            Parent rootNode = FXMLLoader.load(Navigation.class.getResource("/view/guardian_manage_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Guardian Manage Form");
            stage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToMaintenanceManageForm() {
        try {
            Parent rootNode = FXMLLoader.load(Navigation.class.getResource("/view/maintenance_manage_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Maintenance Manage Form");
            stage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToInventoryManageForm() {
        try {
            Parent rootNode = FXMLLoader.load(Navigation.class.getResource("/view/inventory_manage_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Inventory Manage Form");
            stage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToPaymentPlanManageForm() {
        try {
            Parent rootNode = FXMLLoader.load(Navigation.class.getResource("/view/payment_plan_manage_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Payment Plan Manage Form");
            stage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToSetPaymentPlanForm() {
        try {
            Parent rootNode = FXMLLoader.load(Navigation.class.getResource("/view/set_plan_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Set Payment Plan Form");
            stage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToPaymentForm() {
        try {
            Parent rootNode = FXMLLoader.load(Navigation.class.getResource("/view/make_payments_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Payment Form");
            stage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    public static void navigateToSendTimeForm() {
        try {
            Parent rootNode = FXMLLoader.load(Navigation.class.getResource("/view/bus_schedule_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Bus Schedule Form");
            stage.show();
        } catch (IOException e) {
            showErrorAlert();
        }
    }

    private static void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Navigation Error");
        alert.showAndWait();
    }
}
