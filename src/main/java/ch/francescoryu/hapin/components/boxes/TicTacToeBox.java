package ch.francescoryu.hapin.components.boxes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class TicTacToeBox extends VBox {

    int row = 0;
    int column = 0;
    public TicTacToeBox() {
        this.getStylesheets().add(TodoBox.class.getResource("/css/ticTacToe.css").toExternalForm());
        GridPane buttonGridPane = new GridPane();
        buttonGridPane.setVgap(10);
        buttonGridPane.setHgap(10);
        buttonGridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 9; i++) {

            Button button = new Button();
            button.getStyleClass().add(".button");
            buttonGridPane.add(button, row, column);
            column++;
            if (column % 3 == 0) {
                row++;
                column = 0;
            }
        }
        VBox playgroundBox = new VBox(buttonGridPane);
        playgroundBox.setAlignment(Pos.CENTER);
        getChildren().addAll(playgroundBox);
        setAlignment(Pos.CENTER);
    }
}
