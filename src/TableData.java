import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TableData
{
    private String title;
    private String description;

    LocalDateTime startTime;
    LocalDateTime endTime;

    private JLabel imageLable;

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

    public void setImageLable(JLabel imageLable)
    {
        this.imageLable = imageLable;
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

    public JLabel getImage()
    {
        return imageLable;
    }
}
