package app.classes.rulesGaming;

/**
 * Created by kills on 16.02.2017.
 */
public enum CardRare {
    COMMON(55, 100),//45%
    LESS_FREQUENT(27, 55),//27%
    RARE(10, 27),//18%
    VERY_RARE(0, 10);//10%

    private Integer chance;
    private Integer chanceTo;

    CardRare(Integer chance, Integer chanceTo) {
        this.chance = chance;
        this.chanceTo = chanceTo;
    }

    public Integer getChance() {
        return chance;
    }

    public Integer getChanceTo() {
        return chanceTo;
    }
}
