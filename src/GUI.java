import com.sun.xml.internal.fastinfoset.util.StringArray;

import javax.imageio.ImageIO;
import javax.naming.event.ObjectChangeListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GUI
{
    /**
     * JFrame for this GUI
     */
    private JFrame frame;

    /**
     * Table showing programs
     */
    private JTable programTable;

    /**
     * Table model for programme table
     */
    private RadioInfoTableModel tableModel;

    /**
     * The scrollpane for the current table
     */
    private JScrollPane mainView;

    /**
     * The menu bar
     */
    private JMenuBar menuBar;

    /**
     * Shows information about a selected programme
     */
    private JPanel infoPanel;

    /**
     * JMenu containing all the channles
     */
    private JMenu channels;

    public GUI() {
        frame = new JFrame("Radio Info");

        frame.setLayout(new BorderLayout());

        buildMenuBar();

        buildChannelProgramTable();

        mainView = new JScrollPane(programTable);
        frame.add(mainView, BorderLayout.CENTER);

        frame.setPreferredSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
    }

    /**
     * Shows the GUI
     */
    public void show()
    {
        frame.setVisible(true);
    }

    /**
     * Build the menu bar
     */
    public void buildMenuBar()
    {
        menuBar = new JMenuBar();

        JMenu menu = new JMenu("RadioInfo");
        menuBar.add(menu);

        JMenuItem updateItem = new JMenuItem("Uppdatera");
        menu.add(updateItem);

        JMenuItem exitItem = new JMenuItem("Avsluta");
        menu.add(exitItem);

        channels = new JMenu("Kanaler");
        menuBar.add(channels);

        frame.add(menuBar, BorderLayout.NORTH);
    }


    /**
     * Builds the table of programs
     */
    public void buildChannelProgramTable()
    {
        tableModel = new RadioInfoTableModel();
        programTable = new JTable(tableModel);

        tableModel.addColumn("Titel");
        tableModel.addColumn("Tid");

        programTable.getColumn("Titel").
                setCellRenderer(new RadioInfoTableRenderer());

        programTable.getColumn("Tid").
                setCellRenderer(new RadioInfoTableRenderer());

        programTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {

                System.out.println(programTable.getSelectedRow());

            }
        });

    }

    public void addProgrammeRow(TableData data){

        JLabel title = new JLabel(data.getTitle(), JLabel.CENTER);
        JLabel time = new JLabel(data.getTime(), JLabel.CENTER);

        Object[] rowData = {title, time};

        tableModel.addRow(rowData);
    }

}
