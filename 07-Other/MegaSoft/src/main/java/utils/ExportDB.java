/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.mongodb.client.FindIterable;
import java.util.List;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import ec.edu.espe.megasoft.Products;
import ec.edu.espe.megasoft.UserLogin;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Dev Dynasty, DCCO-ESPE
 */
public class ExportDB {

    public static boolean create(UserLogin user) {

        String uri = "mongodb+srv://mateolisintuna:CristianMateo@cluster0.vhefvyu.mongodb.net/";

        MongoDatabase dataBase = openConnectionToMongo(uri);
        Document dataOfUser = new Document().append("name", user.getName()).append("password", user.getPassword());

        String collection = "megaSoftClients";
        MongoCollection<Document> mongoCollection = accessToCollections(dataBase, collection);
        insertOneData(dataOfUser, mongoCollection);
        return false;
    }

    public static boolean createProduct(Products products) {

        String uri = "mongodb+srv://mateolisintuna:CristianMateo@cluster0.vhefvyu.mongodb.net/";

        MongoDatabase dataBase = openConnectionToMongo(uri);
        Document dataOfUser = new Document().append("id", products.getId()).append("name", products.getName()).append("price", products.getPrice()).append("stock", products.getStock());

        String collection = "megaSoftProducts";
        MongoCollection<Document> mongoCollection = accessToCollections(dataBase, collection);
        insertOneData(dataOfUser, mongoCollection);
        return true;
    }

    public static boolean authenticateUser(String username, String password) {
        String uri = "mongodb+srv://mateolisintuna:CristianMateo@cluster0.vhefvyu.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("OOP");
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
        MongoDatabase dataBase = mongoClient.getDatabase("OOP");
        

        return dataBase;
    }

    
    //extraccion de documentos
    public static List<Document> getAllDocuments() {
        String uri = "mongodb+srv://mateolisintuna:CristianMateo@cluster0.vhefvyu.mongodb.net/";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("OOP");
        MongoCollection<Document> collection = database.getCollection("megaSoftProducts");
        
        List<Document> documents = new ArrayList<>();
        FindIterable<Document> iterable = collection.find();
        for (Document doc : iterable) {
            documents.add(doc);
        }
        return documents;
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
    public static void editDocuments(String key, String data, String newData, MongoCollection<Document> mongoCollection) {
        Document findDocument = new Document(key, data);

        Document updateDocument = new Document("$set", new Document(key, newData));

        mongoCollection.findOneAndUpdate(findDocument, updateDocument);
    }

    //Eliminar documentos
    public static void deleteDocuments(String key, String data, MongoCollection<Document> mongoCollection) {
        //TODO: Combinar con método de obtención de datos
        Document findDocument = new Document("male", true);
        mongoCollection.findOneAndDelete(findDocument);
    }

    
    public static boolean deleteProductById(int id) {
    String uri = "mongodb+srv://mateolisintuna:CristianMateo@cluster0.vhefvyu.mongodb.net/";
    String databaseName = "OOP"; // Asegúrate de reemplazar esto con el nombre correcto de tu base de datos
    String collectionName = "megaSoftProducts"; // Asegúrate de reemplazar esto con el nombre correcto de tu colección

    try (MongoClient mongoClient = MongoClients.create(uri)) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        Document query = new Document("id", id);
        Document result = collection.findOneAndDelete(query);
        return result != null;
    } catch (Exception e) {
        System.err.println("An error occurred while deleting the product: " + e.getMessage());
        return false;
    }
}
 public static boolean buyProductById(int id, int quantity) {
    String uri = "mongodb+srv://mateolisintuna:CristianMateo@cluster0.vhefvyu.mongodb.net/";
    String databaseName = "OOP"; // Reemplaza esto con el nombre correcto de tu base de datos
    String collectionName = "megaSoftProducts"; // Reemplaza esto con el nombre correcto de tu colección

    try (MongoClient mongoClient = MongoClients.create(uri)) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        // Encuentra el producto por ID
        Document query = new Document("id", id);
        Document product = collection.find(query).first();

        if (product != null) {
            int currentStock = product.getInteger("stock");
            if (currentStock >= quantity) {
                int newStock = currentStock - quantity;
                Document update = new Document("$set", new Document("stock", newStock));
                collection.updateOne(query, update);
                return true;
            } else {
                System.err.println("Not enough stock available.");
                return false;
            }
        } else {
            System.err.println("Product not found.");
            return false;
        }
    } catch (Exception e) {
        System.err.println("An error occurred while buying the product: " + e.getMessage());
        return false;
    }
}
        public static Document getProductById(int id) {
        MongoCollection<Document> collection = getCollection("megaSoftProducts");
        return collection.find(Filters.eq("id", id)).first();
    }
     private static MongoCollection<Document> getCollection(String collectionName) {
        MongoDatabase database = openConnectionToMongo();
        return database.getCollection(collectionName);
    }
         private static MongoDatabase openConnectionToMongo() {
         String uri = "mongodb+srv://mateolisintuna:CristianMateo@cluster0.vhefvyu.mongodb.net/";
        MongoClient mongoClient = MongoClients.create(uri);
        return mongoClient.getDatabase("OOP");
    }
}

