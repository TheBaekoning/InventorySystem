package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.InHouse;
import main.model.OutSourced;

import java.io.IOException;

public class PartController {
    @FXML
    RadioButton inHouseButton;

    @FXML
    RadioButton outSourcedButton;

    @FXML
    TextField partId;

    @FXML
    TextField partName;

    @FXML
    TextField partInv;

    @FXML
    TextField partCost;

    @FXML
    TextField partMax;

    @FXML
    TextField partMin;

    @FXML
    TextField partCompany;

    @FXML
    Label miscName;

    @FXML
    Button savePartButton;

    @FXML
    private void clickCancelButton(ActionEvent event) throws IOException {
        Parent partPageParent = FXMLLoader.load(getClass().getResource("../fxml/MainMenu.fxml"));
        Scene partPageScene = new Scene(partPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(partPageScene);
        appStage.show();
    }

    @FXML
    private void inHouseSelected() {
        miscName.setText("Machine ID");
        if (outSourcedButton.isSelected()) {
            outSourcedButton.setSelected(false);
        }
    }

    @FXML
    private void outsourcedSelected() {
        miscName.setText("Company Name");
        if (inHouseButton.isSelected()) {
            inHouseButton.setSelected(false);

        }
    }

    @FXML
    private void clickAddSavePart() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainMenu.fxml"));
        MainMenuController mainMenuController = loader.getController();
        if (inHouseButton.isSelected()) {
            InHouse inHouse = new InHouse(mainMenuController.partId,
                    partName.getText(),
                    Integer.parseInt(partCost.getText()),
                    Integer.parseInt(partInv.getText()),
                    Integer.parseInt(partMin.getText()),
                    Integer.parseInt(partMax.getText()),
                    Integer.parseInt(partCompany.getText()));

            Stage stage;
            Parent root;
            stage = (Stage) savePartButton.getScene().getWindow();
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            mainMenuController.addPart(inHouse);
            mainMenuController.partId++;
        }

        if (outSourcedButton.isSelected()) {
            OutSourced outSourced = new OutSourced(mainMenuController.partId,
                    partName.getText(),
                    Integer.parseInt(partCost.getText()),
                    Integer.parseInt(partInv.getText()),
                    Integer.parseInt(partMin.getText()),
                    Integer.parseInt(partMax.getText()),
                    partCompany.getText());

            Stage stage;
            Parent root;
            stage = (Stage) savePartButton.getScene().getWindow();
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            mainMenuController.addPart(outSourced);
            mainMenuController.partId++;
        }

    }
}
