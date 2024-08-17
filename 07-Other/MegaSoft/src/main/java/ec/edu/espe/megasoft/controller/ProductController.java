/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.controller;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kenny Gavilanez, Dev Dynasty, DCCO-ESPE
 */
public class ProductController {
    
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void handleBuyProduct(String idText, String quantityText, DefaultTableModel tableModel) {
        try {
            int id = Integer.parseInt(idText);
            int quantity = Integer.parseInt(quantityText);

            boolean success = productService.buyProductById(id, quantity);
            if (success) {
                JOptionPane.showMessageDialog(null, "Compra realizada con éxito.");

                Products product = productService.getProductById(id);
                if (product != null) {
                    // Actualizar la tabla con el nuevo stock
                    boolean productFound = false;
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        if ((int) tableModel.getValueAt(i, 0) == product.getId()) {
                            tableModel.setValueAt(product.getStock(), i, 3);
                            productFound = true;
                            break;
                        }
                    }

                    if (!productFound) {
                        tableModel.addRow(new Object[]{product.getId(), product.getName(), product.getPrice(), product.getStock()});
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo obtener el producto actualizado.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al realizar la compra. Verifica el stock disponible.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa valores válidos para el ID y la cantidad.");
        }
    }
}
