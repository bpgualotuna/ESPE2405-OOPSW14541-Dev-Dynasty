/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.edu.espe.megasoft.view;

/**
 *
 * @author DELL
 */
public class FrmMegaSoftcustomer extends javax.swing.JFrame {

    /**
     * Creates new form FrmMegaSoftcustomer
     */
    public FrmMegaSoftcustomer() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuMegaSoft = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        itmViewProducts = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnBuscarOferta = new javax.swing.JMenuItem();
        mnVerOferta = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        mnuUser = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem7.setText("jMenuItem7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mnuMegaSoft.setText("MegaSoft");

        jMenuItem3.setText("Logout");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnuMegaSoft.add(jMenuItem3);

        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        mnuMegaSoft.add(jMenuItem4);

        jMenuBar1.add(mnuMegaSoft);

        jMenu1.setText("Productos");

        itmViewProducts.setText("Ver Productos");
        itmViewProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmViewProductsActionPerformed(evt);
            }
        });
        jMenu1.add(itmViewProducts);

        jMenuItem9.setText("Buscar Productos");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ofertas");

        mnBuscarOferta.setText("Buscar Ofertas");
        mnBuscarOferta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBuscarOfertaActionPerformed(evt);
            }
        });
        jMenu2.add(mnBuscarOferta);

        mnVerOferta.setText("Ver Ofertas");
        mnVerOferta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnVerOfertaActionPerformed(evt);
            }
        });
        jMenu2.add(mnVerOferta);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Reseña");

        jMenuItem8.setText("Ver Reseña");
        jMenu3.add(jMenuItem8);

        jMenuItem6.setText("Agregar Reseña");
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        mnuUser.setText("Usuario");

        jMenuItem1.setText("Historial de Compra");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnuUser.add(jMenuItem1);

        jMenuItem2.setText("Ver Carrito");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnuUser.add(jMenuItem2);

        jMenuBar1.add(mnuUser);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        FrmMegaSoftLoginCustomer frmLogin = new FrmMegaSoftLoginCustomer();
        this.setVisible(false);
        frmLogin.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        FrmHistoriaCompra frmHistoriaCompra = new FrmHistoriaCompra();
        this.setVisible(false);
        frmHistoriaCompra.setVisible(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void itmViewProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmViewProductsActionPerformed
        // TODO add your handling code here:
        FrmVerProductos frmVerProductos = new FrmVerProductos();
        this.setVisible(false);
        frmVerProductos.setVisible(true);
    }//GEN-LAST:event_itmViewProductsActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        FrmVerCarrito frmVerCarrito = new FrmVerCarrito();
        this.setVisible(false);
        frmVerCarrito.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void mnVerOfertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnVerOfertaActionPerformed
        // TODO add your handling code here:
        FrmOfertas frmMegasoft = new FrmOfertas();
        this.setVisible(false);
        frmMegasoft.setVisible(true);
    }//GEN-LAST:event_mnVerOfertaActionPerformed

    private void mnBuscarOfertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBuscarOfertaActionPerformed
        // TODO add your handling code here:
        FrmOfertas frmMegasoft = new FrmOfertas();
        this.setVisible(false);
        frmMegasoft.setVisible(true);
    }//GEN-LAST:event_mnBuscarOfertaActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMegaSoftcustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMegaSoftcustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMegaSoftcustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMegaSoftcustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMegaSoftcustomer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itmViewProducts;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem mnBuscarOferta;
    private javax.swing.JMenuItem mnVerOferta;
    private javax.swing.JMenu mnuMegaSoft;
    private javax.swing.JMenu mnuUser;
    // End of variables declaration//GEN-END:variables
}
