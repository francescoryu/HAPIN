package ch.francescoryu.hapin.components.boxes;

import ch.francescoryu.hapin.DataHandler;
import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.components.buttons.AddButton;
import ch.francescoryu.hapin.components.buttons.DeleteButton;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    ArrayList<Button> buttons = new ArrayList<>();
    Text text = new Text();

    public TodoBox() throws IOException {

        MenuMethods menuMethods = new MenuMethods();

        Text todoLabel = new Text("ToDo-List");
        menuMethods.setLabelStyle(todoLabel);

        GridPane gridPane = new GridPane();

        ScrollPane todoScrollPane = new ScrollPane(gridPane);
        todoScrollPane.setMinWidth(350);
        todoScrollPane.setMaxWidth(350);
        todoScrollPane.setMaxHeight(500);
        todoScrollPane.setMinHeight(500);

        ComboBox<String> priority = new ComboBox<>();
        priority.getItems().addAll("High", "Medium", "Low");
        priority.setPromptText("choose priority");
        priority.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'");

        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------

        DataHandler.readTodoFile(gridPane, priority);

        TextField inputTodoList = new TextField();
        inputTodoList.setPromptText("e.g. buy milk");
        menuMethods.setInputTextFieldStyle(inputTodoList);

        AddButton addTodoButton = new AddButton();
        addTodoButton.setOnAction(actionEvent -> {
            DataHandler.writeTodoFile(inputTodoList, priority);
            try {
                DataHandler.readTodoFile(gridPane, priority);
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

        setMinWidth(400);
        setMaxWidth(400);
        setStyle("-fx-padding: 20; -fx-border-radius: 15; -fx-border-width: 2; -fx-border-color: black;");
        setSpacing(10);
        getChildren().addAll(todoLabel, inputTodoList, buttonTodoBox, todoScrollPane);
    }

}
