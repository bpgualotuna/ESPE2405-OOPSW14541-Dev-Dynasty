/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.modelo;

/**
 *
 * @author  Dev Dynasty, DCCO-ESPE
 */
public class Usuario {
    private String nombreUsuario;
    private String contraseña;
    private boolean esAdmin;

    public Usuario(String nombreUsuario, String contraseña, boolean esAdmin) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.esAdmin = esAdmin;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean esAdmin() {
        return esAdmin;
    }

    public void setAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombreUsuario=" + nombreUsuario + ", esAdmin=" + esAdmin + '}';
    }
    
}
