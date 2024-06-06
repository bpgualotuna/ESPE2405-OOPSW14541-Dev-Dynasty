/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.onlinestore.view;


import ec.edu.espe.onlinestore.model.Category;
import ec.edu.espe.onlinestore.model.Offer;
import ec.edu.espe.onlinestore.model.Product;
import ec.edu.espe.onlinestore.model.Review;
import ec.edu.espe.onlinestore.model.Sale;
import ec.edu.espe.onlinestore.model.Store;
import ec.edu.espe.onlinestore.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author  Dev Dynasty, DCCO-ESPE
 */

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public int showAdminMenu() {
        return -1; // Placeholder, to be overridden by AdminMenu
    }

    public int showClientMenu() {
        return -1; // Placeholder, to be overridden by ClientMenu
    }

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void showMenu(User user) {
        if (user.isAdmin()) {
            showAdminMenu();
        } else {
            showClientMenu();
        }
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
        System.out.println("Enter the product category: ");
        String categoryName = scanner.nextLine();
        Category category = new Category(categoryName, "Description of " + categoryName);
        return new Product(0, name, price, category);
    }
    
    public Offer obtainOfferData() {
    System.out.println("Enter the name of the offer:");
    String name = scanner.nextLine();

    System.out.println("Enter the discount percentage:");
    double discount = scanner.nextDouble();
    scanner.nextLine(); // Consume the remaining newline character

    return new Offer(name, discount);
}


    public Product getEditedProductData() {
        System.out.println("Enter the new product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the new product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume the remaining newline character
        return new Product(0, name, price, null);
    }

    public int getProductId() {
        System.out.println("Enter the product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el carácter de nueva línea residual
        return id;
    }

    public List<Integer> getProductIDsForPurchase() {
        List<Integer> ids = new ArrayList<>();
        System.out.println("Enter the product IDs for purchase (enter -1 to finish): ");
        int id;
        while ((id = scanner.nextInt()) != -1) {
            ids.add(id);
        }
        return ids;
    }

    public boolean confirmPurchase(double total) {
        System.out.println("Total amount: $" + total + ". Do you confirm the purchase? (yes/no)");
        String response = scanner.next();
        return response.equalsIgnoreCase("yes");
    }

    public void displayProducts(List<Product> products) {
        System.out.println("Product list:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void displayOffers(List<Offer> offers) {
        System.out.println("Offers:");
        for (Offer offer : offers) {
            System.out.println(offer);
        }
    }

    public Review getReviewData() {
        System.out.println("Enter product ID for review: ");
        int productId = scanner.nextInt();
        scanner.nextLine();  // Consume the remaining newline character
        System.out.println("Enter your review: ");
        String content = scanner.nextLine();
        return new Review(productId, content);
    }

    public void displayReviews(List<Review> reviews) {
        System.out.println("Reviews:");
        for (Review review : reviews) {
            System.out.println(review);
        }
    }

    public void showProduct(Product product) {
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void displaySales(List<Sale> sales, List<Product> products) {
        System.out.println("Sales history:");
        for (Sale sale : sales) {
            System.out.println("User: " + sale.getUsername());
            System.out.println("Products:");
            for (int productId : sale.getProductIds()) {
                Product product = findProductById(products, productId);
                if (product != null) {
                    System.out.println(product);
                }
            }
            System.out.println();
        }
    }

    private Product findProductById(List<Product> products, int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
     
    public int getStockQuantity() {
    System.out.println("Enter stock quantity: ");
    return scanner.nextInt();
}

  
}
