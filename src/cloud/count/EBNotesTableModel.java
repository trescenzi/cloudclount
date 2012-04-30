package cloud.count;

import badm.Budget;
import badm.Note;
import cc.test.bridge.BridgeConstants;
import cc.test.bridge.NoteInterface;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class EBNotesTableModel extends AbstractTableModel
{
    
    Budget budget; 
    ArrayList<NoteInterface> notes;
    BridgeConstants.Side side;
    
    
    private String[] columnNames = 
    {
        "Updated", 
        "By", 
        "Note"

    };

    private String[] notesData = 
    {
        "24 / 12 / 2011",
        "Bob",
        "Updated the budget for 2012"
    };    

    @Override
    public String getColumnName(int col) 
    {
        return columnNames[col];
    }
    
    @Override
    public int getRowCount() 
    {
        if(notes == null && budget != null)
        {
            notes = budget.fetchNotes();
        }
        else if(budget == null)
        {
            return 0;
        }
        System.out.println("NUMBER OF NOTES:"+notes.size()+side);
        return notes.size();
    }

    @Override
    public int getColumnCount() 
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) 
    {
        Note note = (Note) notes.get(row);
        if(column == 0)
            ;//return note.getDate();
        else if(column == 1)
            ;//return note.getAuthor();
        else if(column == 2)
            return note.getText();
        
        return notesData[column];
    }
    
    public void refresh()
    {
        if(notes != null){
            notes.clear();
            notes = budget.fetchNotes();
        }
        this.fireTableDataChanged(); // Tells our table the data has changed
    }
    
    public Boolean isNew(int row) 
    {
        // Apply Conditions for when a row is new
        int id = -1;
        
        id  = Integer.parseInt(notesData[0]);
        
        return id % 2 == 0;
    }
    
    public void setBudget(Budget b){
        budget = b;
    }
    
    public void setSide(BridgeConstants.Side s){
        side = s;
    }
}
