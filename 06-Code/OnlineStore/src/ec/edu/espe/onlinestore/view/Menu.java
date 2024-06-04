/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.onlinestore.view;

import ec.edu.espe.onlinestore.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class Menu {
    
    private Scanner scanner = new Scanner (System.in);
    public int showMenu () {
        System.out.println("******Choose an option****");
        System.out.println("1. Make a purchase ");
        System.out.println("2. Delete product");
        System.out.println("3. Edit product");
        System.out.println("4. Search product");
        System.out.println("5. Exit");
        return scanner.nextInt(); 
    }
    
    public Product obtainProductData(){
        System.out.println("Enter the product name: ");
        String name = scanner.next();
        System.out.println("Enter the product price");
        double price = scanner.nextDouble();
        return new Product(0, name, price);
    }
    
    public int getProductId(){
        System.out.println("Enter the product ID: ");
        return scanner.nextInt();
    }
    
    public Product getEditedProductData(){
        System.out.println("Enter new product name: ");
        String name = scanner.next();
        System.out.println("Enter new product price: ");
        double price = scanner.nextDouble();
        return new Product(0, name, price);
    }
    
    public void showProduct(Product product){
     if (product != null) {
            System.out.println("Product found: " + product);
        } else {
            System.out.println("Product not found.");
        } 
}
    public void showMensaje(String mensaje){
        System.out.println(mensaje);
    }
    
    public List<Integer> getProductIDsForPurchase(){
        List<Integer> ids = new ArrayList<>();
        System.out.println("Enter the IDs of the products to purchase (0 to finish): ");
        while(true){
            int id = scanner.nextInt();
            if (id == 0){
                break;
            }
            ids.add(id);
        }
        return ids;
    }
}
