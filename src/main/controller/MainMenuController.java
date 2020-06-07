package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @FXML
    public Button addPart;

    @FXML
    public Button addProduct;

    @FXML
    public Button modifyPart;

    @FXML
    public Button modifyProduct;

    public static Inventory inventory = new Inventory();

    public static int partId = 1;
    public static int productId = 1;

    private static int updateIndex = 0;

    private static Boolean isInitialized = false;

    public static Product modifiedProduct;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!isInitialized) {
            inventory.addPart(new InHouse(partId++, "2", 3, 4, 5, 6, 7));
            inventory.addPart(new InHouse(partId++, "23", 3.2, 5, 5, 6, 8));
            inventory.addPart(new InHouse(partId++, "24", 31, 6, 5, 6, 9));

            inventory.addProduct(new Product(productId++, "2", 3, 4, 5, 6));

            isInitialized = true;
        }

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTable.setItems(inventory.getAllParts());

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
        Stage stage;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/AddPart.fxml"));
        stage = (Stage) addPart.getScene().getWindow();
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        PartController partController = loader.getController();
        partController.partNumber = partId;
    }

    @FXML
    private void clickModifyPartButton(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ModifyPart.fxml"));
        stage = (Stage) modifyPart.getScene().getWindow();
        root = loader.load();
        PartController partController = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Part part = partTable.getSelectionModel().getSelectedItem();
        String className = part.getClass().getName();
        switch (className) {
            case "main.model.InHouse" :
                partController.setPart((InHouse) part);
                break;
            case "main.model.OutSourced" :
                partController.setPart((OutSourced) part);
                break;
        }
        for (int i=0; i < inventory.getAllParts().size(); i++){
            if (inventory.getAllParts().get(i).getId() == part.getId() )
                updateIndex = i;
        }
        partController.modifyPart = part;
    }

    @FXML
    private void clickAddProductButton(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/AddProduct.fxml"));
        stage = (Stage) addProduct.getScene().getWindow();
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ProductController productController = loader.getController();
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

    public void updatePart(Part part){
        inventory.updatePart(updateIndex, part);
    }

    public void addProduct(Product product){
        inventory.addProduct(product);
    }

    public void updateProduct(Product product) {
        inventory.updateProduct(updateIndex, product);
    }
}
