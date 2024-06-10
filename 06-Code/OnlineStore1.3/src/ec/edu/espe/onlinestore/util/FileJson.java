/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.onlinestore.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
 */
public class FileJson {
    
   private final Gson gson;

    public FileJson() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void saveToJson(Object obj, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(obj, writer);
        }
    }
    
    public <T> T loadFromJson(String filePath, Class<T> clazz) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return gson.fromJson(reader, clazz);
        }
    }

  
}

    



    


