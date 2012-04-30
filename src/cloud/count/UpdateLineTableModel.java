package cloud.count;

import badm.Budget;
import badm.Line;
import badm.Subline;
import cc.test.bridge.BridgeConstants.Side;
import cc.test.bridge.LineInterface;
import cc.test.bridge.SublineInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import org.workplicity.util.DateFormatter;

public class UpdateLineTableModel extends AbstractTableModel {

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
    private String[] expendituresData = {
        "1",
        "24 / 12 / 2011",
        "fred",
        "100",
        "Ordinary Collections",
        "sex",
        "sex2",
        "sex3"
    };

    @Override
    public String getColumnName(int col) {
        return columnNames.get(col);
    }

    public void buildColNames() {
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
        if (column == 0) {
            return subline.getNumber();
        } else if (column == 1) {
            return subline.getName();
        } else if (column == 2) {
            Double d = subline.getTotal();
            if (d != null) {
                return 0;
            }
            return d;
        }
        return expendituresData[column];
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
}
