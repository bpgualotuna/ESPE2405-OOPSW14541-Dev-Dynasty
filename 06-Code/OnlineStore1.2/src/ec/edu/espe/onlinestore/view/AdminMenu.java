/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.onlinestore.view;

import java.util.Scanner;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class AdminMenu extends Menu {
    
     @Override
    public int showAdminMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. Add Product");
        System.out.println("2. Delete Product");
        System.out.println("3. Edit Product");
        System.out.println("4. Search Product by ID");
        System.out.println("5. View Products");
        System.out.println("6. View Offers");
        System.out.println("7. View Reviews");
        System.out.println("8. Add Stock");
         System.out.println("9. View Purchase History");
        System.out.println("10. Exit");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        return choice;
    }
    
}
