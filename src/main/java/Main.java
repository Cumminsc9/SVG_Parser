import creation.BuildClass;
import creation.ClassWriter;
import helpers.OutputClasses;
import objects.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import parsers.ParseConstructor;
import parsers.ParseMethod;
import parsers.ParseVariable;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by c015406c on 18/10/2016.
 */
public class Main
{
    private static Map<String, String[]> elementMap = new HashMap<>();
    private static List<Relation> relations = new ArrayList<>();
//
//    public static void main(String[] args) {
//        final Matcher matcher = Pattern.compile( "\\((.*?)\\)" ).matcher( "+ getName()");
//        System.out.println(matcher.find());
//    }


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
                String enumTitle = CheckInput.checkTitle( titleElements.text() );
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

        for( ArrayList<ClassMember> classMembers : SortClasses.arrangeMethodAndVariables( relations ) )
        {
            for( ClassMember tempObject : classMembers )
            {
                CheckInput.checkMember( tempObject );
            }

            clazzToBuilds.add( new ClazzToBuild( classMembers.get( 0 ).getClassName(),
                    ParseVariable.parseVariable( classMembers ), ParseMethod.parseMethod( classMembers ), ParseConstructor.parseConstructor( classMembers )) );
        }

        // Always goes after the above loop.
        //OutputClasses.outputClasses( clazzToBuilds );

        for( ClazzToBuild clazzToBuild : clazzToBuilds )
        {
//            BuildClass.buildClass(clazzToBuilds.get(2));
//            break;
            String clazz = BuildClass.buildClass(clazzToBuild);
            ClassWriter.classWriter(clazz,clazzToBuild.getClassName());
            //break;
        }
    }
}
