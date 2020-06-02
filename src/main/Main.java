package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.model.InHouse;
import main.model.Inventory;
import main.model.Product;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../main/fxml/MainMenu.fxml"));

        Scene scene = new Scene(root, 1364, 617);

        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {


        launch(args);
    }
}
