package app.view.gamingTable;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

/**
 * Created by kills on 14.02.2017.
 */
public class card extends Pane {
    private Image image;
    private double sizeX;
    private double sizeY;
    private double perSecondUpdate;
    private double parentSizeX;
    private double parentSizeY;
    private int del;
    private Timeline animation;
    private double checkLocation;
    private boolean up;

    public card(Image image, double sizeX, double sizeY, double updateAnimation, int del, double sizeParentX, double sizeParentY, boolean upOrDown) {
        this.image = image;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.del = del;
        parentSizeX = sizeParentX;
        parentSizeY = sizeParentY;
        perSecondUpdate = updateAnimation;
        this.up = upOrDown;
        setBackground(new Background
                (new BackgroundImage(
                        image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        new BackgroundSize(sizeX, sizeY, true, true, true, false))));
        setPrefSize(sizeX / del, sizeY / del);
        setOnMouseClicked(event -> {
            animation.play();
            for (Node node : getParent().getChildrenUnmodifiable()) {
                if (!node.equals(this)) node.setDisable(true);
            }

        });

        animationPlay();

    }

    public void animationPlay() {
        if (up) parentSizeY = parentSizeY / -1;
        else parentSizeY = parentSizeY - getPrefHeight() * del;
        KeyFrame keyFrameStartPosition = new KeyFrame(Duration.seconds(perSecondUpdate),
                new KeyValue(translateXProperty(), 0),
                new KeyValue(translateYProperty(), (checkLocation = 0)),
                new KeyValue(prefHeightProperty(), getPrefHeight()),
                new KeyValue(prefWidthProperty(), getPrefWidth()));
        KeyFrame keyFrameCenter = new KeyFrame(Duration.seconds(perSecondUpdate),
                new KeyValue(translateXProperty(), parentSizeX / del - getPrefWidth()),
                new KeyValue(translateYProperty(), (checkLocation = parentSizeY / del)),
                new KeyValue(prefHeightProperty(), getPrefHeight() * del),
                new KeyValue(prefWidthProperty(), getPrefWidth() * del));
        animation = new Timeline();
        animation.getKeyFrames().clear();
        animation.getKeyFrames().addAll(
                keyFrameCenter
        );
        animation.setOnFinished(event1 -> {
            animation.stop();
            if (checkLocation == 0) {
                checkLocation = 1;
                for (Node node : getParent().getChildrenUnmodifiable()) {
                    if (!node.equals(this)) node.setDisable(false);
                }
                animation.getKeyFrames().clear();
                animation.getKeyFrames().addAll(
                        keyFrameCenter
                );
            } else {
                checkLocation = 0;
                animation.getKeyFrames().clear();
                animation.getKeyFrames().addAll(
                        keyFrameStartPosition);
            }
        });
    }
}
