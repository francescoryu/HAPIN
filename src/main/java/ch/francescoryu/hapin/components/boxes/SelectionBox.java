package ch.francescoryu.hapin.components.boxes;

import ch.francescoryu.hapin.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class SelectionBox extends Application {

    ArrayList<Tab> tabs = new ArrayList<>();
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    @Override
    public void start(Stage stage) throws Exception {

        HyperlinkBox hyperlinkBox = new HyperlinkBox();
        BalanceBox balanceBox = new BalanceBox();
        TodoBox todoBox = new TodoBox();
        WelcomeBox welcomeBox = new WelcomeBox();
        TicTacToeBox ticTacToeBox = new TicTacToeBox();

        TabPane tabPane = new TabPane();
        tabPane.getStylesheets().add(TodoBox.class.getResource("/css/style.css").toExternalForm());

        Tab welcomeTab = new Tab("Home", welcomeBox);
        tabs.add(welcomeTab);
        Tab hyperTab = new Tab("Hyperlink", hyperlinkBox);
        tabs.add(hyperTab);
        Tab balanceTab = new Tab("Balance", balanceBox);
        tabs.add(balanceTab);
        Tab toDoTab = new Tab("TodoList", todoBox);
        tabs.add(toDoTab);
        Tab ticTacToeTab = new Tab("TicTacToe", ticTacToeBox);
        tabs.add(ticTacToeTab);

        tabPane.getTabs().addAll(welcomeTab, hyperTab, balanceTab, toDoTab, ticTacToeTab);
        tabPane.getStyleClass().add(".tab-pane");
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabPane);
        stage.setScene(scene);
        stage.setHeight(700);
        stage.getIcons().add(new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("navMenuImg/icon.png")))).getImage());
        stage.setTitle("HAPIN");
        stage.setWidth(650);
        stage.show();
    }
}