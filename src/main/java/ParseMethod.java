import objects.Method;

/**
 * Created by Tom on 19/10/2016.
 */
public class ParseMethod
{
    private static String methodName;
    private static String methodType;
    private static String methodAccessType;
    private static String methodArguments;

    public static Method parseMethod( final String method )
    {
        String[] foo = method.split( " " );

        for( String s : foo )
        {
            System.out.println( s );
        }

        methodAccessType = foo[0];
        methodName = foo[1];
        methodType = foo[3];
        //methodArguments


        return new Method();
    }
}
