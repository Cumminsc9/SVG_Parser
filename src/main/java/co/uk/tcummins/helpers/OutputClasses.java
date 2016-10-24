package co.uk.tcummins.helpers;

import co.uk.tcummins.objects.Attribute;
import co.uk.tcummins.objects.ClazzToBuild;
import co.uk.tcummins.objects.Method;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Tom on 20/10/2016.
 */
public class OutputClasses
{
    private static final Logger logger = Logger.getLogger( OutputClasses.class.getName() );

    /**
     * This class simply outputs each individual class,
     * with its related methods and variables.
     * 
     * This can be called anywhere as long as the class
     * list is passed in.
     * 
     * @param clazzToBuilds The list which contains all the classes to be built.
     */
    public static void outputClasses( final List<ClazzToBuild> clazzToBuilds )
    {
        for( ClazzToBuild clazzToBuild : clazzToBuilds )
        {
            logger.debug( "Class: " + clazzToBuild.getClassName() );

            List<Method> classMethods = clazzToBuild.getClassMethods();
            logger.debug( "\tMethods:" );
            for( Method classMethod : classMethods )
            {
                logger.debug( "\t\t" + classMethod );
            }

            List<Attribute> classVariables = clazzToBuild.getClassVariables();
            logger.debug( "\tVariables:" );
            for( Attribute classVariable : classVariables )
            {
                logger.debug( "\t\t" + classVariable );
            }

            logger.debug( "\n" );
        }
    }
}
