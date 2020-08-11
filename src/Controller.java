import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The Controller object for this program, it communicates with the
 * GUI and the model classes.
 */
public class Controller implements ActionListener
{

    /**
     * The gui
     */
    GUI gui;

    /**
     * Timer that schedules updates
     */
    Timer timer;

    /**
     * The List of all channeldata
     */
    ArrayList<ChannelData> channelData;

    /**
     * The episode data for the current channel
     */
    ArrayList<EpisodeData> episodeData;

    /**
     * The current channeldata
     */
    ChannelData currentChannel;

    /**
     * Constructor for Controller, Creates timer and GUI
     */
    public Controller()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Controller.this.gui = new GUI();
                gui.show();


                // Selection listener for changing the info panel
                gui.addListSelectionListener(new ListSelectionListener()
                {
                    @Override
                    public void valueChanged(
                            ListSelectionEvent listSelectionEvent)
                    {
                        if (!gui.isChangingChannel())
                        {
                            if (gui.getSelectedEpisode() !=
                                    gui.getLastInfoShown())
                            {
                                gui.showInfoPanel(episodeData.get(
                                        gui.getSelectedEpisode()));
                            }
                        }
                    }
                });


                // Actionlistener for updating the schedule
                gui.addUpdateActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        if (currentChannel != null)
                        {
                            goToChannel(currentChannel);
                        }
                    }
                });


                // Action listener for exiting the program
                gui.addExitActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        System.exit(0);
                    }
                });


                // Gets the channelData
                ChannelDataFactory factory = new ChannelDataFactory(
                        new FactoryDoneListener()
                        {
                            @Override
                            public void factoryDone(Object returnValue)
                            {
                                Controller.this.channelData =
                                        (ArrayList<ChannelData>)returnValue;

                                Controller.this.addChannelButtons();
                                gui.setGUIEnabled(true);
                            }
                        });
                factory.execute();

                Controller.this.timer =  new Timer(1000 * 60 * 60,
                        Controller.this);

                timer.setRepeats(true);
                timer.start();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if (currentChannel != null)
        {
            goToChannel(currentChannel);
        }
    }

    /**
     * Adds channel buttons
     */
    private void addChannelButtons()
    {
        for (int i = 0; i < channelData.size(); i++)
        {
            ChannelData channel = channelData.get(i);
            gui.addChannel(channel.getTitle(), new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Controller.this.goToChannel(channel);
                }
            });
        }
    }

    /**
     * Creates an array of EpisodeData from a channel and changes the gui
     * @param data The ChannelData to use when creating
     */
    private void goToChannel(ChannelData data)
    {
        // Check if the channel has an availible Schedule
        if (data.getScheduleUrl() != null)
        {
            gui.changingChannel = true;
            gui.setGUIEnabled(false);

            EpisodeDataFactory factory = new EpisodeDataFactory(data,
                    new FactoryDoneListener() {
                        @Override
                        public void factoryDone(Object returnValue) {
                            ArrayList<EpisodeData> episodeData =
                                    (ArrayList<EpisodeData>) returnValue;

                            gui.changeChannel(episodeData);

                            Controller.this.episodeData = episodeData;
                            gui.setGUIEnabled(true);
                        }
                    });

            factory.execute();
            currentChannel = data;
        }
        else
        {
            gui.displayMessage("Det finns ingen tillgänglig tablå för den här kanalen");
        }
    }
}
