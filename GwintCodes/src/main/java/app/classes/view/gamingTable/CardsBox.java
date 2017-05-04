package app.classes.view.gamingTable;

import app.classes.MainApp;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kills on 15.02.2017.
 */
public class CardsBox extends HBox {
    private ArrayList<GamingCard> cards = new ArrayList<>();
    private double sizeX;
    private double sizeY;

    public CardsBox(double x, double y, int spacing) {
        if (spacing > 0)
            setSpacing(spacing / MainApp.getSingleton().getDel());
        sizeX = x / MainApp.getSingleton().getDel();
        sizeY = y / MainApp.getSingleton().getDel();

        setMaxSize(x, y);

//        setStyle("-fx-border-color: aliceblue");
    }

    public void addAll(List<GamingCard> gamingCards) {
        cards.addAll(gamingCards);
        for (GamingCard card : gamingCards) {
            getChildren().add(card);
        }
    }

    public void addAll(GamingCard[] gamingCards) {
        this.addAll(Arrays.asList(gamingCards));
    }

    public void add(GamingCard card) {
        cards.add(card);
        getChildren().add(card);


    }

    public void remove(int index) {

        cards.remove(index);
        getChildren().remove(index);
    }

    public void remove(GamingCard card) {


        cards.remove(card);
        getChildren().remove(card);
    }

    public GamingCard[] getArrays() {
        return cards.toArray(new GamingCard[cards.size()]);
    }

    public List<GamingCard> getItems() {
        return cards;
    }
}
