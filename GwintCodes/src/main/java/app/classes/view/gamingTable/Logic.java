package app.classes.view.gamingTable;

import app.classes.MainApp;
import app.classes.other.HelpClass;
import app.classes.rulesGaming.*;
import app.classes.view.StatusWindow;
import app.classes.view.optionGame.classes.ScreenResolution;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by killsett on 27.04.17.
 */
public class Logic {

    private Cards cards;
    private GamingTable gamingTable;

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
        List<Integer> list = HelpClass.random(11);

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
            if (check){
                gamingTable.drawingMainElementsGamingTable(
                        HelpClass.copyCard(cards.getListCard().get(0)),
                        HelpClass.copyCard(cards.getListCard().get(1)),
                        cardsHend
                );
            }
            else {
                gamingTable.drawingMainElementsGamingTable(
                        HelpClass.copyCard(cards.getListCard().get(1)),
                        HelpClass.copyCard(cards.getListCard().get(0)),
                        cardsHend
                );
            }
        }
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
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(getClass().getResource("/cards.json").getFile()));
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

    public void actionOnCard(GamingCard card, boolean frendly) {
        switch (card.getCard().getType()) {
            case STANDART:
                switch (card.getCard().getForm()) {
                    case ONE_STONE:
                        if (gamingTable.moveToGame(card, frendly, CardForm.ONE_STONE))
                            if (frendly) MainApp.getSingleton().client.step(card.getCard());

                        break;
                    case TWO_STONE:
                        if (gamingTable.moveToGame(card, frendly, CardForm.TWO_STONE))
                            if (frendly) MainApp.getSingleton().client.step(card.getCard());
                        break;
                    case THIRD_STONE:
                        if (gamingTable.moveToGame(card, frendly, CardForm.THIRD_STONE))
                            if (frendly) MainApp.getSingleton().client.step(card.getCard());
                        break;
                    default:
                        break;
                }
                break;
            case LIDER:
                System.out.println("Ход лидером!");
                break;
        }
    }

    public GamingTable getGamingTable() {
        return gamingTable;
    }

    public void backToMenu() {
        MainApp.getSingleton().menuGame(MainApp.getSingleton().getStage());
    }

    public void step() {
        gamingTable.alert("Ваш ход!", null, null);
        gamingTable.step(true);
    }

    public void stepEnemy() {
        gamingTable.alert("Ход противника!", null, null);
        gamingTable.step(false);
    }

    public void backMenu() {
        Platform.runLater(() -> {
            MainApp.getSingleton().menuGame(gamingTable.getStage());
        });
    }

    public void step(Object id, Object damage) {
        Card card = HelpClass.copyCard(getCard(HelpClass.StringToInt(id)));
        card.setDamage(HelpClass.StringToInt(damage));
        GamingCard gamingCard = new GamingCard(
                card,
                ScreenResolution.SIZE.CARD.IN_HEND.WIDTH,
                ScreenResolution.SIZE.CARD.IN_HEND.HEIGHT,
                true,
                GamingTable.countCard++
        );
        actionOnCard(gamingCard, false);
    }

    public ChangeListener<Number> surrender() {
        ChangeListener<Number> value = (observable, oldValue, newValue) -> {
            if (newValue.doubleValue() >= 1 && oldValue.doubleValue() < 1) {
                if (!gamingTable.getHeartFrendlyFirst().isDisable()) {
                    gamingTable.getHeartFrendlyFirst().setOpacity(0.5);
                    gamingTable.getHeartFrendlyFirst().setDisable(true);
                } else {
                    gamingTable.getHeartFrendlySecond().setOpacity(0.5);
                    gamingTable.getHeartFrendlySecond().setDisable(true);
                }
                //сброс карты //счетчика // новый раунд
            }
        };
        return value;
    }
}
