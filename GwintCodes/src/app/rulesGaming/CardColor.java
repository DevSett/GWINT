package app.rulesGaming;

/**
 * Created by kills on 16.02.2017.
 */
public class CardColor {
    private boolean colorBronze = false;
    private boolean colorSilver = false;
    private boolean colorGold = false;
    public static final int BRONZE = 0;
    public static final int SILVER = 1;
    public static final int GOLD = 2;

    public CardColor(int cardColor) {
        set(cardColor);
    }

    public void set(int cardColor) {
        switch (cardColor) {
            case BRONZE:
                colorBronze = true;
                colorSilver = false;
                colorGold = false;
                break;
            case SILVER:
                colorBronze = false;
                colorSilver = true;
                colorGold = false;
                break;
            case GOLD:
                colorBronze = false;
                colorSilver = false;
                colorGold = true;
                break;
            default: {
                colorBronze = false;
                colorSilver = false;
                colorGold = false;
                break;
            }
        }
    }

    public int get() {
        if (colorBronze == true) return 0;
        if (colorSilver == true) return 1;
        if (colorGold == true) return 2;
        return -1;
    }

}
