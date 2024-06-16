/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.vista;

import ec.edu.espe.megasoft.modelo.Categoria;
import ec.edu.espe.megasoft.modelo.Oferta;
import ec.edu.espe.megasoft.modelo.Producto;
import ec.edu.espe.megasoft.modelo.Reseña;
import ec.edu.espe.megasoft.modelo.Usuario;
import ec.edu.espe.megasoft.modelo.Venta;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu(Usuario usuario) {
        if (usuario.esAdmin()) {
            mostrarMenuAdmi();
        } else {
            mostrarMenuCliente();
        }
    }
    public void mostrarMenuAdmi() {
        MenuAdmi menuAdmi = new MenuAdmi();
        int eleccion = menuAdmi.mostrarMenuAdmin();
        procesarOpcion(eleccion); // Procesar la opción seleccionada
    }

    public void mostrarMenuCliente() {
        MenuCliente menuCliente = new MenuCliente();
        int eleccion = menuCliente.mostrarMenuCliente(); // Llamar al método en MenuCliente
        procesarOpcion(eleccion); // Procesar la opción seleccionada
    }
    
    private void procesarOpcion(int eleccion) {
        
        System.out.println("Seleccionaste la opción: " + eleccion);
    }

    public String obtenerNombreUsuario() {
        System.out.print("Ingrese el nombre de usuario: ");
        return scanner.nextLine();
    }

    public String obtenerContraseña() {
        System.out.print("Ingrese la contraseña: ");
        return scanner.nextLine();
    }
    
     private String leerContraseña() {
        // Implementación para leer la contraseña de manera segura
        String contraseña = "";
        Console console = System.console();
        if (console != null) {
            char[] contraseñaChars = console.readPassword();
            contraseña = new String(contraseñaChars);
        } else {
            // Fallback si no se puede usar la consola segura (por ejemplo, en IDEs)
            contraseña = scanner.nextLine();
        }
        return contraseña;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public Producto obtenerDatosProducto() {
        System.out.println("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();  // Consumir el carácter de nueva línea residual
        System.out.println("Ingrese la categoría del producto: ");
        String nombreCategoria = scanner.nextLine();
        Categoria categoria = new Categoria(nombreCategoria, "Descripción de " + nombreCategoria);
        return new Producto(0, nombre, precio, categoria);
    }
    
    public Oferta obtenerDatosOferta() {
        System.out.println("Ingrese el nombre de la oferta:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el porcentaje de descuento:");
        double descuento = scanner.nextDouble();
        scanner.nextLine(); // Consumir el carácter de nueva línea residual
        return new Oferta(nombre, descuento);
    }

    public Producto obtenerDatosProductoEditado() {
        System.out.println("Ingrese el nuevo nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el nuevo precio del producto: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();  // Consumir el carácter de nueva línea residual
        return new Producto(0, nombre, precio, null);
    }

    public int obtenerIdProducto() {
        System.out.println("Ingrese el ID del producto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el carácter de nueva línea residual
        return id;
    }

    public List<Integer> obtenerIdsProductosParaCompra() {
        List<Integer> ids = new ArrayList<>();
        System.out.println("Ingrese los IDs de los productos para la compra (ingrese -1 para finalizar): ");
        int id;
        while ((id = scanner.nextInt()) != -1) {
            ids.add(id);
        }
        return ids;
    }

    public boolean confirmarCompra(double total) {
        System.out.println("Monto total: $" + total + ". ¿Confirma la compra? (sí/no)");
        String respuesta = scanner.next();
        return respuesta.equalsIgnoreCase("sí");
    }

    public void mostrarProductos(List<Producto> productos) {
        System.out.println("Lista de productos:");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public void mostrarOfertas(List<Oferta> ofertas) {
        System.out.println("Ofertas:");
        for (Oferta oferta : ofertas) {
            System.out.println(oferta);
        }
    }

    public Reseña obtenerDatosReseña() {
        System.out.println("Ingrese el ID del producto para la reseña: ");
        int idProducto = scanner.nextInt();
        scanner.nextLine();  // Consumir el carácter de nueva línea residual
        System.out.println("Ingrese su reseña: ");
        String contenido = scanner.nextLine();
        return new Reseña(idProducto, contenido);
    }

    public void mostrarReseñas(List<Reseña> reseñas) {
        System.out.println("Reseñas:");
        for (Reseña reseña : reseñas) {
            System.out.println(reseña);
        }
    }

    public void mostrarProducto(Producto producto) {
        if (producto != null) {
            System.out.println(producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void mostrarVentas(List<Venta> ventas, List<Producto> productos) {
        System.out.println("Historial de ventas:");
        for (Venta venta : ventas) {
            System.out.println("Usuario: " + venta.getNombreUsuario());
            System.out.println("Productos:");
            for (int idProducto : venta.getIdsProductos()) {
                Producto producto = encontrarProductoPorId(productos, idProducto);
                if (producto != null) {
                    System.out.println(producto);
                }
            }
            System.out.println();
        }
    }

    private Producto encontrarProductoPorId(List<Producto> productos, int idProducto) {
        for (Producto producto : productos) {
            if (producto.getId() == idProducto) {
                return producto;
            }
        }
        return null;
    }
     
    public int obtenerCantidadStock() {
        System.out.println("Ingrese la cantidad de stock: ");
        return scanner.nextInt();
    }
    
    public String getPassword() {
        Console console = System.console();
        if (console == null) {
            System.out.println("No se puede ocultar la contraseña en esta consola.");
            System.out.println("Ingrese la contraseña:");
            return scanner.nextLine();
        } else {
            char[] passwordArray = console.readPassword("Ingrese la contraseña:%n");
            return new String(passwordArray);
        }
    }
    
    
    
}
