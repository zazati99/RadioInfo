public class ChannelData
{
    private String Title;
    private String scheduleUrl;

    public ChannelData()
    {

    }

    public void setTitle(String title)
    {
        Title = title;
    }

    public void setScheduleUrl(String scheduleUrl)
    {
        this.scheduleUrl = scheduleUrl;
    }

    public String getTitle()
    {
        return Title;
    }

    public String getScheduleUrl()
    {
        return scheduleUrl;
    }
}
