
package ec.edu.espe.megasoft.controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import utils.ExportDB;

/**
 *
 * @author Kenny Gavilanez, Dev Dynasty, DCCO-ESPE
 */
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    public void handleBuyProduct(String idText, String quantityText, DefaultTableModel productTableModel, DefaultTableModel historyTableModel) {
        try {
            int id = Integer.parseInt(idText);
            int quantity = Integer.parseInt(quantityText);
            int rowCount = productTableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                int productId = (int) productTableModel.getValueAt(i, 0); 

                if (productId == id) {
                    String name = (String) productTableModel.getValueAt(i, 1); 
                    double price = (double) productTableModel.getValueAt(i, 2); 
                    int stock = (int) productTableModel.getValueAt(i, 3); 
                    
                    if (quantity <= stock) {
                        productTableModel.setValueAt(stock - quantity, i, 3);
                        double total = price * quantity;
                        historyTableModel.addRow(new Object[]{id, name, new java.util.Date(), quantity, total});
                    } else {
                        System.out.println("No hay suficiente stock para completar la compra.");
                    }

                    return;
                }
            }

            System.out.println("Producto no encontrado con el ID especificado.");
        } catch (NumberFormatException e) {
            System.out.println("ID o cantidad inválida.");
        }
    }
    
    public Products saveProduct(String name, double price, int stock) {
        Products product = new Products(name, price, stock);
        ExportDB.createProduct(product);
        return product;
    }
    
    public void updateTableOfMenus(DefaultTableModel model) {
        List<Document> documents = ExportDB.getAllDocuments();
        model.setRowCount(0);

        for (Document doc : documents) {
            int id = doc.getInteger("id");
            String name = doc.getString("name");
            Double price = doc.getDouble("price");
            int stock = doc.getInteger("stock");        
            model.addRow(new Object[]{id, name, price, stock});
        }
    }
}
