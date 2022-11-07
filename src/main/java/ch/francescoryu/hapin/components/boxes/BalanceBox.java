package ch.francescoryu.hapin.components.boxes;

import ch.francescoryu.hapin.DataHandler;
import ch.francescoryu.hapin.Main;
import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.components.buttons.AddButton;
import ch.francescoryu.hapin.components.buttons.DeleteButton;
import ch.francescoryu.hapin.components.buttons.SaveButton;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BalanceBox extends VBox {
    int rowCntr = 0;
    int columbCntr = 0;

    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<TextField> textFields = new ArrayList<>();
    ArrayList<HBox> hBoxes = new ArrayList<>();
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public BalanceBox() throws IOException {

        getStylesheets().add(TodoBox.class.getResource("/css/style.css").toExternalForm());

        setStyle("-fx-border-color: black");

        Label balanceLabel = new Label("Balance");
        MenuMethods.setLabelStyle(balanceLabel);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent");
        scrollPane.getStyleClass().addAll(".scroll-pane", ".scroll-bar");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        HBox buttonBox = new HBox();
        buttonBox.setStyle("-fx-alignment: center;");
        buttonBox.setSpacing(10);

        AddButton addButton = new AddButton(35);

        DeleteButton deleteButton = new DeleteButton(35);

        buttonBox.getChildren().addAll(addButton, deleteButton);

        //--------------------------------------------------------------------------------------------------------------

        VBox addBox = new VBox();

        Label addAccountLabel = new Label("Add Account");
        MenuMethods.setLabelStyle(addAccountLabel);
        MenuMethods.setLabelStyle(addAccountLabel);

        TextField inputAccountName = new TextField();
        inputAccountName.setPromptText("Add Account Name");
        MenuMethods.setInputTextFieldStyle(inputAccountName);

        SaveButton saveButton = new SaveButton(25);
        saveButton.setCursor(Cursor.HAND);
        ImageView saveButtonImageView = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("navMenuImg/save.png"))));
        saveButtonImageView.setFitHeight(25);
        saveButtonImageView.setPreserveRatio(true);
        saveButton.setGraphic(saveButtonImageView);

        addBox.setSpacing(10);
        addBox.setAlignment(Pos.TOP_CENTER);
        addBox.getChildren().addAll(addAccountLabel, inputAccountName, saveButton);
        addBox.setMaxWidth(300);


        //--------------------------------------------------------------------------------------------------------------

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(scrollPane);
        borderPane.setStyle("-fx-alignment: center;");
        getChildren().addAll(balanceLabel, borderPane, buttonBox);
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        setStyle("-fx-padding: 20");
        //setMaxWidth(650);

        //--------------------------------------------------------------------------------------------------------------

        addButton.setOnAction(actionEvent -> {
            getChildren().removeAll(balanceLabel, borderPane, buttonBox);
            getChildren().addAll(addBox);
        });

        //--------------------------------------------------------------------------------------------------------------

        saveButton.setOnAction(actionEvent -> {
            try {
                DataHandler.createFile(inputAccountName, buttons, gridPane, this, balanceLabel, borderPane, buttonBox, hBoxes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            getChildren().remove(addBox);
            getChildren().addAll(balanceLabel, borderPane, buttonBox);
        });

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        DataHandler.createAccButtons(buttons, gridPane, this, balanceLabel, borderPane, buttonBox, hBoxes);
    }

}
