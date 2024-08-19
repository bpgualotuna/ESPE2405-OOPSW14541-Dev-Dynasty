/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import utils.ExportDB;

/**
 *
 * @author Kenny Gavilanez, Dev Dynasty, DCCO-ESPE
 */
public class ProductService {
    public boolean buyProductById(int id, int quantity) {
        return ExportDB.buyProductById(id, quantity);
    }

    public Product getProductById(int id) {
        Document productDoc = ExportDB.getProductById(id);
        if (productDoc != null) {
            String name = productDoc.getString("name");
            double price = productDoc.getDouble("price");
            int stock = productDoc.getInteger("stock");
            return new Product(id, name, price, stock);
        }
        return null;
    }
    
    public void updateTableOfMenus(DefaultTableModel tableModel) {
        List<Document> documents = ExportDB.getAllDocuments();

        tableModel.setRowCount(0);

        for (Document doc : documents) {
            int id = doc.getInteger("id");
            String name = doc.getString("name");
            double price = doc.getDouble("price");
            int stock = doc.getInteger("stock");

            tableModel.addRow(new Object[]{id, name, price, stock});
        }
    }
}

