package app.classes.view.gamingTable;


import app.classes.MainApp;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CardsBoxFromPane extends Pane {
    private ArrayList<GamingCard> cards = new ArrayList<>();
    private double sizeX;
    private double sizeY;
    private double minus = 0;

    public CardsBoxFromPane(double x, double y) {
        sizeX = x / MainApp.getSingleton().getDel();
        sizeY = y / MainApp.getSingleton().getDel();

        setMaxSize(sizeX, sizeY);

//        setStyle("-fx-border-color: aliceblue");
    }

    public void addAll(List<GamingCard> gamingCards) {
        cards.addAll(gamingCards);
        fire();
    }

    public void addAll(GamingCard[] gamingCards) {
        this.addAll(Arrays.asList(gamingCards));
        fire();
    }

    public void addAll(Collection<? extends GamingCard> c) {
        cards.addAll(c);
    }

    public void add(GamingCard card) {
        cards.add(card);
        fire();

    }

    public void remove(int index) {
        cards.remove(index);
        fire();
    }

    public int indexOf(GamingCard card) {
        return cards.indexOf(card);
    }

    public int indexOf(Long id) {
       System.out.println(cards.size());
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).identificator().equals(id)) {
                return i;
            }
        }
        return -10;
    }

    public void remove(GamingCard card) {
        cards.remove(card);
        fire();
    }

    public void fire() {
        Platform.runLater(() -> {
            getChildren().clear();
            double checkMaxSize = 0;
            for (GamingCard card : cards) {
                if (checkMaxSize + card.getPrefWidth() - minus < getMaxWidth()) {
                    card.setLayoutX(checkMaxSize);
                    getChildren().add(card);
                    checkMaxSize += card.getPrefWidth() - minus;
                } else {
                    minus += 10 / MainApp.getSingleton().getDel();
                    fire();
                    break;
                }
            }
        });
    }

    public GamingCard[] getArrays() {
        return cards.toArray(new GamingCard[cards.size()]);
    }

    public List<GamingCard> getItems() {
        return cards;
    }

    public void clear() {
        Platform.runLater(() -> {
            cards.clear();
            getChildren().clear();
        });
    }
}
