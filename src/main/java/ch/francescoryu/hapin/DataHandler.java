package ch.francescoryu.hapin;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {

    static String filePath = "src/main/resources/save.txt";

    public static void writeFile(String input) {
        try {
            BufferedWriter myWriter = Files.newBufferedWriter(Path.of(filePath), StandardOpenOption.APPEND);
            myWriter.write(input);
            myWriter.newLine();
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


    public static void createButtons(ArrayList<Button> buttons, GridPane gridPane) throws IOException {
        MenuMethods menuMethods = new MenuMethods();
        int j = 5;

        List<String> lines = Files.readAllLines(new File(filePath).toPath());

        for (String s : lines) {
            String[] arr = s.split(" ");
            Button b = new Button(arr[0]);
            b.setOnAction(e -> {
                try {
                    Desktop.getDesktop().browse(new URI("https://" + arr[1]));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            });
            menuMethods.setButtonStyle(b);
            buttons.add(b);
            gridPane.add(b, 0, j);
            j++;
        }
    }

}