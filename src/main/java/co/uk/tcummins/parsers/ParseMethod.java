package co.uk.tcummins.parsers;

import co.uk.tcummins.enums.AccessType;
import co.uk.tcummins.enums.Title;
import co.uk.tcummins.objects.ClassMember;
import co.uk.tcummins.objects.Method;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Tom on 19/10/2016.
 */
public class ParseMethod
{
    public static List<Method> parseMethod( final ArrayList<ClassMember> method )
    {
        List<Method> methodList = new ArrayList<>();
        LinkedHashMap<String, String> newHashMap = null;
        String accessType = null;
        String methodName = null;
        String returnType = null;

        for( ClassMember classMembers : method )
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

                    if( accessType != null && methodName != null && returnType != null )
                    {
                        if( newHashMap != null )
                        {
                            methodList.add(new Method(accessType, returnType, methodName, newHashMap));
                            newHashMap = null;
                        }
                        else
                        {
                            methodList.add( new Method( accessType, returnType, methodName ) );
                        }
                    }
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
        String newStr = stringMethod.substring(stringMethod.indexOf("(")+1,stringMethod.indexOf(")"));
        if(!newStr.matches(".*[a-z].*"))
        {
            //System.out.println(stringMethod + ": sfsd : " + newStr);//System.out.println(newStr);
//            str0 = stringMethod.substring(0, stringMethod.indexOf("(") + 1);
//            str1 = stringMethod.trim();
//            str2 = stringMethod.substring(stringMethod.indexOf("(") + 1, stringMethod.length()).trim();

            String str4 = stringMethod.substring(0, stringMethod.indexOf("(") + 1) + newStr.trim() + stringMethod.substring(stringMethod.indexOf("(") + 1, stringMethod.length()).trim();
//            String str0 = stringMethod.substring(0, stringMethod.indexOf("(") + 1);
//            String str1 = newStr.trim();
//            String str2 = stringMethod.substring(stringMethod.indexOf("(") + 1, stringMethod.length()).trim();
            //System.out.println(str0);
            //System.out.println(str1);
            //System.out.println(str2);
            //System.out.println(str4);
            //System.out.println(str4.split(" ")[3].trim());
            return str4.split(" ")[3].trim();
        }
//        String str4 = stringMethod.substring(0, stringMethod.indexOf("(") + 1) + stringMethod.trim() + stringMethod.substring(stringMethod.indexOf("(") + 1, stringMethod.length()).trim();
//        System.out.println(str4.split(" ")[3].trim());
        return stringMethod.split(" ")[3].trim();//stringMethod.split( "[^\\w\\s]" )[4].trim();
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
//        final Matcher matcher = Pattern.compile( "\\((.*?)\\)" ).matcher( stringMethod );//\((\W\S.*?)\)
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
