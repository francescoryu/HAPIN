package ch.francescoryu.hapin.components.boxes;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;

public class CustomLinkBox extends VBox {


    public void loadLink(String link) throws Exception {

        this.getStylesheets().add(CustomLinkBox.class.getResource("/css/style.css").toExternalForm());

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        int height = (int) size.getHeight();

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webView.getStyleClass().addAll(".webkit-scrollbar-thumb", ".scroll-bar");

        webEngine.load("https://" + link);
        webView.setPrefHeight(height);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().add(webView);

        Scene scene = new Scene(vbox);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setMaximized(true);

        stage.show();
    }
}
