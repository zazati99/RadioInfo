import java.awt.*;

public class ChannelData
{
    private String title;
    private String scheduleUrl;
    private Image image;

    public ChannelData()
    {

    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setScheduleUrl(String scheduleUrl)
    {
        this.scheduleUrl = scheduleUrl;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public String getTitle()
    {
        return title;
    }

    public String getScheduleUrl()
    {
        return scheduleUrl;
    }

    public Image getImage()
    {
        return image;
    }
}
