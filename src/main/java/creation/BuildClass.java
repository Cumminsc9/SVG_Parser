package creation;

import objects.Attribute;
import objects.ClazzToBuild;
import objects.Method;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tom on 20/10/2016.
 */
public class BuildClass
{
    public static void buildClass( final ClazzToBuild clazzToBuild )
    {
        StringBuilder sb = new StringBuilder();
        sb.append("public class" + clazzToBuild.getClassName());
        sb.append("\n{\n");

        for( Attribute attribute : clazzToBuild.getClassVariables() )
        {
            sb.append("\t" + attribute.getAttributeAccessType() + " " +
                             attribute.getAttributeType() + " " +
                             attribute.getAttributeName() + ";\n");
        }

        sb.append("\n");

        for( Method method : clazzToBuild.getClassMethods() )
        {
            sb.append("\t" + method.getMethodAccessType() + " " +
                             method.getMethodType() + " " +
                             method.getMethodName() + "(");

            if(method.getMethodArguments() == null)
            {
                sb.append(")");
                sb.append("\n\t{\n");
            }
            else
            {
                for (Map.Entry<String, String> entry : method.getMethodArguments().entrySet())
                {
                    String key = entry.getKey();
                    String value = entry.getValue();

                    sb.append(" " + entry.getValue() + " " + entry.getKey());
                }
            }
            sb.append("\n\t}");
        }
        sb.append("\n}");
        System.out.println(sb.toString());
    }
}
