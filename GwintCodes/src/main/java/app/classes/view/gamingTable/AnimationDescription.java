package app.classes.view.gamingTable;


import app.classes.MainApp;
import app.classes.view.optionGame.classes.ScreenResolution;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class AnimationDescription extends Region {
    private Timeline timeline = new Timeline();

    public AnimationDescription(GamingCard gamingCard, double perSecondUpdate) {

        GamingCard tempGamingCard = new GamingCard(
                gamingCard.getCard(),
                ScreenResolution.SIZE.CARD.FOR_ANIMATION.WIDTH / MainApp.getSingleton().getDel(),
                ScreenResolution.SIZE.CARD.FOR_ANIMATION.HEIGHT / MainApp.getSingleton().getDel(),
                false, -1l
        );

        tempGamingCard.setSizeLabel(tempGamingCard.getSizeY()/9);
        tempGamingCard.setLabelLayoutX(tempGamingCard.getSizeX()/12);

        Pane pane = new Pane();
        pane.setPrefWidth(1920 / MainApp.getSingleton().getDel());
        pane.setPrefHeight(1080 / MainApp.getSingleton().getDel());
        pane.setStyle("-fx-background-color: black");

        tempGamingCard.setLayoutX(tempGamingCard.getSizeX() * -1);
        tempGamingCard.setLayoutY(pane.getPrefHeight() / 2 - tempGamingCard.getPrefHeight() / 2);

        Label label = new Label(tempGamingCard.getCard().getDescription());

        label.setWrapText(true);
        label.setStyle("-fx-text-fill: whitesmoke");
        label.setFont(new Font(30 / MainApp.getSingleton().getDel()));
        label.setOpacity(0);
        label.setLayoutY(tempGamingCard.getLayoutY());
        label.setLayoutX(
                tempGamingCard.getSizeX() +
                        100 / MainApp.getSingleton().getDel() * 2
        );
        label.setMaxWidth(pane.getPrefWidth()-label.getLayoutX());



        getChildren().addAll(pane, tempGamingCard, label);


        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().addAll(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(pane.opacityProperty(), 0),
                        new KeyValue(tempGamingCard.translateXProperty(), -1 * tempGamingCard.getSizeX()),
                        new KeyValue(label.opacityProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(perSecondUpdate),
                        new KeyValue(pane.opacityProperty(), 0.7),
                        new KeyValue(
                                tempGamingCard.translateXProperty(),
                                tempGamingCard.getSizeX() + 100 / MainApp.getSingleton().getDel()
                        ),
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
}
