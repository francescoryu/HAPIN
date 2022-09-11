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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class MenuInputPopup extends Application {
    TextField inputButtonName;
    TextField inputButtonUrl;

    final Text source = new Text(50, 100, "DRAG ME");
    final Text target = new Text(300, 100, "DROP HERE");

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

        saveButton.setOnAction(actionEvent -> {
            DataHandler.writeFile(getInputFromTextField());
            try {
                DataHandler.readFileAsString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        FileChooser fileChooser = new FileChooser();

        Button chooseFile = new Button("Datei auswählen");

        chooseFile.setOnAction(actionEvent -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
            fileChooser.setInitialDirectory(new File("navMenuImg/" + selectedFile.getName()));
            System.out.println(selectedFile.getName());
            Path from = Paths.get(selectedFile.toURI());
            Path to = Paths.get("Images/" + selectedFile.getName());
            try {
                Files.copy(from, to);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

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
    }



    public String getInputFromTextField() {
        String inputFromTextField = inputButtonName.getText() + " " + inputButtonUrl.getText();
        System.out.println(inputFromTextField);

        return inputFromTextField;
    }
}
