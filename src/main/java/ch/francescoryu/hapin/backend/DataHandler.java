package ch.francescoryu.hapin.backend;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.components.TodoObject;
import ch.francescoryu.hapin.components.boxes.CustomLinkBox;
import ch.francescoryu.hapin.components.boxes.SelectionBox;
import ch.francescoryu.hapin.components.boxes.TodoBox;
import ch.francescoryu.hapin.components.buttons.BackButton;
import ch.francescoryu.hapin.components.buttons.DeleteButton;
import ch.francescoryu.hapin.components.buttons.SaveButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;

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
    static String txtFileFolder = "src/main/resources/AccFiles/";
    static String tempUserName;

    public static void reloadHyperLinkButtons(ArrayList<Button> buttons, GridPane gridPane, Button deleteButton) {
        gridPane.getChildren().removeAll(buttons);
        buttons.clear();
        try {
            createHyperLinkButtons(buttons, gridPane, true, deleteButton);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveHyperLinkData(String input) {
        Stage stage1 = new Stage();
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage1);

        File file = new File("src/main/resources/buttonImages/" + selectedFile.getName());

        fileChooser.setInitialDirectory(new File("src/main/resources/buttonImages/" + selectedFile.getName()));
        Path from = Paths.get(selectedFile.toURI());
        try {
            BufferedWriter myWriter = Files.newBufferedWriter(Path.of(saveFilePath), StandardOpenOption.APPEND);
            myWriter.write(input + ";" + "src/main/resources/buttonImages/" + selectedFile.getName());
            myWriter.newLine();
            myWriter.close();
        } catch (IOException e) {
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

    public static void writeTodoFile(TextField textField, ComboBox<String> comboBox, DatePicker datePicker, String status) throws IOException {
        if (textField.getText().matches("") || datePicker.getEditor().getText().matches("") || comboBox.getValue().matches("") || !datePicker.getEditor().getText().matches("^\\d{2}.\\d{2}.\\d{4}$")) {
            comboBox.setPromptText("select priority");
            datePicker.setPromptText("Missing deadline");
            textField.setPromptText("You need to type something");
        } else {
            if (comboBox.getValue().matches("High")) {
                writeFile("1;" + textField.getText() + ";" + datePicker.getEditor().getText() + ";unselected");
                textField.setText("");
                datePicker.getEditor().setText("");
            } else if (comboBox.getValue().matches("Medium")) {
                writeFile("2;" + textField.getText() + ";" + datePicker.getEditor().getText() + ";unselected");
                textField.setText("");
                datePicker.getEditor().setText("");
            } else if (comboBox.getValue().matches("Low")) {
                writeFile("3;" + textField.getText() + ";" + datePicker.getEditor().getText() + ";unselected");
                textField.setText("");
                datePicker.getEditor().setText("");
            }

        }
    }


    public static void deleteTodo(TodoObject todoObject, ArrayList<Label> labels, TableView tableView, String path, Button deleteButton) throws IOException {
        tableView.getItems().clear();
        try {
            remButtonFromFile(todoObject.getInput(), path, 1);
            readTodoFile(tableView, labels, deleteButton);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void replaceTodoLine(String oldText, String newText, TableView tableView, ArrayList<Label> labels, Button deleteButton) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of(todoFilePath), StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            String s = fileContent.get(i);
            if (s.equals(oldText)) {
                fileContent.set(i, newText);
                break;
            }
        }

        Files.write(Path.of(todoFilePath), fileContent, StandardCharsets.UTF_8);
    }

    public static void readTodoFile(TableView tableView, ArrayList<Label> labels, Button deleteButton) throws IOException {
        List<String> lines = Files.readAllLines(new File(todoFilePath).toPath());
        int i = 0;
        int cntr = 0;

        tableView.getItems().clear();
        tableView.getStylesheets().add(TodoBox.class.getResource("/css/style.css").toExternalForm());

        //Collections.sort(lines);
        for (String s : lines) {

            String[] arr = s.split(";");

            Label priorityLabel = new Label(arr[0]);
            Label deadLine = new Label(arr[2]);
            CheckBox checkBox = new CheckBox();
            checkBox.getStyleClass().addAll(".check-box");

            if (arr[3].equals("unselected")) {
                checkBox.setSelected(false);
            } else {
                checkBox.setSelected(true);
            }

            checkBox.setOnAction(actionEvent -> {
                if (checkBox.isSelected()) {
                    try {
                        replaceTodoLine(arr[0] + ";" + arr[1] + ";" + arr[2] + ";unselected", arr[0] + ";" + arr[1] + ";" + arr[2] + ";selected", tableView, labels, deleteButton);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        replaceTodoLine(arr[0] + ";" + arr[1] + ";" + arr[2] + ";selected", arr[0] + ";" + arr[1] + ";" + arr[2] + ";unselected", tableView, labels, deleteButton);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            deadLine.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: black");

            deleteButton.setOnAction(actionEvent -> {
                TodoObject todoObject = (TodoObject) tableView.getSelectionModel().getSelectedItem();
                try {
                    deleteTodo(todoObject, labels, tableView, todoFilePath, deleteButton);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });


            if (Objects.equals(arr[0], "1")) {
                priorityLabel.setText("1 High");
            } else if (Objects.equals(arr[0], "2")) {
                priorityLabel.setText("2 Medium");
            } else if (Objects.equals(arr[0], "3")) {
                priorityLabel.setText("3 Low");
            }

            tableView.getItems().addAll(new TodoObject(priorityLabel.getText(), arr[1], arr[2], checkBox));

            cntr++;
        }

        tableView.setPlaceholder(new Label("No rows to display"));
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

    public static void deleteWholeTodoFile(TableView tableView, ArrayList<Label> labels, Button deleteButton) throws IOException {
        new FileWriter(todoFilePath, false).close();
        readTodoFile(tableView, labels, deleteButton);
    }


    public static void createHyperLinkButtons(ArrayList<Button> buttons, GridPane gridPane, boolean withLink, Button deleteButton) throws IOException, URISyntaxException {
        int j = 0;
        List<String> lines = Files.readAllLines(new File(saveFilePath).toPath());

        gridPane.getChildren().removeAll(buttons);

        //gridPane.getStylesheets().add(TodoBox.class.getResource("/css/style.css").toExternalForm());

        for (String s : lines) {
            String[] arr = s.split(";");
            Button b = new Button(arr[0]);
            b.getStylesheets().add(TodoBox.class.getResource("/css/style.css").toExternalForm());
            b.getStyleClass().addAll(".hyperlink");

            ImageView imageView = new ImageView(new Image(Files.newInputStream(Paths.get(arr[2]))));
            imageView.setFitHeight(25);
            imageView.setPreserveRatio(true);
            b.setGraphic(imageView);
            MenuMethods.setButtonStyle(b);

            if (withLink) {
                b.setOnAction(e -> {
                    try {

                        CustomLinkBox customLinkBox = new CustomLinkBox();

                        customLinkBox.loadLink(arr[1]);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
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

    public static void reloadHyperLinkButtons(GridPane pane, ArrayList<Button> buttons, Button deleteButton) throws IOException, URISyntaxException {
        pane.getChildren().removeAll(buttons);
        createHyperLinkButtons(buttons, pane, true, deleteButton);
    }

    public static void deleteButton(Button b, ArrayList<Button> buttons, GridPane pane, String path, Button deleteButton) {
        pane.getChildren().remove(b);
        buttons.remove(b);
        try {
            remButtonFromFile(b.getText(), path, 0);
            reloadHyperLinkButtons(pane, buttons, deleteButton);
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

    public static void checkLoginData(String userName, PasswordField pwdField, Stage actualStage) throws Exception {
        List<String> lines = Files.readAllLines(new File(loginFilePath).toPath());
        for (String s : lines) {
            String[] arrString = s.split(";");

            if (userName.equals(arrString[0]) && pwdField.getText().equals(arrString[1])) {
                tempUserName = userName;
                SelectionBox selectionBox = new SelectionBox();
                Stage stage = new Stage();
                selectionBox.start(stage);
                actualStage.close();
            } else {

            }
        }
    }

    public static String transferUserName() {
        return tempUserName;
    }

    public static void loadUserToComboBox(ComboBox<String> comboBox) throws IOException {
        List<String> lines = Files.readAllLines(new File(loginFilePath).toPath());
        for (String s : lines) {
            String[] arrString = s.split(";");
            String name = arrString[0];
            comboBox.getItems().add(name);
        }
    }

    public static Text createClock() {
        final Text clock = new Text();
        final DateFormat format = DateFormat.getInstance();
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            final Calendar cal = Calendar.getInstance();
            clock.setText("Current time: " + format.format(cal.getTime()).split(",")[1]);
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        return clock;
    }

    public static String getLocalDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MMMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static void createAccButtons(ArrayList<Button> buttons, GridPane gridPane, VBox vBox, Label balanceLabel, BorderPane borderPane, HBox buttonBox, ArrayList<HBox> hBoxes, DeleteButton deleteButton) throws IOException {
        gridPane.getChildren().removeAll(buttons);

        File folder = new File(Paths.get(txtFileFolder).toUri());
        File[] allFiles = folder.listFiles();

        assert allFiles != null;
        int btnCntr = 0;
        int columnCntr = 0;
        int rowCntr = 0;
        for (File f : allFiles) {
            if (btnCntr % 2 == 0) {
                columnCntr++;
                rowCntr = 0;
            }
            Button btn = new Button();
            MenuMethods.setButtonStyle(btn);
            String[] btnNameArr = f.getName().split("\\.");
            btn.setText(btnNameArr[0]);
            buttons.add(btn);
            gridPane.add(btn, rowCntr, columnCntr);
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            rowCntr++;
            btnCntr++;

            btn.setOnAction(actionEvent -> {

                btn.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                        deleteButton.setOnAction(actionEvent1 -> {
                            try {
                                Files.delete(Paths.get(txtFileFolder + f.getName()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        });
                    }
                });

                try {
                    vBox.getChildren().clear();
                    createAccButtonVBox(f, vBox, hBoxes, balanceLabel, borderPane, buttonBox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void createAccButtonVBox(File f, VBox vBox, ArrayList<HBox> hBoxes, Label balanceLabel, BorderPane borderPane, HBox buttonBox) throws IOException {
        double endVal = 0;

        List<String> lines = Files.readAllLines(f.toPath());

        int cntr = 0;

        Label fileNameLabel = new Label(f.getName().split("\\.")[0]);
        MenuMethods.setLabelStyle(fileNameLabel);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.getChildren().removeAll(hBoxes);

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        TextField textField = new TextField();
        textField.setPrefColumnCount(10);
        textField.setStyle("-fx-font-size: 17; -fx-text-fill: black; -fx-border-radius: 3");

        Label totalLabel = new Label();
        Label newVal = new Label("Add value: ");
        newVal.setStyle("-fx-font-family: 'Microsoft Sans Serif'; -fx-font-size: 20; -fx-text-fill: black;");

        BackButton backButton = new BackButton(25);
        SaveButton saveButton = new SaveButton(25);

        HBox navButtonBox = new HBox(backButton, saveButton);
        navButtonBox.setAlignment(Pos.CENTER);
        navButtonBox.setSpacing(10);

        HBox inputHBox = new HBox();
        inputHBox.setAlignment(Pos.CENTER);
        inputHBox.getChildren().addAll(newVal, textField);
        if (textField.getText() == null) {
            System.out.println("FAIL");
        }

        for (String s : lines) {

            double currVal = Double.parseDouble(s);

            HBox hBox = new HBox();
            hBox.setSpacing(5);
            hBox.setAlignment(Pos.CENTER_LEFT);

            TextField inputTextField = new TextField();
            inputTextField.setPrefColumnCount(10);

            DeleteButton deleteButton = new DeleteButton(20);
            deleteButton.setOnAction(actionEvent -> {
                gridPane.getChildren().remove(lines.indexOf(s), 0);
                lines.remove(s);

            });

            SaveButton button = new SaveButton(20);

            button.setOnAction(actionEvent -> {
                try {
                    replaceLineInFile(s, inputTextField.getText(), f.getName(), gridPane, hBoxes, f, vBox, hBox, balanceLabel, borderPane);
                    vBox.getChildren().clear();
                    createAccButtonVBox(f, vBox, hBoxes, balanceLabel, borderPane, buttonBox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            hBox.getChildren().addAll(inputTextField, button, deleteButton);

            if (currVal > 0) {
                inputTextField.setText(String.valueOf(currVal));
                inputTextField.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-background-color: rgba(151,255,53,0.58); -fx-text-fill: black");
            } else if (currVal < 0) {
                inputTextField.setText(String.valueOf(currVal));
                inputTextField.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-background-color: rgba(255,53,53,0.58); -fx-text-fill: black");
            } else if (currVal == 0) {
                inputTextField.setText(String.valueOf(currVal));
                inputTextField.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-background-color: transparent; -fx-background: transparent; -fx-text-fill: black");
                gridPane.getChildren().remove(hBox);
            }

            endVal = endVal + currVal;

            gridPane.setVgap(5);
            gridPane.add(hBox, 0, cntr);
            hBoxes.add(hBox);
            cntr++;
        }

        String totalValRounded = String.format("%.2f", endVal);

        if (endVal > 0) {
            totalLabel.setText("Total: +" + totalValRounded);
            totalLabel.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: black; -fx-background-color: rgba(151,255,53,0.58); -fx-padding: 10");
        }
        if (endVal < 0) {
            totalLabel.setText("Total: " + totalValRounded);
            totalLabel.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: black; -fx-background-color: rgba(255,53,53,0.58); -fx-padding: 10");
        } else if (endVal == 0) {
            totalLabel.setText("Total: " + totalValRounded);
            totalLabel.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'; -fx-text-fill: black; -fx-background-color: transparent; -fx-background: transparent; -fx-padding: 10");
        }

        vBox.getChildren().addAll(fileNameLabel, scrollPane, totalLabel, inputHBox, navButtonBox);
        vBox.setBackground(null);

        Label errorLabel = new Label("Not Matching Format");
        errorLabel.setStyle("-fx-text-fill: black");

        backButton.setOnAction(actionEvent -> {
            vBox.getChildren().clear();
            vBox.getChildren().addAll(balanceLabel, borderPane, buttonBox);
        });
        saveButton.setOnAction(actionEvent -> {
            FileWriter fw = null;

            if (textField.getText().matches("^-?([0-9])+\\.?([0-9]+)?$")) {
                try {
                    vBox.getChildren().remove(errorLabel);
                    fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(textField.getText());
                    bw.newLine();
                    bw.close();
                    vBox.getChildren().removeAll(fileNameLabel, scrollPane, totalLabel, inputHBox, navButtonBox);
                    createAccButtonVBox(f, vBox, hBoxes, balanceLabel, borderPane, buttonBox);
                    textField.clear();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                textField.clear();
                vBox.getChildren().clear();
                vBox.getChildren().addAll(fileNameLabel, scrollPane, totalLabel, inputHBox, errorLabel, navButtonBox);
            }
        });
    }

    public static void replaceLineInFile(String oldText, String newText, String fileName, GridPane gridPane, ArrayList<HBox> hBoxes, File file, VBox vBox, HBox hBox1, Label balanceLabel, BorderPane borderPane) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of(txtFileFolder + fileName), StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).equals(oldText)) {
                fileContent.set(i, newText);
                break;
            }
        }

        Files.write(Path.of(txtFileFolder + fileName), fileContent, StandardCharsets.UTF_8);
        gridPane.getChildren().removeAll(hBoxes);
        vBox.getChildren().clear();
        createAccButtonVBox(file, vBox, hBoxes, balanceLabel, borderPane, hBox1);
    }

    public static void createFile(TextField textField, ArrayList<Button> buttons, GridPane gridPane, VBox vBox, Label label, BorderPane borderPane, HBox hBox, ArrayList<HBox> hBoxes, DeleteButton deleteButton) throws IOException {
        File file = new File("src/main/resources/AccFiles/" + textField.getText() + ".txt");
        file.createNewFile();
        createAccButtons(buttons, gridPane, vBox, label, borderPane, hBox, hBoxes, deleteButton);
    }
}

