import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Creates an EpisodeData array by parsing XML from a URL
 * on a SwingWorker thread
 */
public class EpisodeDataFactory extends SwingWorker<ArrayList<EpisodeData>, Void>
{

    /**
     * Listener that will be called when this is done producing EpisodeData
     */
    private FactoryDoneListener listener;

    /**
     * The cahnnel to create episode data from
     */
    private ChannelData channelData;

    public EpisodeDataFactory(ChannelData data , FactoryDoneListener listner)
    {
        this.listener = listner;
        this.channelData = data;
    }

    @Override
    protected ArrayList<EpisodeData> doInBackground() throws Exception
    {
        try
        {
            // Parse the XML from the channel data schedule URL
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            EpisodeDataHandler tableDataHandler =
                    new EpisodeDataHandler(channelData.getImage());

            URL url = new URL(channelData.getScheduleUrl());
            InputSource input = new InputSource(url.openStream());

            saxParser.parse(input, tableDataHandler);

            ArrayList<EpisodeData> data = tableDataHandler.getEpisodeData();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            for (int i = 0; i < data.size(); i++)
            {
                EpisodeData tableData = data.get(i);

                if (tableData.getStartTime().isAfter(LocalDateTime.now()))
                {
                    tableData.setEpisodeStarted(true);
                    if (tableData.getEndTime().isBefore(LocalDateTime.now()))
                    {
                        tableData.setEpisodeRunning(true);
                    }

                }

                String time = tableData.getStartTime().format(formatter);
                time += " - ";
                time += tableData.getEndTime().format(formatter);

                tableData.setTime(time);

            }

            return tableDataHandler.getEpisodeData();
        }
        catch(ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch(SAXException e)
        {
            e.printStackTrace();
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void done()
    {
        try
        {
            ArrayList<EpisodeData> data = get();
            listener.factoryDone(data);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }
}
