import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class RadioInfoTableModel extends DefaultTableModel
{
    @Override
    public boolean isCellEditable(int row, int column)
    {
        // No cell should be editable
        return false;
    }
}
