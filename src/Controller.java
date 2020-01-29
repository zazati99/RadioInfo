import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller extends SwingWorker<Boolean, ArrayList<TableData>>
{
    /**
     * is the program running
     */
    boolean programRunning;

    /**
     * The GUI
     */
    GUI gui;

    public Controller()
    {
        programRunning = true;

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run() {

                gui = new GUI();
                gui.show();

            }
        });
    }

    @Override
    protected Boolean doInBackground() throws Exception
    {
        /*
        TableDataFactory factory = new TableDataFactory();
        publish(factory.parseEpisodeData("http://api.sr.se/v2/scheduledepisodes?channelid=132"));

        while(programRunning)
        {

            Thread.sleep(1000 * 5);
        }

        return null;
        */
        return null;
    }

    @Override
    protected void process(List<ArrayList<TableData>> list)
    {
        ArrayList<TableData> data = list.get(list.size() - 1);
        gui.episodeData = data;

        for (int i = 0; i < data.size(); i++)
        {
            gui.addEpisodeRow(data.get(i));
        }
    }
}
