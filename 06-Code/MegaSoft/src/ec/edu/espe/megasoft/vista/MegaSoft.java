/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ec.edu.espe.megasoft.vista;

import ec.edu.espe.megasoft.controlador.ControladorTienda;
import ec.edu.espe.megasoft.modelo.*;
import java.io.IOException;

/** 
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class MegaSoft {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
   public static void main(String[] args) throws IOException {
    Tienda tienda = new Tienda();
    Menu menu = new Menu();
    ControladorTienda controlador = new ControladorTienda(tienda, menu);
    controlador.iniciar();
        
    }
}

