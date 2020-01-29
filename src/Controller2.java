import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller2 implements ActionListener
{

    GUI gui;

    Timer timer;

    ArrayList<ChannelData> channelData;

    public Controller2()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Controller2.this.gui = new GUI();
                gui.show();

                Controller2.this.timer =  new Timer(1000,
                        Controller2.this);

                timer.setRepeats(false);
                timer.start();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {

        ChannelDataFactory factory = new ChannelDataFactory(
                new SwingWorkerDoneListener()
        {
            @Override
            public void SwingWorkerDone(Object returnValue)
            {
                ArrayList<ChannelData> data =
                        (ArrayList<ChannelData>)returnValue;

                for (int i = 0; i < data.size(); i++)
                {
                    System.out.println(data.get(i).getTitle());
                    System.out.println(data.get(i).getScheduleUrl());
                    System.out.println();
                }
            }
        });

        factory.execute();
    }
}
