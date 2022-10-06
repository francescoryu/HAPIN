package ch.francescoryu.hapin;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.popups.ErrorPopup;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    public void start(Stage stage) throws FileNotFoundException {


        MenuMethods menuMethods = new MenuMethods();
        //--------------------------------------------------------------------------------------------------------------

        final Text clock = new Text();
        final DateFormat format = DateFormat.getInstance();
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            final Calendar cal = Calendar.getInstance();
            clock.setText(format.format(cal.getTime()));
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //--------------------------------------------------------------------------------------------------------------

        Text linksText = new Text("Hyperlinks");
        menuMethods.setLabelStyle(linksText);

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
        welcomeBox.getChildren().addAll(welcomeText, clock);

        HBox infoBox = new HBox(3);
        infoBox.setStyle("-fx-alignment: center-right; -fx-font-size: 40; -fx-padding: 10; ");
        infoBox.getChildren().addAll(clock);

        //--------------------------------------------------------------------------------------------------------------

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-alignment: top-center; -fx-background-color: #ffdddd");
        gridPane.setVgap(5);
        /*gridPane.setVgap(10);
        gridPane.setHgap(20);*/
        //gridPane.setMinHeight(498);


        //--------------------------------------------------------------------------------------------------------------


        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setStyle("-fx-border-color: black; -fx-border-width: 2");
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setMaxHeight(500);
        scrollPane.setMinHeight(500);

        Button addButton = new Button();
        ImageView addButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/add.png"))));
        addButtonImageView.setFitHeight(35);
        addButtonImageView.setPreserveRatio(true);
        addButton.setGraphic(addButtonImageView);

        Button deleteButton = new Button();
        ImageView deleteButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/delete.png"))));
        deleteButtonImageView.setFitHeight(35);
        deleteButtonImageView.setPreserveRatio(true);
        deleteButton.setGraphic(deleteButtonImageView);

        //--------------------------------------------------------------------------------------------------------------

        addButton.setOnAction(actionEvent -> {
            DataHandler.reloadButtonList(buttons, gridPane, deleteButton);

        });

        //--------------------------------------------------------------------------------------------------------------

        try {
            DataHandler.createButtons(buttons, gridPane, true, deleteButton);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }


        HBox navButtonBox = new HBox();
        navButtonBox.setStyle("-fx-alignment: center; -fx-padding: 20");
        navButtonBox.setSpacing(10);
        navButtonBox.getChildren().addAll(addButton, deleteButton);

        VBox navBox = new VBox();
        navBox.setStyle("-fx-padding: 20; -fx-border-radius: 15; -fx-border-width: 2; -fx-border-color: black;");
        navBox.setMaxHeight(1);
        navBox.setSpacing(10);
        navBox.setMinWidth(280);
        navBox.getChildren().addAll(linksText, scrollPane, navButtonBox);

        //--------------------------------------------------------------------------------------------------------------

        VBox placeBox = new VBox();
        placeBox.setMinWidth(280);

        //--------------------------------------------------------------------------------------------------------------

        BorderPane popupBorderPane = new BorderPane();

        Text addButtonLabel = new Text("Add button");
        menuMethods.setLabelStyle(addButtonLabel);

        Text buttonNameText = new Text("Button Name: ");
        menuMethods.setPopUpStyle(buttonNameText);
        Text urlText = new Text("URL: ");
        menuMethods.setPopUpStyle(urlText);
        Text imgText = new Text("Add Icon: ");
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
        chooseFile.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'");

        chooseFile.setOnAction(actionEvent -> {
            DataHandler.saveData(stage, DataHandler.getInputFromTextField(inputButtonName, inputButtonUrl));
        });

        VBox inputBox = new VBox();
        inputBox.setStyle("-fx-padding: 15");
        inputBox.setSpacing(26);
        inputBox.getChildren().addAll(inputButtonName, inputButtonUrl, chooseFile);

        Button saveButton = new Button();
        ImageView saveButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/save.png"))));
        saveButtonImageView.setFitHeight(25);
        saveButtonImageView.setPreserveRatio(true);
        saveButton.setGraphic(saveButtonImageView);

        Button clearButton = new Button();
        ImageView clearButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/delete.png"))));
        clearButtonImageView.setFitHeight(25);
        clearButtonImageView.setPreserveRatio(true);
        clearButton.setGraphic(clearButtonImageView);

        clearButton.setOnAction(actionEvent -> {
            inputButtonName.setText("");
            inputButtonUrl.setText("");
        });

        Button cancelButton = new Button();
        ImageView cancelButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/cancel.png"))));
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
        addButtonBox.setStyle("-fx-padding: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 15");
        addButtonBox.setAlignment(Pos.CENTER);
        addButtonBox.setSpacing(10);
        addButtonBox.setMaxWidth(1);

        VBox wholeAddButtonBox = new VBox();
        wholeAddButtonBox.getChildren().addAll(addButtonBox);
        wholeAddButtonBox.setStyle("-fx-alignment: center");
        wholeAddButtonBox.setVisible(false);

        cancelButton.setOnAction(actionEvent -> {
            wholeAddButtonBox.setVisible(false);
        });

        saveButton.setOnAction(actionEvent -> {
            if (inputButtonName.getText() == "" || inputButtonUrl.getText() == "") {
                ErrorPopup errorPopup = new ErrorPopup();
                Stage errorStage = new Stage();
                try {
                    errorPopup.start(errorStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                wholeAddButtonBox.setVisible(false);
                DataHandler.reloadButtonList(buttons, gridPane, deleteButton);
                inputButtonName.setText("");
                inputButtonUrl.setText("");
            }
        });

        //--------------------------------------------------------------------------------------------------------------

        addButton.setOnAction(actionEvent -> {
            wholeAddButtonBox.setVisible(true);
        });

        //--------------------------------------------------------------------------------------------------------------

        BorderPane borderPane = new BorderPane();
        //borderPane.setStyle("-fx-background-color: linear-gradient(to top, #CBE1EF, #9ACDE0, #5EA9BE, #F3BFB3);");
        borderPane.setStyle("-fx-background-image: url(menuBackground.png); -fx-background-size: cover; -fx-alignment: center");
        borderPane.setTop(welcomeBox);
        borderPane.setCenter(wholeAddButtonBox);
        borderPane.setLeft(navBox);
        borderPane.setRight(placeBox);
        borderPane.setBottom(infoBox);

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