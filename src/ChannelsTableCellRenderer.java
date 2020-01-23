import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ChannelsTableCellRenderer implements TableCellRenderer
{

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {

        TableColumn tc = jTable.getColumn("Bild");
        tc.setMinWidth(150);
        tc.setMaxWidth(150);
        jTable.setRowHeight(150);

        return (Component) o;
    }
}
