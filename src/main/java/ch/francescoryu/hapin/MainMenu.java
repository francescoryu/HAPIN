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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.Calendar;


public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
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
        Button googleButton = new Button("Google");
        navButtonMethod.setButtonStyle(googleButton);

        googleButton.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("http://www.google.com"));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        }
        );

        Button youtubeButton = new Button("Youtube");
        navButtonMethod.setButtonStyle(youtubeButton);

        youtubeButton.setOnAction(e -> {
                    try {
                        Desktop.getDesktop().browse(new URI("http://www.youtube.com"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
        );
        //--------------------------------------------------------------------------------------------------------------
        Group navButtonGroup = new Group();
        navButtonGroup.getChildren().addAll(googleButton, youtubeButton);

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
        navBox.getChildren().addAll(googleButton, youtubeButton);
        navBox.setStyle("-fx-padding: 10px;");
        navBox.setBorder(new Border(new BorderStroke(Color.MEDIUMORCHID, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
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

    public static void main(String[] args) {
        launch();
    }
}