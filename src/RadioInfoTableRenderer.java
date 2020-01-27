import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class RadioInfoTableRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o,
                                                   boolean b, boolean b1,
                                                   int i, int i1)
    {
        return(Component)o;
    }
}
