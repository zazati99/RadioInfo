import java.awt.*;

/**
 * Holds data for a radio channel
 */
public class ChannelData
{
    /**
     * Title of the channel
     */
    private String title;

    /**
     * Url tot the schedule for the channel
     */
    private String scheduleUrl;

    /**
     * The Channel image
     */
    private Image image;

    /**
     * Sets the title of this data
     * @param title the new title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Sets the schedule of this data
     * @param scheduleUrl the schedule url
     */
    public void setScheduleUrl(String scheduleUrl)
    {
        this.scheduleUrl = scheduleUrl;
    }

    /**
     * Sets the image for this data
     * @param image the new image
     */
    public void setImage(Image image)
    {
        this.image = image;
    }

    /**
     * Gets the title for this data
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Gets the schedule url for this data
     * @return the schedule url
     */
    public String getScheduleUrl()
    {
        return scheduleUrl;
    }

    /**
     * Gets the image for this data
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }
}
