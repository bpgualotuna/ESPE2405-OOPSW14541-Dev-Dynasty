/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.controller;

/**
 *
 * @author DELL
 */

// Clase ProductoSeleccionado que hereda de Products
public class ProductoSeleccionado extends Products {
    
private int cantidadSeleccionada;

    // Constructor que incluye los atributos de la clase padre y los específicos de esta clase
    public ProductoSeleccionado(int id, String name, double price, int stock, int cantidadSeleccionada) {
        super(id, name, price, stock); // Llama al constructor de la clase Products
        this.cantidadSeleccionada = cantidadSeleccionada;
    }

    // Getter y setter para cantidadSeleccionada
    public int getCantidadSeleccionada() {
        return cantidadSeleccionada;
    }

    public void setCantidadSeleccionada(int cantidadSeleccionada) {
        this.cantidadSeleccionada = cantidadSeleccionada;
    }

    // Método adicional que podría calcular el precio total del producto seleccionado
    public double calcularPrecioTotal() {
        return getPrice() * cantidadSeleccionada;
    }
    
}
