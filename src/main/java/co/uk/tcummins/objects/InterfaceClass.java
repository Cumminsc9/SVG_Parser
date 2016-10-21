package co.uk.tcummins.objects;

import java.util.HashMap;

/**
 * Created by Tom on 21/10/2016.
 */
public class InterfaceClass
{
    private String methodType;
    private String methodAccessType;
    private HashMap<String, String> methodArguments;
    private String methodName;


    public InterfaceClass( String methodAccessType, HashMap<String, String> methodArguments, String methodName, String methodType )
    {
        this.methodAccessType = methodAccessType;
        this.methodArguments = methodArguments;
        this.methodName = methodName;
        this.methodType = methodType;
    }
}
