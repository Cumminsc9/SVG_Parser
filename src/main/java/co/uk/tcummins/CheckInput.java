package co.uk.tcummins;

import co.uk.tcummins.enums.Title;
import co.uk.tcummins.objects.ClassMember;

/**
 * Created by Tom on 19/10/2016.
 */
public class CheckInput
{
    public static String checkTitle( final String title )
    {
        final String parsedTitle = title.replaceAll( "[^a-zA-Z] ", "" );

        if( parsedTitle.contains( Title.CLAZZ.getType() ) )
        {
            return Title.CLAZZ.getType();
        }
        if( parsedTitle.contains( Title.ENUM.getType() ) )
        {
            return Title.ENUM.getType();
        }
        if( parsedTitle.contains( Title.INTERFACE.getType() ) && !parsedTitle.contains( "Realization" ) )
        {
            return Title.INTERFACE.getType();
        }
        if( parsedTitle.contains( Title.MEMBER.getType() ) )
        {
            return Title.MEMBER.getType();
        }
        if( parsedTitle.contains( Title.PACKAGE.getType() ) )
        {
            return null;
        }
        if( parsedTitle.contains( Title.NOTE.getType() ) )
        {
            return null;
        }
        if( parsedTitle.contains( Title.PAGE.getType() ) )
        {
            return null;
        }
        if( parsedTitle.contains( Title.SHEET.getType() ) )
        {
            return null;
        }
        if( parsedTitle.contains( Title.INHERITANCE.getType() ) )
        {
            return null;
        }
        if( parsedTitle.contains( Title.ASSOCIATION.getType() ) )
        {
            return null;
        }
        if( parsedTitle.contains( Title.AGGREGATION.getType() ) )
        {
            return null;
        }
        if( parsedTitle.contains( Title.COMPOSITION.getType() ) )
        {
            return null;
        }
        if( parsedTitle.contains( Title.DEPENDENCY.getType() ) )
        {
            return null;
        }
        if( parsedTitle.contains( Title.DIRECTED_ASSOCIATION.getType() ) )
        {
            return null;
        }
        if( parsedTitle.contains( Title.INTERFACE_REALIZATION.getType() ) )
        {
            return null;
        }

        return null;
    }


    public static void checkMember( final ClassMember members )
    {
        final String classMember = members.getClassName();
        final String classMethod = members.getClassMemberValue();
        final String[] strArray = members.getClassMemberValue().split( "\\(" )[0].split( " " );
        String className;
        if( strArray.length >= 2 )
        {
            className = strArray[1];
        }
        else
        {
            className = strArray[0];
        }

        if( classMethod.contains( "(" ) && classMethod.contains( ")" ) && classMember.startsWith( className ) )
        {
            members.setClassMemberType( Title.CONSTRUCTOR.getType() );
        }
        else if( classMethod.contains( "(" ) && classMethod.contains( ")" ) )
        {
            members.setClassMemberType( Title.METHOD.getType() );
        }
        else
        {
            members.setClassMemberType( Title.VARIABLE.getType() );
        }
    }
}