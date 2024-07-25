/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.controlador;

import ec.edu.espe.megasoft.modelo.Categoria;
import ec.edu.espe.megasoft.modelo.Oferta;
import ec.edu.espe.megasoft.modelo.Producto;
import ec.edu.espe.megasoft.modelo.Reseña;
import ec.edu.espe.megasoft.modelo.Tienda;
import ec.edu.espe.megasoft.modelo.Usuario;
import ec.edu.espe.megasoft.modelo.Venta;
import ec.edu.espe.megasoft.util.ArchivoJson;
import ec.edu.espe.megasoft.vista.Menu;
import ec.edu.espe.megasoft.vista.MenuAdmi;
import ec.edu.espe.megasoft.vista.MenuCliente;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author , Dev Dynasty, DCCO-ESPE
 */
    public class ControladorTienda {
        private Tienda tienda;
        private Menu menu;
        private Usuario usuarioLogueado;
        private Scanner scanner;
        private static final String ARCHIVO_DATOS = "datos_tienda.json";
        private MenuCliente menuCliente;
        private MenuAdmi menuAdmi;
        

        public ControladorTienda(Tienda tienda, Menu menu) {
            this.tienda = tienda;
            this.menu = menu;
            this.scanner = new Scanner(System.in);
            menuCliente = new MenuCliente();
            menuAdmi = new MenuAdmi();
            

        }

      public void iniciar() throws IOException {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    iniciarSesionCliente();
                    break;
                case 2:
                    iniciarSesionAdmin();
                    break;
                case 3:
                    registrarNuevoCliente();
                    break;
                case 4:
                    System.out.println("Gracias por usar MEGASOFT. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 4);
        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                          MEGASOFT                                 ");
        System.out.println("                      Menu Principal:                                ");
        System.out.println("         1. Iniciar sesión como Cliente                              ");
        System.out.println("         2. Iniciar sesión como Administrador                        ");
        System.out.println("         3. Registrarse como Nuevo Cliente                           ");
        System.out.println("         4. Salir                                                    ");
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Ingrese su elección: ");
    }



    private void iniciarSesionCliente() throws IOException {
    System.out.println("--------------------------------------------------------------------");
    System.out.print("\n\nIngrese el nombre de usuario: ");
    String nombreUsuario = scanner.nextLine();
    System.out.print("Ingrese la contraseña: ");
    String contrasena = leerContraseña();

    Usuario usuario = tienda.autenticarUsuario(nombreUsuario, contrasena);
    if (usuario != null) {
        if (tienda.esAdministrador(nombreUsuario)) {
            System.out.println("Acceso denegado. Este es el acceso de administrador.");
        } else {
            this.usuarioLogueado = usuario;
            System.out.println("Sesión iniciada correctamente: " + usuario.getNombreUsuario());
            mostrarMenuCliente();
        }
    } else {
        System.out.println("\nCuenta no encontrada. Por favor, regístrese.\n\n");
    }
}
      
    private void iniciarSesionAdmin() throws IOException {
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Ingrese el nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contraseña = leerContraseña();

        // Validar las credenciales de administrador
        if (nombreUsuario.equals("admin") && contraseña.equals("admin123")) {
            mostrarMenuAdmin();
        } else {
            System.out.println("Credenciales incorrectas para administrador, vuelva a intentarlo.");
        }
    }

        private String enmascararContraseña() {
        String contraseña = "";
        try {
            System.out.print("\033[8m"); // Oculta la entrada de texto
            contraseña = scanner.nextLine().trim();
            System.out.print("\033[0m"); // Restaura el formato de la consola
        } catch (Exception e) {
            System.out.println("Error al enmascarar la contraseña: " + e.getMessage());
        }
        return contraseña;
    }

        

        public void registrarNuevoUsuario(Scanner scanner1) {
            System.out.println("--------------------------------------------------------------------  ");
            System.out.print("Ingrese el nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();
            System.out.println("--------------------------------------------------------------------  ");
            System.out.print("Ingrese la contraseña: ");
            String contraseña = scanner.nextLine();
            System.out.println("--------------------------------------------------------------------  ");

            Usuario nuevoUsuario = new Usuario(nombreUsuario, contraseña, false);
            //tienda.registrarUsuario(nuevoUsuario);
        }

        private void registrarCliente() {
            String nombreUsuario = menu.obtenerNombreUsuario();
            String contraseña = menu.obtenerContraseña();
            String confirmarContraseña = menu.obtenerContraseña();
            if (contraseña.equals(confirmarContraseña)) {
                tienda.agregarUsuario(new Usuario(nombreUsuario, contraseña, false));
                menu.mostrarMensaje("Cliente registrado exitosamente.");
            } else {
                menu.mostrarMensaje("Las contraseñas no coinciden. Registro fallido.");
            }
        }

        public void registrarNuevoCliente(String nombreUsuario, String contraseña) throws IOException {
        Usuario nuevoUsuario = new Usuario(nombreUsuario, contraseña, false); 
        tienda.agregarUsuario(nuevoUsuario); 
        guardarDatosTienda(); 
    }

         private void registrarNuevoCliente() throws IOException {
    System.out.println("Registrarse como Nuevo Cliente");
    System.out.print("Ingrese el nombre de usuario: ");
    String nombreUsuario = scanner.nextLine();
    System.out.print("Ingrese la contraseña: ");
    String contraseña = leerContraseña();
    if (tienda.existeUsuario(nombreUsuario)) {
        System.out.println("El nombre de usuario ya existe. Intente con otro nombre de usuario.");
        return;
    }

    Usuario nuevoUsuario = new Usuario(nombreUsuario, contraseña, false);
    tienda.agregarUsuario(nuevoUsuario);

    tienda.guardarUsuariosEnJson("datos_tienda.json");
    System.out.println("¡Registro exitoso! Ahora puede iniciar sesión como cliente.\n\n");
}

        public void guardarDatosTienda() {
            List<?> usuarios = null;
            tienda.guardarEnJson((List<Usuario>) usuarios, ARCHIVO_DATOS);
        
    } 


        private int mostrarMenuCliente() throws IOException {
            boolean salir = false;
            while (!salir) {
                int opcion = menuCliente.mostrarMenuCliente();
                switch (opcion) {
                    case 1:
                        mostrarTodosLosProductos();
                        break;
                    case 2:
                        verOfertas();
                        break;
                    case 3:
                        buscarProducto();
                        break;
                    case 4:
                        System.out.print("Ingrese los IDs de los productos para la compra (ingrese -1 para finalizar): ");
                        List<Integer> idProductos = new ArrayList<>();
                        while (true) {
                            int idProducto = scanner.nextInt();
                            if (idProducto == -1) break;
                            idProductos.add(idProducto);
                        }

                        double montoTotal = tienda.calcularMontoTotal(idProductos);
                        System.out.println("Monto total: $" + montoTotal);

                        System.out.print("¿Desea proceder con la compra? (si/no): ");
                        String confirmar = scanner.next();
                        if (!confirmar.equalsIgnoreCase("si")) {
                            System.out.println("Compra cancelada.");
                            break;
                        }

                        boolean salirMenuCompra = false;
                        while (!salirMenuCompra) {
                            System.out.println("--------------------------------------------------------------------  "); 
                            System.out.println("             Menu de Compra:                     ");
                            System.out.println("1. Pagar la Compra");
                            System.out.println("2. Agregar Otro Producto");
                            System.out.println("3. Eliminar Producto");
                            System.out.println("4. Salir");
                            System.out.println("--------------------------------------------------------------------  ");
                            System.out.print("Ingrese su elección: ");
                            System.out.println("--------------------------------------------------------------------  ");
                            int opcionCompra = scanner.nextInt();
                            scanner.nextLine(); // Consumir nueva línea

                            switch (opcionCompra) {
                                case 1:
                                    tienda.comprarProductos(usuarioLogueado.getNombreUsuario(), idProductos); // Pasa el nombre de usuario aquí
                                   // System.out.println("Compra confirmada. Monto total: $" + montoTotal);
                                    salirMenuCompra = true;
                                    break;

                                case 2:
                                    System.out.print("Ingrese el ID del producto a agregar: ");
                                    int nuevoIdProducto = scanner.nextInt();
                                    idProductos.add(nuevoIdProducto);
                                    montoTotal = tienda.calcularMontoTotal(idProductos);
                                    System.out.println("Monto total: $" + montoTotal);
                                    break;

                                case 3:
                                    if (idProductos.isEmpty()) {
                                        System.out.println("No hay productos para eliminar.");
                                    } else {
                                        System.out.print("Ingrese el ID del producto a eliminar: ");
                                        int idProductoEliminar = scanner.nextInt();
                                        boolean eliminado = idProductos.removeIf(id -> id == idProductoEliminar);
                                        if (eliminado) {
                                            montoTotal = tienda.calcularMontoTotal(idProductos);
                                            System.out.println("Producto eliminado. Monto total: $" + montoTotal);
                                        } else {
                                            System.out.println("Producto no encontrado.");
                                        }
                                    }
                                    break;

                                case 4:
                                    System.out.println("Compra cancelada.");
                                    salirMenuCompra = true;
                                    break;

                                default:
                                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
                                    break;
                            }
                        }
                        break;
                    case 5:
                        agregarReseña();
                        break;
                    case 6:
                        verReseñas();
                        break;
                    case 7:
                        verHistorialCompras();
                        break;
                    case 8:
                        salir = true;
                        break;
                    default:
                        menu.mostrarMensaje("Opción inválida.");
                }
            }
            return 0;

        }

        private void mostrarMenuAdmin() throws IOException {
            boolean salir = false;
            while (!salir) {
                int opcion = menuAdmi.mostrarMenuAdmin();
                switch (opcion) {
                    case 1:
                        agregarProducto();
                        break;
                    case 2:
                        
                        List<Integer> idProductos = new ArrayList<>();
                        try{
                            System.out.println("¿Desea Eliminar Un producto? (si/no): ");
                            String confirmar = scanner.next();
                            if(!confirmar.equalsIgnoreCase("si")){
                                System.out.println("Eliminacion del producto cancelada");
                                break;
                            }
                            boolean menuEliminar= false;
                            while(!menuEliminar){
                                System.out.println("--------------------------------------------------------------------  "); 
                                System.out.println("             Menu de eliminacion:                     ");
                                System.out.println("1. Eliminar productos");
                                System.out.println("2. Eliminar productos por ID");
                                System.out.println("3. Cancelar");
                                System.out.println("--------------------------------------------------------------------  ");
                                System.out.print("  Ingrese su elección: ");
                                System.out.println("--------------------------------------------------------------------  ");
                                int opcionEliminar = scanner.nextInt();
                                scanner.nextLine(); // Consumir nueva línea
                                switch(opcionEliminar){
                                    case 1:
                                        eliminarProducto();
                                        break;
                                    case 2:
                                    if (idProductos.isEmpty()) {
                                        System.out.println("No hay productos a Eliminar.");
                                    } else {
                                        System.out.print("Ingrese el ID del producto a Eliminar: ");
                                        int idProductoEliminar = scanner.nextInt();
                                        boolean eliminado = idProductos.removeIf(id -> id == idProductoEliminar);
                                        if (eliminado) {
                                            System.out.println("Producto Eliminado " );
                                        } else {
                                            System.out.println("Producto no encontrado.");
                                        }
                                    }
                                    break;

                                    case 3:
                                        System.out.println("Eliminacion de productos Cancelada");
                                        menuEliminar = true;
                                        break;

                                    default:
                                        System.out.println("Opción inválida. Por favor, intente de nuevo.");
                                        break;
                                    }
                                }
                        }catch(Exception e){
                            System.out.println("Error al eliminar productos" + e.getMessage());
                        }
                        break;
                        
                    case 3:
                        editarProducto();
                        
                        break;
                    case 4:
                        buscarProducto();
                        break;
                    case 5:
                        mostrarTodosLosProductos();
                        break;
                    case 6:
                        verOfertas();
                        break;
                    case 7:
                        verReseñas();
                        break;
                    case 8:
                        gestionarStock();
                        break;
                    case 9:
                        verVentas();
                        break;
                    case 10:
                        agregarOferta();
                        break;
                    case 11:
                        salir = true;
                        break;
                    default:
                        menu.mostrarMensaje("Opción inválida.");
                }
            }
        }

        private void realizarCompra() {
            List<Integer> idProductos = menu.obtenerIdsProductosParaCompra();
            double total = 0.0;
            for (int id : idProductos) {
                Producto producto = tienda.buscarProducto(id);
                if (producto != null) {
                    total += producto.getPrecio();
                }
            }
            if (idProductos.isEmpty()) {
                menu.mostrarMensaje("No se seleccionaron productos para la compra.");
                return;
            }
            menu.mostrarProductos(tienda.getProductos());
            menu.mostrarMensaje("Monto total: $" + total);

            boolean confirmar = menu.confirmarCompra(total);
            if (confirmar) {
                Venta venta = new Venta(usuarioLogueado.getNombreUsuario(), idProductos);
                tienda.agregarVenta(venta);
                menu.mostrarMensaje("Compra confirmada. Monto total: $" + total);
            } else {
                menu.mostrarMensaje("Compra cancelada.");
            }
        }

        private void agregarProducto() throws IOException {
    Producto producto = menu.obtenerDatosProducto(); 
    tienda.agregarProducto(producto);
    menu.mostrarMensaje("Producto agregado exitosamente.");
    guardarDatosTienda(); 
}

private void agregarOferta() throws IOException {
    Oferta oferta = obtenerDatosOferta(); 
    if (oferta != null) { // Verifica si la oferta no es null
        tienda.agregarOferta(oferta); 
        menu.mostrarMensaje("Oferta agregada exitosamente.");
        guardarDatosTienda();
    } else {
        menu.mostrarMensaje("No se pudo agregar la oferta. Por favor, intente de nuevo.");
    }
}



        private void eliminarProducto() {
            int id = menu.obtenerIdProducto();
            boolean resultado = tienda.eliminarProducto(id);
            if (resultado) {
                menu.mostrarMensaje("Producto eliminado exitosamente.");
            } else {
                menu.mostrarMensaje("Error al eliminar el producto.");
            }
        }

private Oferta obtenerDatosOferta() {
    try {
        menu.mostrarMensaje("Ingrese el nombre de la oferta:");
        String nombre = scanner.nextLine();
        
        menu.mostrarMensaje("Ingrese el porcentaje de descuento:");
        double descuento = scanner.nextDouble();
        
        scanner.nextLine();

        menu.mostrarMensaje("Ingrese la categoría a la que pertenece el producto:");
        String categoria = scanner.nextLine();

        menu.mostrarMensaje("Ingrese el stock de la oferta:");
        int stock = scanner.nextInt();
 
//        menu.mostrarMensaje("Ingrese la categoria:");
//        String nombreCategoria = scanner.nextLine();
        scanner.nextLine();
        
        menu.mostrarMensaje("Ingrese la descripcion:");
        String descripcion = scanner.nextLine();
        //String descripcion = scanner.nextLine();
        
        //scanner.nextLine(); // Consumir la nueva línea pendiente

        //Categoria categorias = new Categoria(nombreCategoria,descripcionCategoria);
        
        // Suponiendo que el constructor de Oferta acepta nombre, categoría, stock y descuento
        return new Oferta(nombre,descuento,categoria,stock,descripcion); 
    } catch (InputMismatchException e) {
        scanner.nextLine(); // Consumir la entrada incorrecta
        menu.mostrarMensaje("Error: Entrada no válida. Inténtelo de nuevo.");
        return null;
    }
}


        private void editarProducto() {
            int id = menu.obtenerIdProducto();
            Producto producto = menu.obtenerDatosProductoEditado();
            boolean resultado = tienda.editarProducto(id, producto.getNombre(), producto.getPrecio());
            if (resultado) {
                menu.mostrarMensaje("Producto editado exitosamente.");
            } else {
                menu.mostrarMensaje("Error al editar el producto.");
            }
        }

        private void buscarProducto() {
            int id = menu.obtenerIdProducto();
            Producto producto = tienda.buscarProducto(id);
            menu.mostrarProducto(producto);
        }

        private void mostrarTodosLosProductos() {
            List<Producto> productos = tienda.getProductos();
            menu.mostrarProductos(productos);
        }

        private void verOfertas() {
            List<Oferta> ofertas = tienda.getOfertas();
            menu.mostrarOfertas(ofertas);
        }

        private void agregarReseña() throws IOException {
            Reseña reseña = (Reseña) menu.obtenerDatosReseña();
            tienda.agregarReseña(reseña);
            guardarDatosTienda();
            menu.mostrarMensaje("Reseña agregada exitosamente.");
        }
        
      

        private void verReseñas() {
            List<Reseña> reseñas = tienda.getReseñas();
            menu.mostrarReseñas(reseñas);
        }

        private void gestionarStock() throws IOException {
            int id = menu.obtenerIdProducto();
            int cantidad = menu.obtenerCantidadStock();
            boolean resultado = tienda.agregarStock(id, cantidad);
            if (resultado) {
                menu.mostrarMensaje("Stock actualizado exitosamente.");
            } else {
                menu.mostrarMensaje("Error al actualizar el stock.");
            }
        }

        private void verVentas() {
            List<Venta> ventas = tienda.getVentas();
            menu.mostrarVentas(ventas, tienda.getProductos()); 
        }

        private void verHistorialCompras() {
            List<Venta> ventas = tienda.getVentas();
            menu.mostrarVentas(ventas, tienda.getProductos());
        }

        
        private void salir() {

        System.exit(0); 
    }
        
        private String leerContraseña() {
    StringBuilder contraseña = new StringBuilder();
    try {
        Console console = System.console();
        if (console == null) {
            // Para entornos que no soportan System.console()
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } else {
            char[] contraseñaArray = console.readPassword();
            return new String(contraseñaArray);
        }
    } catch (Exception e) {
        System.out.println("Error al leer la contraseña: " + e.getMessage());
    }
    return contraseña.toString();
}
      
}


    

    
    
    
    

