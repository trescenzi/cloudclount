package cloud.count;

import badm.Budget;
import badm.Line;
import badm.Subline;
import cc.test.bridge.BridgeConstants.Side;
import cc.test.bridge.LineInterface;
import cc.test.bridge.SublineInterface;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class UpdateLineTableModel extends AbstractTableModel
{
    Line line; 
    ArrayList<SublineInterface> sublines;
    Side side;
    private String[] columnNames = 
    {
        "Number", 
        "Name", 
        "Transaction 1", 
        "Transaction 2",
        "Subtotal"
    };

    private String[] expendituresData = 
    {
        "1",
        "24 / 12 / 2011",
        "fred",
        "100",
        "Ordinary Collections"
    };    

    @Override
    public String getColumnName(int col) 
    {
        return columnNames[col];
    }
    
    @Override
    public int getRowCount() 
    {
        if(sublines == null && line != null)
        {
            sublines = line.fetchSublines();
        }
        else if(line == null)
        {
            return 0;
        }
        System.out.println("NUMBER OF SUBLINES:"+sublines.size());
        return sublines.size();
    }

    @Override
    public int getColumnCount() 
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) 
    {
        Subline subline = (Subline) sublines.get(row);
        if(column == 0)
            return subline.getNumber();
        else if(column == 1)
            return subline.getName();
        else if(column == 4){
            Double d = subline.getTotal();
            if(d != null)
                return 0;
            return d;
        }
        return expendituresData[column];
    }
    
    public void refresh()
    {
        if(sublines != null){
            sublines.clear();
            sublines = line.fetchSublines();
        }
        this.fireTableDataChanged(); // Tells our table the data has changed
    }
    
    public void setLine(Line l){
        line = l;
    }
    
}
