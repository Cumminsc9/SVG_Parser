package co.uk.tcummins.parsers;

import co.uk.tcummins.enums.CollectionType;
import co.uk.tcummins.enums.Title;
import co.uk.tcummins.objects.Attribute;
import co.uk.tcummins.objects.ClassMember;

import java.util.ArrayList;
import java.util.List;

import static co.uk.tcummins.parsers.GeneralParsers.parseAccessType;

/**
 * Created by Tom on 19/10/2016.
 */
public class ParseVariable
{
    public static List<Attribute> parseVariable( final ArrayList<ClassMember> method )
    {
        List<Attribute> variableList = new ArrayList<>();
        String accessType = null;
        String variableName = null;
        String returnType = null;

        for( ClassMember classMembers : method )
        {
            if( !classMembers.getClassMemberType().equals( Title.CONSTRUCTOR.getType() )
                    && !classMembers.getClassMemberType().equals( Title.METHOD.getType() ) )
            {
                if( classMembers.getClassMemberType().equals( Title.VARIABLE.getType() ) )
                {
                    final String classValue = classMembers.getClassMemberValue();

                    if(!classValue.contains(" "))
                    {
                        variableName = classValue;
                        accessType = "";
                        returnType = "";
                    }
                    else
                    {
                        variableName = parseVariableName(classValue);
                        accessType = parseAccessType(classValue);
                        returnType = parseReturnType(classValue);
                    }

                    if( accessType != null && variableName != null && returnType != null )
                    {
                        variableList.add( new Attribute( accessType, returnType, variableName ) );
                    }
                }
            }
        }
        return variableList;
    }


    private static String parseVariableName( final String stringMethod )
    {
        final String[] foo = stringMethod.split( "[^\\w\\s]" );
        if( foo.length >= 2 )
        {
            return foo[1].trim();
        }
        else
        {
            return foo[0].trim();
        }
    }


    private static String parseReturnType( final String stringMethod )
    {
        if( stringMethod.contains( CollectionType.LIST.getCollectionType() )
                || stringMethod.contains( CollectionType.MAP.getCollectionType() ) )
        {
            return stringMethod.split( ":" )[1].trim();
        }
//        else if (!stringMethod.contains(" ")) //no spaces meaning just one long word, this would mean only going to be variable from enum
//        {
//            System.out.println("IT WAS AN ENUM: " + stringMethod );
//            return "";
//        }

        else
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

                if(sb.toString().split(" ").length < 4)
                {
                    System.out.println("existing 3rd element ");
                    int accessorPosition = 0;
                    char accessor = stringMethod.charAt(accessorPosition);
                    char afterCharOfAccessor = stringMethod.charAt(accessorPosition + 1);
                    int afterIndexAccessor = stringMethod.indexOf(stringMethod.charAt(accessorPosition + 1));

                    if(afterIndexAccessor == ' ')
                    {
                        return sb.toString().split(" ")[3].trim();
                    }
                    else
                    {
                        sb.insert(accessorPosition + 1, " ");
                    }
                }
                return sb.toString().split(" ")[3].trim();

            }
            else if(((colon2 == ':' && previousCharOfColon2 == ' ') && afterCharOfColon != ' '))
            {
                StringBuilder sb = new StringBuilder();
                sb.append(stringMethod);
                sb.insert(afterIndex2, " ");

                if(sb.toString().split(" ").length < 4)
                {
                    System.out.println("existing 3rd element ");
                    int accessorPosition = 0;
                    char accessor = stringMethod.charAt(accessorPosition);
                    char afterCharOfAccessor = stringMethod.charAt(accessorPosition + 1);
                    int afterIndexAccessor = stringMethod.indexOf(stringMethod.charAt(accessorPosition + 1));

                    if(afterIndexAccessor == ' ')
                    {
                        return sb.toString().split(" ")[3].trim();
                    }
                    else
                    {
                        sb.insert(accessorPosition + 1, " ");
                    }
                }

                return sb.toString().split(" ")[3].trim();
            }
            else
            {
                StringBuilder sb = new StringBuilder();
                sb.append(stringMethod);

                if(sb.toString().split(" ").length < 4)
                {
                    //System.out.println("existing 3rd element ");
                    int accessorPosition = 0;
                    char accessor = stringMethod.charAt(accessorPosition);
                    char afterCharOfAccessor = stringMethod.charAt(accessorPosition + 1);
                    int afterIndexAccessor = stringMethod.indexOf(stringMethod.charAt(accessorPosition + 1));

                    if(afterIndexAccessor == ' ')
                    {
                        return sb.toString().split(" ")[3].trim();
                    }
                    else
                    {
                        sb.insert(accessorPosition + 1, " ");
                    }
                }
                return sb.toString().split( " " )[3];
            }
            //return stringMethod.split( " " )[3];
        }
    }
}