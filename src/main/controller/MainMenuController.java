package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private TextField partSearchField;
    @FXML
    private TextField productSearchField;

    public TableView<Part> partTable;
    public TableView<Product> productTable;
    public TableColumn partIdColumn;
    public TableColumn partNameColumn;
    public TableColumn partInvColumn;
    public TableColumn partCostColumn;
    public TableColumn productIdColumn;
    public TableColumn productNameColumn;
    public TableColumn productInvColumn;
    public TableColumn productCostColumn;

    public Inventory inventory = new Inventory();

    public int partId = 1;
    public int productId = 1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inventory.addPart(new InHouse(partId++, "2", 3, 4, 5, 6, 7));
        inventory.addPart(new InHouse(partId++, "23", 3.2, 5, 5, 6, 8));
        inventory.addPart(new InHouse(partId++, "24", 31, 6, 5, 6, 9));
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTable.setItems(inventory.getAllParts());

        inventory.addProduct(new Product(productId++, "2", 3, 4, 5, 6));
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTable.setItems(inventory.getAllProducts());
    }

    @FXML
    private void searchParts() {
        CharSequence searchString = partSearchField.getCharacters();
        int searchInt = Integer.parseInt(searchString.toString());

        for (int i = 0; i < inventory.getAllParts().size(); i++) {
            if (inventory.getAllParts().get(i).getId() == searchInt) {
                partTable.getSelectionModel().select(i);
                break;
            }
        }
    }

    @FXML
    private void searchProducts() {
        CharSequence searchString = productSearchField.getCharacters();
        int searchInt = Integer.parseInt(searchString.toString());

        for (int i = 0; i < inventory.getAllProducts().size(); i++) {
            if (inventory.getAllProducts().get(i).getId() == searchInt) {
                productTable.getSelectionModel().select(i);
                break;
            }
        }
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

    @FXML
    private void clickDeletePart() {
        inventory.deletePart(partTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void clickDeleteProduct() {
        inventory.deleteProduct(productTable.getSelectionModel().getSelectedItem());
    }

    public void addPart(InHouse inHouse) {
        inventory.addPart(inHouse);
    }

    public void addPart(OutSourced outSourced) {
        inventory.addPart(outSourced);
    }
}
