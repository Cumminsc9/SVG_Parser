package co.uk.tcummins.parsers;

import co.uk.tcummins.enums.Title;
import co.uk.tcummins.objects.ClassMember;
import co.uk.tcummins.objects.Constructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static co.uk.tcummins.parsers.GeneralParsers.*;

/**
 * Created by anil on 20/10/2016.
 */
public class ParseConstructor
{
    public static List<Constructor> parseConstructor(final ArrayList<ClassMember> constructor )
    {
        List<Constructor> constructorList = new ArrayList<>();
        LinkedHashMap<String, String> newHashMap = null;
        String accessType = null;
        String constructorName = null;

        for( ClassMember classMembers : constructor )
        {
            if( !classMembers.getClassMemberType().equals( Title.METHOD.getType() ) )
            {
                if( !classMembers.getClassMemberType().equals( Title.VARIABLE.getType() ) )
                {
                    final String classValue = classMembers.getClassMemberValue();

                    if( checkForArguments( classValue ) )
                    {
                        newHashMap = parseArguments( classValue );
                    }

                    constructorName = parseName( classValue );
                    accessType = parseAccessType( classValue );

                    if( accessType != null && constructorName != null )
                    {
                        if( newHashMap != null )
                        {
                            constructorList.add(new Constructor(accessType, constructorName, newHashMap));
                        }
                        else
                        {
                            constructorList.add( new Constructor( accessType, constructorName ) );
                        }
                    }
                }
            }
        }
        return constructorList;
    }
}