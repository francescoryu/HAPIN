package ch.francescoryu.hapin;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public static String readFileAsString() throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(filePath)));
        return data;
    }

    public static long getRows() {
        Path path = Paths.get(filePath);
        long lines = 0;

        try {
            lines = Files.lines(path).count();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void createButtons(Button[] buttons, GridPane gridPane) {
        int j = 5;
        for (int i = 0; i < getRows(); i++) {
            buttons[i] = new Button();
            gridPane.add(buttons[i], 0, j);
            j++;
        }
    }
}