package ch.francescoryu.hapin.testing;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;

public class TestApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        // width will store the width of the screen
        int width = (int) size.getWidth();

        // height will store the height of the screen
        int height = (int) size.getHeight();

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://google.ch");
        webView.setPrefHeight(height - 150);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().add(webView);

        Scene scene = new Scene(vbox);

        stage.setScene(scene);

        stage.show();
    }
}
