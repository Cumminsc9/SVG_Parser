package creation;

import objects.Attribute;
import objects.ClazzToBuild;
import objects.Constructor;
import objects.Method;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tom on 20/10/2016.
 */
public class BuildClass
{
    public static String buildClass( final ClazzToBuild clazzToBuild )
    {
        StringBuilder sb = new StringBuilder();
        sb.append("public class " + clazzToBuild.getClassName());
        sb.append("\n{\n");

        for( Attribute attribute : clazzToBuild.getClassVariables() )
        {
            sb.append("\t" + attribute.getAttributeAccessType() + " " +
                             attribute.getAttributeType() + " " +
                             attribute.getAttributeName() + ";\n");
        }

        sb.append("\n");
        //check for constructors and add them to string
        for (Constructor constructor : clazzToBuild.getClassConstructors())
        {
            sb.append("\t" + constructor.getConstructorAccessType() + " " +
                    constructor.getConstructorName() + "(");

            if(constructor.getConstructorArguments().size() < 1)
            {
                sb.append(")");
                sb.append("\n\t{\n");
            }
            else
            {
                Iterator<Map.Entry<String, String>> iter = constructor.getConstructorArguments().entrySet().iterator();
                while( iter.hasNext() )
                {
                    Map.Entry<String, String> entry = iter.next();

                    String key = entry.getKey();
                    String value = entry.getValue();

                    sb.append(" " + value + " " + key);
                    if(iter.hasNext())
                    {
                        sb.append(",");
                    }
                    else
                    {
                        sb.append(" )");
                        sb.append("\n\t{\n");
                    }
                }
            }
            sb.append("\n\t}");
            if(clazzToBuild.getClassConstructors().size() > 0)
                sb.append("\n\n");
        }

        for( Method method : clazzToBuild.getClassMethods() )
        {
            sb.append("\t" + method.getMethodAccessType() + " " +
                             method.getMethodType() + " " +
                             method.getMethodName() + "(");

            if(method.getMethodArguments().size() < 1)
            {
                sb.append(")");
                sb.append("\n\t{\n");
            }
            else
            {
                Iterator<Map.Entry<String, String>> iter = method.getMethodArguments().entrySet().iterator();
                    while( iter.hasNext() )
                    {
                        Map.Entry<String, String> entry = iter.next();

                        String key = entry.getKey();
                        String value = entry.getValue();

                        sb.append(" " + value + " " + key);
                        if(iter.hasNext())
                        {
                            sb.append(",");
                        }
                        else
                        {
                            sb.append(" )");
                            sb.append("\n\t{\n");
                        }
                    }
            }
            sb.append("\n\t}");
            if(clazzToBuild.getClassMethods().size() > 0)
                sb.append("\n\n");
        }


        sb.append("}");
        //System.out.println(sb.toString());
        return sb.toString();
    }
}
