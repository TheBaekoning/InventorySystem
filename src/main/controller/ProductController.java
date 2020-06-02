package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductController {

    @FXML
    private void clickCancelButton(ActionEvent event) throws IOException {
        Parent partPageParent = FXMLLoader.load(getClass().getResource("../fxml/MainMenu.fxml"));
        Scene partPageScene = new Scene(partPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(partPageScene);
        appStage.show();
    }
}
