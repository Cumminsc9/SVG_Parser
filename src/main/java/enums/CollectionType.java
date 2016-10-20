package enums;

/**
 * Created by anil on 20/10/2016.
 */
public enum CollectionType
{
    ARRAY("["),
    LIST("List<"),
    MAP("Map<");


    private String collectionType;

    CollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public String getCollectionType() {
        return collectionType;
    }
}
