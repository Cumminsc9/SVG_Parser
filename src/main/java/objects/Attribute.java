package objects;

/**
 * Created by c015406c on 18/10/2016.
 */
public class Attribute
{
    private String attributeName;
    private String attributeType;
    private String attributeAccessType;

    public Attribute(String attributeAccessType, String attributeType, String attributeName) {
        this.attributeAccessType = attributeAccessType;
        this.attributeType = attributeType;
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public String getAttributeAccessType() {
        return attributeAccessType;
    }

    public void setAttributeAccessType(String attributeAccessType) {
        this.attributeAccessType = attributeAccessType;
    }

    @Override
    public String toString() {
        return attributeAccessType + "\t" + attributeType + "\t" + attributeName;
    }
}
