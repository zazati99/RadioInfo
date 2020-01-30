import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ChannelDataHandler extends DefaultHandler
{
    private static final String CHANNEL = "channel";

    private static final String NAME = "name";
    private static final String IMAGE = "image";
    private static final String SCHEDULE = "scheduleurl";

    private ArrayList<ChannelData> channelData;
    private String elementValue;


    public ChannelDataHandler()
    {

    }

    @Override
    public void characters(char[] ch, int start,
                           int length) throws SAXException
    {
        elementValue = new String(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException
    {
        channelData = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException
    {
        if (qName == CHANNEL)
        {
            ChannelData channel = new ChannelData();
            for (int i = 0; i < attributes.getLength(); i++)
            {
                if (attributes.getQName(i) == NAME)
                {
                    channel.setTitle(attributes.getValue(i));
                }
            }
            channelData.add(channel);
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException
    {
        if (qName == SCHEDULE)
        {
            latestChannel().setScheduleUrl(elementValue);
        }

        switch (qName)
        {
            case SCHEDULE:
                latestChannel().setScheduleUrl(elementValue);
                break;
            case IMAGE:
                    Image image = createImage(elementValue);
                    latestChannel().setImage(image);
                break;
        }
    }

    /**
     * Create image from url
     * @param urlString The url string
     * @return The image
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
     * Get the latest added channel
     * @return The latest channel
     */
    private ChannelData latestChannel()
    {
        return channelData.get(channelData.size() - 1);
    }

    public ArrayList<ChannelData> getChannelData()
    {
        return channelData;
    }

}
