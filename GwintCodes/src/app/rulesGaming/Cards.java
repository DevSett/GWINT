package app;

import app.rulesGaming.Card;
import app.rulesGaming.CardColor;
import app.rulesGaming.CardRare;

import java.util.ArrayList;

/**
 * Created by kills on 21.02.2017.
 */
public class Cards {
    private ArrayList<Card> cards = new ArrayList<>();
    private int id = 0;

    void add(int damage, String description, CardRare rare, CardColor cardColor) {
        cards.add(new Card(id++, damage, description, rare, cardColor));
    }

    void add(Card card) {
        id++;
        cards.add(card);
    }

    Card[] get() {
        Card[] cards = new Card[this.cards.size()];
        int index = 0;
        for (Card card : this.cards) {
            cards[index++] = card;
        }
        return cards;
    }


}

