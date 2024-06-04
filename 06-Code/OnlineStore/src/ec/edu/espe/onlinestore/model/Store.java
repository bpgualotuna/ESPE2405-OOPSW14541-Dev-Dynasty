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
    private int currentId=1;
    
    public Store(){
        uploadProducts();
    }
    
    public void uploadProducts(){
        addProduct("Product A", 10.0);
        addProduct("Product B", 10.0);
        addProduct("Product C", 10.0);
        addProduct("Product D", 10.0);
        addProduct("Product E", 10.0);
        addProduct("Product F", 10.0);
        addProduct("Product G", 10.0);
        addProduct("Product I", 10.0);
        addProduct("Product J", 10.0);
        addProduct("Product K", 10.0);
        addProduct("Product L", 10.0);
        addProduct("Product M", 10.0);
        addProduct("Product N", 10.0);
        addProduct("Product O", 10.0);
        
        
    }
    
    public void addProduct (String name, double price){
        Product product = new Product (currentId++, name, price);
        products.add(product);
    }
    
    public boolean deleteProduct (int id){
        return products.removeIf(p -> p.getId() == id);
    }
    
    public boolean editProduct(int id,  String newName, double newPrice){
          Optional<Product> productoOpt = products.stream().filter(p -> p.getId() == id).findFirst();
        if (productoOpt.isPresent()) {
            Product producto = productoOpt.get();
            producto.setName(newName);
            producto.setPrice(newPrice);
            return true;
        }
        return false;
    }
    
    public Product searchProduct(int id){
        return products.stream().filter(p  -> p.getId() == id).findFirst().orElse(null);
    }
    
    public List<Product> getProducts(){
        return products;
    }
}
