import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Jpanel that shows info for an episode
 */
public class EpisodeInfoPanel extends JPanel
{

    /**
     * The center panel where the title and description will be shown
     */
    JPanel centerPanel;

    /**
     * Eastern panel where time and status will be shown
     */
    JPanel timePanel;

    /**
     * Label that holds the episode or channel image
     */
    JLabel imageLabel;

    /**
     * Label that ´shows the title
     */
    JLabel title;

    /**
     * Label that shows the description
     */
    JLabel description;

    /**
     * Label that shows the time
     */
    JLabel time;

    /**
     * Label that shows the status
     */
    JLabel status;

    public EpisodeInfoPanel()
    {
        this.setLayout(new BorderLayout());

        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        timePanel = new JPanel();
        timePanel.setLayout(new BorderLayout());
        timePanel.setBorder(new EmptyBorder(25, 10, 25, 10));

        imageLabel = new JLabel();
        this.add(imageLabel, BorderLayout.WEST);

        title = new JLabel("", JLabel.CENTER);
        centerPanel.add(title, BorderLayout.NORTH);

        description = new JLabel("", JLabel.CENTER);
        centerPanel.add(description, BorderLayout.SOUTH);

        time = new JLabel("", JLabel.CENTER);
        timePanel.add(time, BorderLayout.NORTH);

        status = new JLabel("", JLabel.CENTER);
        timePanel.add(status, BorderLayout.SOUTH);

        this.add(timePanel, BorderLayout.EAST);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * sets the episode data that will beshown to the user
     * @param data The data to be shown
     */
    public void setData(EpisodeData data)
    {
        Image image = data.getImage();
        imageLabel.setIcon(image == null ? null : new ImageIcon(image));

        title.setText(data.getTitle());
        description.setText("<html>" + data.getDescription() + "</html>");

        time.setText(data.getTime());

        if (data.isEpisodeStarted())
        {
            if (data.isEpisodeRunning())
            {
                status.setText("pågår nu");
            }
            else
            {
                status.setText("Sändningen är över");
            }
        }
        else
        {
            status.setText("Sändningen har inte börjat än");
        }
    }

}
