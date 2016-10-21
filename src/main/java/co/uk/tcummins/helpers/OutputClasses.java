package co.uk.tcummins.helpers;

import co.uk.tcummins.objects.Attribute;
import co.uk.tcummins.objects.ClazzToBuild;
import co.uk.tcummins.objects.Method;

import java.util.List;

/**
 * Created by Tom on 20/10/2016.
 */
public class OutputClasses
{
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
            System.out.println( "Class: " + clazzToBuild.getClassName() );

            List<Method> classMethods = clazzToBuild.getClassMethods();
            System.out.println( "\tMethods:" );
            for( Method classMethod : classMethods )
            {
                System.out.println( "\t\t" + classMethod );
            }

            List<Attribute> classVariables = clazzToBuild.getClassVariables();
            System.out.println( "\tVariables:" );
            for( Attribute classVariable : classVariables )
            {
                System.out.println( "\t\t" + classVariable );
            }

            System.out.print( "\n" );
        }
    }
}
