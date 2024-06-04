/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.onlinestore.view;


import ec.edu.espe.onlinestore.model.Offer;
import ec.edu.espe.onlinestore.model.Product;
import ec.edu.espe.onlinestore.model.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class Menu {
    
   private Scanner scanner = new Scanner(System.in);

    public int showMenu() {
        System.out.println("******Choose an option****");
        System.out.println("1. Make a purchase");
        System.out.println("2. Delete product");
        System.out.println("3. Edit product");
        System.out.println("4. Search product");
        System.out.println("5. Show all products");
        System.out.println("6. View offers");
        System.out.println("7. Manage recommendations");
        System.out.println("8. Exit");
        return scanner.nextInt();
    }

   public String getUsername() {
    System.out.println("Enter username: ");
    return scanner.nextLine();
}

    public String getPassword() {
        System.out.println("Enter password: ");
        return scanner.nextLine();
    }

    public Product obtainProductData() {
        System.out.println("Enter the product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume the remaining newline character
        return new Product(0, name, price, null);
    }

    public int getProductId() {
        System.out.println("Enter the product ID: ");
        return scanner.nextInt();
    }

    public Product getEditedProductData() {
        System.out.println("Enter new product name: ");
        String name = scanner.next();
        System.out.println("Enter new product price: ");
        double price = scanner.nextDouble();
        return new Product(0, name, price, null);
    }

    public void showProduct(Product product) {
        if (product != null) {
            System.out.println("Product found: " + product);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public List<Integer> getProductIDsForPurchase() {
        List<Integer> ids = new ArrayList<>();
        System.out.println("Enter product IDs to purchase (comma separated): ");
        String[] idStrings = scanner.next().split(",");
        for (String idString : idStrings) {
            ids.add(Integer.parseInt(idString.trim()));
        }
        return ids;
    }

    public void displayProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public Review getReviewData() {
        System.out.println("Enter review details: ");
        String details = scanner.nextLine();
        System.out.println("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();  // Consume the remaining newline character
        return new Review(details, rating);
    }

    public void displayOffers(List<Offer> offers) {
        for (Offer offer : offers) {
            System.out.println(offer);
        }
    }

    public void displayReviews(List<Review> reviews) {
        for (Review review : reviews) {
            System.out.println(review);
        }
    }
}