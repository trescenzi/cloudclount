package cloud.count;

import badm.Budget;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public final class DashboardFrame extends javax.swing.JFrame 
{
    JackRabbitHandler jrh;
    int rightClickedRow = 0;
    int doubleClickedRow = 0;
    
    JTable dTable;
    
    public DashboardFrame() 
    {
        initComponents();
        init();
        // Center JFrame
        this.setLocationRelativeTo(null);
    }
    
    protected void init()
    {
        initDashboardTable();
        //eventually you will log in with your own credentials
        jrh = new JackRabbitHandler("http://localhost:8080/rmi",
                "admin", "admin");
    }
    
    protected void initDashboardTable()
    {
        /**
         * This code will automate the width of the columns
         * on our dashboard table
         */
        final int[] WIDTHS = 
        {
            40,  // ID
            70,  // Update date
            70,  // Status
            175, // Title
            75,  // Budget
            50,  // Actual
            50,  // Var
            50   // %
        };
        
        DashboardTableRenderer myTableRenderer = new DashboardTableRenderer();
        
        TableColumnModel tableColumnModel = dashboardTable.getColumnModel();

        for (int i = 0; i < WIDTHS.length; i++) 
        {
            TableColumn col = dashboardTable.getColumnModel().getColumn(i);
            col.setPreferredWidth(WIDTHS[i]);
            
            tableColumnModel.getColumn(i).setCellRenderer(myTableRenderer);
        }
        
        dashboardTable.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {        
                // Double Click
                if (e.getClickCount() == 2) 
                {
                    JTable target = (JTable) e.getSource();

                    final int row = target.getSelectedRow() + 1;
                    doubleClickedRow = target.getSelectedRow();

                    SwingUtilities.invokeLater(new Runnable() 
                    {
                        @Override
                        public void run() 
                        {
                            editEntry(row);
                        }
                    });
                }
                
                // Right Click
                // I found this code from Stack overflow
                // http://stackoverflow.com/questions/766956/how-do-i-create-a-right-click-context-menu-in-java-swing
                if (e.getButton() == 3)
                {
                    rightClickedRow = dashboardTable.rowAtPoint(e.getPoint());
                    rightClickPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                    
                }
            }
        });
        
        dTable = dashboardTable;
    }
    
    public void editEntry(int row)
    {
        ArrayList<Budget> budgets = Budget.all();
        Budget budget = budgets.get(doubleClickedRow);
        
        System.out.println("Row " + row + " clicked");
        final JFrame parent = this;
        EditBudgetDialog dialog = new EditBudgetDialog(parent, false,jrh, budget);
        // Center Dialog
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightClickPopupMenu = new javax.swing.JPopupMenu();
        editBudgetMenuItem = new javax.swing.JMenuItem();
        dashboardScrollPane = new javax.swing.JScrollPane();
        dashboardTable = new javax.swing.JTable();
        exitButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        dashboardMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exportMenu = new javax.swing.JMenu();
        exportToExcelMenu = new javax.swing.JMenu();
        exportToXLSMenuItem = new javax.swing.JMenuItem();
        exportToCSVMenuItem = new javax.swing.JMenuItem();
        exportToCCFormatMenuItem = new javax.swing.JMenuItem();
        importMenu = new javax.swing.JMenu();
        importFromCCMenuItem = new javax.swing.JMenuItem();
        printMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();
        searchMenuItem = new javax.swing.JMenuItem();
        changePasswordMenuItem = new javax.swing.JMenuItem();
        adminMenu = new javax.swing.JMenu();
        usersMenu = new javax.swing.JMenu();
        modifyUserMenuItem = new javax.swing.JMenuItem();
        deleteUserMenuItem = new javax.swing.JMenuItem();
        addUserMenuItem = new javax.swing.JMenuItem();
        budgetsMenu = new javax.swing.JMenu();
        createBudgetMenuItem = new javax.swing.JMenuItem();
        deleteBudgetMenuItem = new javax.swing.JMenuItem();
        modifyBudgetMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();

        rightClickPopupMenu.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                rightClickPopupMenuPopupMenuWillBecomeVisible(evt);
            }
        });

        editBudgetMenuItem.setText("Edit Budget");
        editBudgetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBudgetMenuItemActionPerformed(evt);
            }
        });
        rightClickPopupMenu.add(editBudgetMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cloud Count >> Dashboard");

        dashboardTable.setModel(new cloud.count.DashboardTableModel());
        dashboardScrollPane.setViewportView(dashboardTable);

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        exportMenu.setText("Export");

        exportToExcelMenu.setText("Export to Excel");

        exportToXLSMenuItem.setText("Export to XLS");
        exportToExcelMenu.add(exportToXLSMenuItem);

        exportToCSVMenuItem.setText("Export to CSV");
        exportToExcelMenu.add(exportToCSVMenuItem);

        exportMenu.add(exportToExcelMenu);

        exportToCCFormatMenuItem.setText("Export to CC Format");
        exportMenu.add(exportToCCFormatMenuItem);

        fileMenu.add(exportMenu);

        importMenu.setText("Import");

        importFromCCMenuItem.setText("Import from CC");
        importMenu.add(importFromCCMenuItem);

        fileMenu.add(importMenu);

        printMenuItem.setText("Print");
        fileMenu.add(printMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        dashboardMenuBar.add(fileMenu);

        toolsMenu.setText("Tools");

        searchMenuItem.setText("Search");
        toolsMenu.add(searchMenuItem);

        changePasswordMenuItem.setText("Change Password");
        toolsMenu.add(changePasswordMenuItem);

        dashboardMenuBar.add(toolsMenu);

        adminMenu.setText("Admin");

        usersMenu.setText("Users");

        modifyUserMenuItem.setText("Modify User");
        modifyUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyUserMenuItemActionPerformed(evt);
            }
        });
        usersMenu.add(modifyUserMenuItem);

        deleteUserMenuItem.setText("Delete User");
        deleteUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserMenuItemActionPerformed(evt);
            }
        });
        usersMenu.add(deleteUserMenuItem);

        addUserMenuItem.setText("Add User");
        addUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserMenuItemActionPerformed(evt);
            }
        });
        usersMenu.add(addUserMenuItem);

        adminMenu.add(usersMenu);

        budgetsMenu.setText("Budgets");

        createBudgetMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        createBudgetMenuItem.setText("Create Budget");
        createBudgetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBudgetMenuItemActionPerformed(evt);
            }
        });
        budgetsMenu.add(createBudgetMenuItem);

        deleteBudgetMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        deleteBudgetMenuItem.setText("Delete Budget");
        deleteBudgetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBudgetMenuItemActionPerformed(evt);
            }
        });
        budgetsMenu.add(deleteBudgetMenuItem);

        modifyBudgetMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        modifyBudgetMenuItem.setText("Modify Budget");
        modifyBudgetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyBudgetMenuItemActionPerformed(evt);
            }
        });
        budgetsMenu.add(modifyBudgetMenuItem);

        adminMenu.add(budgetsMenu);

        dashboardMenuBar.add(adminMenu);

        helpMenu.setText("Help");
        dashboardMenuBar.add(helpMenu);

        setJMenuBar(dashboardMenuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dashboardScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(refreshButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(exitButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(dashboardScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(exitButton)
                    .add(refreshButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                DashboardTableModel dashboardModel = (DashboardTableModel)dashboardTable.getModel();
                
                dashboardModel.refresh();
            }
        });
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        winClose();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void createBudgetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBudgetMenuItemActionPerformed
        final JFrame parent = this;
        
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                CreateBudgetDialog dialog = new CreateBudgetDialog(parent, true);
                // Center Dialog
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                // This is a location for post porcessing after cloasing a window
                dialog.dispose();
                // Post processing refresh
                DashboardTableModel dashboardModel = (DashboardTableModel)dashboardTable.getModel();
                dashboardModel.refresh();
            }
        });
    }//GEN-LAST:event_createBudgetMenuItemActionPerformed

    private void rightClickPopupMenuPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_rightClickPopupMenuPopupMenuWillBecomeVisible

    }//GEN-LAST:event_rightClickPopupMenuPopupMenuWillBecomeVisible

    private void editBudgetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBudgetMenuItemActionPerformed
        
        final JFrame parent = this;
        
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                
                ArrayList<Budget> budgets = Budget.all();
                Budget budget = budgets.get(doubleClickedRow);
                EditBudgetDialog dialog = new EditBudgetDialog(parent, true,jrh, budget);
                // Center Dialog
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                // This is a location for post porcessing after cloasing a window
            }
        });
    }//GEN-LAST:event_editBudgetMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        winClose();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void deleteUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserMenuItemActionPerformed
        
        final JFrame parent = this;
        
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                DeleteUserDialog dialog = new DeleteUserDialog(parent, true);
                // Center Dialog
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_deleteUserMenuItemActionPerformed

    private void modifyUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyUserMenuItemActionPerformed
        
        final JFrame parent = this;
        
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                ModifyUserDialog dialog = new ModifyUserDialog(parent, true);
                // Center Dialog
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                // This is a location for post porcessing after cloasing a window
            }
        });
    }//GEN-LAST:event_modifyUserMenuItemActionPerformed

    private void addUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserMenuItemActionPerformed
        
        final JFrame parent = this;
        
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                AddUserDialog dialog = new AddUserDialog(parent, true);
                // Center Dialog
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                // This is a location for post porcessing after cloasing a window
            }
        });
    }//GEN-LAST:event_addUserMenuItemActionPerformed

    private void deleteBudgetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBudgetMenuItemActionPerformed
        
        final JFrame parent = this;
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                DeleteBudgetDialog dialog = new DeleteBudgetDialog(parent, true);
                // Center Dialog
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                
                // Post processing refresh
                DashboardTableModel dashboardModel = (DashboardTableModel)dashboardTable.getModel();
                dashboardModel.refresh();
            }
        });
    }//GEN-LAST:event_deleteBudgetMenuItemActionPerformed

    private void modifyBudgetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyBudgetMenuItemActionPerformed
        final JFrame parent = this;
        
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                
                ArrayList<Budget> budgets = Budget.all();
                Budget budget = budgets.get(doubleClickedRow);
                EditBudgetDialog dialog = new EditBudgetDialog(parent, true,jrh, budget);
                // Center Dialog
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                // This is a location for post porcessing after cloasing a window
            }
        });
    }//GEN-LAST:event_modifyBudgetMenuItemActionPerformed

    public void winClose() 
    {
        System.out.println("System has been terminated.");
        jrh.logOff();
        System.exit(0);
    }
    
    public static void main(String args[]) 
    {

        try 
        {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
//            {
//                if ("Nimbus".equals(info.getName())) 
//                {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
           
            // Set System L&F
             UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
     
        } 
        catch (ClassNotFoundException ex) 
        {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) 
        {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) 
        {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                final DashboardFrame dashboard = new DashboardFrame();
                
                dashboard.addWindowListener(new java.awt.event.WindowAdapter() 
                {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) 
                    {
                        dashboard.winClose();

                        dashboard.dispose();
                    }
                });
        
                dashboard.setVisible(true);
            }
        });
        
    }
        
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addUserMenuItem;
    private javax.swing.JMenu adminMenu;
    private javax.swing.JMenu budgetsMenu;
    private javax.swing.JMenuItem changePasswordMenuItem;
    private javax.swing.JMenuItem createBudgetMenuItem;
    private javax.swing.JMenuBar dashboardMenuBar;
    private javax.swing.JScrollPane dashboardScrollPane;
    private javax.swing.JTable dashboardTable;
    private javax.swing.JMenuItem deleteBudgetMenuItem;
    private javax.swing.JMenuItem deleteUserMenuItem;
    private javax.swing.JMenuItem editBudgetMenuItem;
    private javax.swing.JButton exitButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu exportMenu;
    private javax.swing.JMenuItem exportToCCFormatMenuItem;
    private javax.swing.JMenuItem exportToCSVMenuItem;
    private javax.swing.JMenu exportToExcelMenu;
    private javax.swing.JMenuItem exportToXLSMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem importFromCCMenuItem;
    private javax.swing.JMenu importMenu;
    private javax.swing.JMenuItem modifyBudgetMenuItem;
    private javax.swing.JMenuItem modifyUserMenuItem;
    private javax.swing.JMenuItem printMenuItem;
    private javax.swing.JButton refreshButton;
    private javax.swing.JPopupMenu rightClickPopupMenu;
    private javax.swing.JMenuItem searchMenuItem;
    private javax.swing.JMenu toolsMenu;
    private javax.swing.JMenu usersMenu;
    // End of variables declaration//GEN-END:variables
}
                
