package sg.util;
import java.io.*;
import java.util.LinkedList;

public class SaveFile {
    public static void save(String SaveName, LinkedList<String> content){
        try {
            FileWriter writer = new FileWriter(SaveName);

            int intRead = -1;
            // Keep reading from the file input read() returns -1, which means the end of the file
            // was reached.
            for(String SaveContent : content) {
                // The integer read can be cast to a char, because we're assuming ASCII.
                char charRead = SaveContent.charAt(0);
                writer.write(charRead);
            }

            System.out.println("Successfully save file " + SaveName);

            // Close the reader and writer.
            writer.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found! Exception was: " + fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println("Error when copying; exception was: " + ioException);
        }
    }
}
