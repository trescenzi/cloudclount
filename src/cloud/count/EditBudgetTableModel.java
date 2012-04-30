package cloud.count;

import badm.Budget;
import badm.Line;
import cc.test.bridge.BridgeConstants.Side;
import cc.test.bridge.LineInterface;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class EditBudgetTableModel extends AbstractTableModel
{
    Budget budget; 
    ArrayList<LineInterface> lines;
    Side side;
    private String[] columnNames = 
    {
        "Id", 
        "Updated", 
        "By", 
        "Line #",
        "Name",
        "Sub-Total"
    };

    private String[] expendituresData = 
    {
        "1",
        "24 / 12 / 2011",
        "fred",
        "100",
        "Ordinary Collections",
        "1,000.00"
    };    

    @Override
    public String getColumnName(int col) 
    {
        return columnNames[col];
    }
    
    @Override
    public int getRowCount() 
    {
        if(lines == null && budget != null)
        {
            lines = budget.fetchLines(side);
        }
        else if(budget == null)
        {
            return 0;
        }
        System.out.println("NUMBER OF LINES:"+lines.size()+side);
        return lines.size();
    }

    @Override
    public int getColumnCount() 
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) 
    {
        Line line = (Line) lines.get(row);
        if(column == 0)
            return line.getId();
        else if(column == 3)
            return line.getNumber();
        else if(column == 4)
            return line.getName();
        else if(column == 5)
            return line.getTotal();
        return expendituresData[column];
    }
    
    public void refresh()
    {
        if(lines != null){
            lines.clear();
            lines = budget.fetchLines(side);
        }
        this.fireTableDataChanged(); // Tells our table the data has changed
    }
    
    public Boolean isNew(int row) 
    {
        // Apply Conditions for when a row is new
        int id = -1;
        
        id  = Integer.parseInt(expendituresData[0]);
        
        return id % 2 == 0;
    }
    
    public void setBudget(Budget b){
        budget = b;
    }
    
    public void setSide(Side s){
        side = s;
    }
    
}
