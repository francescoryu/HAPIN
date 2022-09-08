/**
 * @author: Francesco Ryu
 * @version: 1.0
 * @description methods for main menu
 */

package ch.francescoryu.hapin.buttonMethods;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.jetbrains.annotations.Nullable;

public class MenuMethods {
    public void setButtonStyle(Button button) {
        button.setStyle("-fx-font-size: 25; " +
                "-fx-font-family: 'Microsoft Sans Serif'; " +
                "-fx-background-radius: 10; " +
                "-fx-border-width: 2;  " +
                "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.7) , 10,0,0,1 ); ");

        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public void setLabelStyle(Text text) {
        text.setStyle("-fx-font-size: 30; " +
                "-fx-font-family: 'Microsoft Sans Serif'; " +
                "-fx-text-alignment: center");
    }

    public void setPopUpStyle(Text text) {
        text.setStyle("-fx-font-size: 20;" +
                "-fx-font-family: 'Microsoft Sans Serif'");
    }
    public void setInputTextFieldStyle (TextField textField) {
        textField.setStyle("-fx-font-size: 18;" +
                "-fx-font-family: 'Microsoft Sans Serif'");
    }

}
