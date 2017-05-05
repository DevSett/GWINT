package app;

import java.io.IOException;

/**
 * Created by killsett on 05.05.17.
 */
public class dsa {
    public static void main(String[] args) {
        int inChar;
        System.out.println("Enter a Character:");
        try {
            inChar = System.in.read();
            System.out.print("You entered ");
            System.out.println(inChar);
        } catch (IOException e) {
            System.out.println("Error reading from user");
        }
    }
}
