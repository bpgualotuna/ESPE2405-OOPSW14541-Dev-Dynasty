/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ec.edu.espe.megasoft.util.ArchivoJson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Dev Dynasty, DCCO-ESPE
 */
    public class Tienda {
        private List<Producto> productos;
    private List<Oferta> ofertas;
    private List<Reseña> reseñas;
    private Inventario inventario;
    private List<Usuario> usuarios;
    private List<Venta> ventas;
    private UsuarioLista usuariolista;
    private DatosTienda datosTienda;
    private static final String ARCHIVO_DATOS = "datos_tienda.json";
    private static final String ARCHIVO_USUARIOS = "usuarios.json";
    private final ArchivoJson jsonFileHandler;

    // Resto de variables y constantes

    public Tienda() throws IOException {
        this.jsonFileHandler = new ArchivoJson();
        this.productos = new ArrayList<>();
        this.ofertas = new ArrayList<>();
        this.reseñas = new ArrayList<>();
        this.inventario = new Inventario();
        this.usuarios = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.usuariolista = new UsuarioLista(new ArrayList<>());
        this.datosTienda = new DatosTienda(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        if (!Files.exists(Paths.get(ARCHIVO_DATOS))) {
            guardarDatosEnJson(); // Guarda datos iniciales vacíos
        }
            if (!Files.exists(Paths.get(ARCHIVO_USUARIOS))) {
                      guardarUsuariosEnJson(ARCHIVO_USUARIOS); // Guarda datos iniciales vacíos de usuarios
}

        //usuariolista.cargarUsuariosDesdeJson(ARCHIVO_USUARIOS);
        try {
            cargarDatosIniciales();
        } catch (IOException e) {
            System.out.println("Error al inicializar la tienda: " + e.getMessage());
            // Aquí puedes manejar el error según tu lógica
        }
    }

private void cargarDatosIniciales() throws IOException {
    cargarDesdeJson(ARCHIVO_DATOS);
    cargarUsuariosDesdeJson(ARCHIVO_USUARIOS);
}
    public void agregarProducto(Producto producto) {
        if (this.productos == null) {
            this.productos = new ArrayList<>();
        }
        productos.add(producto);
        guardarDatosEnJson();
    }

        public boolean eliminarProducto(int id) {
        boolean eliminado = productos.removeIf(producto -> producto.getId() == id);
        if (eliminado) {
            guardarDatosEnJson(); // Guardar los datos actualizados en JSON si se eliminó un producto
        }
        return eliminado;
    }
        public boolean editarProducto(int id, String nombre, double precio) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                producto.setNombre(nombre);
                producto.setPrecio(precio);
                guardarDatosEnJson(); // Guardar los datos actualizados en JSON si se editó un producto
                return true;
            }
        }
        return false;
    }
        
         public void agregarVenta(Venta venta) {
        ventas.add(venta);
        guardarDatosEnJson();
    }
         
         
        public Producto buscarProducto(int id) {
            for (Producto producto : productos) {
                if (producto.getId() == id) {
                    return producto;
                }
            }
            return null;
        }

        public List<Producto> getProductos() {
            return productos;
        }

        public void agregarOferta(Oferta oferta) {
            ofertas.add(oferta);
        }

        public List<Oferta> getOfertas() {
            return ofertas;
        }
        
       

        public void agregarReseña(Reseña reseña) {
            reseñas.add(reseña);
        }

        public List<Reseña> getReseñas() {
            return reseñas;
        }

        public Inventario getInventario() {
            return inventario;
        }

        public void agregarUsuario(Usuario usuario) {
            usuarios.add(usuario);
        }
        
        

        
public Usuario autenticarUsuario(String nombreUsuario, String contrasena) {
    if (usuarios == null || usuarios.isEmpty()) {
        return null; // Devuelve null si no hay usuarios registrados
    }
    for (Usuario usuario : usuarios) {
        if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContraseña().equals(contrasena)) {
            return usuario;
        }
    }
    return null; // Devuelve null si no se encuentra el usuario
}

        
        
        
       
   public synchronized void guardarUsuariosEnJson(String filePath) {
    try {
        jsonFileHandler.guardarEnJson(new UsuarioLista(this.usuarios), filePath);
        System.out.println("Usuarios guardados correctamente en " + filePath);
    } catch (IOException e) {
        System.out.println("Error al guardar usuarios en " + filePath + ": " + e.getMessage());
    }
}
       
  private void cargarUsuariosDesdeJson(String rutaArchivo) {
    try {
        ArchivoJson jsonFileHandler = new ArchivoJson();
        UsuarioLista usuarioLista = jsonFileHandler.cargarDesdeJson(rutaArchivo, UsuarioLista.class);
        if (usuarioLista != null) {
            this.usuarios = usuarioLista.getUsuarios();
            this.usuariolista = usuarioLista;
        } else {
            this.usuarios = new ArrayList<>();
        }
    } catch (IOException e) {
        System.out.println("Error al cargar usuarios desde JSON: " + e.getMessage());
        this.usuarios = new ArrayList<>();
    }
}
   
 public synchronized void guardarDatosEnJson() {
    try {
        datosTienda.setProductos(this.productos);
        datosTienda.setOfertas(this.ofertas);
        datosTienda.setReseñas(this.reseñas);
        datosTienda.setUsuarios(this.usuarios);

        jsonFileHandler.guardarEnJson(datosTienda, ARCHIVO_DATOS);
        jsonFileHandler.guardarEnJson(new UsuarioLista(datosTienda.getUsuarios()), ARCHIVO_USUARIOS);

        System.out.println("Datos guardados correctamente en JSON.");
    } catch (IOException e) {
        System.out.println("Error al guardar los datos en JSON: " + e.getMessage());
    }
}

   
 
      private void cargarDesdeJson(String filePath) {
    try {
        ArchivoJson jsonFileHandler = new ArchivoJson();
        datosTienda = jsonFileHandler.cargarDesdeJson(filePath, DatosTienda.class);
        if (datosTienda == null) {
            datosTienda = new DatosTienda(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }

        this.productos = datosTienda.getProductos() != null ? datosTienda.getProductos() : new ArrayList<>();
        this.ofertas = datosTienda.getOfertas() != null ? datosTienda.getOfertas() : new ArrayList<>();
        this.reseñas = datosTienda.getReseñas() != null ? datosTienda.getReseñas() : new ArrayList<>();
        this.usuarios = datosTienda.getUsuarios() != null ? datosTienda.getUsuarios() : new ArrayList<>();
    } catch (IOException e) {
        System.out.println("Error al cargar datos desde JSON: " + e.getMessage());
        this.productos = new ArrayList<>();
        this.ofertas = new ArrayList<>();
        this.reseñas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }
}



     
    public void guardarEnJson(List<Usuario> usuarios1, String datos_tiendajson) {
        try (Writer writer = new FileWriter(ARCHIVO_DATOS)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(datosTienda, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos en JSON: " + e.getMessage());
            // Manejar la excepción según tu lógica de la aplicación
        }
    }
        
      public void registrarUsuario(Usuario nuevoUsuario) {
    usuarios.add(nuevoUsuario);
    guardarUsuariosEnJson(ARCHIVO_USUARIOS); // Guardar en el archivo de usuarios
    System.out.println("Usuario registrado exitosamente.");
}


        public boolean gestionarStock(int idProducto, int cantidad) {
            Producto producto = buscarProducto(idProducto);
            if (producto != null) {
                producto.setStock(cantidad);
                return true;
            }
            return false;
        }

        public List<Venta> getVentas() {
            return ventas;
        }

    

        public boolean agregarStock(int idProducto, int cantidad) throws IOException {
        Producto producto = obtenerProducto(idProducto);
        if (producto != null) {
            producto.setStock(producto.getStock() + cantidad);
            guardarDatosEnJson();
            return true;
        }
        return false;
    }

    public boolean eliminarStock(int idProducto, int cantidad) {
        Producto producto = obtenerProducto(idProducto);
        if (producto != null && producto.getStock() >= cantidad) {
            producto.setStock(producto.getStock() - cantidad);
            guardarDatosEnJson();
            return true;
        }
        return false;
    }

    public boolean editarStock(int idProducto, int nuevaCantidad) {
        Producto producto = obtenerProducto(idProducto);
        if (producto != null) {
            producto.setStock(nuevaCantidad);
            guardarDatosEnJson();
            return true;
        }
        return false;
    }

        public Producto obtenerProducto(int idProducto) {
            for (Producto producto : productos) {
                if (producto.getId() == idProducto) {
                    return producto;
                }
            }
            return null; // Si no se encuentra el producto con el ID dado
        }

        public int obtenerStock(int idProducto) {
            Producto producto = obtenerProducto(idProducto);
            if (producto != null) {
                return producto.getStock();
            }
            return -1; // Valor de retorno predeterminado si el producto no se encuentra
        }

//    public void comprarProductos(String nombreUsuario, List<Integer> idsProductos) throws IOException {
//        double montoTotal = 0.0;
//        List<Producto> productosComprados = new ArrayList<>();
//
//        for (int idProducto : idsProductos) {
//            Producto producto = obtenerProducto(idProducto);
//            if (producto != null && producto.getStock() > 0) {
//                montoTotal += producto.getPrecio();
//                producto.setStock(producto.getStock() - 1); // Reduce el stock en 1 por cada compra
//                productosComprados.add(producto);
//            }
//        }
//
//        if (!productosComprados.isEmpty()) {
//            Venta venta = new Venta(nombreUsuario, idsProductos);
//            ventas.add(venta);
//            guardarEnJson(usuarios, "datos_tienda.json"); // Guardar datos después de la compra
//            System.out.println("Compra confirmada. Monto total: $" + montoTotal);
//            System.out.println("Productos comprados:");
//            for (Producto producto : productosComprados) {
//                System.out.println(producto);
//            }
//        } else {
//            System.out.println("No se compraron productos. Por favor, verifique el stock e intente nuevamente.");
//        }
//    }
 public void comprarProductos(String nombreUsuario, List<Integer> idsProductos) throws IOException {
        double montoTotal = 0.0;
        List<Producto> productosComprados = new ArrayList<>();
        Map<Producto, Integer> cantidadesCompradas = new HashMap<>();

        for (int idProducto : idsProductos) {
            Producto producto = obtenerProducto(idProducto);
            if (producto != null && producto.getStock() > 0) {
                montoTotal += producto.getPrecio();
                producto.setStock(producto.getStock() - 1);

                cantidadesCompradas.put(producto, cantidadesCompradas.getOrDefault(producto, 0) + 1);

                if (!productosComprados.contains(producto)) {
                    productosComprados.add(producto);
                }
            }
        }

        if (!productosComprados.isEmpty()) {
            Venta venta = new Venta(nombreUsuario, idsProductos);
            ventas.add(venta);
            guardarDatosEnJson();

            double iva = montoTotal * 0.15;
            double montoTotalConIva = montoTotal + iva;

            System.out.printf("Compra confirmada%n%-10s %-20s %-10s %-10s%n", "ID", "Nombre", "Precio", "Cantidad");
            System.out.println("---------------------------------------------------------------");
            for (Producto producto : productosComprados) {
                int cantidadComprada = cantidadesCompradas.get(producto);
                System.out.printf("%-10d %-20s %-10.2f %-10d%n", producto.getId(), producto.getNombre(), producto.getPrecio(), cantidadComprada);
            }
            System.out.println("---------------------------------------------------------------");
            System.out.printf("%-30s %10.2f%n", "Monto total sin IVA:", montoTotal);
            System.out.printf("%-30s %10.2f%n", "IVA (15%):", iva);
            System.out.printf("%-30s %10.2f%n", "Monto total con IVA:", montoTotalConIva);
        } else {
            System.out.println("No se compraron productos. Por favor, verifique el stock e intente nuevamente.");
        }
    }



        
        

    public double calcularMontoTotal(List<Integer> idsProductos) {
        double montoTotal = 0.0;
        for (int idProducto : idsProductos) {
            Producto producto = buscarProducto(idProducto);
            if (producto != null) {
                montoTotal += producto.getPrecio();
            }
        }
        return montoTotal;
        
    }
    
    public boolean esAdministrador(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.esAdmin()) {
                return true;
            }
        }
        return false;
    }

    // Método para verificar si un usuario existe
//    public boolean existeUsuario(String nombreUsuario) {
//        for (Usuario usuario : usuarios) {
//            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
//                return true;
//            }
//        }
//        return false;
//    }   
    public boolean existeUsuario(String nombreUsuario) {
    if (this.usuarios == null) {
        this.usuarios = new ArrayList<>(); // Inicializa si es null
    }
    for (Usuario usuario : usuarios) {
        if (usuario.getNombreUsuario().equals(nombreUsuario)) {
            return true;
        }
    }
    return false;
}

    
  
    }
