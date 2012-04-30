package cloud.count;

import badm.Budget;
import badm.Audit;
import cc.test.bridge.BridgeConstants;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import org.workplicity.util.DateFormatter;

public class EBAuditTrailTableModel extends AbstractTableModel
{
    Budget budget; 
    ArrayList<Audit> audits;
    BridgeConstants.Side side;
    
    private String[] columnNames = 
    {
        "Updated", 
        "By", 
        "Action"

    };

    private String[] auditTrailData = 
    {
        "24 / 12 / 2011",
        "Bob",
        "Uploaded attachment JoeEstimate.doc"
    };    

    @Override
    public String getColumnName(int col) 
    {
        return columnNames[col];
    }
    
    @Override
    public int getRowCount() 
    {
        if(audits == null && budget != null)
        {
            audits = budget.fetchAudits();
        }
        else if(budget == null)
        {
            return 0;
        }
        System.out.println("NUMBER OF NOTES:"+audits.size()+side);
        return audits.size();
    }

    @Override
    public int getColumnCount() 
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) 
    {
        Audit audit = (Audit) audits.get(row);
        if(column == 0)
            return DateFormatter.toString(new Date(audit.getCreateTime()));
        else if(column == 1)
            ;//return line.getAuthor();
        else if(column == 2)
            return audit.getDescription();
        
        return auditTrailData[column];
    }
    
    public void refresh()
    {
        if(audits != null){
            audits.clear();
            audits = budget.fetchAudits();
        }
        this.fireTableDataChanged(); // Tells our table the data has changed
    }
    
    public Boolean isNew(int row) 
    {
        // Apply Conditions for when a row is new
        int id = -1;
        
        id  = Integer.parseInt(auditTrailData[0]);
        
        return id % 2 == 0;
    }
    
    public void setBudget(Budget b){
        budget = b;
    }
    
    public void setSide(BridgeConstants.Side s){
        side = s;
    }
}
