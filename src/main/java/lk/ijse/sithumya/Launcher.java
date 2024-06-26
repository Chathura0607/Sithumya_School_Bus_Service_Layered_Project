package lk.ijse.sithumya;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.sithumya.util.Navigation;

public class Launcher extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Navigation.setPrimaryStage(primaryStage);

            Parent root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Login Page");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
