import com.sun.xml.internal.fastinfoset.util.StringArray;

import javax.imageio.ImageIO;
import javax.naming.event.ObjectChangeListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

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
     * Table showing channels
     */
    private JTable channelsTable;

    /**
     * Table showing programs
     */
    private JTable programTable;

    /**
     * The scrollpane for the current table
     */
    private JScrollPane mainView;

    private JMenuBar menuBar;

    public GUI() {
        frame = new JFrame("Radio Info");

        frame.setLayout(new BorderLayout());

        buildMenuBar();

        buildChannelsTable();
        buildChannelProgramTable();

        mainView = new JScrollPane(channelsTable);
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

        JMenuItem channelsItem = new JMenuItem("Kanaler");
        menu.add(channelsItem);

        JMenuItem updateItem = new JMenuItem("Uppdatera");
        menu.add(updateItem);

        JMenuItem exitItem = new JMenuItem("Avsluta");
        menu.add(exitItem);

        menuBar.setVisible(true);
        menu.setVisible(true);
        frame.add(menuBar, BorderLayout.NORTH);
    }

    /**
     * Builds the table of channels
     */
    public void buildChannelsTable() {
        channelsTable = new JTable();

        RadioInfoTableModel model = new RadioInfoTableModel();
        channelsTable.setModel(model);

        model.addColumn("Bild");
        model.addColumn("Namn och beskrivning");

        channelsTable.getColumn("Bild").setCellRenderer(new ChannelsTableCellRenderer());
        channelsTable.setCellSelectionEnabled(false);

        try
        {
            URL url = new URL("https://static-cdn.sr.se/sida/images/163/2186754_512_512.jpg?preset=api-default-square");
            BufferedImage image = ImageIO.read(url);
            
            ImageIcon bild = new ImageIcon(image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH));
            JLabel label = new JLabel();
            label.setIcon(bild);

            Object[] meme = {label, "awdawd"};
            model.addRow(meme);
        }
        catch(IOException e){}

    }

    /**
     * Builds the table of programs
     */
    public void buildChannelProgramTable()
    {
        String[] programInfo = {"Bild", "Namn och beskrivninv", "Tid"};
        Object[][] items = new Object[0][0];
        programTable = new JTable(items, programInfo);
    }

    /**
     * Switch the current view to the program table
     */
    public void switchToProgramTable()
    {
        mainView.setViewportView(programTable);
    }

    /**
     * Switch the current view to the channels table
     */
    public void switchToChannelsTable()
    {
        mainView.setViewportView(channelsTable);
    }

}
