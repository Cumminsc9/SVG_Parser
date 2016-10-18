package helpers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * Created by Tom on 18/10/2016.
 */
public class Clean
{
    public static String clean( final String html )
    {
        if( html == null )
        {
            return html;
        }

        Document document = Jsoup.parse( html );
        //makes html() preserve linebreaks and spacing
        document.outputSettings( new Document.OutputSettings().prettyPrint( false ) );

        return Jsoup.clean( html, "", Whitelist.none(), new Document.OutputSettings().prettyPrint( false ) );
    }
}
