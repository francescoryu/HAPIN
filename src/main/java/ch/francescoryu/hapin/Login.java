package ch.francescoryu.hapin;

import ch.francescoryu.hapin.components.boxes.TodoBox;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author: Francesco Ryu
 * @version: 1.0
 * @date 29.09.2022
 * @description Login-dialog to recognize the person.
 */

public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Label loginLabel = new Label("Login");
        loginLabel.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 30; -fx-text-fill: #cfcfcf");

        HBox loginLabelBox = new HBox(loginLabel);
        loginLabelBox.setAlignment(Pos.TOP_CENTER);

        ComboBox<String> userComboBox = new ComboBox<>();
        userComboBox.getStyleClass().addAll(".combo-box", ".combo-box-base");
        userComboBox.setCursor(Cursor.HAND);
        DataHandler.loadUserToComboBox(userComboBox);
        userComboBox.setMinWidth(200);
        userComboBox.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15; -fx-border-width: 1; -fx-border-color: #737373");
        userComboBox.setPromptText("Select user");

        PasswordField pwdTextField = new PasswordField();
        pwdTextField.setMaxWidth(200);
        pwdTextField.setStyle("-fx-font-size: 15;" +
                "-fx-font-family: 'Microsoft Sans Serif';" +
                "-fx-text-fill: #cfcfcf;" +
                "-fx-control-inner-background: black;" +
                "-fx-border-color: #737373;" +
                "-fx-border-width: 1");
        pwdTextField.setPromptText("Enter Password");


        Label showPwdLabel = new Label();

        Button showPwdButton = new Button();
        showPwdButton.setStyle("-fx-min-width: 20; -fx-min-height: 20");

        ImageView showPwdImageView = new ImageView(new Image(Objects.requireNonNull(Login.class.getResourceAsStream("navMenuImg/showPwd.png"))));
        showPwdImageView.setFitHeight(20);
        showPwdImageView.setPreserveRatio(true);
        showPwdButton.setGraphic(showPwdImageView);

        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(userComboBox, pwdTextField);
        inputBox.setSpacing(15);
        inputBox.setStyle("-fx-padding: 5");
        inputBox.setAlignment(Pos.TOP_CENTER);

        showPwdButton.setOnAction(actionEvent -> {

        });

        Button loginButton = new Button("Login");
        loginButton.setCursor(Cursor.HAND);
        loginButton.setStyle("-fx-font-family: 'Times New Roman'; -fx-background-color: black; -fx-text-fill: #cfcfcf; -fx-font-size: 15; -fx-border-color: #737373; -fx-border-width: 1; -fx-prompt-text-fill: #cfcfcf");
        loginButton.setOnAction(actionEvent -> {
            try {
                DataHandler.checkLoginData(userComboBox.getValue(), pwdTextField, stage);
                //DataHandler.setUserName(userComboBox.getValue());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        HBox buttonBox = new HBox(loginButton);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setStyle("-fx-padding: 10");

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: linear-gradient(to right bottom, #11002b, #3f0028);");
        borderPane.setTop(loginLabelBox);
        borderPane.setCenter(inputBox);
        borderPane.setBottom(buttonBox);

        borderPane.getStylesheets().add(TodoBox.class.getResource("/css/style.css").toExternalForm());

        Scene scene = new Scene(borderPane, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.show();

    }
}
