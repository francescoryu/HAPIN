package ch.francescoryu.hapin.components.boxes;

import ch.francescoryu.hapin.DataHandler;
import ch.francescoryu.hapin.Main;
import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.components.buttons.AddButton;
import ch.francescoryu.hapin.components.buttons.DeleteButton;
import ch.francescoryu.hapin.popups.ErrorPopup;
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
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

public class HyperlinkBox extends VBox {

    private final ArrayList<Button> buttons = new ArrayList<>();

    TextField inputButtonName;
    TextField inputButtonUrl;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public HyperlinkBox() {
        Label linksText = new Label("Hyperlinks");
        linksText.setStyle("-fx-font-size: 30; " +
                "-fx-font-family: 'Microsoft Sans Serif'; " +
                "-fx-text-alignment: center;" +
                "-fx-text-fill: black");

        //--------------------------------------------------------------------------------------------------------------



        //--------------------------------------------------------------------------------------------------------------

        HBox infoBox = new HBox(3);
        infoBox.setStyle("-fx-alignment: center-right; -fx-font-size: 40; -fx-padding: 10; ");

        //--------------------------------------------------------------------------------------------------------------

        GridPane buttonGridPane = new GridPane();
        //buttonGridPane.setStyle(" -fx-border-color: red;");
        buttonGridPane.setAlignment(Pos.TOP_CENTER);
        buttonGridPane.setVgap(10);
        //buttonGridPane.setAlignment(Pos.CENTER);
        buttonGridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        ScrollPane buttonScrollPane = new ScrollPane();
        buttonScrollPane.setMaxWidth(300);
        buttonScrollPane.setFitToWidth(true);
        buttonScrollPane.setContent(buttonGridPane);
        buttonScrollPane.getStyleClass().addAll(".scroll-bar", ".scroll-pane");
        buttonScrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-padding: 10");

        AddButton addButton = new AddButton(35);

        DeleteButton deleteButton = new DeleteButton(35);

        //--------------------------------------------------------------------------------------------------------------

        try {
            DataHandler.createHyperLinkButtons(buttons, buttonGridPane, true, deleteButton);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        HBox navButtonBox = new HBox();
        navButtonBox.setStyle("-fx-alignment: center; -fx-padding: 20");
        navButtonBox.setSpacing(10);
        navButtonBox.getChildren().addAll(addButton, deleteButton);

        VBox navBox = new VBox();
        navBox.setStyle("-fx-padding: 20;");
        //navBox.setPrefHeight(size.getHeight() / 2);
        navBox.setSpacing(10);
        //navBox.setPrefWidth(size.getWidth() / 3);
        navBox.setAlignment(Pos.TOP_CENTER);
        navBox.getChildren().addAll(linksText, buttonScrollPane, navButtonBox);

        //--------------------------------------------------------------------------------------------------------------

        BorderPane popupBorderPane = new BorderPane();



        Label addButtonLabel = new Label("Add button");
        MenuMethods.setLabelStyle(addButtonLabel);

        Label buttonNameText = new Label("Button Name: ");
        MenuMethods.setPopUpStyle(buttonNameText);
        Label urlText = new Label("URL: ");
        MenuMethods.setPopUpStyle(urlText);
        Label imgText = new Label("Add Icon: ");
        MenuMethods.setPopUpStyle(imgText);

        VBox textBox = new VBox();
        textBox.setStyle("-fx-padding: 20");
        textBox.setSpacing(35);
        textBox.getChildren().addAll(buttonNameText, urlText, imgText);

        inputButtonName = new TextField();
        MenuMethods.setInputTextFieldStyle(inputButtonName);

        inputButtonUrl = new TextField();
        MenuMethods.setInputTextFieldStyle(inputButtonUrl);

        Button chooseFile = new Button("Select File");
        chooseFile.setCursor(javafx.scene.Cursor.HAND);
        chooseFile.setStyle("-fx-background-color: #F8F5FA;");
        chooseFile.setOnAction(actionEvent -> {
            DataHandler.saveHyperLinkData(DataHandler.getInputFromTextField(inputButtonName, inputButtonUrl));
        });

        VBox inputBox = new VBox();
        inputBox.setStyle("-fx-padding: 15");
        inputBox.setSpacing(30);
        inputBox.getChildren().addAll(inputButtonName, inputButtonUrl, chooseFile);

        Button saveButton = new Button();
        saveButton.setCursor(javafx.scene.Cursor.HAND);
        ImageView saveButtonImageView = new ImageView(new javafx.scene.image.Image(Objects.requireNonNull(ch.francescoryu.hapin.Main.class.getResourceAsStream("navMenuImg/save.png"))));
        saveButton.setStyle("-fx-background-color: #F8F5FA;");
        saveButtonImageView.setFitHeight(25);
        saveButtonImageView.setPreserveRatio(true);
        saveButton.setGraphic(saveButtonImageView);

        Button clearButton = new Button();
        clearButton.setCursor(javafx.scene.Cursor.HAND);
        ImageView clearButtonImageView = new ImageView(new javafx.scene.image.Image(Objects.requireNonNull(ch.francescoryu.hapin.Main.class.getResourceAsStream("navMenuImg/clear.png"))));
        clearButton.setStyle("-fx-background-color: #F8F5FA;");
        clearButtonImageView.setFitHeight(25);
        clearButtonImageView.setPreserveRatio(true);
        clearButton.setGraphic(clearButtonImageView);

        clearButton.setOnAction(actionEvent -> {
            inputButtonName.setText("");
            inputButtonUrl.setText("");
        });

        Button cancelButton = new Button();
        cancelButton.setCursor(Cursor.HAND);
        ImageView cancelButtonImageView = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("navMenuImg/cancel.png"))));
        cancelButton.setStyle("-fx-background-color: #F8F5FA;");
        cancelButtonImageView.setFitHeight(25);
        cancelButtonImageView.setPreserveRatio(true);
        cancelButton.setGraphic(cancelButtonImageView);

        HBox buttonBox = new HBox();
        buttonBox.setStyle("-fx-alignment: center; -fx-padding: 10");
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(cancelButton, clearButton, saveButton);

        popupBorderPane.setStyle("");
        popupBorderPane.setLeft(textBox);
        popupBorderPane.setCenter(inputBox);
        popupBorderPane.setBottom(buttonBox);
        //popupBorderPane.setMaxSize(300, 200);

        VBox addButtonBox = new VBox();
        addButtonBox.getChildren().addAll(addButtonLabel, popupBorderPane);
        addButtonBox.setStyle("-fx-padding: 20;");
        addButtonBox.setAlignment(Pos.CENTER);
        addButtonBox.setSpacing(10);

        getChildren().addAll(navBox);

        addButton.setOnAction(actionEvent -> {
            DataHandler.reloadHyperLinkButtons(buttons, buttonGridPane, deleteButton);
            getChildren().clear();
            getChildren().add(addButtonBox);
        });

        cancelButton.setOnAction(actionEvent -> {
            getChildren().clear();
            getChildren().add(navBox);
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
                DataHandler.reloadHyperLinkButtons(buttons, buttonGridPane, deleteButton);
                inputButtonName.setText("");
                inputButtonUrl.setText("");
                getChildren().clear();
                getChildren().add(navBox);
            }
        });
    }
}
