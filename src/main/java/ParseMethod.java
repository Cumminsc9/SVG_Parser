import enums.Title;
import objects.ClazzToBuild;
import objects.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 19/10/2016.
 */
public class ParseMethod
{
    private static String methodName;
    private static String methodType;
    private static String methodAccessType;
    private static List<String> methodArguments = new ArrayList<>();

    private static List<Method> methodList = new ArrayList<>();

    public static List<Method> parseMethod( final ArrayList<ClassMembers> method )
    {
        for( ClassMembers classMembers : method )
        {
            if( !classMembers.getClassType().equals(Title.CONSTRUCTOR.getType()) && !classMembers.getClassType().equals( Title.VARIABLE.getType()) )
            {
                System.out.println( classMembers.getClassValue() );
            }
        }

        //if( !method.getClassType().equals(Title.CONSTRUCTOR.getType()) && !method.getClassType().equals( Title.VARIABLE.getType()) )
        //{
        //    String stringMethod = method.getClassValue();

//
//            String[] foo = parseArguments( stringMethod );
//            if( foo != null ) {
//                for (String s : foo) {
//                    System.out.println(s);
//                }
//            }

            //String[] strings = parseArguments(stringMethod);
            //for (String string : strings) {
            //    System.out.println( string );
            //}


//            String[] foo = method.getClassValue().split( " " );
//
//            for( String s : foo )
//            {
//                System.out.println( s );
//            }
//
//            methodAccessType = foo[0];
//            methodName = foo[1];
//            methodType = foo[3];
//            //methodArguments

       // }
       //
        return methodList;
    }

    private static String[] parseArguments( String stringMethod )
    {
        String f = stringMethod.split( "[\\(\\)]")[1].trim();

        if(stringMethod.contains(",")) //contains comma
        {
            String[] newF = f.split(","); //array of arguements


                for (String s : newF)
                {
                    if(!s.isEmpty())
                    {
                        System.out.println(s);
                    }
                }
        }
        else
        {
            if(!f.isEmpty())
            {
                String[] f2 = f.split(" ");


                //System.out.println("f2[0]  = " + f2[0] );
                //System.out.println("f2[1]  = " + f2[2] );
            }
        }

        return new String[0];

//        String[] arguments = null;
//
//        if( !f.isEmpty() )
//        {
//            String[] tempArray = f.split(" ");
//            arguments = new String[]{ tempArray[0], tempArray[2] };
//        }

       // return arguments;
    }
}
