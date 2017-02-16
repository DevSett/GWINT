package app.view.gamingTable;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by kills on 15.02.2017.
 */
public class AnimationCardTest extends Pane {
    private KeyFrame keyFrameStartPosition;
    private KeyFrame keyFrameCenter;
    private boolean checkLocation=true;
    private Timeline animation = new Timeline();

    public AnimationCardTest(GamingCard gamingCard, int del, double mainSizeX, double mainSizeY,double perSecondUpdate) {
        Pane pane = new Pane();
        pane.setPrefSize(mainSizeX,mainSizeY);
        pane.setStyle("-fx-background-color:black;" +
                "-fx-opacity:0;");
        getChildren().addAll(pane,gamingCard);

        keyFrameStartPosition = new KeyFrame(Duration.seconds(perSecondUpdate),
                new KeyValue(gamingCard.prefHeightProperty(), gamingCard.getPrefHeight()),
                new KeyValue(gamingCard.prefWidthProperty(), gamingCard.getPrefWidth()));
        KeyFrame keyFrameStartPositionPane  = new KeyFrame(Duration.seconds(perSecondUpdate),new KeyValue(pane.opacityProperty(),0));
                keyFrameCenter = new KeyFrame(Duration.seconds(perSecondUpdate),
                new KeyValue(gamingCard.prefHeightProperty(), gamingCard.getPrefHeight() * del),
                new KeyValue(gamingCard.prefWidthProperty(), gamingCard.getPrefWidth() * del));
        KeyFrame keyFrameStartCenterPane  = new KeyFrame(Duration.seconds(perSecondUpdate),new KeyValue(pane.opacityProperty(),0.8));

        animation.getKeyFrames().clear();
        animation.getKeyFrames().addAll(
                keyFrameCenter
        );
        animation.setOnFinished(event1 -> {
            animation.stop();
            if (checkLocation) {
                checkLocation = false;
                animation.getKeyFrames().clear();
                animation.getKeyFrames().addAll(
                        keyFrameCenter,keyFrameStartCenterPane
                );
            } else {
                checkLocation = true;
                animation.getKeyFrames().clear();
                animation.getKeyFrames().addAll(
                        keyFrameStartPosition,keyFrameStartPositionPane);
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
