import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Creates channel data by parsing XML from a URL
 * on a SwingWorker thread
 */
public class ChannelDataFactory
        extends SwingWorker<ArrayList<ChannelData>, Void>
{
    /**
     * URL for the channels
     */
    private static final String CHANNELSURL =
            "http://api.sr.se/api/v2/channels/?pagination=false";

    /**
     * Listener that will be called when this is done
     * creating the ChannelData object
     */
    FactoryDoneListener listener;

    public ChannelDataFactory(FactoryDoneListener listner)
    {
        this.listener = listner;
    }

    @Override
    protected ArrayList<ChannelData> doInBackground() throws Exception
    {
        try
        {
            // Creates SAXParser and tries to parse the XML from the channel URL
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ChannelDataHandler channelDataHandler = new ChannelDataHandler();

            URL url = new URL(CHANNELSURL);
            InputSource input = new InputSource(url.openStream());

            saxParser.parse(input, channelDataHandler);
            return channelDataHandler.getChannelData();
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
            // try to get the data and send it to the listener
            ArrayList<ChannelData> data = get();
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
            listener.factoryFailed("Channel data was null");
        }
    }


}
