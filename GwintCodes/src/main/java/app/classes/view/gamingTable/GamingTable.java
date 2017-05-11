package app.classes.view.gamingTable;
/**
 * Created by kills on 06.02.2017.
 */

import app.classes.MainApp;
import app.classes.other.HelpClass;
import app.classes.rulesGaming.Card;
import app.classes.rulesGaming.CardForm;
import app.classes.rulesGaming.Cards;
import app.classes.view.optionGame.classes.ScreenResolution;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("ALL")
public class GamingTable extends AnchorPane {
    private final Image image = new Image(getClass().getResource("/images/gamingTable/tableTimes.png").toExternalForm());
    private CardsBox[] fieldsForGame;
    private CardsBox cardsHand;
    public static Long countCard = 0l;
    private Logic logic;
    private GamingCard friendlyLider;
    private GamingCard enemyLider;
    private Stage stage;
    private Label forceCounterEnemy;
    private Label forceCounterFrendly;
    private Circle heartFrendlyFirst;
    private Circle heartFrendlySecond;
    private Circle heartEnemyFirst;
    private Circle heartEnemySecond;

    public GamingTable(Stage stage, Logic logic) {
        super();
        this.stage = stage;
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

    public Stage getStage() {
        return stage;
    }

    public Circle getHeartFrendlyFirst() {
        return heartFrendlyFirst;
    }

    public Circle getHeartFrendlySecond() {
        return heartFrendlySecond;
    }

    public Circle getHeartEnemyFirst() {
        return heartEnemyFirst;
    }

    public Circle getHeartEnemySecond() {
        return heartEnemySecond;
    }

    public void drawingMainElementsGamingTable(Card idFrendlyLider, Card idEnemyLider, Cards cards) {


        Font font = null;

        font = HelpClass.getFont(50 / MainApp.getSingleton().getDel());


        Font font2 = null;

        font2 = HelpClass.getFont(15 / MainApp.getSingleton().getDel());

        forceCounterFrendly = new Label("00");
        forceCounterFrendly.setStyle("-fx-text-fill: whitesmoke;");
        forceCounterFrendly.setFont(font);
        AnchorPane.setRightAnchor(forceCounterFrendly, ScreenResolution.PADDING.FORCE_COUNTER.RIGHT / MainApp.getSingleton().getDel());
        AnchorPane.setBottomAnchor(forceCounterFrendly, ScreenResolution.PADDING.FORCE_COUNTER.FRIENDLY.BOTTOM / MainApp.getSingleton().getDel());

        forceCounterEnemy = new Label("00");
        forceCounterEnemy.setStyle("-fx-text-fill: whitesmoke");
        forceCounterEnemy.setFont(font);
        AnchorPane.setRightAnchor(forceCounterEnemy, ScreenResolution.PADDING.FORCE_COUNTER.RIGHT / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(forceCounterEnemy, ScreenResolution.PADDING.FORCE_COUNTER.ENEMY.TOP / MainApp.getSingleton().getDel());

        Image image = new Image(getClass().getResource("/images/gamingTable/hearts2.png").toExternalForm());
        Image image2 = new Image(getClass().getResource("/images/gamingTable/hearts1.png").toExternalForm());

        heartFrendlyFirst = new Circle(25 / MainApp.getSingleton().getDel(), Paint.valueOf("#4d0000"));
        AnchorPane.setRightAnchor(heartFrendlyFirst, ScreenResolution.PADDING.LIFE.RIGHT_FIRST / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(heartFrendlyFirst, ScreenResolution.PADDING.LIFE.FRIENDLY.TOP / MainApp.getSingleton().getDel());
        heartFrendlyFirst.setFill(new ImagePattern(image));

        heartFrendlySecond = new Circle(25 / MainApp.getSingleton().getDel(), Paint.valueOf("#4d0000"));
        AnchorPane.setRightAnchor(heartFrendlySecond, ScreenResolution.PADDING.LIFE.RIGHT_SECOND / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(heartFrendlySecond, ScreenResolution.PADDING.LIFE.FRIENDLY.TOP / MainApp.getSingleton().getDel());
        heartFrendlySecond.setFill(new ImagePattern(image));

        heartEnemyFirst = new Circle(25 / MainApp.getSingleton().getDel(), Paint.valueOf("#4d0000"));

        AnchorPane.setRightAnchor(heartEnemyFirst, ScreenResolution.PADDING.LIFE.RIGHT_FIRST / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(heartEnemyFirst, ScreenResolution.PADDING.LIFE.ENEMY.TOP / MainApp.getSingleton().getDel());
        heartEnemyFirst.setFill(new ImagePattern(image2));

        heartEnemySecond = new Circle(25 / MainApp.getSingleton().getDel(), Paint.valueOf("#4d0000"));

        AnchorPane.setRightAnchor(heartEnemySecond, ScreenResolution.PADDING.LIFE.RIGHT_SECOND / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(heartEnemySecond, ScreenResolution.PADDING.LIFE.ENEMY.TOP / MainApp.getSingleton().getDel());
        heartEnemySecond.setFill(new ImagePattern(image2));


        VBox vBox = new VBox(1);
        vBox.setAlignment(Pos.CENTER);
        Label label = new Label("Сдаться");
        label.setFont(font2);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-text-fill: darkred;");
        CustomButton customButton = new CustomButton(logic.surrender());
        customButton.setPrefSize(110 / MainApp.getSingleton().getDel(), 110 / MainApp.getSingleton().getDel());
        vBox.getChildren().add(label);
        vBox.getChildren().add(customButton);

        AnchorPane.setLeftAnchor(vBox, ScreenResolution.PADDING.CIRCLE_SICE.LEFT / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(vBox, ScreenResolution.PADDING.CIRCLE_SICE.TOP / MainApp.getSingleton().getDel());


        ShirtCard shirtCardPackFrendly = new ShirtCard(
                getClass().getResource("/images/gamingTable/cards/shirt.png").toExternalForm(),
                ScreenResolution.SIZE.CARD.TRASH.WIDTH,
                ScreenResolution.SIZE.CARD.TRASH.HEIGHT,
                true
        );

        ShirtCard shirtCardTrashFrendly = new ShirtCard(
                getClass().getResource("/images/gamingTable/cards/shirt.png").toExternalForm(),
                ScreenResolution.SIZE.CARD.TRASH.WIDTH,
                ScreenResolution.SIZE.CARD.TRASH.HEIGHT,
                true
        );

        ShirtCard shirtCardPackEnemy = new ShirtCard(
                getClass().getResource("/images/gamingTable/cards/shirt.png").toExternalForm(),
                ScreenResolution.SIZE.CARD.TRASH.WIDTH,
                ScreenResolution.SIZE.CARD.TRASH.HEIGHT,
                true
        );

        ShirtCard shirtCardTrashEnemy = new ShirtCard(
                getClass().getResource("/images/gamingTable/cards/shirt.png").toExternalForm(),
                ScreenResolution.SIZE.CARD.TRASH.WIDTH,
                ScreenResolution.SIZE.CARD.TRASH.HEIGHT,
                true
        );

        AnchorPane.setRightAnchor(shirtCardPackFrendly, ScreenResolution.PADDING.TRASH.RIGHT_SECOND / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(shirtCardPackFrendly, ScreenResolution.PADDING.TRASH.FRIENDLY.TOP / MainApp.getSingleton().getDel());

        AnchorPane.setRightAnchor(shirtCardTrashFrendly, ScreenResolution.PADDING.TRASH.RIGHT_FIRST / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(shirtCardTrashFrendly, ScreenResolution.PADDING.TRASH.FRIENDLY.TOP / MainApp.getSingleton().getDel());

        AnchorPane.setRightAnchor(shirtCardPackEnemy, ScreenResolution.PADDING.TRASH.RIGHT_SECOND / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(shirtCardPackEnemy, ScreenResolution.PADDING.TRASH.ENEMY.TOP / MainApp.getSingleton().getDel());

        AnchorPane.setRightAnchor(shirtCardTrashEnemy, ScreenResolution.PADDING.TRASH.RIGHT_FIRST / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(shirtCardTrashEnemy, ScreenResolution.PADDING.TRASH.ENEMY.TOP / MainApp.getSingleton().getDel());

        enemyLider = drawCard(
                idEnemyLider,
                ScreenResolution.SIZE.CARD.LIDER.WIDTH,
                ScreenResolution.SIZE.CARD.LIDER.HEIGHT,
                ScreenResolution.PADDING.CARD.LIDER.LEFT,
                null,
                ScreenResolution.PADDING.CARD.LIDER.ENEMY.TOP,
                null
        );

        friendlyLider = drawCard(
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
                vBox,
                shirtCardPackEnemy,
                shirtCardPackFrendly,
                shirtCardTrashEnemy,
                shirtCardTrashFrendly
        ));
        Platform.runLater(() -> this.getChildren().addAll(fieldsForGame));

    }

    public boolean moveToGame(GamingCard card, boolean isFrendly, CardForm cardForm) {
        //fr 0 1  2 3  4 5
        //en 6 7  8 9  10 11
        int fr = 0;
        if (!isFrendly) fr = 6;
        switch (cardForm) {
            case ONE_STONE:
                if (moveToGame(card, fr)) return false;
                break;
            case TWO_STONE:
                if (moveToGame(card, fr + 2)) return false;
                break;
            case THIRD_STONE:
                if (moveToGame(card, fr + 4)) return false;
                break;
        }
        if (isFrendly) {
            forceCounterFrendly.setText((Integer.valueOf(forceCounterFrendly.getText()) + card.getCard().getDamage()) + "");
        } else {
            forceCounterEnemy.setText((Integer.valueOf(forceCounterEnemy.getText()) + card.getCard().getDamage()) + "");

        }
        return true;
    }

    public boolean moveToGame(GamingCard card, int fr) {
        if (fieldsForGame[fr].getArrays().length < 3) fieldsForGame[fr].add(card);
        else if (fieldsForGame[fr + 1].getArrays().length < 3) fieldsForGame[fr + 1].add(card);
        else return true;
        cardsHand.remove(card);
        card.setOnMouseClicked(event -> {

        });
        return false;
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
                    if (card.identificator().equals(gamingCard)) {
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

    public void alert(String alertT, EventHandler<ActionEvent> eventOnFinished, EventHandler<MouseEvent> eventOnClicked) {
        Alert alert = null;
        if (alert == null) {
            alert = new Alert(alertT, 0.7);
            Alert finalAlert = alert;
            Platform.runLater(() -> getChildren().add(finalAlert));
        } else {
            alert.setText(alertT);
        }
        if (eventOnFinished != null) {
            Alert finalAlert1 = alert;
            alert.getTimeline().setOnFinished(event -> {
                finalAlert1.setVisible(false);
                eventOnFinished.handle(event);
            });
        }
        if (eventOnClicked != null) {
            Alert finalAlert2 = alert;
            alert.setOnMouseClicked(event -> {
                finalAlert2.getOnMouseClicked().handle(event);
                eventOnClicked.handle(event);
            });
        }
        alert.animationStart();
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
        AnchorPane.setTopAnchor(list.get(6), ScreenResolution.PADDING.GAME.ENEMY.TOP_FIRST / MainApp.getSingleton().getDel());

        list.get(7).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_FIRST / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(list.get(7), ScreenResolution.PADDING.GAME.ENEMY.TOP_SECOND / MainApp.getSingleton().getDel());

        list.get(8).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_SECOND / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(list.get(8), ScreenResolution.PADDING.GAME.ENEMY.TOP_FIRST / MainApp.getSingleton().getDel());

        list.get(9).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_SECOND / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(list.get(9), ScreenResolution.PADDING.GAME.ENEMY.TOP_SECOND / MainApp.getSingleton().getDel());

        list.get(10).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_THERT / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(list.get(10), ScreenResolution.PADDING.GAME.ENEMY.TOP_FIRST / MainApp.getSingleton().getDel());

        list.get(11).setLayoutX(ScreenResolution.PADDING.GAME.LEFT_THERT / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(list.get(11), ScreenResolution.PADDING.GAME.ENEMY.TOP_SECOND / MainApp.getSingleton().getDel());

        return list.toArray(new CardsBox[list.size()]);
    }

    public void clear() {
        Platform.runLater(() -> this.getChildren().clear());
    }


    public void step(boolean is) {
        if (is) {
            cardsHand.getItems().forEach(gamingCard -> {
                gamingCard.setOnMouseClicked((event) -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        logic.actionOnCard(gamingCard, true);
                        logic.stepEnemy();
                    }
                });
            });
            friendlyLider.setOnMouseClicked((event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    logic.actionOnCard(friendlyLider, true);
                    logic.stepEnemy();
                }
            }));

        } else {
            cardsHand.getItems().forEach(gamingCard -> {
                gamingCard.setOnMouseClicked((event -> {
                }));
            });
            friendlyLider.setOnMouseClicked(event -> {

            });
        }
    }
}
