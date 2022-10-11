package ch.francescoryu.hapin.components.buttons;

import ch.francescoryu.hapin.Menu;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class AddButton extends Button {
    public AddButton() {
        ImageView addButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/add.png"))));
        setStyle("-fx-background-color: black;-fx-border-width: 2; -fx-border-color: #737373");
        addButtonImageView.setFitHeight(35);
        addButtonImageView.setPreserveRatio(true);
        setGraphic(addButtonImageView);
        setCursor(Cursor.HAND);
    }
}
