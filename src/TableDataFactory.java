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

public class TableDataFactory extends SwingWorker<ArrayList<TableData>, Void>
{

    private FactoryDoneListener listener;
    private String scheduleUrl;

    public TableDataFactory(String scheduleUrl ,FactoryDoneListener listner)
    {
        this.listener = listner;
        this.scheduleUrl = scheduleUrl;
    }

    @Override
    protected ArrayList<TableData> doInBackground() throws Exception
    {
        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            TableDataHandler tableDataHandler = new TableDataHandler();

            URL url = new URL(scheduleUrl);
            InputSource input = new InputSource(url.openStream());

            saxParser.parse(input, tableDataHandler);
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
            ArrayList<TableData> data = get();
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
