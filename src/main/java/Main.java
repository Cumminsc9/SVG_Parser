import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by c015406c on 18/10/2016.
 */
public class Main
{
    private String stringFile;

    public static void main(String[] args)
    {
        new Main();
    }

    private Main()
    {
        stringFile = FileParser.parseFile();
        foo();
    }


    private void foo()
    {
        Document doc = Jsoup.parse( stringFile );

        System.out.println( doc.text() );
    }
}
