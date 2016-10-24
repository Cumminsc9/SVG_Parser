package co.uk.tcummins.creation;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by anil on 20/10/2016.
 */
public class ClassWriter
{
    private static final Logger logger = Logger.getLogger( ClassWriter.class.getName() );
    private static String SYSTEM_OS_NAME = System.getProperty( "os.name" );
    private static String WINDOWS = "Windows";
    private static String OSX = "Mac OS X";


    //TODO: set with the users selected path from file chooser
    public static boolean classWriter( String clazz, String className )
    {
        File file;

        if( SYSTEM_OS_NAME.contains( OSX ) )
        {
            file = new File( "src/main/resources/createdClasses/" + className + ".java" );
            logger.info( "Using OSX system..." );
        }
        else if( SYSTEM_OS_NAME.contains( WINDOWS ) )
        {
            file = new File( "src\\main\\resources\\createdClasses\\" + className + ".java" );
            logger.info( "Using Windows system..." );
        }
        else
        {
            logger.error( "Unable to create the created classes" );
            return false;
        }

        try
        {
            if( !file.exists() )
            {
                file.createNewFile();
            }
            else
            {
                logger.info( className + ".java already exists. Removing existing file." );
                file.delete();
                file.createNewFile();
            }

            // creates a FileWriter Object
            FileWriter writer = new FileWriter( file );

            // Writes the content to the file
            writer.write( clazz );
            writer.flush();
            writer.close();
        }
        catch( IOException e )
        {
            logger.error( "IOException error. See following Stack Trace: " + e.toString() );
        }

        return true;
    }
}