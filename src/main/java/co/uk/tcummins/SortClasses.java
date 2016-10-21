package co.uk.tcummins;

import co.uk.tcummins.enums.Title;
import co.uk.tcummins.objects.ClassMember;
import co.uk.tcummins.objects.Relation;

import java.util.*;

/**
 * Created by Tom on 19/10/2016.
 */
public class SortClasses
{
    protected static List<Map<String, String>> hashMapList = new ArrayList<>();
    protected static List<ArrayList<ClassMember>> classMap = new ArrayList<>();


    public static List<ArrayList<ClassMember>> arrangeMethodAndVariables( List<Relation> relations )
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
                ArrayList<ClassMember> tempClass = new ArrayList<>();

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
                                tempClass.add( new ClassMember( m.getValue(), relation.getValue(), relation.getType() ) );
                            }
                        }
                    }
                }
                classMap.add( tempClass );
            }
        }

        return classMap;
    }
}