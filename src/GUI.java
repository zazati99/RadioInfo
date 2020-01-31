import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GUI for RadioInfo
 */
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
     * Update button
     */
    private JMenuItem updateButton;

    /**
     * Exit button
     */
    private JMenuItem exitButton;

    /**
     * The index of the last info shown
     */
    int lastInfoShown;

    /**
     * boolean used to see if the channel is currently being changed
     */
    public boolean changingChannel;

    public GUI()
    {
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
        changingChannel = false;

        setGUIEnabled(false);

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

        updateButton = new JMenuItem("Uppdatera");
        menu.add(updateButton);

        exitButton = new JMenuItem("Avsluta");
        menu.add(exitButton);

        channels = new JMenu("Kanaler");
        menuBar.add(channels);

        frame.add(menuBar, BorderLayout.NORTH);
    }

    /**
     * Adds a channel to the JMenu
     * @param name The channel name
     * @param listener The Actionlistner for the menu item
     */
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

    }

    /**
     * Shows the info panel for an episode
     * @param data The episode data
     */
    public void showInfoPanel(EpisodeData data)
    {
        infoPanel.setData(data);
        infoPanel.setVisible(true);

        changeTitleColor(lastInfoShown, Color.DARK_GRAY);
        lastInfoShown = episodeTable.getSelectedRow();
        changeTitleColor(lastInfoShown, Color.LIGHT_GRAY);
    }

    /**
     * Changes the color of the selected row
     */
    public void changeTitleColor(int row, Color color)
    {
        if (row >= 0 && row < tableModel.getRowCount())
        {
            JLabel label = (JLabel) tableModel.getValueAt(row, 0);
            label.setForeground(color);
        }
    }

    /**
     * Add a programme to the table
     * @param data The data for the episode
     */
    public void addEpisodeRow(EpisodeData data)
    {

        JLabel title = new JLabel(data.getTitle(), JLabel.CENTER);

        JLabel time = new JLabel(data.getTime(), JLabel.CENTER);

        if (data.isEpisodeStarted())
        {
            time.setForeground(data.isEpisodeRunning() ?
                    Color.GREEN : Color.RED);
        }

        Object[] rowData = {title, time};
        tableModel.addRow(rowData);
    }

    /**
     * Adds a list of episodes to the table
     * @param data The data for the episodes
     */
    public void changeChannel(ArrayList<EpisodeData> data)
    {

        infoPanel.setVisible(false);

        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--)
        {
            tableModel.removeRow(i);
        }

        for (int i = 0; i < data.size(); i++)
        {
            addEpisodeRow(data.get(i));
        }

        changingChannel = false;
        lastInfoShown = -1;
    }

    /**
     * Enables or disables the gui
     * @param enabled the status
     */
    public void setGUIEnabled(boolean enabled)
    {
        channels.setEnabled(enabled);
        updateButton.setEnabled(enabled);
    }

    /**
     * Adds a selectionlistener to the episode table
     * @param listener
     */
    public void addListSelectionListener(ListSelectionListener listener)
    {
        episodeTable.getSelectionModel().addListSelectionListener(listener);
    }

    /**
     * Adds actionlistener to the exit button
     * @param listener the actionlistener
     */
    public void addExitActionListener(ActionListener listener)
    {
        exitButton.addActionListener(listener);
    }

    /**
     * Adds actionlistener to the update button
     * @param listener the actionlistener
     */
    public void addUpdateActionListener(ActionListener listener)
    {
        updateButton.addActionListener(listener);
    }

    /**
     * Gets the selected row in the epsidoeTable
     * @return The index of the row
     */
    public int getSelectedEpisode()
    {
        return episodeTable.getSelectedRow();
    }

    /**
     * Checks if the channel is currently being changed
     * @return true if it's being changed, false if it isn't
     */
    public boolean isChangingChannel()
    {
        return changingChannel;
    }

    /**
     * Gets the index of the episode info that was shown
     * @return The index of the last info
     */
    public int getLastInfoShown()
    {
        return lastInfoShown;
    }
}
