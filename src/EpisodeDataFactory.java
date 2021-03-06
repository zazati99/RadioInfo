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

    /**
     * Constructor for EpisodeDataFactory
     * @param data Data for the channel to get the episodes from
     * @param listner FactoryDoneListener that will be called when the episodes are loaded
     */
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

            String urlString =
                    channelData.getScheduleUrl() + "&pagination=false";

            URL url = new URL(urlString);
            InputSource input = new InputSource(url.openStream());

            saxParser.parse(input, tableDataHandler);

            ArrayList<EpisodeData> data = tableDataHandler.getEpisodeData();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            for (int i = 0; i < data.size(); i++)
            {
                EpisodeData tableData = data.get(i);

                if (tableData.getStartTime().isBefore(LocalDateTime.now()))
                {
                    tableData.setEpisodeStarted(true);
                    if (tableData.getEndTime().isAfter(LocalDateTime.now()))
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
            listener.factoryFailed(e.getMessage());
        }
        catch(SAXException e)
        {
            listener.factoryFailed(e.getMessage());
        }
        catch(MalformedURLException e)
        {
            listener.factoryFailed("The URL was malformed "
                    + e.getMessage());
        }
        catch(IOException e)
        {
            listener.factoryFailed("File could not be found "
                    + e.getMessage());
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
            listener.factoryFailed(e.getMessage());
        }
        catch (ExecutionException e)
        {
            listener.factoryFailed(e.getMessage());
        }
        catch (NullPointerException e)
        {
            listener.factoryFailed("Episode data was null");
        }
    }
}
