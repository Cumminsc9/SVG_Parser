package objects;

/**
 * Created by c015406c on 18/10/2016.
 */
public class Method
{
    private String methodName;
    private String methodType;
    private String methodAccessType;
    private String methodArguments;
    private int id;


    public Method( int id, String methodName )
    {
        this.methodName = methodName;
        this.id = id;
    }


    public Method( String methodAccessType, String methodType, String methodName, String methodArguments )
    {
        this.methodAccessType = methodAccessType;
        this.methodArguments = methodArguments;
        this.methodName = methodName;
        this.methodType = methodType;
    }


    @Override
    public String toString()
    {
        return  id +" "+ methodName;
    }
}
