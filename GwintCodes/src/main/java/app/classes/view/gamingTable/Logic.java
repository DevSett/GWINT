package app.classes.view.gamingTable;

import app.classes.MainApp;
import app.classes.other.HelpClass;
import app.classes.rulesGaming.*;
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

    public void initGamingTable(Stage stage) {
        gamingTable = new GamingTable(stage, this);
        initElGamingTable();
    }

    public void initElGamingTable() {
        gamingTable.clear();
        List<Integer> list = HelpClass.random(11);

        Cards cardsHend = new Cards();
        for (int i = 0; i < list.size(); i++) {
            cardsHend.getListCard().add(HelpClass.copyCard(cards.getListCard().get(list.get(i))));
        }
        gamingTable.drawingMainElementsGamingTable(
                HelpClass.copyCard(cards.getListCard().get(0)),
                HelpClass.copyCard(cards.getListCard().get(0)),
                cardsHend
        );
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

    public void actionOnCard(GamingCard card) {

    }

    public void backToMenu() {
        MainApp.getSingleton().menuGame(MainApp.getSingleton().getStage());
    }
}