package sg.util;
import java.io.*;
import java.util.LinkedList;

public class SaveFile {
    public static void save(String SaveName, FastLinkedList content){
        try {
            FileWriter writer = new FileWriter(SaveName);

            int intRead = -1;
            // Keep reading from the file input read() returns -1, which means the end of the file
            // was reached.
            if (content.isEmpty()){
                writer.write("");
            }else{
                FastLinkedList.Node startNode = content.getStartNode();
                while(startNode!=null){
                    char charRead = startNode.nodeText.getText().charAt(0);
                    writer.write(charRead);
                    startNode = startNode.next;
                }

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
