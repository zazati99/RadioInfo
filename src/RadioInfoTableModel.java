import javax.swing.table.DefaultTableModel;

/**
 * override TableModel that where cells are not editable
 */
public class RadioInfoTableModel extends DefaultTableModel
{

    @Override
    public boolean isCellEditable(int row, int column)
    {
        // No cell should be editable
        return false;
    }

}
