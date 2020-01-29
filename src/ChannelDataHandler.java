import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.event.ChangeEvent;
import java.util.ArrayList;

public class ChannelDataHandler extends DefaultHandler
{
    private static final String CHANNEL = "channel";

    private static final String NAME = "name";
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
