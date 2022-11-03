package ch.francescoryu.hapin.components.boxes;

import ch.francescoryu.hapin.DataHandler;
import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.components.buttons.AddButton;
import ch.francescoryu.hapin.components.buttons.DeleteButton;
import ch.francescoryu.hapin.components.buttons.DeleteEverythingButton;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class TodoBox extends VBox {


    static String todoFile = "src/main/resources/txt-files/todo.txt";

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public TodoBox() throws IOException {

        getStylesheets().add(TodoBox.class.getResource("/css/style.css").toExternalForm());

        MenuMethods menuMethods = new MenuMethods();

        Label todoLabel = new Label("ToDo-List");
        todoLabel.setStyle("-fx-font-size: 30; " +
                "-fx-font-family: 'Microsoft Sans Serif'; " +
                "-fx-text-alignment: center;" +
                "-fx-text-fill: black");

        ArrayList<Button> buttons = new ArrayList<>();

        GridPane gridPane = new GridPane();

        gridPane.setVgap(10);
        //gridPane.setStyle("-fx-padding: 10; -fx-border-color: white; -fx-spacing: 10");

        DeleteButton deleteButton = new DeleteButton(35);

        DeleteEverythingButton deleteEverythingButton = new DeleteEverythingButton();
        deleteEverythingButton.setOnAction(actionEvent -> {
            try {
                DataHandler.deleteWholeTodoFile(gridPane, buttons, deleteEverythingButton);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        ScrollPane todoScrollPane = new ScrollPane(gridPane);
        todoScrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-padding: 10");
        todoScrollPane.getStyleClass().addAll(".scroll-bar", ".scroll-pane");



        ComboBox<String> priority = new ComboBox<>();
        priority.getStyleClass().addAll(".combo-box", ".combo-box-base");
        priority.getItems().addAll("High", "Medium", "Low");
        priority.setPromptText("choose priority");
        priority.setStyle("-fx-font-size: 15; " +
                "-fx-font-family: 'Microsoft Sans Serif'; " +
                //"-fx-border-color: #737373; " +
                "-fx-border-width: 2;");
        priority.setCursor(Cursor.HAND);

        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------


        DataHandler.readTodoFile(gridPane, buttons, deleteButton);


        TextField inputTodoList = new TextField();
        inputTodoList.setPromptText("e.g. buy milk");
        inputTodoList.setStyle("-fx-font-size: 16;" +
                "-fx-font-family: 'Microsoft Sans Serif';" +
                "-fx-text-fill: black;" +
                "-fx-control-inner-background: #F8F5FA;" +
                "-fx-prompt-text-fill: #6c6c6c;" +
                "-fx-padding: 3;" +
                "-fx-background-radius: 3;" +
                "-fx-text-box-border: transparent;");
        inputTodoList.setMaxWidth(360);


        AddButton addTodoButton = new AddButton(25);
        addTodoButton.setOnAction(actionEvent -> {
            try {
                DataHandler.writeTodoFile(inputTodoList, priority, buttons, gridPane);
                DataHandler.readTodoFile(gridPane, buttons, deleteButton);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        DeleteButton clearTodoButton = new DeleteButton(25);
        clearTodoButton.setOnAction(actionEvent -> {
            inputTodoList.setText("");
            inputTodoList.setPromptText("e.g. buy milk");
        });

        HBox buttonTodoBox = new HBox();
        buttonTodoBox.setStyle("-fx-alignment: center");
        buttonTodoBox.setSpacing(22);
        buttonTodoBox.getChildren().addAll(priority, addTodoButton, clearTodoButton);

        DatePicker datePicker = new DatePicker();
        datePicker.getStyleClass().addAll(".date-picker" );

        VBox datePickerBox = new VBox(datePicker);
        datePickerBox.setAlignment(Pos.CENTER);

        HBox buttonDeleteBox = new HBox();
        buttonDeleteBox.setSpacing(10);
        buttonDeleteBox.setStyle("-fx-alignment: center");
        buttonDeleteBox.getChildren().addAll(deleteButton, deleteEverythingButton);

        setStyle("-fx-padding: 20;");
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(todoLabel, inputTodoList, buttonTodoBox, datePickerBox, todoScrollPane, buttonDeleteBox);
    }

}
