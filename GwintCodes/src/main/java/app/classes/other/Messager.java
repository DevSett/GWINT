
package app.classes.other;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Messager {

    Stage stage;

    public static final int ERROR = 0;
    public static final int INFO = 1;
    public static final int SUCCESS = 2;


    public Messager(Stage stage) {
        this.stage = stage;
    }

    private static Popup createPopup(final String message, int state) {
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setHideOnEscape(true);
        Label label = new Label(message);
        label.setWrapText(true);
        label.setMaxWidth(400.0d);
        label.setOnMouseReleased(event -> popup.hide());

        String color = "#6e9086";
        switch (state) {
            case ERROR:
                color = "#cf1f42";
//                playSomeSound("/sound/notify-2.mp3");
                break;
            case INFO:
                color = "#3387c9";
                break;
            case SUCCESS:
                color = "#08b4a3";
                break;
        }

        label.setStyle(
                "    -fx-background-color: #FCFCFC;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-padding: 25;\n" +
                        "    -fx-border-color: " + color + "; \n" +
                        "    -fx-border-width: 5;\n" +
                        "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                        "    -fx-font-size: 16;");
        popup.getContent().add(label);
        return popup;
    }

    public void showPopupMessage(final String message) {
        showPopupMessage(message, INFO);
    }

    public void showPopupMessage(final String message, int state) {
        showPopupMessage(message, state, 3);
    }

    public void showPopupMessage(final String message, int state, int second) {
        showPopupMessage(message, state, second, true);
    }

    public void showPopupMessage(final String message, int state, int seconds, boolean block) {
        final Popup popup = createPopup(message, state);
        popup.setOnShown(event -> {
            popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
            popup.setY(stage.getY() + stage.getHeight() / 2 - popup.getHeight() / 2);
        });
        Platform.runLater(() -> popup.show(stage));
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));

        pause.setOnFinished(event -> {
            if (block) stage.getScene().getRoot().setDisable(false);
            popup.hide();
        });
        pause.play();
        if (block) stage.getScene().getRoot().setDisable(true);
    }

//    private static void playMedia(Media m){
//        if (m != null){
//            MediaPlayer mp = new MediaPlayer(m);
//            mp.play();
//        }
//    }
//
//    public static void playSomeSound(String url){
//        try{
//            Media someSound = new Media(Messager.class.getResource(url).toString());
//            playMedia(someSound);
//        }catch(Exception ex){
//
//        }
//
//    }

}
