package app.classes.view.gamingTable;
/**
 * Created by kills on 06.02.2017.
 */

import app.classes.MainApp;
import app.classes.rulesGaming.Card;
import app.classes.rulesGaming.Cards;
import app.classes.view.optionGame.classes.ScreenResolution;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class GamingTable {
    private Pane pane;
    private final Image image = new Image(getClass().getResource("/images/gamingTable/tableTimes.png").toExternalForm());

    public GamingTable(Stage stage) {

        pane = new AnchorPane();
        pane.getChildren().clear();
        pane.setPrefSize(stage.getWidth(), stage.getHeight());

        pane.setBackground(
                new Background(
                        new BackgroundImage(
                                image,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                new BackgroundSize(
                                        pane.getWidth(),
                                        pane.getHeight(),
                                        true,
                                        true,
                                        true,
                                        false)
                        )
                )
        );

        stage.getScene().setRoot(pane);

    }


    public void drawingMainElementsGamingTable(Card idFrendlyLider, Card idEnemyLider, Cards cards) {

        GamingCard enemyLider = drawCard(
                idEnemyLider,
                ScreenResolution.SIZE.CARD.LIDER.WIDTH,
                ScreenResolution.SIZE.CARD.LIDER.HEIGHT,
                ScreenResolution.PADDING.CARD.LIDER.LEFT,
                null,
                ScreenResolution.PADDING.CARD.LIDER.ENEMY.TOP,
                null
        );

        GamingCard frendlyLider = drawCard(
                idFrendlyLider,
                ScreenResolution.SIZE.CARD.LIDER.WIDTH,
                ScreenResolution.SIZE.CARD.LIDER.HEIGHT,
                ScreenResolution.PADDING.CARD.LIDER.LEFT,
                null,
                null,
                ScreenResolution.PADDING.CARD.LIDER.FRENDLY.BOTTOM
        );

        ArrayList<GamingCard> gamingCards = new ArrayList<>();
        for (Card card : cards.getListCard()) {
            gamingCards.add(new GamingCard(card, ScreenResolution.SIZE.CARD.IN_HEND.WIDTH, ScreenResolution.SIZE.CARD.IN_HEND.HEIGHT));
        }

        CardsHand cardsHand = new CardsHand(-1, ScreenResolution.SIZE.CARDS_IN_HEND.HEIGHT, 10);

        //
//        cardsHand.setStyle("-fx-border-color: aliceblue");
//        cardsHand.setOpacity(0.5);
        //

        AnchorPane.setBottomAnchor(
                cardsHand,
                ScreenResolution.PADDING.CARDS_IN_HEND.BOTTOM / MainApp.getSingleton().getDel());

        cardsHand.addAll(gamingCards);

        AnchorPane.setLeftAnchor(
                cardsHand,
                ScreenResolution.PADDING.CARDS_IN_HEND.LEFT / MainApp.getSingleton().getDel()
        );
        AnchorPane.setRightAnchor(
                cardsHand,
                ScreenResolution.PADDING.CARDS_IN_HEND.RIGHT / MainApp.getSingleton().getDel()
        );

        pane.getChildren().addAll(enemyLider, frendlyLider, cardsHand);

//        AnimationCardTest test = new AnimationCardTest(enemyLider, 2, pane.getPrefWidth(), pane.getPrefHeight(), 0.15);
//        enemyLider.setOnMouseClicked(event -> test.animationStart());
//        pane.getChildren().add(cardsHand);
//        pane.getChildren().add(test);
    }

    public GamingCard drawCard(
            Card card,
            Integer width,
            Integer height,
            Integer paddingLeft,
            Integer paddingRight,
            Integer paddingTop,
            Integer paddingBottom
    ) {
        GamingCard gamingCard = new GamingCard(
                card,
                width,
                height
        );

        if (paddingLeft != null)
            gamingCard.setLayoutX(paddingLeft / MainApp.getSingleton().getDel());
        if (paddingTop != null)
            gamingCard.setLayoutY(paddingTop / MainApp.getSingleton().getDel());
        if (paddingRight != null)
            gamingCard.setLayoutX(
                    pane.getWidth() - width / MainApp.getSingleton().getDel() - paddingRight / MainApp.getSingleton().getDel());
        if (paddingBottom != null)
            gamingCard.setLayoutY(
                    pane.getHeight() - height / MainApp.getSingleton().getDel() - paddingBottom / MainApp.getSingleton().getDel());

        return gamingCard;
    }


    public void drawCardsInHend(List<Integer> idCards) {
        //
    }


    private void setAnimationOnCard(Pane pane) {
        AnimationCard enemyAnimationCard = new AnimationCard(
                pane,
                this.pane.getPrefWidth(),
                this.pane.getPrefHeight(),
                0.7);
        pane.setOnMouseClicked(event -> {
            if (event.getButton().name().equals("SECONDARY")) enemyAnimationCard.animationStart();
        });
    }

    public void clear() {
        pane.getChildren().clear();
    }
}
