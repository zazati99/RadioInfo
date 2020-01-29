import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ChannelDataFactory
        extends SwingWorker<ArrayList<ChannelData>, Void>
{
    private static final String CHANNELSURL =
            "http://api.sr.se/api/v2/channels/";

    SwingWorkerDoneListener listener;

    public ChannelDataFactory(SwingWorkerDoneListener listner)
    {
        this.listener = listner;
    }

    @Override
    protected ArrayList<ChannelData> doInBackground() throws Exception
    {
        try
        {
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
            ArrayList<ChannelData> data = get();
            listener.SwingWorkerDone(data);
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
