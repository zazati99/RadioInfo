import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EpisodeInfoPanel extends JPanel
{

    JPanel centerPanel;
    JPanel timePanel;

    JLabel imageLable;
    JLabel title;
    JLabel description;

    JLabel time;
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

        imageLable = new JLabel();
        this.add(imageLable, BorderLayout.WEST);

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

    public void setData(TableData data)
    {
        Image image = data.getImage();
        imageLable.setIcon(image == null ? null : new ImageIcon(image));

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
