package co.uk.tcummins.creation;

import java.util.Iterator;
import java.util.Map;

import co.uk.tcummins.enums.Title;
import co.uk.tcummins.objects.Attribute;
import co.uk.tcummins.objects.ClazzToBuild;
import co.uk.tcummins.objects.Constructor;
import co.uk.tcummins.objects.Method;

/**
 * Created by Tom on 20/10/2016.
 */
public class BuildClass
{
    public static String buildClass( final ClazzToBuild clazzToBuild )
    {
        StringBuilder sb = new StringBuilder();
                if(clazzToBuild.getClassType().toLowerCase().equals(Title.ENUM.getType().toLowerCase()))
                {
                    //if enum then do this
                    sb.append( "public " + clazzToBuild.getClassType().toLowerCase().substring(0,4) + " " + clazzToBuild.getClassName() );
                }
                else
                {
                    //otherwise continue as normal
                    sb.append( "public " + clazzToBuild.getClassType().toLowerCase() + " " + clazzToBuild.getClassName() );
                }
        sb.append( "\n{\n" );

        int i = 0;
        //variables
        for( Attribute attribute : clazzToBuild.getClassVariables() )
        {

            if(clazzToBuild.getClassType().toLowerCase().equals(Title.ENUM.getType().toLowerCase()))
            {
                //if enum do this
                //sb.append( "\t" + attribute.getAttributeName() );
                if (i+1 < clazzToBuild.getClassVariables().size())
                {
                    sb.append( "\t" + attribute.getAttributeName() + ",\n" );
                }
                else
                {
                    sb.append( "\t" + attribute.getAttributeName() + ";" );
                }

            }
            else
            {
                sb.append("\t" + attribute.getAttributeAccessType() + " " + attribute.getAttributeType() + " "
                        + attribute.getAttributeName() + ";\n");
            }
            i++;
        }

        sb.append( "\n" );
        //check for constructors and add them to string
        for( Constructor constructor : clazzToBuild.getClassConstructors() )
        {
            sb.append( "\t" + constructor.getConstructorAccessType() + " " + constructor.getConstructorName() + "(" );

            if( constructor.getConstructorArguments() == null || constructor.getConstructorArguments().size() < 1 )
            {
                {

                }
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
                if(clazzToBuild.getClassType().toLowerCase().equals(Title.INTERFACE.getType().toLowerCase()))
                {
                    //if interface do this
                    sb.append(");");
                }
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
                        if(clazzToBuild.getClassType().toLowerCase().equals(Title.INTERFACE.getType().toLowerCase()))
                        {
                            //if interface do this
                            sb.append(");");
                        }
                        else
                        {
                            sb.append(" )");
                            sb.append("\n\t{\n");

                            for (Attribute attribute : clazzToBuild.getClassVariables()) {
                                if (key.toLowerCase().contains(attribute.getAttributeName().toLowerCase())) {
                                    sb.append("\t\tthis." + attribute.getAttributeName() + " = " + key + ";\n");
                                }
                            }
                        }
                    }
                }
            }
            if(clazzToBuild.getClassType().toLowerCase().equals(Title.INTERFACE.getType().toLowerCase()))
            {
                //if interface do this
                sb.append("\n");

            }
            else
            {
                sb.append("\n\t}");
                if (clazzToBuild.getClassMethods().size() > 0)
                    sb.append("\n\n");
            }
        }


        sb.append( "}" );
        //System.out.println(sb.toString());
        return sb.toString();
    }
}
