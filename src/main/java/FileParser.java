import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by c015406c on 18/10/2016.
 */
public class FileParser
{

    public static String parseFile()
    {
        String file = "";

        try
        {
            BufferedReader bufferedReader = new BufferedReader( readFile() );

            String line;
            while( ( line = bufferedReader.readLine()) != null)
            {
                file += line;
            }
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        return file;
    }


    private static FileReader readFile() throws Exception
    {
        return new FileReader("C:\\Users\\c015406c\\Downloads\\Visio Docs\\Sample.svg");
    }
}
