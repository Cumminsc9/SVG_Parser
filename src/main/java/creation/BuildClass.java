package creation;

import java.util.Iterator;
import java.util.Map;

import objects.Attribute;
import objects.ClazzToBuild;
import objects.Constructor;
import objects.Method;

/**
 * Created by Tom on 20/10/2016.
 */
public class BuildClass
{
    public static String buildClass( final ClazzToBuild clazzToBuild )
    {
        StringBuilder sb = new StringBuilder();
        sb.append( "public class " + clazzToBuild.getClassName() );
        sb.append( "\n{\n" );

        //variables
        for( Attribute attribute : clazzToBuild.getClassVariables() )
        {
            sb.append( "\t" + attribute.getAttributeAccessType() + " " + attribute.getAttributeType() + " "
                    + attribute.getAttributeName() + ";\n" );
        }

        sb.append( "\n" );
        //check for constructors and add them to string
        for( Constructor constructor : clazzToBuild.getClassConstructors() )
        {
            sb.append( "\t" + constructor.getConstructorAccessType() + " " + constructor.getConstructorName() + "(" );

            if( constructor.getConstructorArguments() == null || constructor.getConstructorArguments().size() < 1 )
            {
                sb.append( ")" );
                sb.append( "\n\t{\n" );
            }
            else
            {
                Iterator<Map.Entry<String, String>> iter = constructor.getConstructorArguments().entrySet().iterator();
                while( iter.hasNext() )
                {
                    Map.Entry<String, String> entry = iter.next();

                    String key = entry.getKey();
                    String value = entry.getValue();

                    sb.append( " " + value + " " + key );
                    if( iter.hasNext() )
                    {
                        sb.append( "," );
                    }
                    else
                    {
                        sb.append( " )" );
                        sb.append( "\n\t{\n" );

                        for( Attribute attribute : clazzToBuild.getClassVariables() )
                        {
                            for( Map.Entry<String, String> entryKey : constructor.getConstructorArguments().entrySet() )
                            {
                                if( attribute.getAttributeName().toLowerCase().contains( entryKey.getKey().toLowerCase() ) )
                                {
                                    sb.append( "\t\tthis." + attribute.getAttributeName() + " = " + entryKey.getKey() + ";\n" );
                                }
                            }

                        }
                    }
                }
            }
            sb.append( "\n\t}" );
            if( clazzToBuild.getClassConstructors().size() > 0 )
                sb.append( "\n\n" );
        }

        //methods
        for( Method method : clazzToBuild.getClassMethods() )
        {
            sb.append( "\t" + method.getMethodAccessType() + " " + method.getMethodType() + " " + method.getMethodName() + "(" );

            if( method.getMethodArguments() == null || method.getMethodArguments().size() < 1 )
            {
                sb.append( ")" );
                sb.append( "\n\t{\n" );

                for( Attribute attribute : clazzToBuild.getClassVariables() )
                {
                    if( method.getMethodName().toLowerCase().contains( attribute.getAttributeName().toLowerCase() ) )
                    {
                        sb.append( "\t\treturn " + attribute.getAttributeName() + ";" );
                    }
                }
            }
            else
            {
                Iterator<Map.Entry<String, String>> iter = method.getMethodArguments().entrySet().iterator();
                while( iter.hasNext() )
                {
                    Map.Entry<String, String> entry = iter.next();

                    String key = entry.getKey();
                    String value = entry.getValue();

                    sb.append( " " + value + " " + key );
                    if( iter.hasNext() )
                    {
                        sb.append( "," );
                    }
                    else
                    {
                        sb.append( " )" );
                        sb.append( "\n\t{\n" );

                        for( Attribute attribute : clazzToBuild.getClassVariables() )
                        {
                            if( key.toLowerCase().contains( attribute.getAttributeName().toLowerCase() ) )
                            {
                                sb.append( "\t\tthis." + attribute.getAttributeName() + " = " + key + ";\n" );
                            }
                        }
                    }
                }
            }
            sb.append( "\n\t}" );
            if( clazzToBuild.getClassMethods().size() > 0 )
                sb.append( "\n\n" );
        }


        sb.append( "}" );
        //System.out.println(sb.toString());
        return sb.toString();
    }
}
