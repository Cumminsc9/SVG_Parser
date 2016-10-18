import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by c015406c on 18/10/2016.
 */
public class Main
{
    private String stringFile;

    private static Map<Integer,String> locations = new HashMap<>();


    public static void main( String[] args ) throws Exception
    {
        //new Main();

        final File file = new File( "src/main/resources/DiagramToCodeSVG.svg" );
        final Document doc = Jsoup.parse( file, "UTF-8", "http://example.com/" );
        Elements textTag = doc.getElementsByTag( "text" );
        Elements descTag = doc.getElementsByTag("desc");

//        for( Element ele : doc.getAllElements() )
//        {
//            final String loc = ele.select( "g" ).attr( "transform" );
//            if( !loc.isEmpty() )
//            {
//                locations.add( id,loc.replaceAll( "[^\\d.]", " " ).trim().replace( " ", "," ).split( "," )[0] );
//            }
//        }

        StringComparison stringComparison = new StringComparison();
        stringComparison.foo( textTag, locations , descTag, doc);
    }


    private Main()
    {
        stringFile = FileParser.parseFile();
        foo();
    }


    private void foo()
    {
        Document doc = Jsoup.parse( stringFile );
        System.out.println( doc.getElementById( "g" ) );
    }
}
