import enums.Title;

/**
 * Created by Tom on 19/10/2016.
 */
public class CheckTitle
{
    public static String checkTitle( final String title )
    {
        if( title.contains( Title.CLAZZ.getType() ) )
        {
            return Title.CLAZZ.getType();
        }
        if( title.contains( Title.ENUM.getType() ) )
        {
            return Title.ENUM.getType();
        }
        if( title.contains( Title.INTERFACE.getType() ) )
        {
            return Title.INTERFACE.getType();
        }
        if( title.contains( Title.MEMBER.getType() ) )
        {
            return Title.MEMBER.getType();
        }
        if( title.contains( Title.PACKAGE.getType() ) )
        {
            return null;
        }
        if( title.contains( Title.NOTE.getType() ) )
        {
            return null;
        }
        if( title.contains( Title.PAGE.getType() ) )
        {
            return null;
        }
        if( title.contains( Title.SHEET.getType() ) )
        {
            return null;
        }
        if( title.contains( Title.INHERITANCE.getType() ) )
        {
            return null;
        }
        if( title.contains( Title.ASSOCIATION.getType() ) )
        {
            return null;
        }
        if( title.contains( Title.AGGREGATION.getType() ) )
        {
            return null;
        }
        if( title.contains( Title.COMPOSITION.getType() ) )
        {
            return null;
        }
        if( title.contains( Title.DEPENDENCY.getType() ) )
        {
            return null;
        }
        if( title.contains( Title.DIRECTED_ASSOCIATION.getType() ) )
        {
            return null;
        }
        if( title.contains( Title.INTERFACE_REALIZATION.getType() ) )
        {
            return null;
        }

        return null;
    }


    public static void checkMember( final ClassMembers members )
    {
        final String classMember = members.getClassName();
        final String classMethod = members.getClassValue();
        final String className = members.getClassValue().split( "\\(" )[0].split(" ")[1];

        if( classMethod.contains( "(" ) && classMethod.contains( ")" ) && classMember.startsWith( className ) )
        {
            members.setClassType( Title.CONSTRUCTOR.getType() );
        }
        else if( classMethod.contains( "(" ) && classMethod.contains( ")" ) )
        {
            members.setClassType( Title.METHOD.getType() );
        }
        else
        {
            members.setClassType( Title.VARIABLE.getType() );
        }
    }
}