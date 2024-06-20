/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.vista;

import ec.edu.espe.megasoft.modelo.Usuario;
import java.util.Scanner;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class MenuCliente  {
    
    private Scanner scanner;

    public MenuCliente() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuCliente() {
        System.out.println("--------------------------------------------------------------------  ");
        System.out.println("                         MENU DE CLIENTE"                          );
        System.out.println("--------------------------------------------------------------------  ");
        System.out.println("Menu de Cliente:");
        System.out.println("1. Ver Todos los Productos");
        System.out.println("2. Ver Ofertas");
        System.out.println("3. Buscar Producto");
        System.out.println("4. Comprar Productos");
        System.out.println("5. Agregar Reseña");
        System.out.println("6. Ver Reseñas");
        System.out.println("7. Ver Historial de Compras");
        System.out.println("8. Salir");
        System.out.println("--------------------------------------------------------------------  ");
        System.out.println("Ingrese su elección:");
        System.out.println("--------------------------------------------------------------------  ");

        return scanner.nextInt();
    }
}

