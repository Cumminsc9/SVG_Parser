package co.uk.tcummins.objects;

/**
 * Created by Tom on 19/10/2016.
 */
public class Relation
{
    private String location;
    private String value;
    private String type;


    public Relation( String location, String value, String type )
    {
        this.location = location;
        this.value = value;
        this.type = type;
    }


    public String getLocation()
    {
        return location;
    }


    public void setLocation( String location )
    {
        this.location = location;
    }


    public String getValue()
    {
        return value;
    }


    public void setValue( String value )
    {
        this.value = value;
    }


    public String getType()
    {
        return type;
    }


    public void setType( String type )
    {
        this.type = type;
    }


    @Override
    public String toString()
    {
        if( type != null )
        {
            return "Location: " + location + "\tType: " + type + "\t\tValue: " + value  ;
        }
        else
        {
            return "Location: " + location + "\tValue: " + value;
        }
    }
}
