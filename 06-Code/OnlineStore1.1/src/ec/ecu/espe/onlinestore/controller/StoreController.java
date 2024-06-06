/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.ecu.espe.onlinestore.controller;

import ec.edu.espe.onlinestore.model.Offer;
import ec.edu.espe.onlinestore.model.Product;
import ec.edu.espe.onlinestore.model.Review;
import ec.edu.espe.onlinestore.model.Sale;
import ec.edu.espe.onlinestore.model.Store;
import ec.edu.espe.onlinestore.model.User;
import ec.edu.espe.onlinestore.view.AdminMenu;
import ec.edu.espe.onlinestore.view.ClientMenu;
import ec.edu.espe.onlinestore.view.Menu;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */


public class StoreController {
    private Store store;
    private Menu menu;
    private User loggedInUser;
    private Scanner scanner;
    private static final String DATA_FILE = "store_data.json";

    public StoreController(Store store, Menu menu) {
        this.store = store;
        this.menu = menu;
        this.scanner = new Scanner(System.in);
        loadStoreData();
    }

    public void start() throws IOException {
    try {
        if (!login()) {
            menu.showMessage("Invalid credentials. Exiting.");
            return;
        }

        if (loggedInUser.isAdmin()) {
            menu = new AdminMenu();
            adminMenu();
        } else {
            menu = new ClientMenu();
            clientMenu();
        }
    } catch (IOException e) {
        e.printStackTrace();
        menu.showMessage("Error loading data: " + e.getMessage());
    }
}


    private boolean login() {
        String username = menu.getUsername();
        String password = menu.getPassword();
        loggedInUser = store.authenticateUser(username, password);
        return loggedInUser != null;
    }

    private void clientMenu() {
    String username = loggedInUser.getUsername(); // Obtener el nombre de usuario del usuario conectado
    boolean exit = false;
    while (!exit) {
        int option = menu.showClientMenu();
        switch (option) {
            case 1:
                showAllProducts();
                break;
            case 2:
                viewOffers();
                break;
            case 3:
                searchProduct();
                break;
            case 4:
                
System.out.print("Enter the product IDs for purchase (enter -1 to finish): ");
List<Integer> productIds = new ArrayList<>();
while (true) {
    int productId = scanner.nextInt();
    if (productId == -1) break;
    productIds.add(productId);
}

double totalAmount = store.calculateTotalAmount(productIds);
System.out.println("Total amount: $" + totalAmount);

System.out.print("Do you want to proceed with the purchase? (yes/no): ");
String confirm = scanner.next();
if (!confirm.equalsIgnoreCase("yes")) {
    System.out.println("Purchase canceled.");
    break;
}

boolean exitPurchaseMenu = false;
while (!exitPurchaseMenu) {
    System.out.println("Purchase Menu:");
    System.out.println("1. Pay for Purchase");
    System.out.println("2. Add Another Product");
    System.out.println("3. Remove Product");
    System.out.println("4. Exit");
    System.out.print("Enter your choice: ");
    int purchaseChoice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    switch (purchaseChoice) {
        case 1:
            store.buyProducts(username, productIds); // Pasa el nombre de usuario aquí
            System.out.println("Purchase confirmed. Total amount: $" + totalAmount);
            exitPurchaseMenu = true;
            break;

        case 2:
            System.out.print("Enter the ID of the product to add: ");
            int newProductId = scanner.nextInt();
            productIds.add(newProductId);
            totalAmount = store.calculateTotalAmount(productIds);
            System.out.println("Total amount: $" + totalAmount);
            break;

        case 3:
            if (productIds.isEmpty()) {
                System.out.println("No products to remove.");
            } else {
                System.out.print("Enter the ID of the product to remove: ");
                int removeProductId = scanner.nextInt();
                boolean removed = productIds.removeIf(id -> id == removeProductId);
                if (removed) {
                    totalAmount = store.calculateTotalAmount(productIds);
                    System.out.println("Product removed. Total amount: $" + totalAmount);
                } else {
                    System.out.println("Product not found.");
                }
            }
            break;

        case 4:
            System.out.println("Purchase canceled.");
            exitPurchaseMenu = true;
            break;

        default:
            System.out.println("Invalid choice. Please try again.");
            break;
    }
}
break;  
            case 5:
                addReview();
                break;
            case 6:
                viewReviews();
                break;
            case 7:
                viewPurchaseHistory();
                break;
            case 8:
                exit = true;
                break;
            default:
                menu.showMessage("Invalid option.");
        }
    }
}
    private void adminMenu() throws IOException {
        boolean exit = false;
        while (!exit) {
            int option = menu.showAdminMenu();
            switch (option) {
                case 1:
                    addProduct();
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
                    viewReviews();
                    break;
                case 8:
                    manageStock();
                    break;
                case 9:
                    viewSales();
                    break;
                case 10:
                    addOffer();
                    break;
                case 11:
                    exit = true;
                    break;
                default:
                    menu.showMessage("Invalid option.");
            }
        }
    }

    private void makePurchase() {
    List<Integer> idProducts = menu.getProductIDsForPurchase();
    double total = 0.0;
    for (int id : idProducts) {
        Product product = store.searchProduct(id);
        if (product != null) {
            total += product.getPrice();
        }
    }
    if (idProducts.isEmpty()) {
        menu.showMessage("No products selected for purchase.");
        return;
    }
    menu.displayProducts(store.getProducts());
    menu.showMessage("Total amount: $" + total);

    boolean confirm = menu.confirmPurchase(total);
    if (confirm) {
        Sale sale = new Sale(loggedInUser.getUsername(), idProducts);
        store.addSale(sale);
        menu.showMessage("Purchase confirmed. Total amount: $" + total);
    } else {
        menu.showMessage("Purchase cancelled.");
    }
}

   private void addProduct() {
    Product product = menu.obtainProductData(); 
    store.addProduct(product.getName(), product.getPrice(), product.getCategory());
    menu.showMessage("Product added successfully.");
    saveStoreData(); 
}
   
   private void addOffer() {
    Offer offer = menu.obtainOfferData(); 
    store.addOffer(offer); 
    menu.showMessage("Offer added successfully.");
    saveStoreData(); 
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
    
    private Offer obtainOfferData() {
    menu.showMessage("Enter the name of the offer:");
    String name = scanner.nextLine();

    menu.showMessage("Enter the discount percentage:");
    double discount = scanner.nextDouble();
    scanner.nextLine(); 

    return new Offer(name, discount); // Suponiendo que el constructor de Offer acepta un nombre y un descuento
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

    private void addReview() {
        Review review = menu.getReviewData();
        store.addReview(review);
        saveStoreData();
        menu.showMessage("Review added successfully.");
        
    }
    
    private void saveStoreData() {
        try {
            store.saveToJson(DATA_FILE);
        } catch (IOException e) {
            e.printStackTrace();
            menu.showMessage("Error saving data: " + e.getMessage());
        }
    }

    private void viewReviews() {
        List<Review> reviews = store.getReviews();
        menu.displayReviews(reviews);
    }

  private void manageStock() throws IOException {
    int id = menu.getProductId();
    int quantity = menu.getStockQuantity();
    boolean result = store.addStock(id, quantity);
    if (result) {
        menu.showMessage("Stock updated successfully.");
    } else {
        menu.showMessage("Failed to update stock.");
    }
}


    private void viewSales() {
        List<Sale> sales = store.getSales();
        menu.displaySales(sales, store.getProducts()); 
    }

    private void viewPurchaseHistory() {
        List<Sale> sales = store.getSales();
        menu.displaySales(sales, store.getProducts());
    }
    
   /* public void initializeStore() {
    store.addUser(new User("admin", "admin123", true));
    store.addUser(new User("client", "client123", false));
    store.addProduct("Laptop", 1000, new Category("Electronics", "Devices and gadgets"));
    store.addProduct("Smartphone", 700, new Category("Electronics", "Devices and gadgets"));
    store.addProduct("Tablet", 300, new Category("Electronics", "Devices and gadgets"));
    store.addOffer(new Offer("10% off on all electronics", new Category("Electronics", "Devices and gadgets")));
}*/
    
    private void loadStoreData() {
        try {
            store.loadFromJson(DATA_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with an empty store.");
        } catch (IOException e) {
            e.printStackTrace();
            menu.showMessage("Error loading data: " + e.getMessage());
        }
    }
    
}
