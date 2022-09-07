module ch.francescoryu.hapin {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens ch.francescoryu.hapin to javafx.fxml;
    exports ch.francescoryu.hapin;
}