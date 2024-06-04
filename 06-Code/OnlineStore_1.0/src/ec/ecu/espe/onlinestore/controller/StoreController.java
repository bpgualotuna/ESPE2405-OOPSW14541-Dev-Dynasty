/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.ecu.espe.onlinestore.controller;

import ec.edu.espe.onlinestore.model.Offer;
import ec.edu.espe.onlinestore.model.Product;
import ec.edu.espe.onlinestore.model.Review;
import ec.edu.espe.onlinestore.model.Store;
import ec.edu.espe.onlinestore.model.User;
import ec.edu.espe.onlinestore.view.Menu;
import java.util.List;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class StoreController {
    private Store store;
    private Menu menu;
    private User loggedInUser;

    public StoreController(Store store, Menu menu) {
        this.store = store;
        this.menu = menu;
    }

    public void start() {
    if (!login()) {
        menu.showMessage("Invalid credentials. Exiting.");
        return;
    }

    boolean exit = false;
    while (!exit) {
        int option = menu.showMenu();
        switch (option) {
            case 1:
                makePurchase();
                break;
            case 2:
                deleteProduct();
                break;
            case 3:
                editProduct();
                break;
            case 4:
                searchProduct();
                break;
            case 5:
                showAllProducts();
                break;
            case 6:
                viewOffers();
                break;
            case 7:
                manageRecommendations();
                break;
            case 8:
                exit = true;
                break;
            default:
                menu.showMessage("Invalid option.");
        }
    }
}
    private boolean login() {
        String username = menu.getUsername();
        String password = menu.getPassword();
        loggedInUser = store.authenticateUser(username, password);
        return loggedInUser != null;
    }

    private void makePurchase() {
        menu.displayProducts(store.getProducts());
        List<Integer> idProducts = menu.getProductIDsForPurchase();
        double total = 0.0;
        for (int id : idProducts) {
            Product product = store.searchProduct(id);
            if (product != null) {
                total += product.getPrice();
            }
        }
        menu.showMessage("Total purchase amount: $" + total);
    }

    private void deleteProduct() {
        int id = menu.getProductId();
        boolean result = store.deleteProduct(id);
        if (result) {
            menu.showMessage("Product deleted successfully.");
        } else {
            menu.showMessage("Failed to delete product.");
        }
    }

    private void editProduct() {
        int id = menu.getProductId();
        Product product = menu.getEditedProductData();
        boolean result = store.editProduct(id, product.getName(), product.getPrice());
        if (result) {
            menu.showMessage("Product edited successfully.");
        } else {
            menu.showMessage("Failed to edit product.");
        }
    }

    private void searchProduct() {
        int id = menu.getProductId();
        Product product = store.searchProduct(id);
        menu.showProduct(product);
    }

    private void showAllProducts() {
        List<Product> products = store.getProducts();
        menu.displayProducts(products);
    }

    private void viewOffers() {
        List<Offer> offers = store.getOffers();
        menu.displayOffers(offers);
    }

    private void manageRecommendations() {
        Review review = menu.getReviewData();
        store.addReview(review);
        menu.showMessage("Review added successfully.");
        List<Review> reviews = store.getReviews();
        menu.displayReviews(reviews);
    }
}