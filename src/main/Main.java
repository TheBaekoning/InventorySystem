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
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Inventory Management System");
        primaryStage.show();
    }


    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        InHouse part = new InHouse(1, "test", 2.00, 10, 1, 10, 0001);
        inventory.addPart(part);

        System.out.println(inventory.getAllParts().get(0).getName());

        launch(args);
    }
}
