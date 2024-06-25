/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.modelo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dev Dynasty, DCCO-ESPE
 */
public class Inventario {
    
    private Map<Producto, Integer> stock;

    public Inventario() {
        stock = new HashMap<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        stock.put(producto, stock.getOrDefault(producto, 0) + cantidad);
    }

    public int obtenerStockProducto(Producto producto) {
        return stock.getOrDefault(producto, 0);
    }
}
    

