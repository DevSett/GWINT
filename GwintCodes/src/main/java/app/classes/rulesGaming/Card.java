package app.classes.rulesGaming;

/**
 * Created by kills on 16.02.2017.
 */
public class Card {

    private Integer id;
    private Integer damage;
    private String description;
    private CardRare rare;
    private CardColor color;
    private CardType type;
    private CardForm form;

    public Card(int id, int damage, String description, CardRare rare, CardColor color, CardType typeCard, CardForm cardForm) {
        this.id = id;
        this.damage = damage;
        this.description = description;
        this.rare = rare;
        this.color = color;
        this.type = typeCard;
        this.form = cardForm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDamage() {
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

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardForm getForm() {
        return form;
    }

    public void setForm(CardForm form) {
        this.form = form;
    }

    @Override
    public String toString() {
        return "" + getId() + "|" + getDamage();
    }

}
