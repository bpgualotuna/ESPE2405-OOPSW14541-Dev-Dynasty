/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.onlinestore.model;

import java.util.List;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class Sale {
    private String username;
    private List<Integer> productIds;

    public Sale(String username, List<Integer> productIds) {
        this.username = username;
        this.productIds = productIds;
    }

    public String getUsername() {
        return username;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }
    
}
