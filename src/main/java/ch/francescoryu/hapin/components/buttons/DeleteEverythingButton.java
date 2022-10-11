package ch.francescoryu.hapin.components.buttons;

import ch.francescoryu.hapin.Menu;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class DeleteEverythingButton extends Button {
    public DeleteEverythingButton() {
        ImageView deleteButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/deleteEverything.png"))));
        setStyle("-fx-background-color: black;-fx-border-width: 1; -fx-border-color: white");
        deleteButtonImageView.setFitHeight(35);
        deleteButtonImageView.setPreserveRatio(true);
        setGraphic(deleteButtonImageView);
        setCursor(Cursor.HAND);
    }
}
