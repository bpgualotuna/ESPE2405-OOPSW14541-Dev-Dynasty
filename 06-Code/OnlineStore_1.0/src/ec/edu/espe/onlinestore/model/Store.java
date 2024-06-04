/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.onlinestore.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public final class Store {
   private List<Product> products = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Offer> offers = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();
    private Inventory inventory = new Inventory();
    private int currentProductId = 1;
    private int currentUserId = 1;

    public Store() {
        uploadProducts();
        uploadUsers();
        uploadOffers();
    }

    public void uploadProducts() {
        addProduct("Product A", 10.0, new Category("Electronics", "Electronic items"));
        addProduct("Product B", 20.0, new Category("Electronics", "Electronic items"));
        addProduct("Product C", 30.0, new Category("Clothing", "Clothing items"));
        addProduct("Product D", 40.0, new Category("Clothing", "Clothing items"));
        addProduct("Product E", 50.0, new Category("Groceries", "Grocery items"));
    }

    public void uploadUsers() {
        addUser(new User(currentUserId++, "User1", "Dev Dynasty", "user1@example.com"));
        addUser(new User(currentUserId++, "User2", "123", "user2@example.com"));
    }

    public void uploadOffers() {
        addOffer(new Offer("Summer Sale", "Discount on summer items", 15.0));
        addOffer(new Offer("Black Friday", "Huge discounts on electronics", 50.0));
    }

    public void addProduct(String name, double price, Category category) {
        Product product = new Product(currentProductId++, name, price, category);
        products.add(product);
        inventory.addProduct(product, 100); // Adding 100 units of each product initially
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean deleteProduct(int id) {
        Product product = searchProduct(id);
        if (product != null) {
            products.remove(product);
            inventory.removeProduct(product, inventory.getQuantity(product));
            return true;
        }
        return false;
    }

    public boolean editProduct(int id, String newName, double newPrice) {
        Optional<Product> productOpt = products.stream().filter(p -> p.getId() == id).findFirst();
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setName(newName);
            product.setPrice(newPrice);
            return true;
        }
        return false;
    }

    public Product searchProduct(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Inventory getInventory() {
        return inventory;
    }
}