package objects;

/**
 * Created by c015406c on 18/10/2016.
 */
public class Clazz
{
    private String className;
    private int id;

    public Clazz(int id, String className) {
        this.className = className;
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " " + className;
    }
}
