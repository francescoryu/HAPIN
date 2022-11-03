package ch.francescoryu.hapin.components.buttons;

import ch.francescoryu.hapin.Main;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class AddButton extends Button {
    public AddButton(double d) {
        ImageView addButtonImageView = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("navMenuImg/add.png"))));
        setStyle("-fx-background-color: #F8F5FA;");
        addButtonImageView.setFitHeight(d);
        addButtonImageView.setPreserveRatio(true);
        setGraphic(addButtonImageView);
        setCursor(Cursor.HAND);
        setPrefSize(35, 35);
    }
}
