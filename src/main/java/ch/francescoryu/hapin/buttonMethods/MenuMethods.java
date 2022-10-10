package ch.francescoryu.hapin.buttonMethods;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author: Francesco Ryu
 * @version: 1.0
 * @date 29.09.2022
 * @description methods for main menu
 */

public class MenuMethods {
    public static void setButtonStyle(Button button) {
        button.setStyle("-fx-font-size: 25; " +
                "-fx-font-family: 'Microsoft Sans Serif'; " +
                "-fx-background-radius: 5; " +
                "-fx-border-width: 2;  " +
                //"-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.7) , 10,0,0,1 );" +
                "-fx-min-width: 180");

        button.setCursor(Cursor.HAND);

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

    public void setInputTextFieldStyle(TextField textField) {
        textField.setStyle("-fx-font-size: 16;" +
                "-fx-font-family: 'Microsoft Sans Serif'");
    }
}
