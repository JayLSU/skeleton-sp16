package sg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
class ReadFile {
    static FastLinkedList read(String fileName){
        FastLinkedList content = new FastLinkedList();
        try {
            File inputFile = new File(fileName);
            // Check to make sure that the input file exists!
            if (!inputFile.exists()) {
                System.out.println(fileName + " does not exist. We create a new empty one for you! So sweet, right?");
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
            }

            // Close the reader.
            bufferedReader.close();
            //Update content Text positions
            content.XYPosUpdate();
            System.out.println("Positions update done!");

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found! Exception was: " + fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println("Error when copying; exception was: " + ioException);
        }
        return content;
    }
}
