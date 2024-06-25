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
import java.io.Reader;
import java.io.Writer;

/**
 *
 * @author Dev Dynasty, DCCO-ESPE
 */
public class ArchivoJson {
    
   private final Gson gson;

    public ArchivoJson() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public synchronized <T> T cargarDesdeJson(String filePath, Class<T> clazz) throws IOException {
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, clazz);
        }
    }

    public synchronized void guardarEnJson(Object objeto, String filePath) throws IOException {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(objeto, writer);
        }
    }
}
