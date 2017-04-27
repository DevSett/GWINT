package app.classes.rulesGaming;

/**
 * Created by kills on 16.02.2017.
 */
public class CardColor {
    private int currentColor = NOTHING;

    public static final int NOTHING = -1;
    public static final int BRONZE = 0;
    public static final int SILVER = 1;
    public static final int GOLD = 2;

    public CardColor(int cardColor) {
        set(cardColor);
    }

    public void set(int cardColor) {
        switch (cardColor) {
            case BRONZE:
                currentColor = BRONZE;
                break;
            case SILVER:
                currentColor = SILVER;
                break;
            case GOLD:
                currentColor = GOLD;
                break;
            default:
                currentColor = NOTHING;
                break;
        }
    }

    public int get() {
        return currentColor;
    }

}
