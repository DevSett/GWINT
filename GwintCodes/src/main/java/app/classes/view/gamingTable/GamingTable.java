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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
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
    private final Image image = new Image(getClass().getResource("/images/gamingTable/gamingTable.png").toExternalForm());
    private CardsBoxFromPane[] fieldsForGame;
    private CardsBoxFromPane cardsHand;

    public static Long countCard = 0l;

    private Logic logic;

    private GamingCard friendlyLider;
    private GamingCard enemyLider;
    private Stage stage;

    private Force forceCounterEnemy;
    private Force forceCounterFrendly;

    private Circle heartFrendlyFirst;
    private Circle heartFrendlySecond;
    private Circle heartEnemyFirst;
    private Circle heartEnemySecond;
    private VBox vBoxSurrender;
    private Field fieldFriendlyFirst = new Field();
    private Field fieldFriendlySecond = new Field();
    private Field fieldFriendlyThird = new Field();
    private Field fieldEnemyFirst = new Field();
    private Field fieldEnemySecond = new Field();
    private Field fieldEnemyThird = new Field();

    private Field[] regions = new Field[]{
            fieldFriendlyFirst,
            fieldFriendlySecond,
            fieldFriendlyThird,
            fieldEnemyFirst,
            fieldEnemySecond,
            fieldEnemyThird
    };

    private Field[] regionsEnemys = new Field[]{
            fieldEnemyFirst,
            fieldEnemySecond,
            fieldEnemyThird
    };

    private Field[] regionsFriendlys = new Field[]{
            fieldFriendlyFirst,
            fieldFriendlySecond,
            fieldFriendlyThird
    };

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

    public void disableSurrender() {
        vBoxSurrender.setDisable(true);
    }

    public void enableSurrend() {
        vBoxSurrender.setDisable(false);
    }

    public void drawingMainElementsGamingTable(Card idFrendlyLider, Card idEnemyLider, Cards cards) {


        Arrays.stream(regionsFriendlys).forEach(region -> {
            AnchorPane.setTopAnchor(region, ScreenResolution.PADDING.FULL_FIELD.FRIENDLY.TOP / MainApp.getSingleton().getDel());
            AnchorPane.setBottomAnchor(region, ScreenResolution.PADDING.FULL_FIELD.FRIENDLY.BOTTOM / MainApp.getSingleton().getDel());
        });

        Arrays.stream(regionsEnemys).forEach(region -> {
            AnchorPane.setTopAnchor(region, ScreenResolution.PADDING.FULL_FIELD.ENEMY.TOP / MainApp.getSingleton().getDel());
            AnchorPane.setBottomAnchor(region, ScreenResolution.PADDING.FULL_FIELD.ENEMY.BOTTOM / MainApp.getSingleton().getDel());
        });
        AnchorPane.setLeftAnchor(fieldEnemyFirst, ScreenResolution.PADDING.FULL_FIELD.LEFT_FIRST / MainApp.getSingleton().getDel());
        AnchorPane.setLeftAnchor(fieldEnemySecond, ScreenResolution.PADDING.FULL_FIELD.LEFT_SECOND / MainApp.getSingleton().getDel());
        AnchorPane.setLeftAnchor(fieldEnemyThird, ScreenResolution.PADDING.FULL_FIELD.LEFT_THERT / MainApp.getSingleton().getDel());
        AnchorPane.setLeftAnchor(fieldFriendlyFirst, ScreenResolution.PADDING.FULL_FIELD.LEFT_FIRST / MainApp.getSingleton().getDel());
        AnchorPane.setLeftAnchor(fieldFriendlySecond, ScreenResolution.PADDING.FULL_FIELD.LEFT_SECOND / MainApp.getSingleton().getDel());
        AnchorPane.setLeftAnchor(fieldFriendlyThird, ScreenResolution.PADDING.FULL_FIELD.LEFT_THERT / MainApp.getSingleton().getDel());


        Font font2 = HelpClass.getFont(15 / MainApp.getSingleton().getDel());

        forceCounterFrendly = new Force();

        AnchorPane.setRightAnchor(forceCounterFrendly, ScreenResolution.PADDING.FORCE_COUNTER.RIGHT / MainApp.getSingleton().getDel());
        AnchorPane.setBottomAnchor(forceCounterFrendly, ScreenResolution.PADDING.FORCE_COUNTER.FRIENDLY.BOTTOM / MainApp.getSingleton().getDel());

        forceCounterEnemy = new Force();

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


        vBoxSurrender = new VBox(1);
        vBoxSurrender.setAlignment(Pos.CENTER);
        Label label = new Label("Сдаться");
        label.setFont(font2);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-text-fill: navajowhite;");
        CustomButton customButton = new CustomButton(logic.surrenderAction());
        customButton.setPrefSize(110 / MainApp.getSingleton().getDel(), 110 / MainApp.getSingleton().getDel());
        vBoxSurrender.getChildren().add(label);
        vBoxSurrender.getChildren().add(customButton);

        AnchorPane.setLeftAnchor(vBoxSurrender, ScreenResolution.PADDING.CIRCLE_SICE.LEFT / MainApp.getSingleton().getDel());
        AnchorPane.setTopAnchor(vBoxSurrender, ScreenResolution.PADDING.CIRCLE_SICE.TOP / MainApp.getSingleton().getDel());


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
                vBoxSurrender,
                shirtCardPackEnemy,
                shirtCardPackFrendly,
                shirtCardTrashEnemy,
                shirtCardTrashFrendly
        ));
        Platform.runLater(() -> this.getChildren().addAll(fieldsForGame));
        Platform.runLater(() -> this.getChildren().addAll(regions));

    }

    public boolean moveToGame(GamingCard card, boolean isFrendly, CardForm cardForm) {
        //fr 0 1  2 3  4 5
        //en 6 7  8 9  10 11
        int fr = 0;
        if (!isFrendly) fr = 6;
        switch (cardForm) {
            case THIRD_STONE:
                _STONE:
                if (moveToGame(card, fr)) return false;
                break;
            case TWO_STONE:
                if (moveToGame(card, fr + 2)) return false;
                break;
            case ONE_STONE:
                if (moveToGame(card, fr + 4)) return false;
                break;
        }
        Platform.runLater(() -> {
            if (isFrendly) {
                forceCounterFrendly.add(card.getCard().getDamage());
            } else {
                forceCounterEnemy.add(card.getCard().getDamage());
            }
        });


        return true;
    }

    public boolean moveToGame(GamingCard card, int fr) {
        if (fieldsForGame[fr].getArrays().length < 8) fieldsForGame[fr].add(card);
        else if (fieldsForGame[fr + 1].getArrays().length < 8) fieldsForGame[fr + 1].add(card);
        else return true;
        if (cardsHand.indexOf(card) != -1) cardsHand.remove(card);
        card.setOnMouseClicked(event -> {

        });
        return false;
    }

    public void removeFromGame(GamingCard gamingCard) {
        for (CardsBoxFromPane cardsBox : fieldsForGame) {
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


    public CardsBoxFromPane drawCardsInHend(List<Card> cards) {
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

        CardsBoxFromPane cardsHand = new CardsBoxFromPane(ScreenResolution.SIZE.CARDS_IN_HEND.WIDTH, ScreenResolution.SIZE.CARDS_IN_HEND.HEIGHT);


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


    public CardsBoxFromPane[] drawBoxInGame() {

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

        List<CardsBoxFromPane> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(new CardsBoxFromPane(ScreenResolution.SIZE.GAME.WIDTH - 70, ScreenResolution.SIZE.CARDS_IN_HEND.HEIGHT));
//            list.get(i).setStyle("-fx-border-color: white");
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

        return list.toArray(new CardsBoxFromPane[list.size()]);
    }

    public void clear() {
        Platform.runLater(() -> this.getChildren().clear());
    }


    public void step(boolean is) {
        if (is) {
            cardsHand.getItems().forEach(gamingCard -> {
                gamingCard.setOnMouseClicked((event) -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        logic.actionOnCard(true, null, null, gamingCard);
                    }
                });
            });
            friendlyLider.setOnMouseClicked((event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    logic.actionOnCard(true, null, null, friendlyLider);
                }
            }));
        } else {
            Arrays.stream(fieldsForGame).forEach(cardsBox -> cardsBox.getItems().forEach(gamingCard -> gamingCard.setOnMouseClicked(null)));

            cardsHand.getItems().forEach(gamingCard -> {
                gamingCard.setOnMouseClicked((event -> {
                }));
            });
            friendlyLider.setOnMouseClicked(null);
        }
    }

    public void animationDoubleDamageOnField(CardForm cardForm, boolean isFriendly, boolean isDouble) {
        int fr = 0;
        if (!isFriendly) fr += 6;

//        System.out.println(cardForm.toString());

        switch (cardForm) {
            case TWO_STONE:
                fr += 2;
                break;
            case ONE_STONE:
                fr += 4;
                break;
        }
        CardsBoxFromPane cardsBox = fieldsForGame[fr];
        CardsBoxFromPane cardsBox2 = fieldsForGame[fr + 1];

        cardsBox.getItems().forEach(gamingCard -> {
            if (isDouble) gamingCard.setDamage(gamingCard.getDamage() * 2);
            else gamingCard.setDamage(gamingCard.getDamage() / 2);
        });
        cardsBox2.getItems().forEach(gamingCard -> {
            if (isDouble) gamingCard.setDamage(gamingCard.getDamage() * 2);
            else gamingCard.setDamage(gamingCard.getDamage() / 2);
        });
        updateForce();
    }

//    public void animatedSelected(boolean isFrendly) {
//        if (isFrendly) {
//            alert("Выберете карту на том поле на котором хотите увеличить урон!", null, null);
//            for (int i = 0; i < 6; i++) {
//                int finalI1 = i;
//                fieldsForGame[i].getItems().forEach(gamingCard -> {
//                    gamingCard.setOnMouseClicked(event -> {
//                        if (event.getButton() == MouseButton.PRIMARY) {
//                            if (finalI1 % 2 == 0) animationDoubleDamageOnField(finalI1, true);
//                            else animationDoubleDamageOnField(finalI1 - 1, true);
//                            logic.stepEnemy();
//                        }
//                    });
//                });
//            }
//        } else {
//            alert("Выберете карту на том поле на котором хотите понизить урон!", null, null);
//        }
//    }

    public void animatedSelectRegionField(boolean all, boolean frendly, GamingCard cardG, Card card) {
        cardsHand.setDisable(true);
        friendlyLider.setDisable(true);
        if (all) {
            Arrays.stream(regions).forEach(region -> {
                region.setVisible(true);
                region.setOnMouseClicked(event -> {
                    regionVisible(false);
                    if (cardG == null)
                        eventForField(frendly, card, region);
                    else eventForField(frendly, cardG, region);
                });
            });
        } else {
            if (frendly) {
                Arrays.stream(regionsFriendlys).forEach(region -> {
                    region.setVisible(true);
                    region.setOnMouseClicked(event -> {
                        regionVisible(false);
                        if (cardG == null)
                            eventForField(frendly, card, region);
                        else eventForField(frendly, cardG, region);
                    });
                });
            } else {
                Arrays.stream(regionsEnemys).forEach(region -> {
                    region.setVisible(true);
                    region.setOnMouseClicked(event -> {
                        regionVisible(false);
                        if (cardG == null)
                            eventForField(frendly, card, region);
                        else eventForField(frendly, cardG, region);
                    });
                });
            }
        }
    }


    private void eventForField(boolean isFrendly, Card card, Region region) {
        GamingCard gamingCard = HelpClass.buildGamingCard(card.getId());
        forField(isFrendly, region, gamingCard);
    }

    private void eventForField(boolean isFrendly, GamingCard card, Region region) {
        forField(isFrendly, region, card);
    }

    private void forField(boolean isFrendly, Region region, GamingCard gamingCard) {
        cardsHand.setDisable(false);
        friendlyLider.setDisable(false);
        for (int i = 0; i < regions.length; i++) {
            if (regions[i].equals(region)) {
                CardForm cardForm;
                switch (i) {
                    case 0:
                        cardForm = CardForm.THIRD_STONE;
                        break;
                    case 1:
                        cardForm = CardForm.TWO_STONE;
                        break;
                    case 2:
                        cardForm = CardForm.ONE_STONE;
                        break;
                    case 3:
                        cardForm = CardForm.THIRD_STONE;
                        break;
                    case 4:
                        cardForm = CardForm.TWO_STONE;
                        break;
                    case 5:
                        cardForm = CardForm.ONE_STONE;
                        break;
                    default:
                        cardForm = CardForm.OTHER;
                        break;
                }
                switch (gamingCard.getCard().getId()) {
                    case 0:
                        animationDoubleDamageOnField(cardForm, isFrendly, true);
                        gamingCard.setForm(cardForm);
                        MainApp.getSingleton().client.step(gamingCard);
                        logic.stepEnemy();
                        break;
                    case 2:
                        gamingCard.setForm(cardForm);
                        animatedSelectCardAndAction(false, !isFrendly, gamingCard);
                        moveToGame(gamingCard, isFrendly, cardForm);
                        break;
                    case 3:
                        moveToGame(gamingCard, isFrendly, cardForm);
                        gamingCard.setForm(cardForm);
                        GamingCard spark = HelpClass.buildGamingCard(4);
                        spark.setForm(cardForm);
                        moveToGame(spark, isFrendly, cardForm);
                        MainApp.getSingleton().client.step(gamingCard);
                        logic.stepEnemy();
                        break;
                    case 5:
                        animationDoubleDamageOnField(cardForm, isFrendly, true);
                        moveToGame(gamingCard, isFrendly, cardForm);
                        gamingCard.setForm(cardForm);
                        MainApp.getSingleton().client.step(gamingCard);
                        logic.stepEnemy();
                        break;
                    case 6:
                        gamingCard.setForm(cardForm);
                        moveToGame(gamingCard, isFrendly, cardForm);
                        actionReturnDamage(isFrendly, cardForm);
                        MainApp.getSingleton().client.step(gamingCard);
                        logic.stepEnemy();
                        break;
                    case 7:
                        gamingCard.setForm(cardForm);
                        animatedSelectCardAndAction(false, true, gamingCard);
                        moveToGame(gamingCard, isFrendly, cardForm);
                        break;
                    case 8:
                        gamingCard.setForm(cardForm);
                        animatedSelectCardAndAction(false, false, gamingCard);
                        moveToGame(gamingCard, isFrendly, cardForm);
                        break;
                    case 9:
                        int countCard = 0;
                        for (int i1 = 6; i1 < fieldsForGame.length; i1++) {
                            countCard += fieldsForGame[i1].getItems().size();
                        }
                        if (countCard != 0)
                            gamingCard.setDamage(gamingCard.getDamage() * countCard);
                        gamingCard.setForm(cardForm);
                        moveToGame(gamingCard, isFrendly, cardForm);
                        MainApp.getSingleton().client.step(gamingCard);
                        logic.stepEnemy();
                        break;
                    case 19:
                        gamingCard.setForm(cardForm);
                        animationDoubleDamageOnField(cardForm, isFrendly, false);
                        cardsHand.remove(gamingCard);
                        MainApp.getSingleton().client.step(gamingCard);
                        logic.stepEnemy();

                        break;
                    default:
                        gamingCard.setForm(cardForm);
                        moveToGame(gamingCard, isFrendly, cardForm);
                        MainApp.getSingleton().client.step(gamingCard);
                        logic.stepEnemy();
                        break;
                }

            }
        }
        updateForce();
    }

    private void actionReturnDamage(boolean isFrendly, CardForm cardForm) {
        int i = 6;
        if (isFrendly) i = 0;
//        System.out.println(cardForm);
        switch (cardForm) {
            case THIRD_STONE:
                i += 0;
                break;
            case TWO_STONE:
                i += 2;
                break;
            case ONE_STONE:
                i += 4;
                break;
        }
        fieldsForGame[i].getItems().forEach(gamingCard -> {
            if (gamingCard.getDamage() < gamingCard.getCard().getDamage())
                gamingCard.setDamage(gamingCard.getCard().getDamage());
        });
        fieldsForGame[i + 1].getItems().forEach(gamingCard -> {
            if (gamingCard.getDamage() < gamingCard.getCard().getDamage())
                gamingCard.setDamage(gamingCard.getCard().getDamage());
        });

    }

    public void regionVisible(boolean is) {
        Arrays.stream(regions).forEach(region -> {
            region.setVisible(is);
        });
    }

    public boolean isEnemyCards() {
        for (int i = 6; i < fieldsForGame.length; i++) {
            if (fieldsForGame[i].getItems().size() != 0) return true;
        }
        return false;
    }

    public boolean isEnemyCards(CardForm cardForm) {
        int i = 6;
        switch (cardForm) {
            case TWO_STONE:
                i += 2;
                break;
            case ONE_STONE:
                i += 4;
                break;
        }
        if (fieldsForGame[i].getItems().size() != 0 ||
                fieldsForGame[i + 1].getItems().size() != 0) return true;

        return false;
    }

    public boolean isFrendlyCards() {
        for (int i = 0; i < 6; i++) {
            if (fieldsForGame[i].getItems().size() != 0) return true;
        }
        return false;
    }

    public void animatedSelectCardAndAction(boolean isFrendly, GamingCard gamingCard2, CardForm cardForm) {
        if (!isFrendly) {
            if (!isEnemyCards(cardForm)) {
                if (MainApp.getSingleton().client != null)
                    MainApp.getSingleton().client.step(gamingCard2, null, -1, -1);
                logic.stepEnemy();
                return;
            }
            HelpClass.alert("Выберете вражескую карту на соседнем поле!", null, null, this);
            cardsHand.setDisable(true);
            friendlyLider.setDisable(true);
            int i = 6;
            if (isFrendly) i = 0;
            switch (cardForm) {

                case TWO_STONE:
                    i += 2;
                    break;
                case ONE_STONE:
                    i += 4;
                    break;
            }
            for (int index = i; index <= i + 1; index++) {
                int finalIndex1 = index;
                fieldsForGame[index].getItems().forEach(gamingCard -> {
                    int finalIndex = finalIndex1;
                    gamingCard.setOnMouseClicked(event -> {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            cardsHand.setDisable(false);
                            friendlyLider.setDisable(false);
                            if (MainApp.getSingleton().client != null)
                                MainApp.getSingleton().client.step(gamingCard2, gamingCard, finalIndex1, fieldsForGame[finalIndex1].indexOf(gamingCard));
                            switch (gamingCard2.getCard().getId()) {
                                case 17:
                                    if (gamingCard.getDamage() < 5)
                                        fieldsForGame[finalIndex1].remove(gamingCard);
                                    else gamingCard.setDamage(gamingCard.getDamage() - 5);
                                    break;
                            }

                            updateForce();


                            logic.stepEnemy();
                        }
                    });
                });
            }


        }
    }

    public void animatedSelectCardAndAction(boolean all, boolean isFrendly, GamingCard gamingCard2) {
        if (!isFrendly) {
            if (!isEnemyCards()) {
                if (MainApp.getSingleton().client != null)
                    MainApp.getSingleton().client.step(gamingCard2, null, -1, -1);
                logic.stepEnemy();
                return;

            }
            HelpClass.alert("Выберете вражескую карту!", null, null, this);
            cardsHand.setDisable(true);
            friendlyLider.setDisable(true);
            for (int i = 6; i < fieldsForGame.length; i++) {
                int finalI1 = i;
                fieldsForGame[i].getItems().forEach(gamingCard -> {
                    int finalI = finalI1;
                    gamingCard.setOnMouseClicked(event -> {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            cardsHand.setDisable(false);
                            friendlyLider.setDisable(false);

                            if (MainApp.getSingleton().client != null)
                                MainApp.getSingleton().client.step(gamingCard2, gamingCard, finalI1, fieldsForGame[finalI1].indexOf(gamingCard.identificator()));

                            switch (gamingCard2.getCard().getId()) {
                                case 2: {
                                    if (gamingCard.getDamage() - 10 <= 0)
                                        fieldsForGame[finalI].remove(gamingCard);
                                    else
                                        gamingCard.setDamage(gamingCard.getDamage() - 10);
                                    break;
                                }
                                case 8: {
                                    fieldsForGame[finalI].remove(gamingCard);
                                    break;
                                }
                                case 20: {
                                    gamingCard.setDamage(gamingCard.getDamage() / 2);
                                    cardsHand.remove(gamingCard2);
                                }
                            }

                            updateForce();

                            logic.stepEnemy();
                        }
                    });
                });
            }


        } else {
            if (!isFrendlyCards()) {
                if (MainApp.getSingleton().client != null)
                    MainApp.getSingleton().client.step(gamingCard2, null, -1, -1);
                logic.stepEnemy();
                return;
            }
            HelpClass.alert("Выберете союзную карту!", null, null, this);
            cardsHand.setDisable(true);
            friendlyLider.setDisable(true);
            for (int i = 0; i < 6; i++) {
                int finalI1 = i;
                fieldsForGame[i].getItems().forEach(gamingCard -> {
                    int finalI = finalI1;
                    gamingCard.setOnMouseClicked(event -> {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            cardsHand.setDisable(false);
                            friendlyLider.setDisable(false);

                            switch (gamingCard2.getCard().getId()) {
                                case 7: {
                                    gamingCard.setDamage(gamingCard.getDamage() + 20);
                                    break;
                                }
                                case 13: {
                                    if (gamingCard.getDamage() < gamingCard.getCard().getDamage()) {
                                        gamingCard.setDamage(gamingCard.getCard().getDamage());
                                    }
                                    break;
                                }
                            }

                            updateForce();
                            if (MainApp.getSingleton().client != null)
                                MainApp.getSingleton().client.step(gamingCard2, gamingCard, finalI1, fieldsForGame[finalI1].indexOf(gamingCard));
                            logic.stepEnemy();
                        }
                    });
                });
            }
        }

    }

    public void actionOnEnemyStep(GamingCard gamingCard, GamingCard gamingCardAction, Integer numberField, Integer numberCard) {
        CardForm cardForm = null;
        int field = 0;
        if (numberField != null) {
            cardForm = getCardForm(numberField);
            if (numberField >= 6) {
                field = numberField - 6;
            } else field = numberField + 6;
        }
        switch (gamingCard.getCard().getId()) {
            case 13:
                if (gamingCardAction == null) break;
                if (fieldsForGame[field].getItems().get(numberCard).getDamage() < gamingCardAction.getCard().getDamage()) {
                    fieldsForGame[field].getItems().get(numberCard).setDamage(gamingCardAction.getCard().getDamage());
                }
                updateForce();
                break;
            case 17:
                if (gamingCardAction == null) break;
                if (fieldsForGame[field].getItems().get(numberCard).getDamage() < 5)
                    fieldsForGame[field].remove(numberCard);
                else
                    fieldsForGame[field].getItems().get(numberCard).setDamage(fieldsForGame[field].getItems().get(numberCard).getDamage() - 5);

                break;
            case 0:
                animationDoubleDamageOnField(gamingCard.getForm(), false, true);
                break;
            case 2:
                moveToGame(gamingCard, false, gamingCard.getForm());
                if (gamingCardAction == null) break;
//                System.out.println("123");
                if (gamingCardAction.getDamage() - 10 <= 0)
                    fieldsForGame[field].remove(numberCard);
                else
                    fieldsForGame[field].getItems().get(numberCard).setDamage(fieldsForGame[field].getItems().get(numberCard).getDamage() - 10);

                break;
            case 3:
                moveToGame(gamingCard, false, gamingCard.getForm());
                moveToGame(HelpClass.buildGamingCard(4), false, gamingCard.getForm());
                break;
            case 5:
                animationDoubleDamageOnField(gamingCard.getForm(), false, true);
                moveToGame(gamingCard, false, gamingCard.getForm());
                break;
            case 6:
                moveToGame(gamingCard, false, gamingCard.getForm());
                actionReturnDamage(false, gamingCard.getForm());
                break;
            case 7:
                moveToGame(gamingCard, false, gamingCard.getForm());
                if (gamingCardAction == null) break;
                fieldsForGame[field].getItems().get(numberCard).setDamage(fieldsForGame[field].getItems().get(numberCard).getDamage() + 20);
                break;
            case 8:
                if (numberField != -1 && numberCard != -1) fieldsForGame[field].remove(numberCard);
                moveToGame(gamingCard, false, gamingCard.getForm());
                break;
            case 9:
                moveToGame(gamingCard, false, gamingCard.getForm());
                break;
            case 19:
                animationDoubleDamageOnField(gamingCard.getForm(), true, false);
                break;
            case 20:
                fieldsForGame[field].getItems().get(numberCard).setDamage(gamingCardAction.getDamage() / 2);
                break;
            default:
                moveToGame(gamingCard, false, gamingCard.getForm());
                break;
        }
        updateForce();

    }

    private CardForm getCardForm(int field) {
        CardForm cardForm = null;
        switch (field) {
            case 0:
            case 6:
            case 1:
            case 7:
                cardForm = CardForm.THIRD_STONE;
                break;
            case 2:
            case 8:
            case 3:
            case 9:
                cardForm = CardForm.TWO_STONE;
                break;
            case 4:
            case 10:
            case 5:
            case 11:
                cardForm = CardForm.ONE_STONE;
                break;
        }
        return cardForm;
    }

    public void actionVimpire(GamingCard card) {
        GamingCard gamingCardMax = null;
        int i1 = -1;
        int i2 = -1;
        for (int index = 0; index < fieldsForGame.length; index++) {
            CardsBoxFromPane cardsBoxFromPane = fieldsForGame[index];
            for (int index2 = 0; index2 < cardsBoxFromPane.getItems().size(); index2++) {
                if (card.equals(cardsBoxFromPane.getItems().get(index2))) continue;
                if (gamingCardMax == null) {
                    gamingCardMax = cardsBoxFromPane.getItems().get(index2);
                    i1 = index;
                    i2 = index2;
                }
                if (gamingCardMax.getDamage() < cardsBoxFromPane.getItems().get(index2).getDamage()) {
                    gamingCardMax = cardsBoxFromPane.getItems().get(index2);
                    i1 = index;
                    i2 = index2;
                }
            }
        }
        if (i1 != -1) {
            if (gamingCardMax.getDamage() < 11) {
                fieldsForGame[i1].remove(i2);
                card.setDamage(card.getDamage() + gamingCardMax.getDamage());
            } else {
                fieldsForGame[i1].getItems().get(i2).setDamage(fieldsForGame[i1].getItems().get(i2).getDamage() - 10);
                card.setDamage(card.getDamage() + 10);
            }
        }
        updateForce();
    }

    public void updateForce() {
        forceCounterFrendly.fire(new CardsBoxFromPane[]{
                fieldsForGame[0],
                fieldsForGame[1],
                fieldsForGame[2],
                fieldsForGame[3],
                fieldsForGame[4],
                fieldsForGame[5]
        });
        forceCounterEnemy.fire(new CardsBoxFromPane[]{
                fieldsForGame[6],
                fieldsForGame[7],
                fieldsForGame[8],
                fieldsForGame[9],
                fieldsForGame[10],
                fieldsForGame[11]
        });
    }

    public void addCardToHend(GamingCard... gamingCards) {
        cardsHand.addAll(Arrays.asList(gamingCards));
    }

    public void clearFields() {
        for (int i = 0; i < fieldsForGame.length; i++) {
            fieldsForGame[i].clear();
        }
    }

    public Integer getForceFrendly() {
        return forceCounterFrendly.getForce();
    }

    public Integer getForceEnemy() {
        return forceCounterEnemy.getForce();
    }
}
