package ch.francescoryu.hapin;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.components.buttons.DeleteButton;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: Francesco Ryu
 * @version: 1.0
 * @date 29.09.2022
 * @description Anything that has to do with backend.
 */

public class DataHandler {

    static String saveFilePath = "src/main/resources/txt-files/save.txt";
    static String loginFilePath = "src/main/resources/txt-files/login.txt";
    static String todoFilePath = "src/main/resources/txt-files/todo.txt";
    static String userName;

    ArrayList<Button> buttons = new ArrayList<>();
    Button button = new Button();

    public static void reloadButtonList(ArrayList<Button> buttons, GridPane gridPane, Button deleteButton) {
        gridPane.getChildren().removeAll(buttons);
        buttons.clear();
        try {
            createButtons(buttons, gridPane, true, deleteButton);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveButtonData(Stage stage, String input) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);

        File file = new File("src/main/resources/buttonImages/" + selectedFile.getName());

        fileChooser.setInitialDirectory(new File("src/main/resources/buttonImages/" + selectedFile.getName()));
        Path from = Paths.get(selectedFile.toURI());
        try {
            BufferedWriter myWriter = Files.newBufferedWriter(Path.of(saveFilePath), StandardOpenOption.APPEND);
            myWriter.write(input + ";" + "src/main/resources/buttonImages/" + selectedFile.getName());
            myWriter.newLine();
            myWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        if (selectedFile.exists()) {

            Path to = Paths.get("src/main/resources/buttonImages/" + selectedFile.getName());
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

    public static void writeTodoFile(TextField textField, ComboBox<String> comboBox, ArrayList<Button> buttons, GridPane gridPane) throws IOException {

        if (textField.getText().matches("")) {
            textField.setPromptText("You need to type something");
        } else {
            if (comboBox.getValue().matches("High")) {
                writeFile("1;" + textField.getText());
            } else if (comboBox.getValue().matches("Medium")) {
                writeFile("2;" + textField.getText());
            } else if (comboBox.getValue().matches("Low")) {
                writeFile("3;" + textField.getText());
            }
        }
        textField.setText("");
        readTodoFile(gridPane, buttons);
    }

   public static void reloadTodoFile(Button button, GridPane gridPane, ArrayList<Button> buttons) throws IOException {
        buttons.remove(button);
        gridPane.getChildren().remove(button);

        readTodoFile(gridPane, buttons);
    }

    public static void clearTodoFile(GridPane gridPane, ArrayList<Button> buttons) {
        buttons.remove(buttons);
        gridPane.getChildren().remove(buttons);
    }

    public static void readTodoFile(GridPane gridPane, ArrayList<Button> buttons) throws IOException {
        List<String> lines = Files.readAllLines(new File(todoFilePath).toPath());
        int i = 0;
        clearTodoFile(gridPane, buttons);
        Collections.sort(lines);
        for (String s : lines) {
            String[] arr = s.split(";");
            Button button1 = new Button(arr[1]);
            button1.setBackground(null);
            if (Objects.equals(arr[0], "1")) {
                button1.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: red");
            }
            else if (Objects.equals(arr[0], "2")) {
                button1.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: orange;");
            }
            else if (Objects.equals(arr[0], "3")) {
                button1.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: green");
            }
            gridPane.add(button1, 0, i);
            i++;
        }

    }

    public static void writeFile(String input) {
        try {
            BufferedWriter myWriter = Files.newBufferedWriter(Path.of(todoFilePath), StandardOpenOption.APPEND);
            myWriter.write(input);
            myWriter.newLine();
            myWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }


    public static void createButtons(ArrayList<Button> buttons, GridPane gridPane, boolean withLink, Button deleteButton) throws IOException, URISyntaxException {
        int j = 5;
        List<String> lines = Files.readAllLines(new File(saveFilePath).toPath());

        for (String s : lines) {
            String[] arr = s.split(";");
            Button b = new Button(arr[0]);

            ImageView imageView = new ImageView(new Image(Files.newInputStream(Paths.get(arr[2]))));
            imageView.setFitHeight(25);
            imageView.setPreserveRatio(true);
            b.setGraphic(imageView);
            MenuMethods.setButtonStyle(b);


            if (withLink) {
                b.setOnAction(e -> {
                    try {
                        Desktop.getDesktop().browse(new URI("https://" + arr[1]));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                });
                b.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                        deleteButton.setOnAction(actionEvent -> {
                            System.out.println("rechts");
                            System.out.println(b.getText());
                            deleteButton(b, buttons, gridPane, saveFilePath);
                            removeImage(arr[2]);
                        });
                    }
                });
            }

            buttons.add(b);
            gridPane.add(b, 0, j);
            j++;
        }
    }


    public static void deleteButton(Button b, ArrayList<Button> buttons, GridPane pane, String path) {
        pane.getChildren().remove(b);
        buttons.remove(b);
        try {
            remButtonFromFile(b.getText(), path, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void remButtonFromFile(String str, String path, int i) throws IOException {
        List<String> lines = Files.readAllLines(new File(path).toPath());
        BufferedWriter myWriter = Files.newBufferedWriter(Path.of(path));
        for (String s : lines) {
            if (!str.equals(s.split(";")[i])) {
                myWriter.write(s);
                myWriter.newLine();
            }
        }
        myWriter.close();
    }

    private static void removeImage(String arr) {
        File deletingFile = new File(arr);
        deletingFile.delete();
        System.out.println("SUCCESSFULLY DELETED FILE");
    }

    public static void checkLoginData(String userName, PasswordField pwdField, Stage actualStage) throws IOException {
        List<String> lines = Files.readAllLines(new File(loginFilePath).toPath());
        for (String s : lines) {
            String[] arrString = s.split(";");

            if (userName.equals(arrString[0]) && pwdField.getText().equals(arrString[1])) {
                System.out.println("SUCCESSFUL");
                Menu menu = new Menu();
                Stage stage = new Stage();
                menu.start(stage);
                actualStage.close();
            } else {

            }
        }
    }

    public static void loadUserToComboBox(ComboBox<String> comboBox) throws IOException {
        List<String> lines = Files.readAllLines(new File(loginFilePath).toPath());
        for (String s : lines) {
            String[] arrString = s.split(";");
            String name = arrString[0];
            comboBox.getItems().add(name);
        }
    }

    public static void setUserName(String userNameValue) {
        userName = userNameValue;
        System.out.println(userName);
    }
}

