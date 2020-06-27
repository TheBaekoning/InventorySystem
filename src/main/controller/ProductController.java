package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.exceptionHandling.OutOfBoundsInventory;
import main.model.Inventory;
import main.model.Part;
import main.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    Inventory inventory;
    Product product;

    @FXML
    TableView<Part> partTable;
    public TableColumn partIdColumn;
    public TableColumn partNameColumn;
    public TableColumn partInvColumn;
    public TableColumn partCostColumn;
    public TableColumn altPartIdColumn;
    public TableColumn altPartNameColumn;
    public TableColumn altPartInvColumn;
    public TableColumn altPartCostColumn;

    @FXML
    TextField searchField;
    @FXML
    TextField productName;
    @FXML
    TextField productId;
    @FXML
    TextField productInv;
    @FXML
    TextField productMin;
    @FXML
    TextField productMax;
    @FXML
    TextField productCost;

    @FXML
    Button addProductButton;
    @FXML
    Button modifyProductButton;

    @FXML
    TableView<Part> altPartTable;


    @FXML
    private void clickCancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Confirmation");
        alert.setHeaderText("Do you wish to cancel?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (!altPartTable.getItems().isEmpty()) {
                for (int i = 0; i < altPartTable.getItems().size(); i++) {
                    inventory.addPart(altPartTable.getItems().get(i));
                }
            }
            Parent partPageParent = FXMLLoader.load(getClass().getResource("../fxml/MainMenu.fxml"));
            Scene partPageScene = new Scene(partPageParent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(partPageScene);
            appStage.show();
        }
    }

    @FXML
    private void clickAddProduct() throws IOException, OutOfBoundsInventory {
        product.setId(Integer.parseInt(productId.getText()));
        product.setName(productName.getText());
        product.setPrice(Double.parseDouble(productCost.getText()));
        product.setMin(Integer.parseInt(productMin.getText()));
        product.setMax(Integer.parseInt(productMax.getText()));


        if (Integer.parseInt(productInv.getText()) > Integer.parseInt(productMax.getText()) ||
                Integer.parseInt(productInv.getText()) < Integer.parseInt(productMin.getText()))
            throw new OutOfBoundsInventory("Entering an inventory value that exceeds the " +
                    "minimum or maximum value for that part or product");
        product.setStock(Integer.parseInt(productInv.getText()));

        Stage stage;
        Parent root;
        stage = (Stage) addProductButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainMenu.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.addProduct(product);
        MainMenuController.productId++;
    }

    @FXML
    private void clickSearchPart() {
        CharSequence searchString = searchField.getCharacters();
        int searchInt = Integer.parseInt(searchString.toString());

        for (int i = 0; i < inventory.getAllParts().size(); i++) {
            if (inventory.getAllParts().get(i).getId() == searchInt) {
                partTable.getSelectionModel().select(i);
                break;
            }
        }
    }

    @FXML
    private void clickDeletePart() {
        Part part = altPartTable.getSelectionModel().getSelectedItem();
        if (part == null)
            return;

        inventory.addPart(part);
        product.deleteAssociatedPart(part);
    }

    @FXML
    private void clickAddPart() {
        Part part = partTable.getSelectionModel().getSelectedItem();
        if (part == null)
            return;

        product.addAssociatedPart(part);
        inventory.deletePart(part);

        altPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        altPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        altPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        altPartCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        altPartTable.setItems(product.getAllAssociatedParts());
    }

    @FXML
    private void clickModifyProduct() throws IOException, OutOfBoundsInventory {
        product.setId(Integer.parseInt(productId.getText()));
        product.setName(productName.getText());
        product.setPrice(Double.parseDouble(productCost.getText()));
        product.setMin(Integer.parseInt(productMin.getText()));
        product.setMax(Integer.parseInt(productMax.getText()));

        if (Integer.parseInt(productInv.getText()) > Integer.parseInt(productMax.getText()) ||
                Integer.parseInt(productInv.getText()) < Integer.parseInt(productMin.getText()))
            throw new OutOfBoundsInventory("Entering an inventory value that exceeds the " +
                    "minimum or maximum value for that part or product");
        product.setStock(Integer.parseInt(productInv.getText()));
        Stage stage;
        Parent root;
        stage = (Stage) modifyProductButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainMenu.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.updateProduct(product);
    }

    public void setProduct(Product product) {
        productId.setText(Integer.toString(product.getId()));
        productName.setText(product.getName());
        productInv.setText(Integer.toString(product.getStock()));
        productCost.setText(Double.toString(product.getPrice()));
        productMin.setText(Integer.toString(product.getMin()));
        productMax.setText(Integer.toString(product.getMax()));
        this.product = product;
        altPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        altPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        altPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        altPartCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        altPartTable.setItems(product.getAllAssociatedParts());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inventory = MainMenuController.inventory;
        productId.setText(Integer.toString(MainMenuController.productId));
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTable.setItems(inventory.getAllParts());

        if (product != null) {
            altPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            altPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            altPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
            altPartCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

            altPartTable.setItems(product.getAllAssociatedParts());
        }

        product = new Product();

    }
}
