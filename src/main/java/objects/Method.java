package objects;

import java.util.HashMap;

/**
 * Created by c015406c on 18/10/2016.
 */
public class Method
{
    private String methodName;

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodAccessType() {
        return methodAccessType;
    }

    public void setMethodAccessType(String methodAccessType) {
        this.methodAccessType = methodAccessType;
    }

    public HashMap<String, String> getMethodArguments() {
        return methodArguments;
    }

    public void setMethodArguments(HashMap<String, String> methodArguments) {
        this.methodArguments = methodArguments;
    }

    private String methodType;
    private String methodAccessType;
    private HashMap<String, String> methodArguments;


    public Method( String methodAccessType, String methodType, String methodName )
    {
        this.methodAccessType = methodAccessType;
        this.methodType = methodType;
        this.methodName = methodName;
    }


    public Method( String methodAccessType, String methodType, String methodName, HashMap<String, String> methodArguments )
    {
        this.methodAccessType = methodAccessType;
        this.methodArguments = methodArguments;
        this.methodName = methodName;
        this.methodType = methodType;
    }


    @Override
    public String toString()
    {
        if( methodArguments != null )
        {
            return methodAccessType + "\t" + methodType +"\t"+ methodName +"\t"+ methodArguments;
        }
        else
        {
            return methodAccessType + "\t" + methodType +"\t"+ methodName;
        }
    }
}
