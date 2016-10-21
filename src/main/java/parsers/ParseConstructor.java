package parsers;

import enums.AccessType;
import enums.Title;
import objects.ClassMember;
import objects.Constructor;
import objects.Method;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anil on 20/10/2016.
 */
public class ParseConstructor
{
    public static List<Constructor> parseConstructor(final ArrayList<ClassMember> constructor )
    {
        List<Constructor> constructorList = new ArrayList<>();
        LinkedHashMap<String, String> newHashMap = null;
        String accessType = null;
        String constructorName = null;
        String returnType = null;

        for( ClassMember classMembers : constructor )
        {
            if( !classMembers.getClassType().equals( Title.METHOD.getType() ) )
            {
                if( !classMembers.getClassType().equals( Title.VARIABLE.getType() ) )
                {
                    final String classValue = classMembers.getClassValue();

                    if( checkForMethodArguments( classValue ) )
                    {
                        newHashMap = parseArguments( classValue );
                    }

                    constructorName = parseMethodName( classValue );
                    accessType = parseAccessType( classValue );

                    if( accessType != null && constructorName != null )
                    {
                        if( newHashMap != null )
                        {
                            constructorList.add(new Constructor(accessType, constructorName, newHashMap));
                        }
                        else
                        {
                            constructorList.add( new Constructor( accessType, constructorName ) );
                        }
                    }
                }
            }
        }
        return constructorList;
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

//    private static String parseArgumentMethodReturnType( final String stringMethod )
//    {
//        int index = stringMethod.lastIndexOf(":");
//        if(index != -1)
//        {
//            return stringMethod.substring(index).split(" ")[1];
//        }
//        return null;
//    }

//
//    private static String parseReturnType( final String stringMethod )
//    {
//
//        return stringMethod.split( "[^\\w\\s]" )[4].trim();
//    }



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
//        final Matcher matcher = Pattern.compile( "\\((.*?)\\)" ).matcher( stringMethod );
//        return matcher.find();

        String newStr = stringMethod.substring(stringMethod.indexOf("(")+1,stringMethod.indexOf(")"));
        if(newStr.matches(".*[a-z].*")) //has arguments has to have
        {
            //System.out.println(newStr);
            return true;
        }
        return false; //no arguements even if random spaces or no space
    }
}
