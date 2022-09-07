/**
 * @author: Francesco Ryu
 * @version: 1.0
 * @description A helping software for people who want to have everything compact(First project with JavaFx).
 */
package ch.francescoryu.hapin;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.EmptyStackException;


public class Menu extends Application {
    @Override
    public void start(Stage stage) {
        MenuMethods navButtonMethod = new MenuMethods();
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
        Text linksText = new Text("Schnellzugriff");
        navButtonMethod.setLabelStyle(linksText);

        Button googleButton = new Button("Google");
        navButtonMethod.setButtonStyle(googleButton);

        googleButton.setOnAction(e -> {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.google.com"));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
        );

        Button youtubeButton = new Button("Youtube");
        navButtonMethod.setButtonStyle(youtubeButton);

        youtubeButton.setOnAction(e -> {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.youtube.com"));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
        );

        Button tagiButton = new Button("Tagesanzeiger");
        navButtonMethod.setButtonStyle(tagiButton);

        tagiButton.setOnAction(e -> {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.tagesanzeiger.ch"));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
        );

        Button facebookButton = new Button("Facebook");
        navButtonMethod.setButtonStyle(facebookButton);

        facebookButton.setOnAction(e -> {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.facebook.com"));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
        );
        //--------------------------------------------------------------------------------------------------------------
        Group navButtonGroup = new Group();
        navButtonGroup.getChildren().addAll(googleButton, youtubeButton, tagiButton, facebookButton);
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

        VBox navBox = new VBox(5);
        navBox.setSpacing(10);
        navBox.setBackground(Background.fill(Color.web("#d2b0d9")));
        navBox.getChildren().addAll(linksText, googleButton, youtubeButton, tagiButton, facebookButton);
        navBox.setStyle("-fx-padding: 10; -fx-border-style: solid inside; -fx-border-width: 2");
        //--------------------------------------------------------------------------------------------------------------
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: linear-gradient(to bottom right, #6a5acd, #ff7f50);");
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