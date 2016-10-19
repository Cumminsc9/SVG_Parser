package enums;

/**
 * Created by Tom on 19/10/2016.
 */
public enum Title
{
    CLAZZ("Class"),
    ENUM("Enumeration"),
    INTERFACE("Interface"),
    MEMBER("Member"),
    PACKAGE("Package"),
    NOTE("Note"),
    PAGE("Page"),
    SHEET("Sheet"),
    INHERITANCE("Inheritance"),
    ASSOCIATION("Association"),
    AGGREGATION("Aggregation"),
    COMPOSITION("Composition"),
    DEPENDENCY("Dependency"),
    DIRECTED_ASSOCIATION("Directed Association"),
    INTERFACE_REALIZATION("Interface Realization"),
    CONSTRUCTOR("Constructor"),
    METHOD("Method"),
    VARIABLE("Variable");
    
    private String type;


    Title(String type )
    {
        this.type = type;
    }


    public String getType()
    {
        return type;
    }
}
