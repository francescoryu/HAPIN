package ch.francescoryu.hapin.components.boxes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.*;

public class BalanceBox extends VBox {
    int rowCntr = 0;
    int columbCntr = 0;

    public BalanceBox() {
        setStyle("-fx-border-color: black");


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);

        for (int i = 0; i < 40; i++) {
            if (i % 3 == 0) {
                columbCntr++;
                rowCntr = 0;
            }

            Button button = new Button();
            button.setText("TEST " + i);
            gridPane.add(button, rowCntr, columbCntr);
            rowCntr++;
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-border-color: white; -fx-background-color: black; -fx-alignment: center");
        borderPane.setCenter(scrollPane);
        getChildren().add(borderPane);
        setStyle("-fx-pref-height: 500; -fx-pref-width: 500;");
    }
}
