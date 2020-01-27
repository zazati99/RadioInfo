import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller extends SwingWorker<Boolean, TableData>
{
    /**
     * is the program running
     */
    boolean programRunning;

    /**
     * The GUI
     */
    GUI gui;

    public Controller()
    {
        programRunning = true;

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run() {

                gui = new GUI();
                gui.show();

            }
        });
    }

    @Override
    protected Boolean doInBackground() throws Exception
    {


        while(programRunning)
        {

            TableData data = new TableData("ye", "kek",
                                           "oof", "boof");


            publish(data);

            Thread.sleep(1000 * 5);
        }

        return null;
    }

    @Override
    protected void process(List<TableData> list)
    {
        gui.addProgrammeRow(list.get(list.size() - 1));
    }
}
