package ch.francescoryu.hapin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class TestApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        DatePicker datePicker = new DatePicker(LocalDate.now());
        Button button = new Button();
        button.setOnAction(actionEvent -> {
            System.out.println(datePicker.getEditor().getText());
        });
        VBox textBox = new VBox();
        textBox.getChildren().addAll(button, datePicker);
        Scene scene = new Scene(textBox);
        stage.setScene(scene);
        stage.show();
    }
}
