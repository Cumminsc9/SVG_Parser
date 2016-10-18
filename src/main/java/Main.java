import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileReader;

/**
 * Created by c015406c on 18/10/2016.
 */
public class Main
{
    private String stringFile;

    public static void main(String[] args) throws Exception
    {
        //new Main();


        final File file = new File( "src/main/resources/DiagramToCodeSVG.svg" );
        final Document doc = Jsoup.parse(file, "UTF-8", "http://example.com/");
        Elements textTag = doc.getElementsByTag( "text" );

        StringComparison stringComparison = new StringComparison();
        stringComparison.foo( textTag );

    }

    private Main()
    {
        stringFile = FileParser.parseFile();
        foo();
    }


    private void foo()
    {
        Document doc = Jsoup.parse( stringFile );
        System.out.println( doc.getElementById("g") );
    }
}
