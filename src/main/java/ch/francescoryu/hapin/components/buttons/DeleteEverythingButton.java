package ch.francescoryu.hapin.components.buttons;

import ch.francescoryu.hapin.Main;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class DeleteEverythingButton extends Button {
    public DeleteEverythingButton() {
        ImageView deleteButtonImageView = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("navMenuImg/deleteEverything.png"))));
        setStyle("-fx-background-color: #F8F5FA;");
        deleteButtonImageView.setFitHeight(35);
        deleteButtonImageView.setPreserveRatio(true);
        setGraphic(deleteButtonImageView);
        setCursor(Cursor.HAND);
        setPrefSize(35, 35);
    }
}
