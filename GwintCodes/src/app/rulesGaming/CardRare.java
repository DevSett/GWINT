package app.rulesGaming;

/**
 * Created by kills on 16.02.2017.
 */
public class CardRare {
    public static final int COMMON=0;
    public static final int LESS_FREQUENT=1;
    public static final int RARE=2;
    public static final int VERY_RARE=3;
    private boolean rareCommon= false;
    private boolean rareLessFrequent = false;
    private boolean rareRare = false;
    private boolean rareVeryRare = false;

    public CardRare(int cardRare) {
        set(cardRare);
    }

    public void set(int cardColor) {
        switch (cardColor) {
            case COMMON:
                rareCommon= true;
                rareLessFrequent = false;
                rareRare = false;
                rareVeryRare = false;
                break;
            case LESS_FREQUENT:
                rareCommon= false;
                rareLessFrequent = true;
                rareRare = false;
                rareVeryRare = false;
                break;
            case RARE:
                rareCommon= false;
                rareLessFrequent = false;
                rareRare = true;
                rareVeryRare = false;
                break;
            case VERY_RARE:
                rareCommon= false;
                rareLessFrequent = false;
                rareRare = false;
                rareVeryRare = true;
                break;
            default: {
                rareCommon= false;
                rareLessFrequent = false;
                rareRare = false;
                rareVeryRare = false;
                break;
            }
        }
    }

    public int get() {
        if (rareCommon == true) return 0;
        if (rareLessFrequent == true) return 1;
        if (rareRare == true) return 2;
        if (rareVeryRare == true) return 3;
        return -1;
    }

}
