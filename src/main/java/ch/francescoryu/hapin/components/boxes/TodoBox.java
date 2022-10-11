package ch.francescoryu.hapin.components.boxes;

import ch.francescoryu.hapin.DataHandler;
import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.components.buttons.AddButton;
import ch.francescoryu.hapin.components.buttons.DeleteButton;
import ch.francescoryu.hapin.components.buttons.DeleteEverythingButton;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TodoBox extends VBox {


    static String todoFile = "src/main/resources/txt-files/todo.txt";

    public TodoBox() throws IOException {

        MenuMethods menuMethods = new MenuMethods();

        Label todoLabel = new Label("ToDo-List");
        todoLabel.setStyle("-fx-font-size: 30; " +
                "-fx-font-family: 'Microsoft Sans Serif'; " +
                "-fx-text-alignment: center;" +
                "-fx-text-fill: #cfcfcf");

        ArrayList<Button> buttons = new ArrayList<>();

        GridPane gridPane = new GridPane();


        DeleteButton deleteButton = new DeleteButton();

        DeleteEverythingButton deleteEverythingButton = new DeleteEverythingButton();
        deleteEverythingButton.setOnAction(actionEvent -> {
            try {
                DataHandler.deleteWholeTodoFile(gridPane, buttons, deleteEverythingButton);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        ScrollPane todoScrollPane = new ScrollPane(gridPane);
        todoScrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent");
        todoScrollPane.setMinWidth(300);
        todoScrollPane.setMinHeight(250);

        ComboBox<String> priority = new ComboBox<>();
        priority.setCursor(Cursor.HAND);
        priority.getItems().addAll("High", "Medium", "Low");
        priority.setPromptText("choose priority");
        priority.setStyle("-fx-font-size: 20; " +
                "-fx-font-family: 'Microsoft Sans Serif'; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 1;" +
                "-fx-prompt-text-fill: #cfcfcf;" +
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;");

        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------


        DataHandler.readTodoFile(gridPane, buttons, deleteButton);


        TextField inputTodoList = new TextField();
        inputTodoList.setPromptText("e.g. buy milk");
        menuMethods.setInputTextFieldStyle(inputTodoList);

        AddButton addTodoButton = new AddButton();
        addTodoButton.setOnAction(actionEvent -> {
            try {
                DataHandler.writeTodoFile(inputTodoList, priority, buttons, gridPane);
                DataHandler.readTodoFile(gridPane, buttons, deleteButton);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        DeleteButton clearTodoButton = new DeleteButton();
        clearTodoButton.setOnAction(actionEvent -> {
            inputTodoList.setText("");
            inputTodoList.setPromptText("e.g. buy milk");
        });

        HBox buttonTodoBox = new HBox();
        buttonTodoBox.setStyle("-fx-alignment: center");
        buttonTodoBox.setSpacing(10);
        buttonTodoBox.getChildren().addAll(priority, addTodoButton, clearTodoButton);

        HBox buttonDeleteBox = new HBox();
        buttonDeleteBox.setSpacing(10);
        buttonDeleteBox.setStyle("-fx-alignment: center");
        buttonDeleteBox.getChildren().addAll(deleteButton, deleteEverythingButton);

        setMinWidth(400);
        setMaxWidth(400);
        setStyle("-fx-padding: 20; -fx-background-color: black");
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(todoLabel, inputTodoList, buttonTodoBox, todoScrollPane, buttonDeleteBox);
    }

}
