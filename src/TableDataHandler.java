import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TableDataHandler extends DefaultHandler
{
    private static final String SCHEDULEDEPISODE = "scheduledepisode";

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String STARTTIME = "starttimeutc";
    private static final String ENDTIME = "endtimeutc";
    private static final String IMAGE = "imageurl";

    private ArrayList<TableData> episodeData;
    private String elementValue;

    Image defaultImage;

    public TableDataHandler(Image defaultImage)
    {
        this.defaultImage = defaultImage;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        elementValue = new String(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException
    {
        episodeData = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException
    {
        switch (qName)
        {
            case SCHEDULEDEPISODE:
                TableData data = new TableData();
                data.setImage(defaultImage);
                episodeData.add(data);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException
    {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        switch (qName)
        {
            case TITLE:
                latestEpisode().setTitle(elementValue);
                break;
            case DESCRIPTION:
                latestEpisode().setDescription(elementValue);
                break;
            case STARTTIME:
                LocalDateTime startTime =
                        LocalDateTime.parse(elementValue, formatter);
                latestEpisode().setStartTime(startTime);
                break;
            case ENDTIME:
                LocalDateTime endTime =
                        LocalDateTime.parse(elementValue, formatter);
                latestEpisode().setEndTime(endTime);
                break;
            case IMAGE:
                Image image = createImage(elementValue);
                latestEpisode().setImage(image);
                break;
        }
    }

    private Image createImage(String urlString)
    {
        try
        {
            URL url = new URL(urlString);

            Image image = ImageIO.read(url).
                    getScaledInstance(100, 100, 100);

            return image;
        }
        catch (MalformedURLException e){}
        catch (IOException e){}

        return null;
    }

    private TableData latestEpisode()
    {
        return episodeData.get(episodeData.size() - 1);
    }

    public ArrayList<TableData> getEpisodeData()
    {
        return episodeData;
    }
}
