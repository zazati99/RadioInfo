import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Overide DefaultTableCellRenderer so that it returns a component
 * instead of an Object
 */
public class RadioInfoTableRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o,
                                                   boolean b, boolean b1,
                                                   int i, int i1)
    {
        return(Component)o;
    }
}
