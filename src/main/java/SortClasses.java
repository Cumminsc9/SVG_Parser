import enums.Title;
import objects.Relation;

import java.util.*;

/**
 * Created by Tom on 19/10/2016.
 */
public class SortClasses
{
    private static List<Map<String, String>> hashMapList = new ArrayList<>();
    private static List<ArrayList<ClassMembers>> classMap = new ArrayList<>();


    public static List<ArrayList<ClassMembers>> arrangeMethodAndVariables(List<Relation> relations )
    {
        for( Relation relation : relations )
        {
            if( relation.getType().equals( Title.CLAZZ.getType() ) )
            {
                Map<String, String> tempClass = new HashMap<>();
                tempClass.put( relation.getLocation(), relation.getValue() );
                hashMapList.add( tempClass );
            }
        }

        for( Map<String, String> hashMap : hashMapList )
        {
            for( Map.Entry<String, String> m : hashMap.entrySet() )
            {
                ArrayList<ClassMembers> tempClass = new ArrayList<>();

                for( Relation relation : relations )
                {
                    if( !relation.getType().equals( Title.CLAZZ.getType() ) )
                    {
                        double memberLocation = Double.parseDouble( relation.getLocation() );
                        double classLocation = Double.parseDouble( m.getKey() );
                        double f = classLocation - memberLocation;

                        if( f <= 0 || f <= -4 )
                        {
                            if( f <= -10 )
                            {
                                continue;
                            }
                            else
                            {
                                tempClass.add( new ClassMembers( m.getValue(), relation.getValue(), relation.getType() ) );
                            }
                        }
                    }
                }
                classMap.add( tempClass );
            }
        }

        //        for( ArrayList<ClassMembers> tempObjects : classMap )
        //        {
        //            for( ClassMembers tempObject : tempObjects )
        //            {
        //                System.out.println( tempObject );
        //            }
        //        }

        return classMap;
    }
}



class ClassMembers
{
    private String className;
    private String classValue;
    private String classType;


    ClassMembers(String className, String classValue, String classType )
    {
        this.className = className;
        this.classValue = classValue;
        this.classType = classType;
    }


    public String getClassName()
    {
        return className;
    }


    public void setClassName( String className )
    {
        this.className = className;
    }


    public String getClassValue()
    {
        return classValue;
    }


    public void setClassValue( String classValue )
    {
        this.classValue = classValue;
    }


    public String getClassType()
    {
        return classType;
    }


    public void setClassType( String classType )
    {
        this.classType = classType;
    }


    @Override
    public String toString()
    {
        return className + "  " + classValue + "  " + classType;
    }
}