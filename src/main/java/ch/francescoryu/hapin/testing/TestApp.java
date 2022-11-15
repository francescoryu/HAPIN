package ch.francescoryu.hapin.testing;

import ch.francescoryu.hapin.Main;
import ch.francescoryu.hapin.components.TodoObject;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class TestApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Media media = new Media(new File("src/main/resources/sounds/test.wav").toURI().toString());




        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        MediaView mediaView = new MediaView(mediaPlayer);

        Slider slVolume = new Slider();
        slVolume.setPrefWidth(150);
        slVolume.setMaxWidth(Region.USE_PREF_SIZE);
        slVolume.setMinWidth(30);
        slVolume.setValue(50);
        mediaPlayer.volumeProperty().bind(slVolume.valueProperty().divide(100));

        Slider slTime = new Slider();
        slTime.setPrefWidth(150);
        slTime.setMaxWidth(Region.USE_PREF_SIZE);
        slTime.setMinWidth(30);
        slTime.setMin(0);

        Duration duration = mediaPlayer.getMedia().getDuration();

        slTime.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (slTime.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    mediaPlayer.seek(duration.multiply(slTime.getValue() / 100.0));
                }
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(mediaView, slVolume, slTime);

        Scene scene = new Scene(vbox);

        stage.setScene(scene);

        stage.show();
    }
}
