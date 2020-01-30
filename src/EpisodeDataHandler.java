import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Handler for parsing EpisodeData
 */
public class EpisodeDataHandler extends DefaultHandler
{
    /**
     * constant for scheduled episode tag
     */
    private static final String SCHEDULEDEPISODE = "scheduledepisode";

    /**
     * constant for title tag
     */
    private static final String TITLE = "title";

    /**
     * constant for description tag
     */
    private static final String DESCRIPTION = "description";

    /**
     * constant for start time tag
     */
    private static final String STARTTIME = "starttimeutc";

    /**
     * constant for end time tag
     */
    private static final String ENDTIME = "endtimeutc";

    /**
     * constant for image URL tag
     */
    private static final String IMAGE = "imageurl";

    /**
     * ArrayList of EpisodeData
     */
    private ArrayList<EpisodeData> episodeData;

    /**
     * The string value of the current element
     */
    private String elementValue;

    /**
     * Default image for EpisodeData
     */
    Image defaultImage;

    public EpisodeDataHandler(Image defaultImage)
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
                EpisodeData data = new EpisodeData();
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

    /**
     * Creates an image from a URL
     * @param urlString URL to get image from
     * @return the image
     */
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

    /**
     * Gets the latest episode data created
     * @return the episode data
     */
    private EpisodeData latestEpisode()
    {
        return episodeData.get(episodeData.size() - 1);
    }

    public ArrayList<EpisodeData> getEpisodeData()
    {
        return episodeData;
    }
}
