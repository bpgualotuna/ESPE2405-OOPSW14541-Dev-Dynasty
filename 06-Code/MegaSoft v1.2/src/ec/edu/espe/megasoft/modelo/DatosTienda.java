/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dev Dynasty, DCCO-ESPE
 */
public class DatosTienda {
    private List<Producto> productos;
    private List<Venta> ventas;
    private List<Reseña> reseñas;
    private List<Usuario> usuarios;
    private List<Oferta> ofertas;

    public DatosTienda(ArrayList<Object> arrayList, ArrayList<Object> arrayList1, ArrayList<Object> arrayList2, List<Usuario> usuarios1) {
    }
    
    

    public DatosTienda(List<Producto> productos, List<Venta> ventas, List<Reseña> reseñas, List<Usuario> usuarios, List<Oferta> ofertas) {
        this.productos = productos;
        this.ventas = ventas;
        this.reseñas = reseñas;
        this.usuarios = usuarios;
        this.ofertas = ofertas;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public List<Reseña> getReseñas() {
        return reseñas;
    }

    public void setReseñas(List<Reseña> reseñas) {
        this.reseñas = reseñas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    @Override
    public String toString() {
        return "DatosTienda{" + "productos=" + productos + ", ventas=" + ventas + ", rese\u00f1as=" + reseñas + ", usuarios=" + usuarios + ", ofertas=" + ofertas + '}';
    }

    
    

}