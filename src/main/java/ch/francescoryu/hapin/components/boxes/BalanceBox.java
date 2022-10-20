package ch.francescoryu.hapin.components.boxes;

import ch.francescoryu.hapin.DataHandler;
import ch.francescoryu.hapin.Menu;
import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.components.buttons.AddButton;
import ch.francescoryu.hapin.components.buttons.DeleteButton;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BalanceBox extends VBox {
    int rowCntr = 0;
    int columbCntr = 0;

    ArrayList<Button> buttons = new ArrayList<>();

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public BalanceBox() throws IOException {

        getStylesheets().add(TodoBox.class.getResource("/css/style.css").toExternalForm());

        setStyle("-fx-border-color: black");

        Label balanceLabel = new Label("Balance");
        MenuMethods.setLabelStyle(balanceLabel);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent");
        scrollPane.getStyleClass().addAll(".scroll-pane", ".scroll-bar");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        HBox buttonBox = new HBox();
        buttonBox.setStyle("-fx-alignment: center;");
        buttonBox.setSpacing(10);

        AddButton addButton = new AddButton();

        DeleteButton deleteButton = new DeleteButton();

        DataHandler.createAccButtons(buttons, gridPane, deleteButton);

        buttonBox.getChildren().addAll(addButton, deleteButton);

        //--------------------------------------------------------------------------------------------------------------

        VBox addBox = new VBox();

        Label addAccountLabel = new Label("Add Account");
        MenuMethods.setLabelStyle(addAccountLabel);
        MenuMethods.setLabelStyle(addAccountLabel);

        TextField inputAccountName = new TextField();
        inputAccountName.setPromptText("Add Account Name");
        MenuMethods.setInputTextFieldStyle(inputAccountName);

        Button saveButton = new Button();
        saveButton.setCursor(Cursor.HAND);
        ImageView saveButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/save.png"))));
        saveButton.setStyle("-fx-background-color: black; -fx-border-color: #737373; -fx-border-width: 2");
        saveButtonImageView.setFitHeight(25);
        saveButtonImageView.setPreserveRatio(true);
        saveButton.setGraphic(saveButtonImageView);

        addBox.setSpacing(10);
        addBox.setAlignment(Pos.TOP_CENTER);
        addBox.getChildren().addAll(addAccountLabel, inputAccountName, saveButton);
        addBox.setMaxWidth(300);


        //--------------------------------------------------------------------------------------------------------------

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
        borderPane.setStyle("-fx-alignment: center;");
        getChildren().addAll(balanceLabel, borderPane, buttonBox);
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        setStyle("-fx-alignment: center; -fx-padding: 20");
        setMaxHeight(600);
        setMinWidth(size.getWidth() / 3);
        //setMaxWidth(650);

        //--------------------------------------------------------------------------------------------------------------

        addButton.setOnAction(actionEvent -> {
            getChildren().removeAll(balanceLabel, borderPane, buttonBox);
            getChildren().addAll(addBox);
        });

        //--------------------------------------------------------------------------------------------------------------

        saveButton.setOnAction(actionEvent -> {
            getChildren().remove(addBox);
            getChildren().addAll(balanceLabel, borderPane, buttonBox);
        });
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


}
