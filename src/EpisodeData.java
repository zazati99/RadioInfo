import java.awt.*;
import java.time.LocalDateTime;

/**
 * Class that holds Episode information
 */
public class EpisodeData
{
    /**
     * Title of the Episode
     */
    private String title;

    /**
     * The description of the episode
     */
    private String description;

    /**
     * The start time for the episode
     */
    LocalDateTime startTime;

    /**
     * The end time for the episode
     */
    LocalDateTime endTime;

    /**
     * A string that shows what time the episode is airing
     */
    String time;

    /**
     * Boolean that is true if the episode has started
     */
    boolean episodeStarted;

    /**
     * Boolean that is true if the episode is currently airing
     */
    boolean episodeRunning;

    /**
     * Image for the episode
     */
    private Image image;

    /**
     * Sets the title of the episode
     * @param title the new title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Sets the description for the episode
     * @param description the new description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Sets the start time for the episode
     * @param startTime the start time
     */
    public void setStartTime(LocalDateTime startTime)
    {
        this.startTime = startTime;
    }

    /**
     * Sets the end time for the episode
     * @param endTime the new end time
     */
    public void setEndTime(LocalDateTime endTime)
    {
        this.endTime = endTime;
    }

    /**
     * Sets the image for the episode
     * @param image the new image
     */
    public void setImage(Image image)
    {
        this.image = image;
    }

    /**
     * Sets the started status for the data
     * @param hasStarted the started status
     */
    public void setEpisodeStarted(boolean hasStarted)
    {
        this.episodeStarted = hasStarted;
    }

    /**
     * Sets the running status for the data
     * @param isRunning the running status
     */
    public void setEpisodeRunning(boolean isRunning)
    {
        this.episodeRunning = isRunning;
    }

    /**
     * Sets the time string for the data
     * @param time the new time string
     */
    public void setTime(String time)
    {
        this.time = time;
    }

    /**
     * Gets the title for the episode
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Gets the start time for the episode
     * @return The start time
     */
    public LocalDateTime getStartTime()
    {
        return startTime;
    }

    /**
     * Gets the end time for the episode
     * @return the end time
     */
    public LocalDateTime getEndTime()
    {
        return endTime;
    }

    /**
     * Gets the description for the episode
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Gets the image for the episode
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * Gets the episode started status
     * @return the started status
     */
    public boolean isEpisodeStarted()
    {
        return episodeStarted;
    }

    /**
     * Gets the episode running status
     * @return
     */
    public boolean isEpisodeRunning()
    {
        return episodeRunning;
    }

    /**
     * Gets the time string for the episode
     * @return the time string
     */
    public String getTime()
    {
        return time;
    }
}
