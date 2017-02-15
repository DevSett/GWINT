package app.view.gamingTable;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by kills on 15.02.2017.
 */
public class AnimationCard {
    private KeyFrame keyFrameStartPosition;
    private KeyFrame keyFrameCenter;
    private boolean checkLocation;
    private Timeline animation = new Timeline();

    public AnimationCard(Pane card, double mainSizeX, double mainSizeY, boolean up, int del, double perSecondUpdate) {

        if (up) mainSizeY = mainSizeY / -1;
        else mainSizeY = mainSizeY - card.getPrefWidth() * del;
        checkLocation = false;

        keyFrameStartPosition = new KeyFrame(Duration.seconds(perSecondUpdate),
                new KeyValue(card.translateXProperty(), 0),
                new KeyValue(card.translateYProperty(), 0),
                new KeyValue(card.prefHeightProperty(), card.getPrefHeight()),
                new KeyValue(card.prefWidthProperty(), card.getPrefWidth()));
        keyFrameCenter = new KeyFrame(Duration.seconds(perSecondUpdate),
                new KeyValue(card.translateXProperty(), mainSizeX / del - card.getPrefWidth()),
                new KeyValue(card.translateYProperty(), mainSizeY / del),
                new KeyValue(card.prefHeightProperty(), card.getPrefHeight() * del),
                new KeyValue(card.prefWidthProperty(), card.getPrefWidth() * del));
        animation.getKeyFrames().clear();
        animation.getKeyFrames().addAll(
                keyFrameCenter
        );
        animation.setOnFinished(event1 -> {
            animation.stop();
            if (checkLocation) {
                checkLocation = false;
                animation.getKeyFrames().clear();
                animation.getKeyFrames().add(
                        keyFrameCenter
                );
            } else {
                checkLocation = true;
                animation.getKeyFrames().clear();
                animation.getKeyFrames().add(
                        keyFrameStartPosition);
            }
        });
    }

    public void animationStart() {
        animation.play();
    }

    public void animationStop() {
        animation.stop();
    }

}
