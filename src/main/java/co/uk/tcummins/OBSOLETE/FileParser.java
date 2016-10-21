package co.uk.tcummins.OBSOLETE;

import java.io.BufferedReader;
import java.io.File;
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
        final File file = new File( "src/main/resources/Sample.svg" );
        return new FileReader( file.getAbsolutePath() );
    }
}
