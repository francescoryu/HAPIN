package ch.francescoryu.hapin.components.boxes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SelectionBox extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        HyperlinkBox hyperlinkBox = new HyperlinkBox();
        BalanceBox balanceBox = new BalanceBox();
        TodoBox todoBox = new TodoBox();

        TabPane tabPane = new TabPane();
        Tab hyperTab = new Tab("Hyperlink", hyperlinkBox);
        Tab balanceTab = new Tab("Balance", balanceBox);
        Tab toDoTab = new Tab("TodoList", todoBox);
        tabPane.getTabs().addAll(hyperTab, balanceTab, toDoTab);
        tabPane.setStyle("-fx-background-color: linear-gradient(to right bottom, #11002b, #3f0028);");

        Scene scene = new Scene(tabPane);
        stage.setScene(scene);
        stage.show();
    }
}
