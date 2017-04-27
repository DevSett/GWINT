package app.classes.rulesGaming;

/**
 * Created by kills on 16.02.2017.
 */
public class Card {

    private int id;
    private int damage;
    private String description;
    private CardRare rare;
    private CardColor color;
    private TypeCard typeCard;

    public Card(int id, int damage, String description, CardRare rare, CardColor color, TypeCard typeCard) {
        this.id = id;
        this.damage = damage;
        this.description = description;
        this.rare = rare;
        this.color = color;
        this.typeCard = typeCard;
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

    public TypeCard getType() {
        return typeCard;
    }

    public void setType(TypeCard typeCard) {
        this.typeCard = typeCard;
    }

    @Override
    public String toString() {
        return "id: " + getId() + " damage: " + getDamage() + " description: " + getDescription() + " rare: " + getRare().get() + " color: " + getColor().get();
    }


}
