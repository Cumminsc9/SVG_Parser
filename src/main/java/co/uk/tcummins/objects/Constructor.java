package co.uk.tcummins.objects;

import java.util.HashMap;

/**
 * Created by anil on 20/10/2016.
 */
public class Constructor
{
    private String constructorName;
    private String constructorAccessType;
    private HashMap<String, String> constructorArguments;


    public Constructor( String constructorAccessType, String constructorName, HashMap<String, String> constructorArguments )
    {
        this.constructorAccessType = constructorAccessType;
        this.constructorName = constructorName;
        this.constructorArguments = constructorArguments;
    }

    public Constructor(String accessType, String constructorName) {
        this.constructorAccessType = accessType;
        this.constructorName = constructorName;
    }


    public String getConstructorName()
    {
        return constructorName;
    }


    public void setConstructorName( String constructorName )
    {
        this.constructorName = constructorName;
    }


    public String getConstructorAccessType()
    {
        return constructorAccessType;
    }


    public void setConstructorAccessType( String constructorAccessType )
    {
        this.constructorAccessType = constructorAccessType;
    }


    public HashMap<String, String> getConstructorArguments()
    {
        return constructorArguments;
    }


    public void setConstructorArguments( HashMap<String, String> constructorArguments )
    {
        this.constructorArguments = constructorArguments;
    }
}
