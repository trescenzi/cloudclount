package cloud.count;

import badm.Budget;
import badm.Line;
import cc.test.bridge.BridgeConstants;
import cc.test.bridge.LineInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.table.AbstractTableModel;
import org.workplicity.util.DateFormatter;

public class DashboardTableModel extends AbstractTableModel
{
   ArrayList<Budget> budgets;

    private Random rand = new Random();
    
    private String[] columnNames = 
    {
        "Id", 
        "Updated", 
        "Status", 
        "Title", 
        "Budget", 
        "Actual", 
        "Var", 
        "%"
    };
    
    private String[] data1 = 
    {
        "6",
        "12/04/2012",
        "DRAFT",
        "Budget for 2012",
        "N / A",
        "N / A",
        "N / A",
        "N / A"
    }; 

    @Override
    public String getColumnName(int col) 
    {
        return columnNames[col];
    }
    
    @Override
    public int getRowCount() 
    {
        if(budgets == null){
            budgets = Budget.all();
        }
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
            ArrayList<LineInterface> ilines = budget.fetchLines(BridgeConstants.Side.INCOME);
            ArrayList<LineInterface> elines = budget.fetchLines(BridgeConstants.Side.EXPENDITURE);
            
            Double isum = sum(ilines);
            Double esum = sum(elines);

            if (column == 0)
                return budget.getId();
            if(column == 1 && budget.getUpdateTime() != null)
                return DateFormatter.toString(new Date(budget.getUpdateTime()));
            if(column == 2)
                return budget.getStatus();
            if(column == 3)
                return budget.getName();
            if(column == 4)
                return budget.getGoal();
            if(column == 5 && budget.getTotal() != null)
                return isum-esum;
            else if(column == 5)
            {
                budget.setTotal(0.0);
                return 0;
            }    
            if(budget.getGoal() != null && budget.getTotal() != null)
            {
                if(column == 6)
                    return budget.getGoal() - budget.getTotal();
                if(column == 7)
                    return (budget.getTotal()/budget.getGoal()) * 100;
            }
            
            return data1 [column];
    }
    
    public void refresh()
    {
        // Query for all budgets
        budgets.clear();
        budgets = Budget.all();
        // Update table
        this.fireTableDataChanged();
    }
    
    public Boolean isNew(int row) 
    {
        int id = -1;
        
        if(row == 0)
            id = Integer.parseInt(data1[0]);
        
        return id % 2 == 0;
    }
    
    public Double sum(ArrayList<LineInterface> lines){
        Double total = 0.0;  
        for(LineInterface l : lines){
            Line line = (Line) l;
            if(line.getTotal() != null)
            total += line.getTotal();
        }
        return total;
    }
    
}

