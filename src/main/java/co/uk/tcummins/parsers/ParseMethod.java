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
            if( !classMembers.getClassMemberType().equals( Title.CONSTRUCTOR.getType() ) )
            {
                if( !classMembers.getClassMemberType().equals( Title.VARIABLE.getType() ) )
                {
                    final String classValue = classMembers.getClassMemberValue();

                    if( checkForArguments( classValue ) )
                    {
                        newHashMap = parseArguments( classValue );
                    }

                    methodName = parseName( classValue );
                    accessType = parseAccessType( classValue );

                    if( checkForArguments( classValue ) )
                    {
                        //this method parses the method with arguments and looks for the last occurrence of a ':' and takes the ending returntype
                        returnType = parseArgumentMethodReturnType( classValue );
                    }
                    else
                    {
                        //this method has no arguments so it just takes the return type after the colon
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
            //if user accidentally doesnt include space after brackets for the :
            int colonPosition = stringMethod.indexOf(":");
            char colon = stringMethod.charAt(colonPosition);
            char previousCharOfColon = stringMethod.charAt(colonPosition - 1);
            int prevIndex = stringMethod.indexOf(stringMethod.charAt(colonPosition - 1));

            //if user accidentally doesnt include space after :, which breaks the return type
            int colonPosition2 = stringMethod.indexOf(":");
            char colon2 = stringMethod.charAt(colonPosition);
            char previousCharOfColon2 = stringMethod.charAt(colonPosition2 - 1);
            char afterCharOfColon = stringMethod.charAt(colonPosition2 + 1);
            //int prevIndex2 = stringMethod.indexOf(stringMethod.charAt(colonPosition2 - 1));
            int afterIndex2 = stringMethod.indexOf(stringMethod.charAt(colonPosition2 + 1));

            //if the colon has no spaces before add one in, also if it has no space after add one in
            if(colon == ':' && previousCharOfColon != ' ')
            {
                StringBuilder sb = new StringBuilder();
                sb.append(stringMethod);
                sb.insert(prevIndex + 1, " ");

                String str4 = sb.toString().substring(0, sb.toString().indexOf("(") + 1) + newStr.trim()
                        + sb.toString().substring(sb.toString().indexOf("(") + 1, sb.toString().length()).trim();
                return str4.split(" ")[3].trim();

            }
            else if(((colon2 == ':' && previousCharOfColon2 == ' ') && afterCharOfColon != ' '))
            {
                StringBuilder sb = new StringBuilder();
                sb.append(stringMethod);
                sb.insert(afterIndex2, " ");

                String str4 = sb.toString().substring(0, sb.toString().indexOf("(") + 1) + newStr.trim()
                        + sb.toString().substring(sb.toString().indexOf("(") + 1, sb.toString().length()).trim();
                return str4.split(" ")[3].trim();
            }
            else
            {
                String str4 = stringMethod.substring(0, stringMethod.indexOf("(") + 1) + newStr.trim()
                        + stringMethod.substring(stringMethod.indexOf("(") + 1, stringMethod.length()).trim();
                return str4.split(" ")[3].trim();
            }
        }

        return stringMethod.split(" ")[3].trim();
    }
}
