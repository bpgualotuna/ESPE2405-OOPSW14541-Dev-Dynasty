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
public class ClientMenu extends Menu {
    
    @Override
    public int showClientMenu() {
        System.out.println("Client Menu:");
        System.out.println("1. View Products");
        System.out.println("2. View Offers");
        System.out.println("3. Search Product by ID");
        System.out.println("4. Buy Products");
        System.out.println("5. Add Review");
        System.out.println("6. View Reviews");
        System.out.println("7. View Purchase History");
        System.out.println("8. Exit");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        return choice;
    }
    
}
