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
import com.mongodb.client.model.Sorts;
import ec.edu.espe.megasoft.controller.Factura;
import ec.edu.espe.megasoft.controller.Products;
import ec.edu.espe.megasoft.model.UserLogin;
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

     // Variable estática para almacenar el nombre del usuario autenticado
    private static String nombreUsuario;
    
    public static boolean authenticateUser(String username, String password) {
        String uri = "mongodb+srv://mateolisintuna:CristianMateo@cluster0.vhefvyu.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("OOP");
            MongoCollection<Document> collection = database.getCollection("megaSoftClients");

            Document user = collection.find(Filters.eq("id", username)).first();

            if (user != null) {
                String storedPassword = user.getString("password");
                
                 // Guardar el nombre del usuario si la autenticación es exitosa
                   nombreUsuario = user.getString("id");
                   
                 return password.equals(storedPassword);
                
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("An error occurred while authenticating the user: " + e.getMessage());
            return false;
        }
    }
    
      public static String obtenerCliente() {
        return nombreUsuario;
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
         
         
         
// metodos para la factura
         
       
    // Método para obtener el último número de factura
    public static String obtenerUltimoNumeroFactura() {
        MongoCollection<Document> collection = getCollection("megaSoftFactura");

        // Buscar el último documento ordenado por "numeroFactura" en orden descendente
        Document lastInvoice = collection.find()
            .sort(Sorts.descending("numeroFactura"))
            .first();

        // Si hay facturas existentes, devolver el último número, si no, devuelve "00000"
        return (lastInvoice != null) ? lastInvoice.getString("numeroFactura") : "00000";
    }

    // Método para crear una nueva factura
    public static boolean crearNuevaFactura(Factura factura) {
        MongoCollection<Document> collection = getCollection("megaSoftFactura");

        // Obtener el último número de factura y generar el siguiente
        String ultimoNumero = obtenerUltimoNumeroFactura();
        int siguienteNumero = Integer.parseInt(ultimoNumero) + 1;
        String numeroFactura = String.format("%05d", siguienteNumero);
        
        factura.setNumeroFactura(numeroFactura); // Actualizar el número de factura en la factura actual

        // Crear el documento de la factura
        Document facturaDoc = new Document("numeroFactura", factura.getNumeroFactura())
            .append("cliente", factura.getCliente())
            .append("fecha", factura.getFecha().toString())
            .append("productos", factura.getProductos())
            .append("totalSinIVA", factura.getTotalSinIVA())
            .append("totalConIVA", factura.getTotalConIVA());

        // Insertar la factura en la colección
        collection.insertOne(facturaDoc);
        return true;
    }

    // Método para convertir un Document a un objeto Products
public static Products documentToProduct(Document document) {
    if (document == null) {
        return null;
    }

    // Extraer los campos necesarios del documento
    int id = document.getInteger("id", 0); // Ajusta el tipo de dato según tu base de datos
    String name = document.getString("name");
    double price = document.getDouble("price");
    int stock = document.getInteger("stock", 0);

    // Crear y retornar el objeto Products
    return new Products(id, name, price, stock);


}
}

