/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.onlinestore.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class Inventory {
    private Map<Product, Integer> products = new HashMap<>();

    public int getQuantity(Product product) {
        return products.getOrDefault(product, 0);
    }

    public void addProduct(Product product, int quantity) {
        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            int currentQuantity = products.get(product);
            if (currentQuantity >= quantity) {
                products.put(product, currentQuantity - quantity);
                if (products.get(product) == 0) {
                    products.remove(product);
                }
            } else {
                System.out.println("Not enough quantity available for product: " + product.getName());
            }
        } else {
            System.out.println("Product not found in inventory: " + product.getName());
        }
    }

    public void displayInventory() {
        System.out.println("Inventory:");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product.getName() + " - Quantity: " + quantity);
        }
    }
}
    
    