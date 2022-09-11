package ch.francescoryu.hapin;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataHandler {

    static String filePath = "src/main/resources/save.txt";

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




    public static void saveData(Stage stage, String input) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        fileChooser.setInitialDirectory(new File("src/main/resources/" + selectedFile.getName()));
        System.out.println(selectedFile.getName());
        Path from = Paths.get(selectedFile.toURI());
        try {
            BufferedWriter myWriter = Files.newBufferedWriter(Path.of(filePath), StandardOpenOption.APPEND);
            myWriter.write(input + ";" + "src/main/resources/" + selectedFile.getName());
            myWriter.newLine();
            myWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        if (selectedFile != null) {
            Path to = Paths.get("src/main/resources/" + selectedFile.getName());
            try {
                Files.copy(from, to);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getInputFromTextField(TextField inputButtonName, TextField inputButtonUrl) {
        String inputFromTextField = inputButtonName.getText() + ";" + inputButtonUrl.getText();
        System.out.println(inputFromTextField);

        return inputFromTextField;
    }

    public static void createButtons(ArrayList<Button> buttons, GridPane gridPane) throws IOException, URISyntaxException {
        MenuMethods menuMethods = new MenuMethods();
        int j = 5;

        List<String> lines = Files.readAllLines(new File(filePath).toPath());

        for (String s : lines) {
            String[] arr = s.split(";");
            Button b = new Button(arr[0]);
            b.setOnAction(e -> {
                try {
                    Desktop.getDesktop().browse(new URI("https://" + arr[1]));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            });
            ImageView imageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream(arr[2]))));
            imageView.setFitHeight(25);
            imageView.setPreserveRatio(true);
            b.setGraphic(imageView);
            System.out.println(arr[2]);
            menuMethods.setButtonStyle(b);
            buttons.add(b);
            gridPane.add(b, 0, j);
            j++;
        }
    }

}