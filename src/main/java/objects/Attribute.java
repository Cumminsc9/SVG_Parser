package objects;

/**
 * Created by c015406c on 18/10/2016.
 */
public class Attribute
{
    private String attributeName;
    private String attributeType;
    private String attributeAccessType;
    private int id;

    public Attribute(int id, String attributeName) {
        this.attributeName = attributeName;
        this.id = id;
    }

    public Attribute(String attributeAccessType, String attributeType, String attributeName ) {
        this.attributeAccessType = attributeAccessType;
        this.attributeName = attributeName;
        this.attributeType = attributeType;
    }

    @Override
    public String toString() {
        return id+" "+attributeName;
    }
}
