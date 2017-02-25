package app.rulesGaming;

/**
 * Created by kills on 16.02.2017.
 */
public class Rules {
    private int lifeEmemy = 2;
    private int lifeFrendly = 2;
    private int fullDamageEnemy;
    private int fullDamageFrendly;
    private int damageEnemyStone1;
    private int damageEnemyStone2;
    private int damageEnemyStone3;
    private int damageFrendlyStone1;
    private int damageFrendlyStone2;
    private int damageFrendlyStone3;
    boolean passFrendly = false;
    boolean passEnemy = false;
    boolean useLiderEnemy = false;
    boolean useLiderFrendly = false;

    public boolean isUseLiderEnemy() {
        return useLiderEnemy;
    }

    public void setUseLiderEnemy(boolean useLiderEnemy) {
        this.useLiderEnemy = useLiderEnemy;
    }

    public boolean isUseLiderFrendly() {
        return useLiderFrendly;
    }

    public void setUseLiderFrendly(boolean useLiderFrendly) {
        this.useLiderFrendly = useLiderFrendly;
    }

    public int getLifeEmemy() {
        return lifeEmemy;
    }

    public void setLifeEmemy(int lifeEmemy) {
        this.lifeEmemy = lifeEmemy;
    }

    public void lossLifeEnemy() {
        lifeEmemy -= 1;
    }

    public int getLifeFrendly() {
        return lifeFrendly;
    }

    public void setLifeFrendly(int lifeFrendly) {
        this.lifeFrendly = lifeFrendly;
    }

    public void lossLifeFrendly() {
        lifeFrendly -= 1;
    }

    public int getFullDamageEnemy() {
        return fullDamageEnemy;
    }

    public int getFullDamageFrendly() {
        return fullDamageFrendly;
    }

    public int getDamageEnemyStone1() {
        return damageEnemyStone1;
    }

    public void setDamageEnemyStone1(int damageEnemyStone1) {
        this.damageEnemyStone1 = damageEnemyStone1;
        updateFullEnemyDamage();
    }

    public int getDamageEnemyStone2() {
        return damageEnemyStone2;
    }

    public void setDamageEnemyStone2(int damageEnemyStone2) {
        this.damageEnemyStone2 = damageEnemyStone2;
        updateFullEnemyDamage();

    }

    public int getDamageEnemyStone3() {
        return damageEnemyStone3;
    }

    public void setDamageEnemyStone3(int damageEnemyStone3) {
        this.damageEnemyStone3 = damageEnemyStone3;
        updateFullEnemyDamage();
    }

    public void addDamageEnemyStone1(int addDamageEnemyStone1) {
        this.damageEnemyStone1 += addDamageEnemyStone1;
        updateFullEnemyDamage();
    }

    public void addDamageEnemyStone2(int addDamageEnemyStone2) {
        this.damageEnemyStone2 += addDamageEnemyStone2;
        updateFullEnemyDamage();
    }

    public void addDamageEnemyStone3(int addDamageEnemyStone3) {
        this.damageEnemyStone3 += addDamageEnemyStone3;
        updateFullEnemyDamage();
    }

    private void updateFullEnemyDamage() {
        fullDamageEnemy = damageEnemyStone1 + damageEnemyStone2 + damageEnemyStone3;
    }

    ;

    public int getDamageFrendlyStone1() {
        return damageFrendlyStone1;
    }

    public void setDamageFrendlyStone1(int damageFrendlyStone1) {
        this.damageFrendlyStone1 = damageFrendlyStone1;
        updateFullFrendlyDamage();
    }

    public int getDamageFrendlyStone2() {
        return damageFrendlyStone2;
    }

    public void setDamageFrendlyStone2(int damageFrendlyStone2) {
        this.damageFrendlyStone2 = damageFrendlyStone2;
        updateFullFrendlyDamage();
    }

    public int getDamageFrendlyStone3() {
        return damageFrendlyStone3;
    }

    public void setDamageFrendlyStone3(int damageFrendlyStone3) {
        this.damageFrendlyStone3 = damageFrendlyStone3;
        updateFullFrendlyDamage();
    }

    public void addDamageFrendlyStone1(int addDamageFrendlyStone1) {
        this.damageFrendlyStone1 += addDamageFrendlyStone1;
        updateFullFrendlyDamage();
    }

    public void addDamageFrendlyStone2(int addDamageFrendlyStone2) {
        this.damageFrendlyStone2 += addDamageFrendlyStone2;
        updateFullFrendlyDamage();
    }

    public void addDamageFrendlyStone3(int addDamageFrendlyStone3) {
        this.damageFrendlyStone3 += addDamageFrendlyStone3;
        updateFullFrendlyDamage();
    }

    private void updateFullFrendlyDamage() {
        fullDamageFrendly = damageFrendlyStone1 + damageFrendlyStone2 + damageFrendlyStone3;
    }

    public boolean isPassFrendly() {
        return passFrendly;
    }

    public void setPassFrendly(boolean passFrendly) {
        this.passFrendly = passFrendly;
    }

    public boolean isPassEnemy() {
        return passEnemy;
    }

    public void setPassEnemy(boolean passEnemy) {
        this.passEnemy = passEnemy;
    }


}
