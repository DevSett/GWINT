package app.view.gamingTable;

import javafx.scene.Group;

import java.util.ArrayList;

/**
 * Created by kills on 15.02.2017.
 */
public class CardsHand extends Group {
    private ArrayList<GamingCard> cards = new ArrayList<>();
    private double sizeX;
    private double sizeY;

    public CardsHand(int x, int y) {
        sizeX = x;
        sizeY = y;

}
    public void addAll(ArrayList<GamingCard> gamingCards) {
        cards.addAll(gamingCards);
        for (GamingCard card : gamingCards) {
            getChildren().add(card);
        }
    }

    public void add(GamingCard card) {
        cards.add(card);
        getChildren().add(card);
    }

    public void remove(int index) {
        for (int indexId = index + 1; indexId < cards.size(); indexId++) {
        }
        cards.remove(index);
        getChildren().remove(index);
    }

    public void remove(GamingCard card) {

        for (int indexId = cards.indexOf(card) + 1; indexId < cards.size(); indexId++) {
        }
        cards.remove(card);
        getChildren().remove(card);
    }

}
