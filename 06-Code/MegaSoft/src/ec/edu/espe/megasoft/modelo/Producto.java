/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.modelo;

/**
 *
 * @author , Dev Dynasty, DCCO-ESPE
 * 
 */
public class Producto {
    private static int idCounter = 1; // Declarar idCounter como variable estática
    
    private int id;
    private String nombre;
    private double precio;
    private Categoria categoria;
    private int stock;

    public Producto(int id, String nombre, double precio, Categoria categoria) {
        this.id = generarId();;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = 0;
     

    }
        public int getId() {
        return id;
    }
    

    private static int generarId() {
        return idCounter++; // Retorna el valor actual de idCounter y luego lo incrementa
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", categoria=" + categoria + ", stock=" + stock + '}';
    }

    
}
