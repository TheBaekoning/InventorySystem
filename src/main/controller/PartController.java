package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.InHouse;
import main.model.OutSourced;
import main.model.Part;

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
    Button saveModifyButton;

    @FXML
    Button cancelButton;

    int partNumber;

    Part modifyPart;

    @FXML
    private void clickCancelButton(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainMenu.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void inHouseSelected() {
        partId.setText(Integer.toString(partNumber));
        miscName.setText("Machine ID");
        if (outSourcedButton.isSelected()) {
            outSourcedButton.setSelected(false);
        }
    }

    @FXML
    private void outsourcedSelected() {
        partId.setText(Integer.toString(partNumber));
        miscName.setText("Company Name");
        if (inHouseButton.isSelected()) {
            inHouseButton.setSelected(false);
        }
    }

    @FXML
    private void inHouseSelectedModify() {
        miscName.setText("Machine ID");
        if (outSourcedButton.isSelected()) {
            outSourcedButton.setSelected(false);
        }
    }

    @FXML
    private void outsourcedSelectedModify() {
        miscName.setText("Company Name");
        if (inHouseButton.isSelected()) {
            inHouseButton.setSelected(false);
        }
    }

    @FXML
    private void clickAddSavePart() throws IOException {
        if (inHouseButton.isSelected()) {
            InHouse inHouse = new InHouse(Integer.parseInt(partId.getText()),
                    partName.getText(),
                    Double.parseDouble(partCost.getText()),
                    Integer.parseInt(partInv.getText()),
                    Integer.parseInt(partMin.getText()),
                    Integer.parseInt(partMax.getText()),
                    Integer.parseInt(partCompany.getText()));

            Stage stage;
            Parent root;
            stage = (Stage) savePartButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainMenu.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.addPart(inHouse);
            MainMenuController.partId++;
        }

        if (outSourcedButton.isSelected()) {
            OutSourced outSourced = new OutSourced(Integer.parseInt(partId.getText()),
                    partName.getText(),
                    Double.parseDouble(partCost.getText()),
                    Integer.parseInt(partInv.getText()),
                    Integer.parseInt(partMin.getText()),
                    Integer.parseInt(partMax.getText()),
                    partCompany.getText());

            Stage stage;
            Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainMenu.fxml"));
            stage = (Stage) savePartButton.getScene().getWindow();
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.addPart(outSourced);
            MainMenuController.partId++;
        }

    }

    public void setPart(InHouse inHouse) {
        inHouseButton.setSelected(true);
        partId.setText(Integer.toString(inHouse.getId()));
        partName.setText(inHouse.getName());
        partCost.setText(Double.toString(inHouse.getPrice()));
        partInv.setText(Integer.toString(inHouse.getStock()));
        partMin.setText(Integer.toString(inHouse.getMin()));
        partMax.setText(Integer.toString(inHouse.getMax()));
        partCompany.setText(Integer.toString(inHouse.getMachineId()));

        miscName.setText("Machine ID");
    }

    public void setPart(OutSourced outSourced) {
        outSourcedButton.setSelected(true);
        partId.setText(Integer.toString(outSourced.getId()));
        partName.setText(outSourced.getName());
        partCost.setText(Double.toString(outSourced.getPrice()));
        partInv.setText(Integer.toString(outSourced.getStock()));
        partMin.setText(Integer.toString(outSourced.getMin()));
        partMax.setText(Integer.toString(outSourced.getMax()));
        partCompany.setText(outSourced.getCompanyName());

        miscName.setText("Company Name");
    }

    @FXML
    private void clickSaveModify() throws IOException {
        if (inHouseButton.isSelected()) {
            InHouse inHouse = new InHouse(Integer.parseInt(partId.getText()),
                    partName.getText(),
                    Double.parseDouble(partCost.getText()),
                    Integer.parseInt(partInv.getText()),
                    Integer.parseInt(partMin.getText()),
                    Integer.parseInt(partMax.getText()),
                    Integer.parseInt(partCompany.getText()));

            Stage stage;
            Parent root;
            stage = (Stage) saveModifyButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainMenu.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.updatePart(inHouse);
        }

        if (outSourcedButton.isSelected()) {
            OutSourced outSourced = new OutSourced(Integer.parseInt(partId.getText()),
                    partName.getText(),
                    Double.parseDouble(partCost.getText()),
                    Integer.parseInt(partInv.getText()),
                    Integer.parseInt(partMin.getText()),
                    Integer.parseInt(partMax.getText()),
                    partCompany.getText());

            Stage stage;
            Parent root;
            stage = (Stage) saveModifyButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainMenu.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.updatePart(outSourced);

        }
    }

}
