import com.sun.org.apache.regexp.internal.RE;
import enums.AccessType;
import enums.Title;
import objects.ClazzToBuild;
import objects.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tom on 19/10/2016.
 */
public class ParseMethod
{
    private static List<Method> methodList = new ArrayList<>();

    public static List<Method> parseMethod( final ArrayList<ClassMembers> method )
    {
        LinkedHashMap<String, String> newHashMap = null;
        String accessType = null;
        String methodName = null;
        String returnType = null;

        for( ClassMembers classMembers : method )
        {
            if( !classMembers.getClassType().equals( Title.CONSTRUCTOR.getType() ) )
            {
                if( !classMembers.getClassType().equals( Title.VARIABLE.getType() ) )
                {
                    final String classValue = classMembers.getClassValue();

                    if( checkForMethodArguments( classValue ) )
                    {
                        newHashMap = parseArguments( classValue );
                    }

                    methodName = parseMethodName( classValue );
                    accessType = parseAccessType( classValue );

                    if( checkForMethodArguments( classValue ))
                    {
                        returnType = parseArgumentMethodReturnType( classValue );
                    }
                    else
                    {
                        returnType = parseReturnType(classValue);
                    }
                }
            }

            if( accessType != null && methodName != null && returnType != null )
            {
                if( newHashMap != null )
                {
                    methodList.add(new Method(accessType, returnType, methodName, newHashMap));
                }
                else
                {
                    methodList.add( new Method( accessType, returnType, methodName ) );
                }
            }
        }

        return methodList;
    }


    private static LinkedHashMap<String, String> parseArguments( final String stringMethod )
    {
        final String f = stringMethod.split( "[\\(\\)]" )[1].trim();
        final LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();

        if( stringMethod.contains( "," ) )
        {
            String[] newF = f.split( "," );

            for( String aNewF : newF )
            {
                String[] f2 = aNewF.split( " : " );
                hashMap.put( f2[0].trim(), f2[1].trim() );
            }
        }
        else
        {
            if( !f.isEmpty() )
            {
                String[] f2 = f.split( " : " );
                hashMap.put( f2[0].trim(), f2[1].trim() );
            }
        }

        return hashMap;
    }



    private static String parseMethodName( final String stringMethod )
    {
        return stringMethod.split( "[^\\w\\s]" )[1].trim();
    }

    private static String parseArgumentMethodReturnType( final String stringMethod )
    {
        int index = stringMethod.lastIndexOf(":");
        if(index != -1)
        {
            return stringMethod.substring(index).split(" ")[1];
        }
        return null;
    }


    private static String parseReturnType( final String stringMethod )
    {

        return stringMethod.split( "[^\\w\\s]" )[4].trim();
    }



    private static String parseAccessType( final String stringMethod )
    {
        // Regex string = "^[^(]\w*"
        final String accessType = stringMethod.substring( 0, 1 );

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


    private static boolean checkForMethodArguments( final String stringMethod )
    {
        final Matcher matcher = Pattern.compile( "\\((\\W\\S.*?)\\)" ).matcher( stringMethod );
        return matcher.find();
    }
}
