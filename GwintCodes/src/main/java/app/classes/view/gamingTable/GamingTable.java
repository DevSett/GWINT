package app.classes.view.gamingTable;
/**
 * Created by kills on 06.02.2017.
 */

import app.classes.MainApp;
import app.classes.rulesGaming.Card;
import app.classes.rulesGaming.Cards;
import app.classes.view.optionGame.classes.ScreenResolution;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GamingTable extends AnchorPane {
    private final Image image = new Image(getClass().getResource("/images/gamingTable/tableTimes.png").toExternalForm());
    private CardsBox[] fieldsForGame;
    private CardsBox cardsHand;
    public static Long countCard = 0l;
    private Logic logic;

    public GamingTable(Stage stage, Logic logic) {
        super();
        this.logic = logic;
        getChildren().clear();
        setPrefSize(stage.getWidth(), stage.getHeight());

        setBackground(
                new Background(
                        new BackgroundImage(
                                image,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                new BackgroundSize(
                                        getWidth(),
                                        getHeight(),
                                        true,
                                        true,
                                        true,
                                        false)
                        )
                )
        );

        stage.getScene().setRoot(this);

    }


    public void drawingMainElementsGamingTable(Card idFrendlyLider, Card idEnemyLider, Cards cards) {


        Font font = null;

        font = Font.loadFont(
                getClass().getResource("/fonts/Intro.otf").toExternalForm(),
                50 / MainApp.getSingleton().getDel()
        );

        Font font2 = null;

        font2 = Font.loadFont(
                getClass().getResource("/fonts/Intro.otf").toExternalForm(),
                15 / MainApp.getSingleton().getDel()
        );

        String damage = "0";
        String damage2 = "0";
        Label forceCounterFrendly = new Label(damage);
        forceCounterFrendly.setStyle("-fx-text-fill: whitesmoke;");
        forceCounterFrendly.setFont(font);
        AnchorPane.setRightAnchor(forceCounterFrendly, ScreenResolution.PADDING.FORCE_COUNTER.RIGHT / MainApp.getSingleton().getDel());
        AnchorPane.setBottomAnchor(forceCounterFrendly, ScreenResolution.PADDING.FORCE_COUNTER.FRIENDLY.BOTTOM / MainApp.getSingleton().getDel());

        Label forceCounterEnemy = new Label(damage2);
        forceCounterEnemy.setStyle("-fx-text-fill: whitesmoke");
        forceCounterEnemy.setFont(font);
        AnchorPane.setRightAnchor(forceCounterEnemy, ScreenResolution.PADDING.FORCE_COUNTER.RIGHT / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(forceCounterEnemy, ScreenResolution.PADDING.FORCE_COUNTER.ENEMY.TOP / MainApp.getSingleton().getDel());

        Circle heartFrendlyFirst = new Circle(25 / MainApp.getSingleton().getDel(), Paint.valueOf("#4d0000"));
        AnchorPane.setRightAnchor(heartFrendlyFirst, ScreenResolution.PADDING.LIFE.RIGHT_FIRST / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(heartFrendlyFirst, ScreenResolution.PADDING.LIFE.FRIENDLY.TOP / MainApp.getSingleton().getDel());

        Circle heartFrendlySecond = new Circle(25 / MainApp.getSingleton().getDel(), Paint.valueOf("#4d0000"));

        AnchorPane.setRightAnchor(heartFrendlySecond, ScreenResolution.PADDING.LIFE.RIGHT_SECOND / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(heartFrendlySecond, ScreenResolution.PADDING.LIFE.FRIENDLY.TOP / MainApp.getSingleton().getDel());

        Circle heartEnemyFirst = new Circle(25 / MainApp.getSingleton().getDel(), Paint.valueOf("#4d0000"));

        AnchorPane.setRightAnchor(heartEnemyFirst, ScreenResolution.PADDING.LIFE.RIGHT_FIRST / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(heartEnemyFirst, ScreenResolution.PADDING.LIFE.ENEMY.TOP / MainApp.getSingleton().getDel());

        Circle heartEnemySecond = new Circle(25 / MainApp.getSingleton().getDel(), Paint.valueOf("#4d0000"));

        AnchorPane.setRightAnchor(heartEnemySecond, ScreenResolution.PADDING.LIFE.RIGHT_SECOND / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(heartEnemySecond, ScreenResolution.PADDING.LIFE.ENEMY.TOP / MainApp.getSingleton().getDel());


        VBox vBox = new VBox(1);
        vBox.setAlignment(Pos.CENTER);
        Label label = new Label("Сдаться");
        label.setFont(font2);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-text-fill: darkred;");
        CustomButton customButton = new CustomButton();
        customButton.setPrefSize(110/MainApp.getSingleton().getDel(), 110/ MainApp.getSingleton().getDel());

        vBox.getChildren().add(label);
        vBox.getChildren().add(customButton);

        AnchorPane.setLeftAnchor(vBox, ScreenResolution.PADDING.CIRCLE_SICE.LEFT / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(vBox, ScreenResolution.PADDING.CIRCLE_SICE.TOP / MainApp.getSingleton().getDel());


        GamingCard enemyLider = drawCard(
                idEnemyLider,
                ScreenResolution.SIZE.CARD.LIDER.WIDTH,
                ScreenResolution.SIZE.CARD.LIDER.HEIGHT,
                ScreenResolution.PADDING.CARD.LIDER.LEFT,
                null,
                ScreenResolution.PADDING.CARD.LIDER.ENEMY.TOP,
                null
        );

        GamingCard friendlyLider = drawCard(
                idFrendlyLider,
                ScreenResolution.SIZE.CARD.LIDER.WIDTH,
                ScreenResolution.SIZE.CARD.LIDER.HEIGHT,
                ScreenResolution.PADDING.CARD.LIDER.LEFT,
                null,
                null,
                ScreenResolution.PADDING.CARD.LIDER.FRIENDLY.BOTTOM
        );


        cardsHand = drawCardsInHend(cards.getListCard());
        fieldsForGame = drawBoxInGame();

        for (GamingCard card : cardsHand.getArrays()) {
            card.setOnMouseClicked(event -> {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    logic.actionOnCard(card);
                }
            });
        }
        Platform.runLater(() -> this.getChildren().addAll(
                enemyLider,
                friendlyLider,
                cardsHand,
                forceCounterEnemy,
                forceCounterFrendly,
                heartEnemySecond,
                heartEnemyFirst,
                heartFrendlyFirst,
                heartFrendlySecond,
                vBox
        ));
        Platform.runLater(() -> this.getChildren().addAll(fieldsForGame));

    }

    public void moveToGame(GamingCard card) {
        if (fieldsForGame[4].getArrays().length < 3)
            fieldsForGame[4].add(card);
        else if (fieldsForGame[5].getArrays().length < 3) fieldsForGame[5].add(card);
        else return;
        cardsHand.remove(card);
        card.setOnMouseClicked(event1 -> {
//                removeFromGame(card.getIdn());
        });
    }

    public void removeFromGame(GamingCard gamingCard) {
        for (CardsBox cardsBox : fieldsForGame) {
            if (cardsBox.getItems().indexOf(gamingCard) != -1)
                cardsBox.getItems().remove(gamingCard);
        }
    }

    public void addToHand(GamingCard gamingCard) {
        cardsHand.add(gamingCard);
    }

    public void returnToMenu() {
        //
    }

    public void removeFromGame(Long gamingCard) {
        Arrays.asList(fieldsForGame).forEach(cardsBox -> {
            if (cardsBox.getItems().size() != 0)
                for (GamingCard card : cardsBox.getItems()) {
                    if (card.getIdn().equals(gamingCard)) {
                        Platform.runLater(() -> cardsBox.remove(card));
                    }
                }
        });
    }


    public GamingCard drawCard(
            Card card,
            Integer width,
            Integer height,
            Integer paddingLeft,
            Integer paddingRight,
            Integer paddingTop,
            Integer paddingBottom) {

        GamingCard gamingCard = new GamingCard(
                card,
                width,
                height,
                true,
                countCard++
        );

        if (paddingLeft != null)
            gamingCard.setLayoutX(paddingLeft / MainApp.getSingleton().getDel());
        if (paddingTop != null)
            gamingCard.setLayoutY(paddingTop / MainApp.getSingleton().getDel());
        if (paddingRight != null)
            gamingCard.setLayoutX(
                    this.getWidth() - width / MainApp.getSingleton().getDel() - paddingRight / MainApp.getSingleton().getDel());
        if (paddingBottom != null)
            gamingCard.setLayoutY(
                    this.getHeight() - height / MainApp.getSingleton().getDel() - paddingBottom / MainApp.getSingleton().getDel());

        return gamingCard;
    }


    public CardsBox drawCardsInHend(List<Card> cards) {
        ArrayList<GamingCard> gamingCards = new ArrayList<>();
        for (Card card : cards) {
            gamingCards.add(
                    new GamingCard(
                            card,
                            ScreenResolution.SIZE.CARD.IN_HEND.WIDTH,
                            ScreenResolution.SIZE.CARD.IN_HEND.HEIGHT,
                            true,
                            countCard++
                    )
            );
        }

        CardsBox cardsHand = new CardsBox(-1, ScreenResolution.SIZE.CARDS_IN_HEND.HEIGHT, 10);


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
        return cardsHand;
    }


    public CardsBox[] drawBoxInGame() {

        /**
         *
         * CardBox 0 - friendly 1
         * CardBox 1 - friendly 11
         * CardBox 2 - friendly 2
         * CardBox 3 - friendly 22
         * CardBox 4 - friendly 3
         * CardBox 5 - friendly 33
         * CardBox 6 - enemy 1
         * CardBox 7 - enemy 11
         * CardBox 8 - enemy 2
         * CardBox 9 - enemy 22
         * CardBox 10 - enemy 3
         * CardBox 11 - enemy 33
         *
         */

        List<CardsBox> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(new CardsBox(-1, ScreenResolution.SIZE.CARDS_IN_HEND.HEIGHT, 0));
        }

        //friendly
        list.get(0).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_FIRST / MainApp.getSingleton().getDel());
//        AnchorPane.setLeftAnchor(list.get(0), ScreenResolution.PADDING.GAME.LEFT_FIRST / MainApp.getSingleton().getDel());
        AnchorPane.setBottomAnchor(list.get(0), ScreenResolution.PADDING.GAME.FRIENDLY.BOTTOM_FIRST / MainApp.getSingleton().getDel());

        list.get(1).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_FIRST / MainApp.getSingleton().getDel());
        AnchorPane.setBottomAnchor(list.get(1), ScreenResolution.PADDING.GAME.FRIENDLY.BOTTOM_SECOND / MainApp.getSingleton().getDel());

        list.get(2).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_SECOND / MainApp.getSingleton().getDel());
        AnchorPane.setBottomAnchor(list.get(2), ScreenResolution.PADDING.GAME.FRIENDLY.BOTTOM_FIRST / MainApp.getSingleton().getDel());

        list.get(3).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_SECOND / MainApp.getSingleton().getDel());
        AnchorPane.setBottomAnchor(list.get(3), ScreenResolution.PADDING.GAME.FRIENDLY.BOTTOM_SECOND / MainApp.getSingleton().getDel());

        list.get(4).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_THERT / MainApp.getSingleton().getDel());
        AnchorPane.setBottomAnchor(list.get(4), ScreenResolution.PADDING.GAME.FRIENDLY.BOTTOM_FIRST / MainApp.getSingleton().getDel());

        list.get(5).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_THERT / MainApp.getSingleton().getDel());
        AnchorPane.setBottomAnchor(list.get(5), ScreenResolution.PADDING.GAME.FRIENDLY.BOTTOM_SECOND / MainApp.getSingleton().getDel());

        //enemy
        list.get(6).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_FIRST / MainApp.getSingleton().getDel());
        list.get(6).setLayoutY(ScreenResolution.PADDING.GAME.ENEMY.TOP_FIRST / MainApp.getSingleton().getDel());

        list.get(7).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_FIRST / MainApp.getSingleton().getDel());
        list.get(7).setLayoutY(ScreenResolution.PADDING.GAME.ENEMY.TOP_SECOND / MainApp.getSingleton().getDel());

        list.get(8).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_SECOND / MainApp.getSingleton().getDel());
        list.get(8).setLayoutY(ScreenResolution.PADDING.GAME.ENEMY.TOP_FIRST / MainApp.getSingleton().getDel());

        list.get(9).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_SECOND / MainApp.getSingleton().getDel());
        list.get(9).setLayoutY(ScreenResolution.PADDING.GAME.ENEMY.TOP_SECOND / MainApp.getSingleton().getDel());

        list.get(10).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_THERT / MainApp.getSingleton().getDel());
        list.get(10).setLayoutY(ScreenResolution.PADDING.GAME.ENEMY.TOP_FIRST / MainApp.getSingleton().getDel());

        list.get(11).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_THERT / MainApp.getSingleton().getDel());
        list.get(11).setLayoutY(ScreenResolution.PADDING.GAME.ENEMY.TOP_SECOND / MainApp.getSingleton().getDel());

        return list.toArray(new CardsBox[list.size()]);
    }

    public void clear() {
        Platform.runLater(() -> this.getChildren().clear());
    }


}
