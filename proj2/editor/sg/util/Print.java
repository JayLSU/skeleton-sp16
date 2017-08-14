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
        content = ReadFile.read(Filename);
        return content;
    }
}
