package co.uk.tcummins.objects;

/**
 * Created by Tom on 20/10/2016.
 */
public class ClassMember
{
    private String className;
    private String classMemberType;
    private String classMemberValue;


    public ClassMember( String className, String classMemberValue, String classMemberType )
    {
        this.className = className;
        this.classMemberValue = classMemberValue;
        this.classMemberType = classMemberType;
    }


    public String getClassName()
    {
        return className;
    }


    public void setClassName( String className )
    {
        this.className = className;
    }


    public String getClassMemberValue()
    {
        return classMemberValue;
    }


    public void setClassMemberValue( String classMemberValue )
    {
        this.classMemberValue = classMemberValue;
    }


    public String getClassMemberType()
    {
        return classMemberType;
    }


    public void setClassMemberType( String classMemberType )
    {
        this.classMemberType = classMemberType;
    }


    @Override
    public String toString()
    {
        return className + "  " + classMemberValue + "  " + classMemberType;
    }
}
