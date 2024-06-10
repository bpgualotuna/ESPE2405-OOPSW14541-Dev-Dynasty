/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.onlinestore.model;

import com.google.gson.Gson;
import ec.edu.espe.onlinestore.util.FileJson;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class StoreData {
    private List<Product> products;
    private List<Sale> sales;
    private List<Review> reviews;
    private List<User> users;

    public StoreData(List<Product> products, List<Sale> sales, List<Review> reviews, List<User> users) {
        this.products = products;
        this.sales = sales;
        this.reviews = reviews;
        this.users = users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<User> getUsers() {
        return users;
    }
}


    
