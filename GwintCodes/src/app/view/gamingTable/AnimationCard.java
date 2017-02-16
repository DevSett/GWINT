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

    public AnimationCard(Pane card, double mainSizeX, double mainSizeY, int del, double perSecondUpdate) {

        double transCardWigth = mainSizeX/2-card.getPrefWidth();
        double transCardHeigt = mainSizeY/2 - card.getPrefHeight();
        if(card.getLayoutY()>mainSizeY/2)
        {
            transCardHeigt = -mainSizeY/8 - card.getPrefHeight();
        }
        checkLocation = false;

        keyFrameStartPosition = new KeyFrame(Duration.seconds(perSecondUpdate),
                new KeyValue(card.translateXProperty(), 0),
                new KeyValue(card.translateYProperty(), 0),
                new KeyValue(card.prefHeightProperty(), card.getPrefHeight()),
                new KeyValue(card.prefWidthProperty(), card.getPrefWidth()));

        keyFrameCenter = new KeyFrame(Duration.seconds(perSecondUpdate),
                new KeyValue(card.translateXProperty(), transCardWigth),
                new KeyValue(card.translateYProperty(), transCardHeigt),
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
