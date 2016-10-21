package co.uk.tcummins.parsers;

import co.uk.tcummins.enums.AccessType;

import java.util.LinkedHashMap;

/**
 * Created by Tom on 21/10/2016.
 */
class GeneralParsers
{
    static String parseName( final String stringValue )
    {
        return stringValue.split( "[^\\w\\s]" )[1].trim();
    }


    static LinkedHashMap<String, String> parseArguments( final String stringValue )
    {
        final String f = stringValue.split( "[\\(\\)]" )[1].trim();
        final LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();

        if( stringValue.contains( "," ) )
        {
            String[] newF = f.split( "," );

            for( String aNewF : newF )
            {
                String[] f2 = aNewF.split( ":" );
                hashMap.put( f2[0].trim(), f2[1].trim() );
            }
        }
        else
        {
            if( !f.isEmpty() )
            {
                String[] f2 = f.split( ":" );
                hashMap.put( f2[0].trim(), f2[1].trim() );
            }
        }

        return hashMap;
    }


    static String parseAccessType( final String stringValue )
    {
        // Regex string = "^[^(]\w*"
        final String accessType = stringValue.substring( 0, 1 );

        if( accessType.equals( AccessType.PUBLIC.getAccessType() ) )
        {
            return "public";
        }
        else if( accessType.equals( AccessType.PROTECTED.getAccessType() ) )
        {
            return "protected";
        }
        else if( accessType.equals( AccessType.PRIVATE.getAccessType() ) )
        {
            return "private";
        }
        else
        {
            return "public";
        }
    }


    static boolean checkForArguments( final String stringValue )
    {
        String newStr = stringValue.substring( stringValue.indexOf( "(" ) + 1, stringValue.indexOf( ")" ) );
        return newStr.matches(".*[a-z].*");
    }
}
