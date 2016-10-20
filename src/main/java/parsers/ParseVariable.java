package parsers;

import enums.AccessType;
import enums.CollectionType;
import enums.Title;
import objects.Attribute;
import objects.ClassMember;
import objects.Method;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Tom on 19/10/2016.
 */
public class ParseVariable
{
    private static List<Attribute> variableList = new ArrayList<>();

    public static List<Attribute> parseVariable(final ArrayList<ClassMember> method )
    {
        List<Attribute> variableList = new ArrayList<>();
        //LinkedHashMap<String, String> newHashMap = null;
        String accessType = null;
        String variableName = null;
        String returnType = null;


        for( ClassMember classMembers : method )
        {
            if( !classMembers.getClassType().equals( Title.CONSTRUCTOR.getType() ) && !classMembers.getClassType().equals( Title.METHOD.getType() ) )
            {
                if( classMembers.getClassType().equals( Title.VARIABLE.getType() ) )
                {
                    final String classValue = classMembers.getClassValue();

                    variableName = parseVariableName( classValue );
                    accessType = parseAccessType( classValue );
                    returnType = parseReturnType(classValue);


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
//        if(stringMethod.contains(CollectionType.ARRAY.getCollectionType()))//contains characters
//        {
//            return stringMethod.split(" ")[3];
//        }

//        else if(stringMethod.contains(CollectionType.MAP.getCollectionType()))
//        {
//            return stringMethod.split(" ")[3];
//        }
          if(stringMethod.contains(CollectionType.LIST.getCollectionType()) || stringMethod.contains(CollectionType.MAP.getCollectionType()))
          {

              return stringMethod.split(":")[1].trim();
          }
          else
          {
              return stringMethod.split(" ")[3];
          }
        //return stringMethod.split( "[^\\w\\s]" )[2].trim();
    }

    private static String parseAccessType( final String stringMethod )
    {
        // Regex string = "^[^(]\w*"
        final String accessType = stringMethod.substring( 0, 1 );

        if( accessType.equals( AccessType.PUBLIC.getAccessType() ) )
        {
            return "public";
        }
        else if( accessType.equals( AccessType.PROTECTED.getAccessType() ) )
        {
            return "protected";
        }
        else if( accessType.equals( AccessType.PRIVATE.getAccessType() ) )
        {
            return "private";
        }
        else
        {
            return "public";
        }
    }

}
