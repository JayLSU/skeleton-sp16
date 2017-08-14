package sg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
public class Print {
    public static LinkedList<String> print(String[] args){
        LinkedList<String> content = new LinkedList<>();
        if (args.length < 1) {
            System.out.println("No filename is provided!");
            System.exit(1);
        }
        else if (args.length >= 2) {
            String option = args[1];
            if (option.equals("debug")) {
                System.out.println("Debugging starts!");
            }else{
                System.out.println("The second argument can only be \"debug\"! ");
                System.exit(1);
            }
        }

        String Filename = args[0];
        try {
            File inputFile = new File(Filename);
            // Check to make sure that the input file exists!
            if (!inputFile.exists()) {
                System.out.println(Filename + " does not exist. We create a new empty one for you! So sweet, right?");
                return content;
            }


            FileReader reader = new FileReader(inputFile);
            // It's good practice to read files using a buffered reader.  A buffered reader reads
            // big chunks of the file from the disk, and then buffers them in memory.  Otherwise,
            // if you read one character at a time from the file using FileReader, each character
            // read causes a separate read from disk.  You'll learn more about this if you take more
            // CS classes, but for now, take our word for it!
            BufferedReader bufferedReader = new BufferedReader(reader);

            // Create a FileWriter to write to outputFilename. FileWriter will overwrite any data
            // already in outputFilename.
            //FileWriter writer = new FileWriter(Filename);

            int intRead = -1;
            // Keep reading from the file input read() returns -1, which means the end of the file
            // was reached.
            while ((intRead = bufferedReader.read()) != -1) {
                // The integer read can be cast to a char, because we're assuming ASCII.
                char charRead = (char) intRead;
                content.add(Character.toString(charRead));
             //   writer.write(charRead);
            }

            System.out.println("Successfully copied file " + Filename + " to "
                    + Filename);

            // Close the reader and writer.
            bufferedReader.close();
            //writer.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found! Exception was: " + fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println("Error when copying; exception was: " + ioException);
        }
        return content;
    }
}
