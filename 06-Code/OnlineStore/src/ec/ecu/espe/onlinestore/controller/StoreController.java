/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.ecu.espe.onlinestore.controller;

import ec.edu.espe.onlinestore.model.Product;
import ec.edu.espe.onlinestore.model.Store;
import ec.edu.espe.onlinestore.view.Menu;
import java.util.List;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class StoreController {
    private Store store;
    private Menu menu;

    public StoreController(Store store, Menu menu) {
        this.store = store;
        this.menu = menu;
    }
    
    public void star() {
        boolean exit = false;
        while (!exit) {
            int option = menu.showMenu();
            switch(option) {
                case 1:
                    showAllProducts();
                    makeaPurchase();
                    break;
                case 2:
                    showAllProducts();
                    deleteProduct();
                    break;
                case 3:
                    showAllProducts();
                    editProduct();
                    break;
                case 4:
                    showAllProducts();
                    searchProduct();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    menu.showMensaje("Invalid option");
            }
        }
    }
    
    private void makeaPurchase() {
        List<Integer> idProducts = menu.getProductIDsForPurchase();
        double total = 0.0;
        for (int id : idProducts) {
            Product product = store.searchProduct(id);
            if(product != null) {
                total += product.getPrice();
            } else {
                menu.showMensaje("Product with ID " + id + " not found");
            }
        }
        menu.showMensaje("Total purchase price: $" + total);
    }
    
    private void deleteProduct() {
        int id = menu.getProductId();
        if(store.deleteProduct(id)) {
            menu.showMensaje("Removed product");
        } else {
            menu.showMensaje("Product not found");
        }
    }
    
    private void editProduct() {
        int id = menu.getProductId();
        Product productE = menu.getEditedProductData();
        if(store.editProduct(id, productE.getName(), productE.getPrice())) {
            menu.showMensaje("Edited product: " + productE);
        } else {
            menu.showMensaje("Product not found");
        }
    }
    
    private void searchProduct() {
        int id = menu.getProductId();
        Product product = store.searchProduct(id);
        menu.showProduct(product);
    }

    public void showAllProducts() {
        List<Product> products = store.getProducts();
        for (Product product : products) {
            System.out.println(product);
        }
    }
}