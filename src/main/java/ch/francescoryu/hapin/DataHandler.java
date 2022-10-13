package ch.francescoryu.hapin;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.components.boxes.TodoBox;
import ch.francescoryu.hapin.components.buttons.DeleteButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
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
import javafx.util.Duration;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

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
    }


    public static void deleteTodo(Button b, ArrayList<Button> buttons, GridPane pane, String path, Button deleteButton) throws IOException {
        pane.getChildren().remove(b);
        buttons.remove(b);
        try {
            remButtonFromFile(b.getText(), path, 1);
            readTodoFile(pane, buttons, deleteButton);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reloadTodo(ArrayList<Button> buttons, GridPane gridPane, Button deleteButton) throws IOException {
        gridPane.getChildren().removeAll(buttons);
        buttons.clear();
    }

    public static void readTodoFile(GridPane gridPane, ArrayList<Button> buttons, Button deleteButton) throws IOException {
        List<String> lines = Files.readAllLines(new File(todoFilePath).toPath());
        int i = 0;
        gridPane.getChildren().removeAll(buttons);
        Collections.sort(lines);
        for (String s : lines) {
            String[] arr = s.split(";");
            Button button1 = new Button(arr[1]);
            button1.setAlignment(Pos.CENTER_LEFT);
            button1.setCursor(Cursor.HAND);

            button1.setOnAction(actionEvent -> {
                if (Objects.equals(arr[0], "1")) {
                    button1.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: #ff5454; -fx-background-color: darkgrey; -fx-padding: 0");
                } else if (Objects.equals(arr[0], "2")) {
                    button1.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: orange; -fx-background-color: darkgrey; -fx-padding: 0");
                } else if (Objects.equals(arr[0], "3")) {
                    button1.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: green; -fx-background-color: darkgrey; -fx-padding: 0");
                }
                deleteButton.setOnAction(actionEvent1 -> {
                    try {
                        deleteTodo(button1, buttons, gridPane, todoFilePath, deleteButton);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            });

            if (Objects.equals(arr[0], "1")) {
                button1.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: #ff5454; -fx-background-color: transparent; -fx-background: transparent; -fx-padding: 0");

            } else if (Objects.equals(arr[0], "2")) {
                button1.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: orange; -fx-background-color: transparent; -fx-background: transparent; -fx-padding: 0");

            } else if (Objects.equals(arr[0], "3")) {
                button1.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: green; -fx-background-color: transparent; -fx-background: transparent; -fx-padding: 0");

            }

            buttons.add(button1);
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
            e.printStackTrace();
        }
    }

    public static void deleteWholeTodoFile(GridPane gridPane, ArrayList<Button> buttons, Button deleteButton) throws IOException {
        new FileWriter(todoFilePath, false).close();
        readTodoFile(gridPane, buttons, deleteButton);
    }


    public static void createButtons(ArrayList<Button> buttons, GridPane gridPane, boolean withLink, Button deleteButton) throws IOException, URISyntaxException {
        int j = 5;
        List<String> lines = Files.readAllLines(new File(saveFilePath).toPath());

        gridPane.getChildren().removeAll(buttons);

        gridPane.getStylesheets().add(TodoBox.class.getResource("/css/style.css").toExternalForm());

        for (String s : lines) {
            String[] arr = s.split(";");
            Button b = new Button(arr[0]);
            b.getStyleClass().addAll(".button");

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
                        MenuMethods.setSelectedButtonStyle(b);
                        deleteButton.setOnAction(actionEvent -> {
                            deleteButton(b, buttons, gridPane, saveFilePath, deleteButton);
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

    public static void reloadNavButton(GridPane pane, ArrayList<Button> buttons, Button deleteButton) throws IOException, URISyntaxException {
        pane.getChildren().removeAll(buttons);
        createButtons(buttons, pane, true, deleteButton);
    }

    public static void deleteButton(Button b, ArrayList<Button> buttons, GridPane pane, String path, Button deleteButton) {
        pane.getChildren().remove(b);
        buttons.remove(b);
        try {
            remButtonFromFile(b.getText(), path, 0);
            reloadNavButton(pane, buttons, deleteButton);
        } catch (IOException | URISyntaxException e) {
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
    }

    public static void checkLoginData(String userName, PasswordField pwdField, Stage actualStage) throws IOException {
        List<String> lines = Files.readAllLines(new File(loginFilePath).toPath());
        for (String s : lines) {
            String[] arrString = s.split(";");

            if (userName.equals(arrString[0]) && pwdField.getText().equals(arrString[1])) {
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
    }

    public static Text createClock() {
        final Text clock = new Text();
        final DateFormat format = DateFormat.getInstance();
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            final Calendar cal = Calendar.getInstance();
            clock.setText(format.format(cal.getTime()));
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        return clock;
    }
}

