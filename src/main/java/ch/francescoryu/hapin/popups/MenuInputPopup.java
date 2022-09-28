package ch.francescoryu.hapin.popups;

import ch.francescoryu.hapin.DataHandler;
import ch.francescoryu.hapin.Menu;
import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * @author: Francesco Ryu
 * @version: 1.0
 * @date 08.09.2022
 * @description A helping software for people who want to have everything compact(First project with JavaFx).
 */

public class MenuInputPopup extends Application {
    TextField inputButtonName;
    TextField inputButtonUrl;
    @Override
    public void start(Stage stage) throws Exception {

        BorderPane popupBorderPane = new BorderPane();
        MenuMethods menuMethods = new MenuMethods();

        Text buttonNameText = new Text("Button Name: ");
        menuMethods.setPopUpStyle(buttonNameText);
        Text urlText = new Text("Pfad eingeben: ");
        menuMethods.setPopUpStyle(urlText);
        Text imgText = new Text("Bild hinzufügen: ");
        menuMethods.setPopUpStyle(imgText);

        VBox textBox = new VBox();
        textBox.setStyle("-fx-padding: 20");
        textBox.setSpacing(35);
        textBox.getChildren().addAll(buttonNameText, urlText, imgText);

        inputButtonName = new TextField();
        inputButtonName.setPrefColumnCount(20);
        menuMethods.setInputTextFieldStyle(inputButtonName);

        inputButtonUrl = new TextField();
        menuMethods.setInputTextFieldStyle(inputButtonUrl);
        inputButtonUrl.setPrefColumnCount(20);

        VBox inputBox = new VBox();
        inputBox.setStyle("-fx-padding: 15");
        inputBox.setSpacing(20);
        inputBox.getChildren().addAll(inputButtonName, inputButtonUrl);

        Button saveButton = new Button();
        ImageView saveButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/save.png"))));
        saveButtonImageView.setFitHeight(25);
        saveButtonImageView.setPreserveRatio(true);
        saveButton.setGraphic(saveButtonImageView);


        Button chooseFile = new Button("Datei auswählen");
        chooseFile.setStyle("-fx-font-size: 20; -fx-font-family: 'Microsoft Sans Serif'");

        chooseFile.setOnAction(actionEvent -> {
            DataHandler.saveData(stage, DataHandler.getInputFromTextField(inputButtonName, inputButtonUrl));
        });

        Button cancelButton = new Button();
        ImageView cancelButtonImageView = new ImageView(new Image(Objects.requireNonNull(Menu.class.getResourceAsStream("navMenuImg/delete.png"))));
        cancelButtonImageView.setFitHeight(25);
        cancelButtonImageView.setPreserveRatio(true);
        cancelButton.setGraphic(cancelButtonImageView);

        cancelButton.setOnAction(actionEvent -> {
            inputButtonName.setText("");
            inputButtonUrl.setText("");
        });

        HBox buttonBox = new HBox();
        buttonBox.setStyle("-fx-alignment: center; -fx-padding: 10");
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(cancelButton, saveButton, chooseFile);

        popupBorderPane.setLeft(textBox);
        popupBorderPane.setRight(inputBox);
        popupBorderPane.setBottom(buttonBox);
        Scene scene = new Scene(popupBorderPane, 600, 300);
        Stage stage1 = new Stage();
        stage1.setTitle("Hinzufügen");
        stage1.setScene(scene);
        stage1.setResizable(false);
        stage1.show();

        saveButton.setOnAction(actionEvent -> {
            stage1.hide();
        });
    }


}