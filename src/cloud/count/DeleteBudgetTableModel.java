/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloud.count;

import badm.Budget;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class DeleteBudgetTableModel extends AbstractTableModel 
{
    // Query for all available budgets
    ArrayList<Budget> budgets = Budget.all();
    
    private String[] columnNames = 
    {
        "Id",  
        "Status", 
        "Title", 
    };
        
    private String[] data1 = 
    {
        "N/A",
        "N/A",
        "N/A",
    }; 
        
    @Override
    public String getColumnName(int col) 
    {
        return columnNames[col];
    }
    
    @Override
    public int getRowCount() 
    {
        return budgets.size();
    }

    @Override
    public int getColumnCount() 
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) 
    {
        Budget budget = budgets.get(row);
        
        if (column == 0)
            return budget.getId();
        if(column == 2)
            return budget.getName();      
         
        return data1 [column];
    }
    
    public void refresh()
    {
        // Query for all budgets
        budgets = Budget.all();
        // Update table
        this.fireTableDataChanged();
    }
   
}
