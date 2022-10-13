package ch.francescoryu.hapin.components.boxes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BalanceBox extends VBox {
    int rowCntr = 0;
    int columbCntr = 0;

    public BalanceBox() {
        setStyle("-fx-border-color: black");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);

        for (int i = 0; i < 20; i++) {
            Button button = new Button();
            button.setText("TEST " + i);
            gridPane.add(button, i, columbCntr);
            if (i % 3 == 0) {

            }
        }

        ScrollPane scrollPane = new ScrollPane(gridPane);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
        getChildren().add(borderPane);
    }
}
