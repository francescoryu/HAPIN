package ch.francescoryu.hapin.components.boxes;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BalanceBox extends VBox {
    int rowCntr = 0;
    int columbCntr = 0;

    public BalanceBox() {

        getStylesheets().add(TodoBox.class.getResource("/css/style.css").toExternalForm());

        setStyle("-fx-border-color: black");

        Label balanceLabel = new Label("Balance");
        MenuMethods.setLabelStyle(balanceLabel);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        for (int i = 0; i < 40; i++) {
            if (i % 3 == 0) {
                columbCntr++;
                rowCntr = 0;
            }

            Button button = new Button();
            MenuMethods.setButtonStyle(button);
            button.setText("TEST " + i);
            gridPane.add(button, rowCntr, columbCntr);
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            rowCntr++;
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent");
        scrollPane.getStyleClass().addAll(".scroll-pane" , ".scroll-bar");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
        borderPane.setStyle("-fx-alignment: center;");
        getChildren().addAll(balanceLabel, borderPane);
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        setStyle("-fx-alignment: center; -fx-padding: 20");
        setMaxHeight(400);
        setMaxWidth(650);
    }
}
