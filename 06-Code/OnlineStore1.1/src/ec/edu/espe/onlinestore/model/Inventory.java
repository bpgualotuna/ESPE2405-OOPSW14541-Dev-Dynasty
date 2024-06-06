/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.onlinestore.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author  Dev Dynasty, DCCO-ESPE
 */
public class Inventory {
    private Map<Product, Integer> stock;

    public Inventory() {
        stock = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        stock.put(product, stock.getOrDefault(product, 0) + quantity);
    }

    public int getProductStock(Product product) {
        return stock.getOrDefault(product, 0);
    }
}