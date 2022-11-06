module ch.francescoryu.hapin {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires org.jetbrains.annotations;

    opens ch.francescoryu.hapin to javafx.fxml;
    exports ch.francescoryu.hapin;
    exports ch.francescoryu.hapin.testing;
    opens ch.francescoryu.hapin.testing to javafx.fxml;
    exports ch.francescoryu.hapin.components;
    opens ch.francescoryu.hapin.components to javafx.fxml;
}