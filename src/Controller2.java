import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller2 implements ActionListener
{

    GUI gui;

    Timer timer;

    ArrayList<ChannelData> channelData;

    ChannelData currentChannel;

    public Controller2()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Controller2.this.gui = new GUI();
                gui.show();

                Controller2.this.timer =  new Timer(1000,
                        Controller2.this);

                timer.setRepeats(false);
                timer.start();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {

        ChannelDataFactory factory = new ChannelDataFactory(
                new FactoryDoneListener()
        {
            @Override
            public void factoryDone(Object returnValue)
            {
                Controller2.this.channelData =
                        (ArrayList<ChannelData>)returnValue;

                Controller2.this.addChannelButtons();
            }
        });

        factory.execute();
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
                public void actionPerformed(ActionEvent actionEvent)
                {
                    goToChannel(channel);
                }
            });
        }
    }

    private void goToChannel(ChannelData data)
    {
        TableDataFactory factory = new TableDataFactory(data.getScheduleUrl(),
                new FactoryDoneListener()
        {
            @Override
            public void factoryDone(Object returnValue)
            {
                ArrayList<TableData> episodeData =
                        (ArrayList<TableData>)returnValue;

                gui.addEpisodeRows(episodeData);
            }
        });

        factory.execute();
        currentChannel = data;
    }
}
