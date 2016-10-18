import helpers.Clean;
import objects.Clazz;
import objects.Attribute;
import objects.Method;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Tom on 18/10/2016.
 */
public class StringComparison
{
    private static final String CLASSTAG = "class";
    private static final String ST3 = "st3";
    private static final String ST6 = "st6";

    List<Clazz> clazzList = new ArrayList<>();
    List<Method> methodList = new ArrayList<>();
    List<Attribute> attributeList = new ArrayList<>();


    public Object foo( Elements elements ) throws InterruptedException
    {
        int id = 0;
        boolean state = false;

        for( Element ele : elements )
        {
            Elements clazz = ele.getElementsByAttributeValue( CLASSTAG, ST3 );
            if( !clazz.text().isEmpty() )
            {
                state = true;
                clazzList.add( new Clazz( id, clazz.text().trim() ) );
            }

            Elements value = ele.getElementsByAttributeValue( CLASSTAG, ST6 );
            if( !value.text().isEmpty() )
            {
                if( value.text().contains( "(" ) && value.text().contains( ")" ) )
                {
                    methodList.add( new Method( id, value.text().trim() ) );
                }
                else
                {
                    attributeList.add( new Attribute( id, value.text().trim() ) );
                }
            }

            if( state )
                id++;
                state = false;
        }

        for( Clazz clazz : clazzList )
        {
            System.out.println( "Class: " + clazz );
        }
        for( Method clazz : methodList )
        {
            System.out.println( "Method: " + clazz );
        }
        for( Attribute clazz : attributeList )
        {
            System.out.println( "Attribute: " + clazz );
        }

        return new Object();
    }
}
