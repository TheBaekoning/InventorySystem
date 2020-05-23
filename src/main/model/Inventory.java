package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;
    List<Part> partList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();

    public Inventory() {
        allParts = FXCollections.observableList(partList);
        allProducts = FXCollections.observableList(productList);
    }

    public void addPart(Part part) {
        allParts.add(part);
    }

    public void addProduct(Product product) {
        allProducts.add(product);
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public Product lookupProduct(int productId) {
        return allProducts.stream().filter(p -> p.getId() == productId).findAny().get();
    }

    public Part lookupPart(int partId) {
        return allParts.stream().filter(p -> p.getId() == partId).findFirst().get();
    }

    public ObservableList<Product> lookupProduct(String productName) {
        return allProducts
                .stream()
                .filter(p -> productName.equalsIgnoreCase(p.getName()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Part> lookupPart(String partName) {
        return allParts
                .stream()
                .filter(p -> partName.equalsIgnoreCase(p.getName()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public Boolean deletePart(Part selectedPart) {
        return allParts.removeIf(part -> selectedPart == part);
    }

    public Boolean deleteProduct(Product selectedProduct) {
        return allProducts.removeIf(product -> selectedProduct == product);
    }
}