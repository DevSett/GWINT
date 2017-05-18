package app.classes.view.gamingTable;

import app.classes.MainApp;
import app.classes.other.HelpClass;
import app.classes.rulesGaming.*;
import app.classes.view.StatusWindow;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Logic {

    private Cards cards;
    private GamingTable gamingTable;
    private int lider = 0;
    private boolean surrenderFr = false;
    private boolean surrenderEn = false;
    private Logger logger = Logger.getLogger(this.getClass());

    public Logic() throws IOException, ParseException {
        initCards();
    }

    public void initGamingTable(Stage stage, Boolean check) {
        gamingTable = new GamingTable(stage, this);
        initElGamingTable(check);
        MainApp.getSingleton().setStatus(StatusWindow.MULTI);
    }

    public void initElGamingTable(Boolean check) {
        gamingTable.clear();
        List<Integer> list = randomRare(11);

        Cards cardsHend = new Cards();
        for (int i = 0; i < list.size(); i++) {
            cardsHend.getListCard().add(HelpClass.copyCard(cards.getListCard().get(list.get(i))));
        }

        if (check == null) {
            gamingTable.drawingMainElementsGamingTable(
                    HelpClass.copyCard(cards.getListCard().get(0)),
                    HelpClass.copyCard(cards.getListCard().get(0)),
                    cardsHend
            );
        } else {
            if (check) {
                gamingTable.drawingMainElementsGamingTable(
                        HelpClass.copyCard(cards.getListCard().get(0)),
                        HelpClass.copyCard(cards.getListCard().get(1)),
                        cardsHend
                );
                checkStep = false;
            } else {
                gamingTable.drawingMainElementsGamingTable(
                        HelpClass.copyCard(cards.getListCard().get(1)),
                        HelpClass.copyCard(cards.getListCard().get(0)),
                        cardsHend
                );
                checkStep = true;
            }
        }
    }

    private List<Integer> randomRare(int i) {
        List<Integer> list = new ArrayList<>();
        for (int index = 0; index < i; index++) {
            int chance = new Random().nextInt(100);
            int card = new Random().nextInt(28) + 2;
            CardRare cardRare = cards.getListCard().get(card).getRare();
            while (cardRare.getChance() >= chance && cardRare.getChanceTo() < chance) {
                card = new Random().nextInt(28) + 2;
                while (checkRepeact(card, list, 2)) card = new Random().nextInt(28) + 2;
            }

            list.add(card);
        }
        return list;
    }

    private boolean checkRepeact(Integer number, List<Integer> list, int count) {
        int countC = 0;
        for (Integer integer : list) {
            if (integer == number) countC++;
        }
        if (count >= countC) return false;
        else return true;
    }

    public Cards getCards() {
        return cards;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }

    private void initCards() throws IOException, ParseException {
        cards = new Cards();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("cards.json"));
        JSONArray jsonArray = (JSONArray) jsonObject.get("cards");
        for (Object object : jsonArray) {
            JSONObject jsonEl = ((JSONObject) object);
            Integer id = HelpClass.LongToInt(jsonEl.get("id"));
            Integer damage = HelpClass.LongToInt(jsonEl.get("damage"));
            String description = (String) jsonEl.get("description");
            Integer rare = HelpClass.LongToInt(jsonEl.get("rare"));
            Integer color = HelpClass.LongToInt(jsonEl.get("color"));
            Integer type = HelpClass.LongToInt(jsonEl.get("type"));
            Integer form = HelpClass.LongToInt(jsonEl.get("form"));


            cards.getListCard().add(
                    new Card(
                            id,
                            damage,
                            description,
                            CardRare.values()[rare],
                            CardColor.values()[color],
                            CardType.values()[type],
                            CardForm.values()[form]
                    )
            );
        }
    }

    public Card getCard(int id) {
        for (Card card : cards.getListCard()) {
            if (card.getId() == id) return card;
        }
        return null;
    }

    public void actionOnCard(boolean frendly, Integer numberField, Integer numberCard, GamingCard... cards) {
        GamingCard gamingCard = cards[0];
        boolean check = true;
        if (!frendly)
            check = false;

        boolean checkInSelect = true;
        switch (gamingCard.getCard().getType()) {
            case STANDART:

                switch (gamingCard.getCard().getForm()) {
                    case ONE_STONE:
                        if (!gamingTable.moveToGame(gamingCard, frendly, CardForm.ONE_STONE))
                            check = false;
                        switch (gamingCard.getCard().getId()) {
                            case 10:
                                gamingTable.moveToGame(HelpClass.copyGamingCard(gamingCard), frendly, CardForm.ONE_STONE);
                                gamingTable.moveToGame(HelpClass.copyGamingCard(gamingCard), frendly, CardForm.ONE_STONE);
                                break;
                        }
                        break;
                    case TWO_STONE:
                        if (!gamingTable.moveToGame(gamingCard, frendly, CardForm.TWO_STONE))
                            check = false;
                        switch (gamingCard.getCard().getId()) {
                            case 11:
                                gamingTable.moveToGame(HelpClass.buildGamingCard(12), frendly, CardForm.TWO_STONE);
                                break;
                            case 12:
                                gamingTable.moveToGame(HelpClass.buildGamingCard(11), frendly, CardForm.TWO_STONE);
                                break;
                            case 16:
                                if (frendly) {
                                    sending(frendly, gamingCard, check, checkInSelect);
                                }
                                check = false;
                                gamingTable.actionVimpire(gamingCard);
                                break;
                            case 22:
                                gamingTable.moveToGame(HelpClass.buildGamingCard(23), frendly, CardForm.TWO_STONE);
                                gamingTable.moveToGame(HelpClass.buildGamingCard(24), frendly, CardForm.TWO_STONE);
                                gamingTable.moveToGame(HelpClass.buildGamingCard(25), frendly, CardForm.TWO_STONE);
                                break;
                        }
                        break;
                    case THIRD_STONE:
                        switch (gamingCard.getCard().getId()) {
                            case 13:
                                check = false;
                                if (frendly) {
                                    gamingTable.animatedSelectCardAndAction(false, frendly, gamingCard);
                                    checkInSelect = false;
                                } else {
                                    gamingTable.actionOnEnemyStep(gamingCard, cards[1], numberField, numberCard);
                                }
                                break;
                            case 17:
                                if (frendly) {
                                    gamingTable.animatedSelectCardAndAction(!frendly, gamingCard, CardForm.THIRD_STONE);
                                    checkInSelect = false;
                                    check = false;
                                } else {
                                    gamingTable.actionOnEnemyStep(gamingCard, cards[1], numberField, numberCard);
                                    checkInSelect = false;
                                    check = false;
                                }
                                break;
                        }
                        if (!gamingTable.moveToGame(gamingCard, frendly, CardForm.THIRD_STONE))
                            check = false;
                        break;
                    case UNIVERSAL:
                        if (frendly)
                            gamingTable.animatedSelectRegionField(false, frendly, gamingCard, null);
                        else if (cards[1] != null)
                            gamingTable.actionOnEnemyStep(gamingCard, cards[1], numberField, numberCard);
                        else {
                            gamingTable.actionOnEnemyStep(gamingCard, null, numberField, numberCard);
                        }
                        checkInSelect = false;
                        check = false;
                        break;
                    default:
                        check = false;
                        System.out.println("Ошибка формы");
                        logger.error("Ошибка формы: " + gamingCard.toString());
                        break;
                }

                break;
            case WEATHER:
                switch (gamingCard.getCard().getId()) {

                    case 19: {
                        if (frendly) {
                            gamingTable.animatedSelectRegionField(false, !frendly, gamingCard, null);
                        } else {
                            gamingTable.actionOnEnemyStep(gamingCard, null, numberField, numberCard);
                        }
                        checkInSelect = false;
                        check = false;
                        break;
                    }
                    case 20: {
                        if (frendly) {
                            gamingTable.animatedSelectCardAndAction(false, !frendly, gamingCard);
                        } else {
                            gamingTable.actionOnEnemyStep(gamingCard, cards[1], numberField, numberCard);
                        }
                        checkInSelect = false;
                        check = false;
                        break;
                    }
                }
                break;

            case LIDER:
                if (lider < 1 || !frendly) {

                    switch (gamingCard.getCard().getId()) {
                        case 0:
                            if (frendly) {
                                gamingTable.animatedSelectRegionField(false, true, null, gamingCard.getCard());
                                lider += 1;
                            } else gamingTable.actionOnEnemyStep(gamingCard, null, null, null);
                            checkInSelect = false;
                            check = false;
                            break;

                        case 1:
                            if (frendly) {
                                gamingTable.animatedSelectRegionField(false, true, null, HelpClass.copyCard(this.cards.getListCard().get(2)));
                                lider += 1;
                            } else
                                gamingTable.actionOnEnemyStep(HelpClass.buildGamingCard(2), cards[1], numberField, numberCard);
                            checkInSelect = false;
                            check = false;

                            break;

                        default:
                            check = false;
                            logger.error("Ошибка формы: " + gamingCard.toString());
                            System.out.println("ошибка лидера ид");
                            break;
                    }
                } else check = false;
                break;
            default:
                check = false;
                break;
        }
        gamingTable.updateForce();
        sending(frendly, gamingCard, check, checkInSelect);

    }

    private void sending(boolean frendly, GamingCard gamingCard, boolean check, boolean checkInSelect) {
        if (check) {
            if (frendly && MainApp.getSingleton().client != null) {
                MainApp.getSingleton().client.step(gamingCard);
            }
            if (checkInSelect) {
                if (frendly) {
                    stepEnemy();
                } else step();
            }
        }
    }

    public GamingTable getGamingTable() {
        return gamingTable;
    }

    public void backToMenu() {
        MainApp.getSingleton().menuGame(MainApp.getSingleton().getStage());
    }

    private boolean checkStep = false;

    public void step() {
        if (!checkStep) {
            checkStep = true;
            HelpClass.alert("Ваш ход!", null, null, gamingTable);
            gamingTable.step(true);
            gamingTable.enableSurrend();
        }
    }

    public void stepEnemy() {
        if (checkStep) {
            checkStep = false;
            if (surrenderEn) return;
            HelpClass.alert("Ход противника!", null, null, gamingTable);
            gamingTable.step(false);
            gamingTable.disableSurrender();
        }
    }

    public void backMenu() {
        Platform.runLater(() -> {
            MainApp.getSingleton().menuGame(gamingTable.getStage());
        });
    }

    public CardForm getCardForm(String value) {
        CardForm cardForm = null;
        for (CardForm cardForm1 : CardForm.values()) {
            if (cardForm1.toString().equals(value)) cardForm = cardForm1;
        }
        return cardForm;
    }

    public void step(Object id, Object damage, Object cardForm, Object id2, Object damage2, Object cardForm2, Object field, Object card) {
        GamingCard gamingCard = HelpClass.buildGamingCard(HelpClass.StringToInt(id));
        gamingCard.setDamage(Integer.valueOf((String) damage));
//        System.out.println(cardForm + "!_@");
        gamingCard.setForm(getCardForm((String) cardForm));
//        System.out.println(gamingCard.getForm() + "!_@");
        GamingCard gamingCard1 = null;
        if (!id2.equals("-1")) {
            gamingCard1 = HelpClass.buildGamingCard(HelpClass.StringToInt(id2));
            gamingCard1.setDamage(HelpClass.StringToInt(damage2));
            gamingCard1.setForm(getCardForm((String) cardForm2));

        }
        Integer fieldN = null;
        Integer cardN = null;

        if (!field.equals("-1")) fieldN = HelpClass.StringToInt(field);

        if (!card.equals("-1")) cardN = HelpClass.StringToInt(card);

        actionOnCard(false, fieldN, cardN, gamingCard, gamingCard1);

        if (!isSurrenderFr()) step();

    }

    public ChangeListener<Number> surrenderAction() {
        ChangeListener<Number> value = (observable, oldValue, newValue) -> {
            if (newValue.doubleValue() >= 1 && oldValue.doubleValue() < 1) {
                if (surrenderEn) {
                    checkAndActionSurrender(false);
                    surrenderEn = false;

                } else {
                    surrenderFr = true;
                    stepEnemy();
                }
                MainApp.getSingleton().client.surrender();

            }

        };

        return value;
    }

    public boolean isSurrenderEn() {
        return surrenderEn;
    }

    public boolean isSurrenderFr() {
        return surrenderFr;
    }

    public void surrender() {
        if (surrenderFr) {
            checkAndActionSurrender(true);
            surrenderFr = false;
        } else {
            HelpClass.alert("Противник сдался!", null, null, gamingTable);
            surrenderEn = true;
            step();
        }

    }

    private void checkAndActionSurrender(boolean isFrendly) {
        surrenderEn = false;
        surrenderFr = false;

        if (gamingTable.getForceFrendly() > gamingTable.getForceEnemy())
            checkEnButton();
        if (gamingTable.getForceFrendly() < gamingTable.getForceEnemy())
            checkFrButton();
        if (gamingTable.getForceFrendly() == gamingTable.getForceEnemy()) {
            checkEnButton();
            checkFrButton();
        }

        EventHandler<ActionEvent> eventHandler = event -> {
            gamingTable.clearFields();
            MainApp.getSingleton().menuGame(MainApp.getSingleton().getStage());
            MainApp.getSingleton().client.endGame();
            MainApp.getSingleton().client.stop();

        };
        gamingTable.updateForce();
        if (gamingTable.getHeartFrendlySecond().isDisable() && gamingTable.getHeartEnemySecond().isDisable()) {
            HelpClass.alert("Ничья!", eventHandler, null, gamingTable);
            return;
        }

        if (isFrendly) {
            if (gamingTable.getHeartFrendlySecond().isDisable()) {
                HelpClass.alert("Вы потерпели поражение!", eventHandler, null, gamingTable);
            } else {
                step();
            }
        } else {
            if (gamingTable.getHeartEnemySecond().isDisable()) {
                HelpClass.alert("Вы Выйграли!", eventHandler, null, gamingTable);
            } else {
                stepEnemy();
            }
        }

    }

    private void checkEnButton() {
        if (!gamingTable.getHeartEnemyFirst().isDisable()) {
            gamingTable.getHeartEnemyFirst().setOpacity(0.5);
            gamingTable.getHeartEnemyFirst().setDisable(true);
            List<Integer> list = randomRare(2);
            gamingTable.addCardToHend(HelpClass.buildGamingCard(list.get(0)), HelpClass.buildGamingCard(list.get(1)));
            gamingTable.clearFields();
        } else {
            gamingTable.getHeartEnemySecond().setOpacity(0.5);
            gamingTable.getHeartEnemySecond().setDisable(true);

        }
    }

    private void checkFrButton() {
        if (!gamingTable.getHeartFrendlyFirst().isDisable()) {
            gamingTable.getHeartFrendlyFirst().setOpacity(0.5);
            gamingTable.getHeartFrendlyFirst().setDisable(true);
            List<Integer> list = randomRare(2);
            gamingTable.addCardToHend(HelpClass.buildGamingCard(list.get(0)), HelpClass.buildGamingCard(list.get(1)));
            gamingTable.clearFields();
        } else {
            gamingTable.getHeartFrendlySecond().setOpacity(0.5);
            gamingTable.getHeartFrendlySecond().setDisable(true);

        }
    }
}
