package app.classes.view.gamingTable;


import app.classes.MainApp;
import app.classes.other.Alerts;
import app.classes.other.HelpClass;
import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;


public class Alert extends Region {
    private Timeline timeline = new Timeline();
    private Label label;

    public Alert(String alert, double perSecondUpdate, Alerts alerts) {


        Pane pane = new Pane();
        pane.setPrefWidth(1920 / MainApp.getSingleton().getDel());
        pane.setPrefHeight(1080 / MainApp.getSingleton().getDel());
        pane.setStyle("-fx-background-color: black");


        label = new Label(alert);
        label.setWrapText(true);
        switch (alerts) {
            case ERROR:
                label.setStyle("-fx-text-fill: darkred");
                break;
            case SUCCESS:
                label.setStyle("-fx-text-fill: forestgreen");
                break;
            default:
                label.setStyle("-fx-text-fill: navajowhite");
                break;
        }
        label.setOpacity(0);
        label.setFont(HelpClass.getFont(100 / MainApp.getSingleton().getDel()));

        label.setLayoutY(pane.getPrefHeight() / 2 - label.getFont().getSize());
        label.setTextAlignment(TextAlignment.CENTER);
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        if (alert.length() < 10)
            label.setLayoutX(
                    pane.getPrefWidth() / 2 - fontLoader.computeStringWidth(label.getText(), label.getFont()) / 2
            );
        else {
            label.setLayoutX(pane.getPrefWidth() / 4);
            label.setLayoutY(pane.getPrefHeight() / 2 - label.getFont().getSize() * 2);
        }
        label.setMaxWidth(pane.getPrefWidth() / 2);
        getChildren().addAll(pane, label);


        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().addAll(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(pane.opacityProperty(), 0),
                        new KeyValue(label.opacityProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(perSecondUpdate),
                        new KeyValue(pane.opacityProperty(), 0.7),
                        new KeyValue(label.opacityProperty(), 1)

                )
        );
        timeline.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.toSeconds() == perSecondUpdate) {

                timeline.pause();
            }
        });
        timeline.setAutoReverse(true);
        timeline.setCycleCount(2);

        setOnMouseClicked(event -> {
            timeline.play();
        });
        timeline.setOnFinished(event -> {
            setVisible(false);
        });
    }


    public void animationStart() {
        timeline.play();
    }

    public void animationStop() {
        timeline.stop();
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setText(String text) {
        label.setText(text);
    }


}
