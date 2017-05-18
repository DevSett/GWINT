package app.classes.view.gamingTable;

import app.classes.MainApp;
import app.classes.rulesGaming.Card;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;


/**
 * Created by kills on 14.02.2017.
 */
public class ShirtCard extends Region {
    private double width;
    private double height;
    private Card card;
    private Timeline timeline;
    private boolean onCard = false;
    private boolean animation;
    private Label label;
    private String src;
    public ShirtCard(String src, double width, double height, boolean animation) {
        super();

        this.width = width / MainApp.getSingleton().getDel();
        this.height = height / MainApp.getSingleton().getDel();
        this.card = card;
        this.animation = animation;
        this.src = src;
        draw();
        if (animation)
            animation();
    }


    private void draw() {

        Image image = new Image(src);

        setBackground(new Background
                (new BackgroundImage(
                        image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        new BackgroundSize(width, height, true, true, true, false))));

        setPrefSize(
                width,
                height
        );

    }


    public double getSizeX() {
        return width;
    }

    public double getSizeY() {
        return height;
    }

    public Card getCard() {
        return card;
    }

    @Override
    protected void setWidth(double value) {
        this.width = value;
        super.setWidth(value);
    }

    @Override
    protected void setHeight(double value) {
        this.height = value;
        super.setHeight(value);
    }

    private AnimationDescription timelineCard;


    private void animation() {

        timeline = new Timeline();
        timeline.getKeyFrames().addAll
                (new KeyFrame(Duration.ZERO,
                                new KeyValue(this.translateYProperty(), 0)),
                        new KeyFrame(new Duration(70),
                                new KeyValue(this.translateYProperty(), -10)));
        timeline.setAutoReverse(true);
        timeline.setCycleCount(2);

        setOnMouseEntered(event -> {
            Platform.runLater(() -> timeline.play());
            onCard = true;
        });

        timeline.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.toSeconds() == 0.07 && onCard) {
                Platform.runLater(() -> timeline.pause());
            }
            if (!onCard && newValue != Duration.ZERO )
                timeline.play();
        });

        setOnMouseExited(event -> {
            onCard = false;
            if (!timeline.getCurrentTime().equals(Duration.ZERO))
                Platform.runLater(() -> timeline.play());
        });

    }

    public boolean isAnimation() {
        return animation;
    }

}
