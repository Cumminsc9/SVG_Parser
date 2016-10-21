package co.uk.tcummins.creation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by anil on 20/10/2016.
 */
public class ClassWriter {
    private static String OSX = "Mac OS X";
    private static String WINDOWS = "Windows";

    public static boolean classWriter(String clazz, String className) {

        File file;

        if (System.getProperty("os.name").contains(OSX))
        {
            file = new File("src/main/resources/createdClasses/" + className + ".java");
        }
        else
        {
            //TODO: set with the users selected path from file chooser
            file = new File("src\\main\\resources\\createdClasses\\" + className + ".java");
        }

        // creates the file
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                System.out.println("File already exists. Removing existing file.");
                file.delete();
                file.createNewFile();
            }

            // creates a FileWriter Object
            FileWriter writer = new FileWriter(file);

            // Writes the content to the file
            writer.write(clazz);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}
