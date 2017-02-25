package app.memoryCards;

import app.rulesGaming.CardColor;
import app.rulesGaming.CardRare;

import java.util.ArrayList;

/**
 * Created by kills on 21.02.2017.
 */
public class defaultCards {
    private ArrayList<defaultCard> cards = new ArrayList<>();
    private int id = 0;

    void add(int damage, String description, CardRare rare, CardColor cardColor) {
        cards.add(new defaultCard(id++, damage, description, rare, cardColor));
    }

    void add(defaultCard card) {
        id++;
        cards.add(card);
    }

    defaultCard[] get() {
        defaultCard[] cards = new defaultCard[this.cards.size()];
        int index = 0;
        for (defaultCard card : this.cards) {
            cards[index++] = card;
        }
        return cards;
    }


}

