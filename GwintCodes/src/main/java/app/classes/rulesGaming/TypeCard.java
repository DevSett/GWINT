package app.classes.rulesGaming;

/**
 * Created by killsett on 27.04.17.
 */
public class TypeCard {
    public static final int LIDER = 1;
    public static final int STANDART = 0;
    public static final int WEATHER = 2;
    private Integer currestType;

    public TypeCard(Integer type) {
        set(type);
    }

    public void set(Integer type) {
        switch (type) {
            case LIDER:
                currestType = LIDER;
                break;
            case STANDART:
                currestType = STANDART;
                break;
            case WEATHER:
                currestType = WEATHER;
                break;
            default:
                currestType = -1;
                break;
        }
    }

    public int get() {
        return currestType;
    }

}
