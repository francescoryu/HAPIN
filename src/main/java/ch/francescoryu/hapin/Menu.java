package ch.francescoryu.hapin;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.components.boxes.BalanceBox;
import ch.francescoryu.hapin.components.boxes.TodoBox;
import ch.francescoryu.hapin.components.buttons.AddButton;
import ch.francescoryu.hapin.components.buttons.DeleteButton;
import ch.francescoryu.hapin.popups.ErrorPopup;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author: Francesco Ryu
 * @version: 1.0
 * @date 29.09.2022
 * @description GUI for the main application.
 */

public class Menu extends Application {

    private final ArrayList<Button> buttons = new ArrayList<>();

    TextField inputButtonName;
    TextField inputButtonUrl;

    @Override
    public void start(Stage stage) throws IOException {

        MenuMethods menuMethods = new MenuMethods();
        //--------------------------------------------------------------------------------------------------------------


        //--------------------------------------------------------------------------------------------------------------

        Label linksText = new Label("Hyperlinks");
        linksText.setStyle("-fx-font-size: 30; " +
                "-fx-font-family: 'Microsoft Sans Serif'; " +
                "-fx-text-alignment: center;" +
                "-fx-text-fill: #cfcfcf;");

        //--------------------------------------------------------------------------------------------------------------



        //--------------------------------------------------------------------------------------------------------------

        HBox welcomeBox = new HBox();
        //welcomeBox.setBorder(new Border(new BorderStroke(Color.MEDIUMORCHID, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        welcomeBox.setStyle("-fx-padding: 10; -fx-alignment: center");
        /*welcomeBox.setSpacing(50);
        clock.setStyle("-fx-font-family: 'Microsoft Sans Serif'; -fx-font-size: 50;");
        */
        Text welcomeText = new Text("Welcome! Have a nice day!");
        welcomeText.setStyle("-fx-font-size: 60; -fx-padding: 20, 40; -fx-font-family: 'Microsoft Sans Serif';");
        welcomeBox.getChildren().addAll(welcomeText);

        HBox infoBox = new HBox(3);
        infoBox.setStyle("-fx-alignment: center-right; -fx-font-size: 40; -fx-padding: 10; ");
        infoBox.getChildren().addAll(DataHandler.createClock());

        //--------------------------------------------------------------------------------------------------------------

        GridPane buttonGridPane = new GridPane();
        buttonGridPane.setStyle("-fx-alignment: center; -fx-padding: 0 0 25 0; -fx-background-color: transparent");
        buttonGridPane.setVgap(10);
        /*gridPane.setVgap(10);
        gridPane.setHgap(20);*/
        //gridPane.setMinHeight(498);

        //--------------------------------------------------------------------------------------------------------------

        BalanceBox balanceBox = new BalanceBox();

        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.getChildren().addAll(balanceBox);

        //--------------------------------------------------------------------------------------------------------------


        ScrollPane buttonScrollPane = new ScrollPane(buttonGridPane);
        buttonScrollPane.setFitToWidth(true);
        buttonScrollPane.setFitToHeight(true);
        //buttonScrollPane.setMinHeight(250);
        buttonScrollPane.setMaxWidth(250);
        buttonScrollPane.setMinHeight(300);
        buttonScrollPane.getStyleClass().addAll(".scroll-bar", ".scroll-pane");
        buttonScrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        AddButton addButton = new AddButton();

        DeleteButton deleteButton = new DeleteButton();
        //--------------------------------------------------------------------------------------------------------------

        addButton.setOnAction(actionEvent -> {
            DataHandler.reloadButtonList(buttons, buttonGridPane, deleteButton);

        });

        //--------------------------------------------------------------------------------------------------------------

        try {
            DataHandler.createButtons(buttons, buttonGridPane, true, deleteButton);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }


        HBox navButtonBox = new HBox();
        navButtonBox.setStyle("-fx-alignment: center; -fx-padding: 20");
        navButtonBox.setSpacing(10);
        navButtonBox.getChildren().addAll(addButton, deleteButton);

        VBox navBox = new VBox();
        navBox.setStyle("-fx-padding: 20;");
        navBox.setSpacing(10);
        navBox.setMinWidth(400);
        navBox.setMaxWidth(400);
        navBox.setAlignment(Pos.TOP_CENTER);
        navBox.getChildren().addAll(linksText, buttonScrollPane, navButtonBox);


        //--------------------------------------------------------------------------------------------------------------

        TodoBox todoBox = new TodoBox();

        //--------------------------------------------------------------------------------------------------------------

        BorderPane popupBorderPane = new BorderPane();

        Label addButtonLabel = new Label("Add button");
        menuMethods.setLabelStyle(addButtonLabel);

        Label buttonNameText = new Label("Button Name: ");
        menuMethods.setPopUpStyle(buttonNameText);
        Label urlText = new Label("URL: ");
        menuMethods.setPopUpStyle(urlText);
        Label imgText = new Label("Add Icon: ");
        menuMethods.setPopUpStyle(imgText);

        VBox textBox = new VBox();
        textBox.setStyle("-fx-padding: 20");
        textBox.setSpacing(35);
        textBox.getChildren().addAll(buttonNameText, urlText, imgText);

        inputButtonName = new TextField();
        inputButtonName.setPrefColumnCount(20);
        menuMethods.setInputTextFieldStyle(inputButtonName);

        inputButtonUrl = new TextField();
        menuMethods.setInputTextFieldStyle(inputButtonUrl);
        inputButtonUrl.setPrefColumnCount(20);

        Button chooseFile = new Button("Select File");
        chooseFile.setCursor(Cursor.HAND);
        chooseFile.setStyle("-fx-font-size: 16; -fx-font-family: 'Microsoft Sans Serif'; -fx-background-color: black; -fx-text-fill: #cfcfcf; -fx-border-color: #737373; -fx-border-width: 2");

        chooseFile.setOnAction(actionEvent -> {
            DataHandler.saveButtonData(stage, DataHandler.getInputFromTextField(inputButtonName, inputButtonUrl));
        });

        VBox inputBox = new VBox();
        inputBox.setStyle("-fx-padding: 15");
        inputBox.setSpacing(24);
        inputBox.getChildren().addAll(inputButtonName, inputButtonUrl, chooseFile);

        Button saveButton = new Button();
        saveButton.setCursor(Cursor.HAND);
        ImageView saveButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/save.png"))));
        saveButton.setStyle("-fx-background-color: black; -fx-border-color: #737373; -fx-border-width: 2");
        saveButtonImageView.setFitHeight(25);
        saveButtonImageView.setPreserveRatio(true);
        saveButton.setGraphic(saveButtonImageView);

        Button clearButton = new Button();
        clearButton.setCursor(Cursor.HAND);
        ImageView clearButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/delete.png"))));
        clearButton.setStyle("-fx-background-color: black; -fx-border-color: #737373; -fx-border-width: 2");
        clearButtonImageView.setFitHeight(25);
        clearButtonImageView.setPreserveRatio(true);
        clearButton.setGraphic(clearButtonImageView);

        clearButton.setOnAction(actionEvent -> {
            inputButtonName.setText("");
            inputButtonUrl.setText("");
        });

        Button cancelButton = new Button();
        cancelButton.setCursor(Cursor.HAND);
        ImageView cancelButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/cancel.png"))));
        cancelButton.setStyle("-fx-background-color: black; -fx-border-color: #737373; -fx-border-width: 2");
        cancelButtonImageView.setFitHeight(25);
        cancelButtonImageView.setPreserveRatio(true);
        cancelButton.setGraphic(cancelButtonImageView);

        HBox buttonBox = new HBox();
        buttonBox.setStyle("-fx-alignment: center; -fx-padding: 10");
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(cancelButton, clearButton, saveButton);

        popupBorderPane.setStyle("");
        popupBorderPane.setLeft(textBox);
        popupBorderPane.setRight(inputBox);
        popupBorderPane.setBottom(buttonBox);
        popupBorderPane.setMaxSize(300, 200);

        VBox addButtonBox = new VBox();
        addButtonBox.getChildren().addAll(addButtonLabel, popupBorderPane);
        addButtonBox.setStyle("-fx-padding: 20; -fx-border-color: #737373; -fx-border-width: 2;");
        addButtonBox.setAlignment(Pos.CENTER);
        addButtonBox.setSpacing(10);
        addButtonBox.setMaxWidth(1);

        VBox wholeAddButtonBox = new VBox();
        wholeAddButtonBox.getChildren().addAll(addButtonBox);
        wholeAddButtonBox.setStyle("-fx-alignment: center");

        //--------------------------------------------------------------------------------------------------------------


        //--------------------------------------------------------------------------------------------------------------

        BorderPane borderPane = new BorderPane();
        borderPane.getStylesheets().add(TodoBox.class.getResource("/css/style.css").toExternalForm());
        borderPane.setStyle("-fx-background-color: linear-gradient(to right bottom, #11002b, #3f0028);");
        //borderPane.setStyle("-fx-background-image: url(menuBackground.png); -fx-background-size: cover; -fx-alignment: center");
        //borderPane.setStyle("-fx-background-color: black");
        //borderPane.setTop(welcomeBox);
        borderPane.setLeft(navBox);
        borderPane.setCenter(centerBox);
        borderPane.setRight(todoBox);
        //borderPane.setBottom(infoBox);

        //--------------------------------------------------------------------------------------------------------------

        addButton.setOnAction(actionEvent -> {
            borderPane.setCenter(wholeAddButtonBox);
            borderPane.getChildren().remove(centerBox);
        });

        cancelButton.setOnAction(actionEvent -> {
            borderPane.getChildren().remove(wholeAddButtonBox);
            borderPane.setCenter(centerBox);
        });

        saveButton.setOnAction(actionEvent -> {
            if (Objects.equals(inputButtonName.getText(), "") || Objects.equals(inputButtonUrl.getText(), "")) {
                ErrorPopup errorPopup = new ErrorPopup();
                Stage errorStage = new Stage();
                try {
                    errorPopup.start(errorStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                borderPane.getChildren().remove(wholeAddButtonBox);
                DataHandler.reloadButtonList(buttons, buttonGridPane, deleteButton);
                inputButtonName.setText("");
                inputButtonUrl.setText("");
                borderPane.setCenter(centerBox);
            }
        });

        //--------------------------------------------------------------------------------------------------------------

        Scene scene = new Scene(borderPane);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.F11) {
                stage.setFullScreen(true);
            }
        });
        stage.setTitle("HAPIN");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        //--------------------------------------------------------------------------------------------------------------
    }
}