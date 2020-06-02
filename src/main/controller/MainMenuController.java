package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

public class MainMenuController implements Initializable {
    @FXML
    private TextField partSearchField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void searchParts() {
        System.out.println(partSearchField.getCharacters());
        String searchString = (String) partSearchField.getCharacters();
    }

    @FXML
    private void clickAddPartButton(ActionEvent event) throws IOException {
        Parent partPageParent = FXMLLoader.load(getClass().getResource("../fxml/AddPart.fxml"));
        Scene partPageScene = new Scene(partPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(partPageScene);
        appStage.show();
    }

    @FXML
    private void clickModifyPartButton(ActionEvent event) throws IOException {
        Parent partPageParent = FXMLLoader.load(getClass().getResource("../fxml/ModifyPart.fxml"));
        Scene partPageScene = new Scene(partPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(partPageScene);
        appStage.show();
    }

    @FXML
    private void clickAddProductButton(ActionEvent event) throws IOException {
        Parent partPageParent = FXMLLoader.load(getClass().getResource("../fxml/AddProduct.fxml"));
        Scene partPageScene = new Scene(partPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(partPageScene);
        appStage.show();
    }

    @FXML
    private void clickModifyProductButton(ActionEvent event) throws IOException {
        Parent partPageParent = FXMLLoader.load(getClass().getResource("../fxml/ModifyProduct.fxml"));
        Scene partPageScene = new Scene(partPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(partPageScene);
        appStage.show();
    }

    @FXML
    private void clickExitButton() {
        System.exit(0);
    }
}
