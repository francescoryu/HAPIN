package ch.francescoryu.hapin.testing;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        LocalDateTime dt2 = LocalDateTime.parse("19:50:12");
        if (now.isAfter(dt2)) {
            System.out.println("TRUE");
        }
    }
}
