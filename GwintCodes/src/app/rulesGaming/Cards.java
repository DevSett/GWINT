package app.rulesGaming;

import java.util.ArrayList;

/**
 * Created by kills on 21.02.2017.
 */
public class Cards {
    private ArrayList<Card> cards = new ArrayList<>();

    void add(int id, int damage, String description, CardRare rare, CardColor cardColor) {
        cards.add(new Card(id, damage, description, rare, cardColor));
    }

    void add(Card card) {
        cards.add(card);
    }

    void remove(int index) {
        cards.remove(index);
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

