/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.modelo;

/**
 *
 * @, Dev Dynasty, DCCO-ESPE
 */
public class Oferta {
    
    private String nombre;
    private String descripcion;
    private String categoria;
    private double descuento;
    private int stock;
    private int id;
    

    public Oferta(String nombre,double descuento,String categoria,int stock,String descripcion) {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.descuento = descuento;
        this.stock = stock;
        this.id = generarId();
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   // @Override
//    public String toString() {
//        return "Oferta{" + "descripcion=" + descripcion + ", categoria=" + categoria + ", descuento=" + descuento + ", stock=" + stock + ", id=" + id + ", nombre=" + nombre + '}';
//    }
//    public String toString() {
//        String header = String.format(
//            "%-15s %-15s %-10s %-10s %-5s %-20s",
//            "Descripcion", "Categoria", "Descuento", "Stock", "ID", "Nombre"
//        );
//
//        String separator = new String(new char[header.length()]).replace("\0", "-");
//
//        String values = String.format(
//            "%-15s %-15s %-10.2f %-10d %-5d %-20s",
//            descripcion, categoria, descuento, stock, id, nombre
//        );
//
//        return header + "\n" + separator + "\n" + values;
//    }


    public String getPrecio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

        private static int generarId() {
              // Implementa la lógica para generar un ID único para cada producto
        return (int) (Math.random() * 10000);
    }
}