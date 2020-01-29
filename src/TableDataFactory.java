import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TableDataFactory
{
    public TableDataFactory()
    {

    }

    public ArrayList<TableData> parseEpisodeData(String scheduleUrl)
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
}
