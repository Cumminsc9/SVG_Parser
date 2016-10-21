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
            if( !classMembers.getClassType().equals( Title.CONSTRUCTOR.getType() )
                    && !classMembers.getClassType().equals( Title.METHOD.getType() ) )
            {
                if( classMembers.getClassType().equals( Title.VARIABLE.getType() ) )
                {
                    final String classValue = classMembers.getClassValue();

                    variableName = parseVariableName( classValue );
                    accessType = parseAccessType( classValue );
                    returnType = parseReturnType( classValue );

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
        return stringMethod.split( "[^\\w\\s]" )[1].trim();
    }


    private static String parseReturnType( final String stringMethod )
    {
        if( stringMethod.contains( CollectionType.LIST.getCollectionType() )
                || stringMethod.contains( CollectionType.MAP.getCollectionType() ) )
        {
            return stringMethod.split( ":" )[1].trim();
        }
        else
        {
            return stringMethod.split( " " )[3];
        }
    }
}