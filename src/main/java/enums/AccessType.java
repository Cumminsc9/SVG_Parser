package enums;

/**
 * Created by Tom on 20/10/2016.
 */
public enum AccessType
{
    PUBLIC("+"),
    PROTECTED("#"),
    PRIVATE("-");

    private String accessType;


    AccessType( String accessType )
    {
        this.accessType = accessType;
    }


    public String getAccessType()
    {
        return accessType;
    }
}
