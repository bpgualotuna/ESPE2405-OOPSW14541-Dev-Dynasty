package ec.edu.espe.onlinestore.model;

import ec.edu.espe.onlinestore.util.FileJson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author  Dev Dynasty, DCCO-ESPE
 */
public final class Store {
   private List<Product> products;
    private List<Offer> offers;
    private List<Review> reviews;
    private Inventory inventory;
    private List<User> users;
    private List<Sale> sales;
    private static final String DATA_FILE = "store_data.json";

    public Store() {
        this.products= new ArrayList<>();
        this.offers = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.inventory = new Inventory();
        this.users = new ArrayList<>();
        this.sales = new ArrayList<>();
         
    }
    
    

    public void addProduct(String name, double price, Category category) {
        products.add(new Product(products.size() + 1, name, price, category));
    }

    public boolean deleteProduct(int id) {
        return products.removeIf(product -> product.getId() == id);
    }

    public boolean editProduct(int id, String name, double price) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setName(name);
                product.setPrice(price);
                return true;
            }
        }
        return false;
    }

    public Product searchProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

  public boolean manageStock(int productId, int quantity) {
    Product product = searchProduct(productId);
    if (product != null) {
        product.setStock(quantity);
        return true;
    }
    return false;
}
  
    public List<Sale> getSales() {
        return sales;
    }

    public void addSale(Sale sale) {
        sales.add(sale);
    }

public boolean addStock(int productId, int quantity) throws IOException {
    Product product = getProduct(productId);
    if (product != null) {
        product.setStock(product.getStock() + quantity);
        saveToJson(DATA_FILE); // Guardar los cambios en el archivo JSON
        return true;
    }
    return false;
}


public boolean removeStock(int productId, int quantity) {
    Product product = getProduct(productId);
    if (product != null && product.getStock() >= quantity) {
        product.setStock(product.getStock() - quantity);
        return true;
    }
    return false;
}

public boolean editStock(int productId, int newQuantity) {
    Product product = getProduct(productId);
    if (product != null) {
        product.setStock(newQuantity);
        return true;
    }
    return false;
}

public Product getProduct(int productId) {
    for (Product product : products) {
        if (product.getId() == productId) {
            return product;
        }
    }
    return null; // Si no se encuentra el producto con el ID dado
}

public int getStock(int productId) {
    Product product = getProduct(productId);
    if (product != null) {
        return product.getStock();
    }
    return -1; // Valor de retorno predeterminado si el producto no se encuentra
}

public void loadFromJson(String filePath) throws IOException {
        FileJson jsonFileHandler = new FileJson();
        StoreData storeData = jsonFileHandler.loadFromJson(filePath, StoreData.class);
        this.products = storeData.getProducts();
        this.sales = storeData.getSales();
        this.reviews = storeData.getReviews();
        this.users = storeData.getUsers();
    }

   public void saveToJson(String filePath) throws IOException {
        FileJson jsonFileHandler = new FileJson();
        StoreData storeData = new StoreData(products, sales, reviews, users);
        jsonFileHandler.saveToJson(storeData, filePath);
    }
   
  public void buyProducts(String username, List<Integer> productIds) {
        double totalAmount = 0.0;
        List<Product> purchasedProducts = new ArrayList<>();

        for (int productId : productIds) {
            Product product = getProduct(productId);
            if (product != null && product.getStock() > 0) {
                totalAmount += product.getPrice();
                product.setStock(product.getStock() - 1); // Reduce the stock by 1 for each purchase
                purchasedProducts.add(product);
            }
        }

        if (!purchasedProducts.isEmpty()) {
            Sale sale = new Sale(username, productIds);
            sales.add(sale);
            try {
                saveToJson("store_data.json"); // Save data after purchase
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Purchase confirmed. Total amount: $" + totalAmount);
            System.out.println("Purchased products:");
            for (Product product : purchasedProducts) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products were purchased. Please check the stock and try again.");
        }
    }

  public double calculateTotalAmount(List<Integer> productIds) {
    double totalAmount = 0.0;
    for (int productId : productIds) {
        Product product = searchProduct(productId);
        if (product != null) {
            totalAmount += product.getPrice();
        }
    }
    return totalAmount;
}

}