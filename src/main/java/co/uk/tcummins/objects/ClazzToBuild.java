package co.uk.tcummins.objects;

import java.util.List;

/**
 * Created by Tom on 19/10/2016.
 */
public class ClazzToBuild
{
    private String classType;
    private String className;
    private List<Attribute> classVariables;
    private List<Method> classMethods;
    private List<Constructor> classConstructors;


    public ClazzToBuild( String classType, String className, List<Attribute> classVariables, List<Method> classMethods, List<Constructor> classConstructors )
    {
        this.classType = classType;
        this.className = className;
        this.classVariables = classVariables;
        this.classMethods = classMethods;
        this.classConstructors = classConstructors;
    }


    public String getClassType()
    {
        return classType;
    }


    public void setClassType( String classType )
    {
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


    public List<Attribute> getClassVariables()
    {
        return classVariables;
    }


    public void setClassVariables( List<Attribute> classVariables )
    {
        this.classVariables = classVariables;
    }


    public List<Method> getClassMethods()
    {
        return classMethods;
    }


    public void setClassMethods( List<Method> classMethods )
    {
        this.classMethods = classMethods;
    }


    public List<Constructor> getClassConstructors()
    {
        return classConstructors;
    }


    public void setClassConstructors( List<Constructor> classConstructors )
    {
        this.classConstructors = classConstructors;
    }


    @Override
    public String toString()
    {
        return classType + "\t\t" + className + "\t\t" + classVariables + "\t\t" + classMethods;
    }
}
