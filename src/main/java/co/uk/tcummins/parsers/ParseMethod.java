package co.uk.tcummins.parsers;

import co.uk.tcummins.enums.Title;
import co.uk.tcummins.objects.ClassMember;
import co.uk.tcummins.objects.Method;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static co.uk.tcummins.parsers.GeneralParsers.*;

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

                    if( checkForArguments( classValue ) )
                    {
                        newHashMap = parseArguments( classValue );
                    }

                    methodName = parseName( classValue );
                    accessType = parseAccessType( classValue );

                    if( checkForArguments( classValue ) )
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
            String str4 = stringMethod.substring( 0, stringMethod.indexOf( "(" ) + 1 ) + newStr.trim()
                    + stringMethod.substring( stringMethod.indexOf( "(" ) + 1, stringMethod.length() ).trim();
            return str4.split( " " )[3].trim();
        }

        return stringMethod.split(" ")[3].trim();
    }
}
