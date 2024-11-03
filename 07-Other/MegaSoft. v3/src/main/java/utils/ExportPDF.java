/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document as BsonDocument;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Brayan Gualotuña, Dev Dynasty, DCCO-ESPE
 */
public class ExportPDF {
    Document pdfDocument = new Document();
        try {
            PdfWriter.getInstance(pdfDocument, new FileOutputStream("Listado.pdf"));
            pdfDocument.open();

            // Agregar título al PDF
            pdfDocument.add(new Paragraph("Listado de datos desde MongoDB"));

            // Recorrer los documentos de la colección y agregarlos al PDF
            List<BsonDocument> documentos = collection.find().into(new java.util.ArrayList<>());
            for (BsonDocument doc : documentos) {
                pdfDocument.add(new Paragraph(doc.toJson()));
            }

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            pdfDocument.close();
            mongoClient.close();
        }
}
