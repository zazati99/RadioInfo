import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class TableData
{
    private String title;
    private String description;

    LocalDateTime startTime;
    LocalDateTime endTime;

    private Image image;

    public TableData()
    {

    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setStartTime(LocalDateTime startTime)
    {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime)
    {
        this.endTime = endTime;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public String getTitle()
    {
        return title;
    }

    public LocalDateTime getStartTime()
    {
        return startTime;
    }

    public LocalDateTime getEndTime()
    {
        return endTime;
    }

    public String getDescription()
    {
        return description;
    }

    public Image getImage()
    {
        return image;
    }
}
