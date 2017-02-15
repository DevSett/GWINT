package app.view.gamingTable;

import javafx.scene.layout.StackPane;

import java.util.ArrayList;

/**
 * Created by kills on 15.02.2017.
 */
public class CardsHand extends StackPane {
    private ArrayList<GamingCard> cards = new ArrayList<>();
    private double sizeX;
    private double sizeY;
    private double locale = -100;
    public CardsHand(int x, int y) {
        setPrefSize(x, y);
    }

    public void addAll(ArrayList<GamingCard> gamingCards) {
        cards.addAll(gamingCards);
        for(GamingCard card : gamingCards)
        {
            card.setLayoutX(locale+=100);
            getChildren().add(card);
        }
    }

    public void add(GamingCard card) {
        cards.add(card);
        card.setLayoutX(locale+=100);
        getChildren().add(card);
    }

    public void remove(int index) {
        for(int indexId=index+1;indexId<cards.size();indexId++)
        {
            cards.get(indexId).setTranslateX(getLayoutX()-100);
        }
            cards.remove(index);
        getChildren().remove(index);
    }

    public void remove(GamingCard card) {

        for(int indexId=cards.indexOf(card)+1;indexId<cards.size();indexId++)
        {
            cards.get(indexId).setTranslateX(getLayoutX()-100);
        }
        cards.remove(card);
        getChildren().remove(card);
    }

}
