/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Dev Dynasty, DCCO-ESPE
 */
;

public class UsuarioLista {

    public UsuarioLista(List<Usuario> usuarios1) {
    }

    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void cargarUsuariosDesdeJson(String rutaArchivo) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            Gson gson = new Gson();
            UsuarioListWrapper wrapper = gson.fromJson(json, UsuarioListWrapper.class);
            usuarios = wrapper.getUsuarios();
            System.out.println("Usuarios cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar los usuarios.");
            e.printStackTrace();
        }
    }

    public void guardarUsuariosEnJson(List<Usuario> usuarios, String rutaArchivo) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            UsuarioListWrapper wrapper = new UsuarioListWrapper();
            wrapper.setUsuarios(usuarios);
            String json = gson.toJson(wrapper);
            Files.write(Paths.get(rutaArchivo), json.getBytes());
            System.out.println("Usuarios guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios.");
            e.printStackTrace();
        }
    }

    public static class UsuarioListWrapper {
        private List<Usuario> usuarios;

        public List<Usuario> getUsuarios() {
            return usuarios;
        }

        public void setUsuarios(List<Usuario> usuarios) {
            this.usuarios = usuarios;
        }
    }
}
