package ch.francescoryu.hapin.popups;

import ch.francescoryu.hapin.DataHandler;
import ch.francescoryu.hapin.MenuInputPopup;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class MenuDeletePopup extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        int i;
        ArrayList<Button> buttons = new ArrayList<>();
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #d2b0d9; -fx-padding: 10; -fx-alignment: center");
        gridPane.setVgap(10);

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setStyle("-fx-padding: 10; -fx-border-style: solid inside; -fx-border-width: 2; -fx-background-color: #d2b0d9;");
        scrollPane.setFitToWidth(true);

        Button deleteButton = new Button();
        ImageView deleteButtonImageView = new ImageView(new Image(Objects.requireNonNull(MenuInputPopup.class.getResourceAsStream("navMenuImg/delete.png"))));
        deleteButtonImageView.setFitHeight(35);
        deleteButtonImageView.setPreserveRatio(true);
        deleteButton.setGraphic(deleteButtonImageView);

        DataHandler.createButtons(buttons, gridPane, false, deleteButton);

        HBox buttonBox = new HBox();
        buttonBox.setStyle("-fx-alignment: center; -fx-padding: 10");
        buttonBox.getChildren().add(deleteButton);


        BorderPane popupBorderPane = new BorderPane();
        popupBorderPane.setCenter(scrollPane);
        popupBorderPane.setBottom(buttonBox);

        Scene scene = new Scene(popupBorderPane, 300, 600);
        stage.setScene(scene);
        stage.show();
    }
}
