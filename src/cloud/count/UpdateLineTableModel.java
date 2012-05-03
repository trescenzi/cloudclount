/**
 * The table model where most of the work goes on.
 * Here the transactions are edited which updates the whole
 * BADM with the proper values alone with the rest of the application.
 */
package cloud.count;

import badm.Budget;
import badm.Line;
import badm.Subline;
import badm.Transaction;
import cc.test.bridge.BridgeConstants.Side;
import cc.test.bridge.LineInterface;
import cc.test.bridge.SublineInterface;
import cc.test.bridge.TransactionInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import org.workplicity.util.DateFormatter;

public class UpdateLineTableModel extends AbstractTableModel {

    /**
     * 
     * @param line The line that you are editing 
     */
    public UpdateLineTableModel(Line line) {
        super();
        this.line = line;
        buildColNames();
    }
    
    Line line;
    final static long timeInADay = 86400000;
    long lineCreateTime;
    ArrayList<SublineInterface> sublines;
    private ArrayList<String> columnNames = new ArrayList();

    @Override
    public String getColumnName(int col) {
        return columnNames.get(col);
    }
    
    /**
     * The column names are built here. They depend upon the roll
     * of the budget along with the current date. For example if the 
     * roll is weekly and it has been 15 weeks since the creation of 
     * the budget then 15 columns will be created so that the proper 
     * number of transactions can be edited.
     */
    private void buildColNames() {
        columnNames.add("Number");
        columnNames.add("Name");
        columnNames.add("Subtotal");
        lineCreateTime = line.getCreateTime();
        if (line != null) {
            Long time = lineCreateTime;
            Long currentTime = Calendar.getInstance().getTimeInMillis();
            int days = (int) Math.abs((currentTime - time) / timeInADay);
            System.out.println("SAYS" + days);
            int tran = Budget.find(line.getBudgetId()).getNumTransactions();
            if (tran == 52) {
                weekly(days);
            } else if (tran == 12) {
                monthly(days, time);
            } else if (tran == 6) {
                bimonthly(days, time);
            } else {
                yearly(days, time);
            }
        }
    }

    /**
     * Builds the columns for the weekly roll.
     * @param days The number of days since the creation of the budget
     */
    private void weekly(int days) {
        if (days < 7) {
            columnNames.add(DateFormatter.toString(new Date(lineCreateTime)));
        } else if (days < 14) {
            columnNames.add(DateFormatter.toString(new Date(lineCreateTime)));
            columnNames.add(DateFormatter.toString(new Date(line.getUpdateTime()
                    + (7 * timeInADay))));
        } else if (days > 13) {
            for (int i = 0; i <= (days % 7) + 1; i += 1) {
                columnNames.add(DateFormatter.toString(new Date(line.getUpdateTime()
                        + (i * 7 * timeInADay))));
            }
        }
    }
    
    //the following three methods wish they were written in scala:
    /**
     * A recursive method that builds the column names if the roll is monthly
     * @param days The number of says since the creation
     * @param current The "current" day. Changes as the recursion gets deeper
     */
    private void monthly(int days, long current) {
        int month = new Date(current).getMonth();
        if (days > 0) {
            if (days <= 30 && (month == Calendar.APRIL
                    || month == Calendar.JUNE
                    || month == Calendar.SEPTEMBER
                    || month == Calendar.NOVEMBER)) {
                columnNames.add(DateFormatter.toString(new Date(current)));
                monthly(days - 30, current + (30 * timeInADay));
            }
            if (days <= 29
                    && month == Calendar.FEBRUARY) {
                columnNames.add(DateFormatter.toString(new Date(current)));
                int year = new Date(current).getYear();
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                    monthly(days - 29, current + (29 * timeInADay));
                }
                monthly(days - 28, current + (28 * timeInADay));
            }
            //otherwise it is a 31 day month
            columnNames.add(DateFormatter.toString(new Date(current)));
            monthly(days - 31, current + (31 * timeInADay));

        }
    }

    /**
     * Same as month but for the roll of bimonthly
     * @param days Number of days since the creation of the budget
     * @param current The "current date" changes as the recursion gets deeper
     */
    private void bimonthly(int days, long current) {
        int month = new Date(current).getMonth();
        if (days > 0) {
            if (days <= 61 && (month != Calendar.JULY
                    && month != Calendar.FEBRUARY)) {
                columnNames.add(DateFormatter.toString(new Date(current)));
                bimonthly(days - 61, current + (61 * timeInADay));
            }
            if (days <= 60
                    && month == Calendar.FEBRUARY) {
                columnNames.add(DateFormatter.toString(new Date(current)));
                int year = new Date(current).getYear();
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                    bimonthly(days - 60, current + (60 * timeInADay));
                }
                bimonthly(days - 59, current + (59 * timeInADay));
            }
            //its July! at least I really hope it is. if not oh well screw your budget
            columnNames.add(DateFormatter.toString(new Date(current)));
            bimonthly(days - 62, current + (62 * timeInADay));
        }
    }
    
    /**
     * Same as monthly and bimonthly but for a yearly roll
     * @param days Number of days since the creation of the budget
     * @param current The "current date" changes as the recursion gets deeper
     */
    private void yearly(int days, long current) {
        int year = new Date(current).getYear();
        if (days > 0) {
            columnNames.add(DateFormatter.toString(new Date(current)));

            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                yearly(days - 366, current + (366 * timeInADay));
            }
            yearly(days - 365, current + (365 * timeInADay));
        }
    }

    @Override
    public int getRowCount() {
        if (sublines == null && line != null) {
            sublines = line.fetchSublines();
        } else if (line == null) {
            return 0;
        }
        return sublines.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        Subline subline = (Subline) sublines.get(row);
        ArrayList<Transaction> trans = sort(subline);
        if (column == 0) {
            return subline.getNumber();
        } 
        else if (column == 1) {
            return subline.getName();
        } 
        else if (column == 2) {
            return subline.getTotal();
        }
        else if (column < trans.size()){
            return trans.get(column).getAmount();
        }
        return 0.0;
    }

    public void refresh() {
        if (sublines != null) {
            sublines.clear();
            sublines = line.fetchSublines();
        }
        this.fireTableDataChanged(); // Tells our table the data has changed
    }

    public void setLine(Line l) {
        line = l;
    }
    
    @Override
    public boolean isCellEditable(int row, int column){
        if(column > 2){
            return true;
        }
        return false;
    }
    
    @Override
    public void fireTableCellUpdated(int row, int column){
        refresh();
    }
    
    /**
     * Used to allow for the direct editing of the cells that contain
     * the transaction information
     * @param o The new object in the cell
     * @param row The row of the cell
     * @param column  The column of the cell
     */
    @Override
    public void setValueAt(Object o, int row, int column){
            if(column > 2){
            Subline subline = (Subline) sublines.get(row);
            ArrayList<Transaction> sorted = sort(subline);
            Collections.sort(sorted);
            try{
                Transaction tran = sorted.get(column);
                tran.setAmount(Double.parseDouble((String) o));
                System.out.println("Please1:"+tran.getAmount());
                tran.commit();
            }catch(Exception e){
                Transaction tran = (Transaction) subline.createTransaction();
                tran.commit();
                tran.setAmount(Double.parseDouble((String) o));
                System.out.println("Please2:"+tran.getAmount());
                tran.commit();
            }
        }
    }
    
    /**
     * Sorts the Transactions of the Subline and returns them. 
     * @param su The subline whose transactions you want
     * @return The sorted list of transactions.
     */
    private ArrayList<Transaction> sort(Subline su){
        ArrayList<TransactionInterface> trans = su.fetchTransactions();

        //this is why the bridge can be very annoying 
        ArrayList<Transaction> sorted = new ArrayList();
        for (TransactionInterface t : trans) {
            sorted.add((Transaction) t);
        }
        return sorted;
    }
    
}
