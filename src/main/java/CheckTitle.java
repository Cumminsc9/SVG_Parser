import enums.Title;

/**
 * Created by Tom on 19/10/2016.
 */
class CheckTitle
{
    static String checkTitle( String title )
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
}