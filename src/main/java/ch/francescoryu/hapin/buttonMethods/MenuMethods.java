/**
 * @author: Francesco Ryu
 * @version: 1.0
 * @description methods for main menu
 */

package ch.francescoryu.hapin.buttonMethods;

import javafx.scene.control.Button;

public class MenuMethods {
    public void setButtonStyle(Button button) {
        button.setStyle("-fx-font-size: 35; -fx-font-family: 'Microsoft Sans Serif';");
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }
}
