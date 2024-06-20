/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class ArchivoJson {
    
    private final Gson gson;

    public ArchivoJson() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void guardarEnJson(Object obj, String filePath) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try (FileWriter writer = new FileWriter(filePath)) {
        gson.toJson(obj, writer);
    } catch (IOException e) {
        e.printStackTrace();
        throw e;
    }
}
    
    public <T> T cargarDesdeJson(String rutaArchivo, Class<T> clase) throws IOException {
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            return gson.fromJson(lector, clase);
        }
    }
    
}
