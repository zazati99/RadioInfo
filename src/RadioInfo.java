import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class RadioInfo
{

    public static void main(String[]  args)
    {

        Controller controller = new Controller();
        controller.execute();

        /*
        try
        {
            URL url = new URL("http://api.sr.se/api/v2/channels/132");

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

        }
        catch (MalformedURLException e)
        {

        }
        catch (IOException e)
        {

        }

        System.out.println("");

        */
    }

}
