/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.controller;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Factura {
         private static int contadorFactura = 1; // Variable estática que empieza en 1
    private String numeroFactura;           // Guardará el número de factura en formato "00001"
    private List<ProductoSeleccionado> productos;       // Lista de productos asociados a la factura
    private double totalSinIVA;             // Total de la factura sin IVA
    private double totalConIVA;             // Total de la factura con IVA
    private static final double IVA = 0.15; // 15% de IVA
    private String cliente;                 // Nombre del cliente
    private LocalDate fecha;                // Fecha de la factura, establecida en la fecha actual

    // Constructor
    public Factura(String cliente, List<ProductoSeleccionado> productos) {
        this.numeroFactura = String.format("%05d", contadorFactura++);
        this.productos = productos;
        this.totalSinIVA = calcularTotalSinIVA();
        this.totalConIVA = calcularTotalConIVA();
        this.cliente = cliente;
        this.fecha = LocalDate.now(); // Fecha actual
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    public void setProductos(List<ProductoSeleccionado> productos) {
        this.productos = productos;
    }

    // Métodos para calcular totales
    private double calcularTotalSinIVA() {
        return productos.stream().mapToDouble(Products::getPrice).sum();
    }

    private double calcularTotalConIVA() {
        return totalSinIVA * (1 + IVA);
    }

    // Getters y Setters
    public String getNumeroFactura() {
        return numeroFactura;
    }

    public List<ProductoSeleccionado> getProductos() {
        return productos;
    }

    public double getTotalSinIVA() {
        return totalSinIVA;
    }

    public double getTotalConIVA() {
        return totalConIVA;
    }

    public String getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
     // Método para calcular los totales
    private void calcularTotales() {
        totalSinIVA = 0.0;
        for (Products producto : productos) {
            totalSinIVA += producto.getPrice() * producto.getStock(); // Aquí supongo que 'stock' es la cantidad del producto
        }
        totalConIVA = totalSinIVA * 1.12; // Suponiendo un IVA del 12%
    }
}
