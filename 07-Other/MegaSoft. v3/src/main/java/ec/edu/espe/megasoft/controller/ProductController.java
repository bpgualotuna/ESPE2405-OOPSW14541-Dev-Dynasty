
package ec.edu.espe.megasoft.controller;

import java.util.List;
import javax.swing.JOptionPane;
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
            // Convertir los textos de ID y cantidad a números
            int id = Integer.parseInt(idText);
            int quantity = Integer.parseInt(quantityText);

            // Buscar el producto por ID en la tabla de productos
            int rowCount = productTableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                int productId = (int) productTableModel.getValueAt(i, 0); // Suponiendo que la columna 0 es el ID

                if (productId == id) {
                    String name = (String) productTableModel.getValueAt(i, 1); // Suponiendo que la columna 1 es el nombre
                    double price = (double) productTableModel.getValueAt(i, 2); // Suponiendo que la columna 2 es el precio
                    int stock = (int) productTableModel.getValueAt(i, 3); // Suponiendo que la columna 3 es el stock

                    // Verificar si hay suficiente stock
                    if (quantity <= stock) {
                        // Actualizar el stock en la tabla de productos
                        productTableModel.setValueAt(stock - quantity, i, 3);

                        // Calcular el total
                        double total = price * quantity;

                        // Agregar una nueva fila al historial de compras
                        historyTableModel.addRow(new Object[]{id, name, new java.util.Date(), quantity, total});
                        
                        JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
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
    
    // Método para guardar el producto y actualizar la base de datos
    public Products saveProduct(String name, double price, int stock) {
        Products product = new Products(name, price, stock);
        ExportDB.createProduct(product);
        return product;
    }
    
    // Método para actualizar la tabla con los productos desde la base de datos
    public void updateTableOfMenus(DefaultTableModel model) {
        List<Document> documents = ExportDB.getAllDocuments();
        model.setRowCount(0);

        for (Document doc : documents) {
            int id = doc.getInteger("id");
            String name = doc.getString("name");
            Double price = doc.getDouble("price");
            int stock = doc.getInteger("stock");

            // Añade una fila al modelo con los valores
            model.addRow(new Object[]{id, name, price, stock});
        }
    }
}

