package ch.francescoryu.hapin;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Label loginLabel = new Label("Login");
        loginLabel.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 30");

        HBox loginLabelBox = new HBox(loginLabel);
        loginLabelBox.setAlignment(Pos.TOP_CENTER);

        ComboBox<String> userComboBox = new ComboBox<>();
        DataHandler.loadUserToComboBox(userComboBox);
        userComboBox.setMinWidth(200);
        userComboBox.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15");
        userComboBox.setPromptText("Select User");

        PasswordField pwdTextField = new PasswordField();
        pwdTextField.setMaxWidth(200);
        pwdTextField.setPromptText("Enter Password");

        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(userComboBox, pwdTextField);
        inputBox.setSpacing(30);
        inputBox.setStyle("-fx-padding: 10");
        inputBox.setAlignment(Pos.TOP_CENTER);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15");
        loginButton.setOnAction(actionEvent -> {
            try {
                DataHandler.checkLoginData(userComboBox.getValue(), pwdTextField, stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        HBox buttonBox = new HBox(loginButton);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setStyle("-fx-padding: 10");

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-image: url(loginBackground.png);-fx-background-position: center center;");
        borderPane.setTop(loginLabelBox);
        borderPane.setCenter(inputBox);
        borderPane.setBottom(buttonBox);

        Scene scene = new Scene(borderPane, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.show();

    }
}
