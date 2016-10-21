package co.uk.tcummins.objects;

/**
 * Created by Tom on 20/10/2016.
 */
public class ClassMember
{
    private String className;
    private String classValue;
    private String classType;


    public ClassMember( String className, String classValue, String classType )
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
