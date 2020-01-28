import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class EpisodeInfoPanel extends JPanel
{

    JLabel imageLable;
    JLabel textArea;

    public EpisodeInfoPanel()
    {
        this.setLayout(new BorderLayout());



        imageLable = new JLabel();
        this.add(imageLable, BorderLayout.WEST);

        textArea = new JLabel("", JLabel.CENTER);
        this.add(textArea, BorderLayout.CENTER);

    }

    public void setData(TableData data)
    {
        if (imageLable != null)
        {
            this.remove(imageLable);
        }

        imageLable = data.getImage();
        if (data.getImage() != null)
        {
            this.add(imageLable, BorderLayout.WEST);
        }

        textArea.setText(
                        "<html>" +
                        "<style>"+
                        "h2{margin-left:20px;}" +
                        "p{margin-left:10px;}" +
                        "</style>" +
                        "<h2>" +
                        data.getTitle() +
                        "</h2>" +
                        "<p>" +
                        data.getDescription() +
                        "</p>" +
                        "</html>"
        );

    }

}
