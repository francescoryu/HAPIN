package ch.francescoryu.hapin;
/**
 * @author: Francesco Ryu
 * @version: 1.0
 * @date 08.09.2022
 * @description A helping software for people who want to have everything compact(First project with JavaFx).
 */


import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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

public class DataHandler {

    static String filePath = "src/main/resources/save.txt";
    public static MenuMethods menuMethods = new MenuMethods();

    public static void readFileAsString(ArrayList<Button> buttons, GridPane gridPane, Button deleteButton) {
        gridPane.getChildren().removeAll(buttons);
        buttons.clear();
        try {
            createButtons(buttons, gridPane, true, deleteButton);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    public static void saveData(Stage stage, String input) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        fileChooser.setInitialDirectory(new File("src/main/resources/" + selectedFile.getName()));
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

    public static void createButtons(ArrayList<Button> buttons, GridPane gridPane, boolean withLink, Button deleteButton) throws IOException, URISyntaxException {
        int j = 5;
        int cntr = 0;
        List<String> lines = Files.readAllLines(new File(filePath).toPath());

        for (String s : lines) {
            String[] arr = s.split(";");
            //String path = arr[2];
            Button b = new Button(arr[0]);

            ImageView imageView = new ImageView(new Image(Files.newInputStream(Paths.get(arr[2]))));
            imageView.setFitHeight(25);
            imageView.setPreserveRatio(true);
            b.setGraphic(imageView);
            menuMethods.setButtonStyle(b);


            if (withLink) {
                b.setOnAction(e -> {
                    try {
                        Desktop.getDesktop().browse(new URI("https://" + arr[1]));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                });
                int finalCntr = cntr;
                b.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                        deleteButton.setOnAction(actionEvent -> {
                            System.out.println("rechts");
                            System.out.println(b.getText());
                            deleteButton(b, buttons, gridPane);

                        });
                    }
                });
            }

            buttons.add(b);
            gridPane.add(b, 0, j);
            j++;
            cntr++;
            ObservableList<Button> list = FXCollections.observableArrayList(buttons);
        }
    }

    public static void deleteButton(Button b, ArrayList<Button> buttons, GridPane pane) {
        pane.getChildren().remove(b);
        buttons.remove(b);
        try {
            remButtonFromFile(b);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void remButtonFromFile(Button b) throws IOException {
        List<String> lines = Files.readAllLines(new File(filePath).toPath());
        ArrayList<String> lines0 = new ArrayList<>(lines.size() - 1);
        BufferedWriter myWriter = Files.newBufferedWriter(Path.of(filePath));
        for (String s : lines0) {
            myWriter.write(s);
            myWriter.newLine();
        }
        myWriter.close();
    }

    private static void removeImage(String arr) {
        File deletingFile = new File(arr);
        deletingFile.delete();
        System.out.println("SUCCESSFULLY DELETED FILE");
    }


    public static void deleteCustomButton(Button button, String s) {

        button.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                button.setOnAction(actionEvent -> {

                });
            }
        });
    }
}
