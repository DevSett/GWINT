package app.memoryCards;

import app.rulesGaming.CardColor;
import app.rulesGaming.CardRare;

/**
 * Created by kills on 16.02.2017.
 */
public class Card {
    private int id;
    private int damage;
    private String description;
    private CardRare rare;
    private CardColor color;

    public Card(int id, int damage,String description,CardRare rare,CardColor color)
    {
        this.id=id;
        this.damage=damage;
        this.description=description;
        this.rare=rare;
        this.color=color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CardRare getRare() {
        return rare;
    }

    public void setRare(CardRare rare) {
        this.rare = rare;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }
}
