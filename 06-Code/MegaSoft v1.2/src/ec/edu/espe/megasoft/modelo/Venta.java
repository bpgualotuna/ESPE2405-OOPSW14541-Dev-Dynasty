/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.modelo;

import java.util.List;

/**
 *
 * @author  Dev Dynasty, DCCO-ESPE
 */
public class Venta {
    
    private String nombreUsuario;
    private List<Integer> idsProductos;

    public Venta(String nombreUsuario, List<Integer> idsProductos) {
        this.nombreUsuario = nombreUsuario;
        this.idsProductos = idsProductos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public List<Integer> getIdsProductos() {
        return idsProductos;
    }
}
