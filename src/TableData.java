import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TableData
{
    private String title;
    private String description;
    private String time;
    private String image;

    public TableData(String title, String description,
                     String time, String image)
    {
        this.title = title;
        this.description = description;
        this.time = time;
        this.image = image;
    }

    public String getTitle()
    {
        return title;
    }

    public String getTime()
    {
        return time;
    }

    public String getDescription()
    {
        return description;
    }

    public String getImage()
    {
        return image;
    }
}
