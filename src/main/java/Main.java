import objects.*;
import objects.Attribute;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.*;


/**
 * Created by c015406c on 18/10/2016.
 */
public class Main
{
    private static Map<String, String[]> elementMap = new HashMap<>();
    private static List<Relation> relations = new ArrayList<>();


    public static void main( String[] args ) throws Exception
    {
        //new Main();

        final File file = new File( "src/main/resources/DiagramToCodeSVG.svg" );
        final Document doc = Jsoup.parse( file, "UTF-8", "http://example.com/" );
        //Elements textTag = doc.getElementsByTag( "text" );

        for( Element element : doc.getAllElements() )
        {
            Elements locationElement = element.select( "[transform*=translate]" );
            if( !locationElement.isEmpty() )
            {
                String locationAttribute = locationElement.select( "g" ).attr( "transform" );
                Elements textElements = locationElement.select( "text" );

                Elements titleElements = locationElement.select( "title" );
                String enumTitle = CheckTitle.checkTitle( titleElements.text() );
                if( enumTitle != null )
                {
                    String theText = textElements.get( 0 ).text();
                    String[] stringArray = { theText, enumTitle };
                    elementMap.put( locationAttribute, stringArray );
                }
            }
        }

        for( Map.Entry<String, String[]> m : elementMap.entrySet() )
        {
            String location = m.getKey().replaceAll( "[^\\d.]", " " ).trim().replace( " ", "," ).split( "," )[0];
            String value = m.getValue()[0];
            String type = m.getValue()[1];

            relations.add( new Relation( location, value, type ) );
        }

        List<ClazzToBuild> clazzToBuilds = new ArrayList<>();


        for( ArrayList<ClassMembers> classMemberses : SortClasses.arrangeMethodAndVariables( relations ) )
        {
            for( ClassMembers tempObject : classMemberses )
            {
                CheckTitle.checkMember( tempObject );
            }

            clazzToBuilds.add( new ClazzToBuild( classMemberses.get( 0 ).getClassName(),
                    ParseVariable.parseVariable( classMemberses ), ParseMethod.parseMethod( classMemberses ) ) );
        }

        for( ClazzToBuild clazzToBuild : clazzToBuilds )
        {
            List<Method> classMethods = clazzToBuild.getClassMethods();
            for (Method classMethod : classMethods)
            {
                System.out.println( classMethod );
            }

            List<Attribute> classVariables = clazzToBuild.getClassVariables();
            for (Attribute classVariable : classVariables) {
                System.out.println( classVariable );
            }
        }



        //        for( ArrayList<ClassMembers> classMemberses : SortClasses.arrangeMethodAndVariables( relations ))
        //        {
        //            for( ClassMembers classMembers : classMemberses)
        //            {
        //                String className;
        //                Method method;
        //
        //                if( classMembers.getClassType().equals( Title.METHOD.getType() ) )
        //                {
        //                    ParseMethod.parseMethod( classMembers.getClassValue() );
        //                }
        //            }
        //        }
    }
}
