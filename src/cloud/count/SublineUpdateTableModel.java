/**
 * The table model for the table in which you insert sublines into 
 * lines.
 */
package cloud.count;

import badm.Line;
import badm.Subline;
import cc.test.bridge.BridgeConstants;
import cc.test.bridge.SublineInterface;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class SublineUpdateTableModel extends AbstractTableModel
{
    private Line line;
    private ArrayList<SublineInterface> sublines;
    private String[] columnNames = 
    {
        "Subline #", 
        "Name", 
        "Budget"
    };
    
    private String[] incomeData = 
    {
        "100.1",
        "N/A",
        "0.00"
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
        if(column == 0){
            return subline.getNumber();
        }
        else if(column == 1){
            return subline.getName();
        }
        else if(column == 2){
            return subline.getGoal();
        }
        return null;
    }   
    
    public void refresh()
    {
        sublines.clear();//clear any mess out
        sublines = line.fetchSublines();//get the new lines
        this.fireTableDataChanged(); // Tells our table the data has changed
    }
    
    public void setLine(Line line){
        this.line = line;
    }
}
