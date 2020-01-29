import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GUI
{
    /**
     * JFrame for this GUI
     */
    private JFrame frame;

    /**
     * Table showing programs
     */
    private JTable episodeTable;

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
    private EpisodeInfoPanel infoPanel;

    /**
     * JMenu containing all the channles
     */
    private JMenu channels;

    /**
     * The index of the last info shown
     */
    int lastInfoShown;

    ArrayList<TableData> episodeData;

    public GUI() {
        frame = new JFrame("Radio Info");
        frame.setLayout(new BorderLayout());

        buildMenuBar();
        buildChannelProgramTable();

        mainView = new JScrollPane(episodeTable);
        frame.add(mainView, BorderLayout.CENTER);

        infoPanel = new EpisodeInfoPanel();
        infoPanel.setVisible(false);
        frame.add(infoPanel, BorderLayout.SOUTH);

        frame.setPreferredSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lastInfoShown = -1;

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

    public void addChannel(String name, ActionListener listener)
    {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(listener);
        channels.add(item);
    }


    /**
     * Builds the table of programs
     */
    public void buildChannelProgramTable()
    {
        tableModel = new RadioInfoTableModel();
        episodeTable = new JTable(tableModel);

        tableModel.addColumn("Titel");
        tableModel.addColumn("Tid");

        episodeTable.getColumn("Titel").
                setCellRenderer(new RadioInfoTableRenderer());

        episodeTable.getColumn("Tid").
                setCellRenderer(new RadioInfoTableRenderer());

        episodeTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (lastInfoShown != episodeTable.getSelectedRow())
                {
                    System.out.println(episodeTable.getSelectedRow());

                    infoPanel.setData(episodeData.get(episodeTable.getSelectedRow()));
                    infoPanel.setVisible(true);

                    lastInfoShown = episodeTable.getSelectedRow();
                }
            }
        });
    }

    /**
     * Add a programme to the table
     * @param data The data for the episode
     */
    public void addEpisodeRow(TableData data){

        JLabel title = new JLabel(data.getTitle(), JLabel.CENTER);

        String timeString = getHourMinute(data.getStartTime()) + " - " +
                            getHourMinute(data.getEndTime());

        JLabel time = new JLabel(timeString, JLabel.CENTER);

        Object[] rowData = {title, time};

        tableModel.addRow(rowData);
    }

    /**
     * Adds a list of episodes to the table
     * @param data The data for the episodes
     */
    public void addEpisodeRows(ArrayList<TableData> data){

        for (int i = 0; i < tableModel.getRowCount(); i++)
        {
            tableModel.removeRow(i);
        }

        for (int i = 0; i < data.size(); i++)
        {
            addEpisodeRow(data.get(i));
        }

        episodeData = data;
    }


    /**
     * Converts a localDateTime object to a String consisting of the
     * hour and minute value
     * @param time The LocalDataTime to convert
     * @return The String containing the parsed time
     */
    public String getHourMinute(LocalDateTime time)
    {
        return time.getHour() + ":" + time.getMinute();
    }
}
