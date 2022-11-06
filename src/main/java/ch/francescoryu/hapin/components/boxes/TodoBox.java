package ch.francescoryu.hapin.components.boxes;

import ch.francescoryu.hapin.DataHandler;
import ch.francescoryu.hapin.Person;
import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.components.buttons.AddButton;
import ch.francescoryu.hapin.components.buttons.ClearButton;
import ch.francescoryu.hapin.components.buttons.DeleteButton;
import ch.francescoryu.hapin.components.buttons.DeleteEverythingButton;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
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
        ArrayList<Label> labels = new ArrayList<>();

        TableView tableView = new TableView();

        TableColumn<Person, String> column1 = new TableColumn<>("Priority");

        column1.setCellValueFactory(new PropertyValueFactory<>("priority"));


        TableColumn<Person, String> column2 = new TableColumn<>("Activity");

        column2.setCellValueFactory(new PropertyValueFactory<>("input"));

        TableColumn<Person, String> column3 = new TableColumn<>("Due To");

        column3.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableView.getColumns().addAll(column1, column2, column3);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        DeleteButton deleteButton = new DeleteButton(35);

        DeleteEverythingButton deleteEverythingButton = new DeleteEverythingButton();
        deleteEverythingButton.setOnAction(actionEvent -> {
            try {
                DataHandler.deleteWholeTodoFile(tableView, labels, deleteEverythingButton);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        ScrollPane todoScrollPane = new ScrollPane(tableView);
        todoScrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-padding: 10; -fx-border-color: red; -fx-fit-to-width: true");
        todoScrollPane.getStyleClass().addAll(".scroll-bar", ".scroll-pane");
        todoScrollPane.setFitToWidth(true);
        todoScrollPane.setFitToHeight(true);



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


        DataHandler.readTodoFile(tableView, labels, deleteButton);


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

        DatePicker datePicker = new DatePicker();
        datePicker.getStyleClass().addAll(".date-picker");

        AddButton addTodoButton = new AddButton(25);

        ClearButton clearTodoButton = new ClearButton(25);
        clearTodoButton.setOnAction(actionEvent -> {
            inputTodoList.setText("");
            inputTodoList.setPromptText("e.g. buy milk");
        });

        HBox buttonTodoBox = new HBox();
        buttonTodoBox.setStyle("-fx-alignment: center");
        buttonTodoBox.setSpacing(22);
        buttonTodoBox.getChildren().addAll(priority, addTodoButton, clearTodoButton);

        VBox datePickerBox = new VBox(datePicker);
        datePickerBox.setAlignment(Pos.CENTER);

        HBox buttonDeleteBox = new HBox();
        buttonDeleteBox.setSpacing(10);
        buttonDeleteBox.setStyle("-fx-alignment: center");
        buttonDeleteBox.getChildren().addAll(deleteButton, deleteEverythingButton);

        setStyle("-fx-padding: 20;");
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(todoLabel, inputTodoList, buttonTodoBox, datePickerBox, tableView, buttonDeleteBox);

        addTodoButton.setOnAction(actionEvent -> {
            try {
                DataHandler.writeTodoFile(inputTodoList, priority, datePicker);
                DataHandler.readTodoFile(tableView, labels, deleteButton);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

}
