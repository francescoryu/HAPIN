package ch.francescoryu.hapin.components.boxes;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.components.buttons.AddButton;
import ch.francescoryu.hapin.components.buttons.DeleteButton;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TodoBox extends VBox {
    public TodoBox() {
        MenuMethods menuMethods = new MenuMethods();

        Text todoLabel = new Text("ToDo-List");
        menuMethods.setLabelStyle(todoLabel);

        GridPane todoGridPane = new GridPane();

        ScrollPane todoScrollPane = new ScrollPane(todoGridPane);
        todoScrollPane.setMinHeight(500);
        todoGridPane.setMaxHeight(500);

        TextField inputTodoList = new TextField();
        inputTodoList.setPromptText("e.g. buy milk");
        menuMethods.setInputTextFieldStyle(inputTodoList);

        AddButton addTodoButton = new AddButton();

        DeleteButton clearTodoButton = new DeleteButton();

        HBox buttonTodoBox = new HBox();
        buttonTodoBox.setStyle("-fx-alignment: center");
        buttonTodoBox.setSpacing(10);
        buttonTodoBox.getChildren().addAll(addTodoButton, clearTodoButton);

        setMinWidth(400);
        setMaxWidth(400);
        setStyle("-fx-padding: 20; -fx-border-radius: 15; -fx-border-width: 2; -fx-border-color: black;");
        setSpacing(10);
        getChildren().addAll(todoLabel, inputTodoList, buttonTodoBox, todoScrollPane);
    }

}
