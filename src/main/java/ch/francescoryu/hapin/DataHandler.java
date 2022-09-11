package ch.francescoryu.hapin;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataHandler {

    static String filePath = "src/main/resources/save.txt";

    public static void writeFile(String input) {
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.write(input + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public static void readFileAsString() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void createButtons(Button[] buttons, GridPane gridPane) {
        MenuMethods menuMethods = new MenuMethods();
        int j = 5;
        for (int i = 0; i < getRows(); i++) {
            buttons[i] = new Button();
            menuMethods.setButtonStyle(buttons[i]);
            gridPane.add(buttons[i], 0, j);
            j++;
        }
    }
}