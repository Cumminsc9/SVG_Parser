import objects.Relation;
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
    private static Map<String, String> elementMap = new HashMap<>();
    private static List<Relation> relations = new ArrayList<>();


    public static void main( String[] args ) throws Exception
    {
        //new Main();

        final File file = new File( "src/main/resources/DiagramToCodeSVG.svg" );
        final Document doc = Jsoup.parse( file, "UTF-8", "http://example.com/" );
        //Elements textTag = doc.getElementsByTag( "text" );
        //Elements descTag = doc.getElementsByTag( "desc" );

        int count = 0;

        for( Element element : doc.getAllElements() )
        {
            Elements locationElement = element.select( "[transform*=translate]" );
            if( !locationElement.isEmpty() )
            {
                String locationAttribute = locationElement.select( "g" ).attr( "transform" );
                Elements textElements = locationElement.select( "text" );

                Elements titleElements = locationElement.select( "title" );
                String enumTtile = CheckTitle.checkTitle( titleElements.text() );
                if( enumTtile != null )
                {
                    for( Element text : textElements )
                    {
                        //String[] stringArray = { locationAttribute, text.text() };
                        elementMap.put( locationAttribute, text.text() );
                    }
                }
            }
            count++;
        }

        System.out.println( count );

        //        Elements titleElements = locationElement.select( "title" );
        //        for( Element title : titleElements )
        //        {
        //            String enumTitle = CheckTitle.checkTitle( title.text() );
        //
        //            if( enumTitle != null )
        //            {
        //                String attr = locationElement.select( "g" ).attr( "transform" );
        //                Elements textElements = locationElement.select( "text" );
        //                for( Element text : textElements )
        //                {
        //                    String[] stringArray = { attr, text.text() };
        //                    elementMap.put( stringArray, enumTitle );
        //                }
        //            }
        //        }


        for( Map.Entry<String, String> m : elementMap.entrySet() )
        {
//            String location = m.getKey()[0].replaceAll( "[^\\d.]", " " ).trim().replace( " ", "," ).split( "," )[0];
//            String value = m.getKey()[1];
//            String type = m.getValue();
//            relations.add( new Relation( location, value, type ) );


            String location = m.getKey().replaceAll( "[^\\d.]", " " ).trim().replace( " ", "," ).split( "," )[0];
            String value = m.getValue();
            relations.add( new Relation( location, value ) );
        }

        for( Relation relation : relations )
        {
            System.out.println( relation );
        }

        //
        //        Iterator<Map.Entry<String, String>> iter = elementMap.entrySet().iterator();
        //        while( iter.hasNext() )
        //        {
        //            Map.Entry<String, String> entry = iter.next();
        ////            if( entry.getValue().isEmpty() )
        ////            {
        ////                iter.remove();
        ////            }
        //
        //            //String newKey = entry.getKey().replaceAll( "[^\\d.]", " " ).trim().replace( " ", "," ).split( "," )[0];
        //
        //            newElementMap.put( entry.getKey(), entry.getValue() );
        //        }
        //
        //        for( Map.Entry<String, String> m : newElementMap.entrySet() )
        //        {
        //            System.out.println( m.getKey() + " " + m.getValue() );
        //        }




        //System.out.println( f );


        //StringComparison stringComparison = new StringComparison();
        //stringComparison.foo( textTag, locations , descTag, doc);
    }

    //
    //    private Main()
    //    {
    //        stringFile = FileParser.parseFile();
    //        foo();
    //    }
    //
    //
    //    private void foo()
    //    {
    //        Document doc = Jsoup.parse( stringFile );
    //        System.out.println( doc.getElementById( "g" ) );
    //    }
}
