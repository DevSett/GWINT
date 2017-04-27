package app.classes.rulesGaming;

/**
 * Created by kills on 16.02.2017.
 */
public class CardRare {
    public static final int NOTHING = -1;
    public static final int COMMON = 0;
    public static final int LESS_FREQUENT = 1;
    public static final int RARE = 2;
    public static final int VERY_RARE = 3;

    private int currentRare = NOTHING;

    public CardRare(int cardRare) {
        set(cardRare);
    }

    public void set(int cardColor) {
        switch (cardColor) {
            case COMMON:
                currentRare = COMMON;
                break;
            case LESS_FREQUENT:
                currentRare = LESS_FREQUENT;
                break;
            case RARE:
                currentRare = RARE;
                break;
            case VERY_RARE:
                currentRare = VERY_RARE;
                break;
            default:
                currentRare = NOTHING;
                break;
        }
    }

    public int get() {
        return currentRare;
    }

}
