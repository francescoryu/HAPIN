package ch.francescoryu.hapin.components.buttons;

import ch.francescoryu.hapin.Menu;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class AddButton extends Button {
    public AddButton() {
        ImageView addButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/add.png"))));
        addButtonImageView.setFitHeight(35);
        addButtonImageView.setPreserveRatio(true);
        this.setGraphic(addButtonImageView);
    }
}