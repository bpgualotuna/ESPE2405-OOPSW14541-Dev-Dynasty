/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.List;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import ec.edu.espe.megasoft.Products;
import ec.edu.espe.megasoft.UserLogin;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author  Dev Dynasty, DCCO-ESPE
 */
public class ExportDB {
    public static boolean create(UserLogin user) {

        String uri = "mongodb+srv://bpgualotuna1:bpgualotuna1@cluster0.elvwlgc.mongodb.net/";

        MongoDatabase dataBase = openConnectionToMongo(uri);
        Document dataOfUser = new Document().append("id", user.getName()).append("password",user.getPassword());

        String collection = "megaSoftClients";
        MongoCollection<Document> mongoCollection = accessToCollections(dataBase, collection);
        insertOneData(dataOfUser, mongoCollection);
        return false;
    }
    
    public static boolean createProduct(Products products) {

        String uri = "mongodb+srv://bpgualotuna1:bpgualotuna1@cluster0.elvwlgc.mongodb.net/";

        MongoDatabase dataBase = openConnectionToMongo(uri);
        Document dataOfUser = new Document().append("id", products.getName()).append("name",products.getName()).append("price", products.getPrice()).append("stock", products.getStock());

        String collection = "megaSoftProducts";
        MongoCollection<Document> mongoCollection = accessToCollections(dataBase, collection);
        insertOneData(dataOfUser, mongoCollection);
        return false;
    }
    
     public static boolean authenticateUser(String username, String password) {
        String uri = "mongodb+srv://bpgualotuna1:bpgualotuna1@cluster0.elvwlgc.mongodb.net/";
        
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("oop"); 
            MongoCollection<Document> collection = database.getCollection("megaSoftClients"); 

            Document user = collection.find(Filters.eq("id", username)).first();

            if (user != null) {
                String storedPassword = user.getString("password");
                return password.equals(storedPassword);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("An error occurred while authenticating the user: " + e.getMessage());
            return false;
        }
    }

    //Abir conexión con mongoDB
    public static MongoDatabase openConnectionToMongo(String uri) {
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase dataBase = mongoClient.getDatabase("oop");

        return dataBase;
    }
    
    
    

    //Acceso a colecciones
    public static MongoCollection<Document> accessToCollections(MongoDatabase dataBase, String collection) {
        MongoCollection<Document> mongoCollection = dataBase.getCollection(collection);
        return mongoCollection;
    }

    //Tipo de ingreso de datos
    public static void insertOneData(Document data, MongoCollection<Document> mongoCollection) {
        mongoCollection.insertOne(data);
    }

    public static void insertMoreThanOneData(List<Document> listOfData, MongoCollection<Document> mongoCollection) {
        mongoCollection.insertMany(listOfData);
    }

    //Obtención de datos
    public static void getAllCollection(MongoCollection<Document> mongoCollection) {
        //Si solo busco en base a un solo dato 
        Document findDocument = new Document("male", true);
        //Si quiero todo el documento:
        //Document findDocument = new Document();

        MongoCursor<Document> resultDocument = mongoCollection.find(findDocument).iterator();
        
        System.out.println("***************************************");
        System.out.println("People male");
        System.out.println("***************************************");
        while (resultDocument.hasNext()) {
            System.out.println(resultDocument.next().getString("name"));
        }
        
        //return resultDocument;
    }
    
    //Actualización de documentos
    public static void editDocuments(String key, String data,String newData, MongoCollection<Document> mongoCollection){
        Document findDocument = new Document(key,data);
        
        Document updateDocument = new Document("$set",new Document(key,newData));
        
        mongoCollection.findOneAndUpdate(findDocument, updateDocument);
    }
    
    
    //Eliminar documentos
    public static void deleteDocuments(String key, String data, MongoCollection<Document> mongoCollection){
        //TODO: Combinar con método de obtención de datos
        Document findDocument = new Document("male", true);
        mongoCollection.findOneAndDelete(findDocument);
    }
}
