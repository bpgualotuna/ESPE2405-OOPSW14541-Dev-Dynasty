/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.vista;

import ec.edu.espe.megasoft.modelo.Usuario;
import java.util.Scanner;

/**
 *
 * @author Dev Dynasty, DCCO-ESPE
 */
    public class MenuAdmi  {
        private Scanner scanner;

    public MenuAdmi() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuAdmin() {
        System.out.println("\n\n--------------------------------------------------------------------  ");
        System.out.println("                    MENU DE ADMINISTRADOR");
        System.out.println("--------------------------------------------------------------------  ");
        System.out.println("Menu de Administrador:");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Eliminar Producto");
        System.out.println("3. Editar Producto");
        System.out.println("4. Buscar Producto por ID");
        System.out.println("5. Ver Productos");
        System.out.println("6. Ver Ofertas");
        System.out.println("7. Ver Reseñas");
        System.out.println("8. Agregar Stock");
        System.out.println("9. Ver Historial de Compras");
        System.out.println("10. Agregar Oferta");
        System.out.println("11. Salir");
        System.out.println("--------------------------------------------------------------------  ");
        System.out.println("Ingrese su elección:");
        System.out.println("--------------------------------------------------------------------  ");
        int opcion= scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente
         return opcion;
    }
    }
