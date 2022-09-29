package ch.francescoryu.hapin;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import ch.francescoryu.hapin.popups.MenuInputPopup;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
 * @date 08.09.2022
 * @description A helping software for people who want to have everything compact(First project with JavaFx).
 */

public class Menu extends Application {

    private final ArrayList<Button> buttons = new ArrayList<>();

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
        welcomeBox.setBorder(new Border(new BorderStroke(Color.MEDIUMORCHID, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        Text welcomeText = new Text("Welcome! Have a nice day!");
        welcomeText.setStyle("-fx-font-size: 60; -fx-padding: 20, 40; -fx-font-family: 'Microsoft Sans Serif'");
        welcomeBox.getChildren().addAll(welcomeText);
        welcomeBox.setStyle("-fx-padding: 10; -fx-alignment: center;");

        HBox infoBox = new HBox(3);
        infoBox.setBorder(new Border(new BorderStroke(Color.MEDIUMORCHID, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        infoBox.setSpacing(50);
        infoBox.setStyle("-fx-alignment: center-right; -fx-font-size: 40; -fx-padding: 10");
        infoBox.getChildren().addAll(clock);

        //--------------------------------------------------------------------------------------------------------------

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #d2b0d9; -fx-padding: 10; -fx-alignment: center");
        gridPane.setVgap(10);
        gridPane.setHgap(20);




        //--------------------------------------------------------------------------------------------------------------




        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setStyle("-fx-padding: 10; -fx-border-style: solid inside; -fx-border-width: 2; -fx-background-color: #d2b0d9;");
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxHeight(500);

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
            MenuInputPopup menuInputPopup = new MenuInputPopup();
            try {
                menuInputPopup.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });

        //--------------------------------------------------------------------------------------------------------------

        try {
            DataHandler.createButtons(buttons, gridPane, true, deleteButton);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Button refreshButton = new Button();
        ImageView refreshButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/refresh.png"))));
        refreshButtonImageView.setFitHeight(35);
        refreshButtonImageView.setPreserveRatio(true);
        refreshButton.setGraphic(refreshButtonImageView);

        refreshButton.setOnAction(actionEvent -> {
            DataHandler.reloadButtonList(buttons, gridPane, deleteButton);
        });

        HBox navButtonBox = new HBox();
        navButtonBox.setStyle("-fx-alignment: center; -fx-padding: 20");
        navButtonBox.setSpacing(10);
        navButtonBox.getChildren().addAll(addButton, deleteButton, refreshButton);

        VBox navBox = new VBox();
        navBox.setStyle("-fx-padding: 20; " +
                "-fx-alignment: center;" +
                "");
        navBox.setSpacing(10);
        navBox.setMinWidth(350);
        navBox.getChildren().addAll(linksText, scrollPane, navButtonBox);

        //-------------------------------------------------------------------------------------------------------------



        //--------------------------------------------------------------------------------------------------------------

        BorderPane borderPane = new BorderPane();
        //borderPane.setStyle("-fx-background-color: linear-gradient(to top, #CBE1EF, #9ACDE0, #5EA9BE, #F3BFB3);");
        borderPane.setStyle("-fx-background-image: url(menuBackground.png); -fx-background-size: cover");
        borderPane.setTop(welcomeBox);
        borderPane.setLeft(navBox);
        borderPane.setBottom(infoBox);

        //--------------------------------------------------------------------------------------------------------------

        Scene scene = new Scene(borderPane);
        stage.setTitle("HAPIN");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        //--------------------------------------------------------------------------------------------------------------
    }
}