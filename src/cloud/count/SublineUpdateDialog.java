package cloud.count;

import badm.Line;
import badm.Subline;
import cc.test.bridge.BridgeConstants;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

public final class SublineUpdateDialog extends javax.swing.JDialog {

    private static Line line;
    /**
     * Creates new form SublineUpdate
     */
    public SublineUpdateDialog(java.awt.Frame parent, boolean modal, Line line) {
        super(parent, modal);
        this.line = line;
        initComponents();
        init();
    }

    protected void init()
    {
        initSublineUpdateTable();
    }
    
        protected void initSublineUpdateTable()
    {
        SublineUpdateTableModel model = (SublineUpdateTableModel) sublineUpdateTable.getModel();
        model.setLine(line);
        /**
         * This code will automate the width of the columns
         * on our dashboard table
         */
        final int[] WIDTHS = 
        {
            75,  // Subline #
            75,  // Name
            75   // Budget
        };

        for (int i = 0; i < WIDTHS.length; i++) 
        {
            TableColumn col = sublineUpdateTable.getColumnModel().getColumn(i);
            col.setPreferredWidth(WIDTHS[i]);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sublineUpdateScrollPane = new javax.swing.JScrollPane();
        sublineUpdateTable = new javax.swing.JTable();
        addSublineButton = new javax.swing.JButton();
        deleteSublineButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        sublineUpdateSeparator = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lineNameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lineBudgetTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cloud Count >> Subline Update");
        setName("");

        sublineUpdateTable.setModel(new cloud.count.SublineUpdateTableModel());
        sublineUpdateScrollPane.setViewportView(sublineUpdateTable);

        addSublineButton.setText("+");
        addSublineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSublineButtonActionPerformed(evt);
            }
        });

        deleteSublineButton.setText("-");
        deleteSublineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSublineButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Sublines:");

        jLabel2.setText("Line Name:");

        lineNameTextField.setText("Name");
        lineNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lineNameTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Line Budget:");

        lineBudgetTextField.setText("0.0");
        lineBudgetTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lineBudgetTextFieldActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(saveButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cancelButton))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(sublineUpdateSeparator)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(addSublineButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(deleteSublineButton)
                        .add(12, 12, 12))))
            .add(layout.createSequentialGroup()
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(sublineUpdateScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 443, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lineNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(lineBudgetTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(lineNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(lineBudgetTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 29, Short.MAX_VALUE)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(sublineUpdateScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, addSublineButton)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, deleteSublineButton))
                .add(18, 18, 18)
                .add(sublineUpdateSeparator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(4, 4, 4)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cancelButton)
                    .add(saveButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void lineBudgetTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lineBudgetTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lineBudgetTextFieldActionPerformed

    private void lineNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lineNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lineNameTextFieldActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        line.setName(lineNameTextField.getText());
        line.setGoal(Double.parseDouble(
                lineBudgetTextField.getText()));
        line.commit();
        dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void addSublineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSublineButtonActionPerformed
        Subline subline = (Subline) line.createSubline();
        subline.setName(
                JOptionPane.showInputDialog("Name:"));
        subline.setGoal(
                Double.parseDouble(JOptionPane.showInputDialog("Budget:")));
        System.out.println("The number of lines is"+line.getNumSublines());
        subline.setNumber(line.getNumber()+(line.getNumSublines())*10);
        subline.commit();
        SublineUpdateTableModel model = (SublineUpdateTableModel) sublineUpdateTable.getModel();
        model.refresh();
    }//GEN-LAST:event_addSublineButtonActionPerformed

    private void deleteSublineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSublineButtonActionPerformed
        int row = sublineUpdateTable.getSelectedRow();
        Subline subline = (Subline) line.fetchSublines().get(row);
        subline.delete();
        SublineUpdateTableModel model = (SublineUpdateTableModel) sublineUpdateTable.getModel();
        model.refresh();
    }//GEN-LAST:event_deleteSublineButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SublineUpdateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SublineUpdateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SublineUpdateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SublineUpdateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                SublineUpdateDialog dialog = new SublineUpdateDialog(new javax.swing.JFrame(), true, line);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                
                // Center Dialog
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSublineButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteSublineButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField lineBudgetTextField;
    private javax.swing.JTextField lineNameTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JScrollPane sublineUpdateScrollPane;
    private javax.swing.JSeparator sublineUpdateSeparator;
    private javax.swing.JTable sublineUpdateTable;
    // End of variables declaration//GEN-END:variables
}
