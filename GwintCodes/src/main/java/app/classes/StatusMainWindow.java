package app.classes;

/**
 * Created by killsett on 06.03.17.
 */
public class StatusMainWindow {


    private int status;


    public static final int UNCNOWN = -1;
    public static final int OPTION = 1;
    public static final int MAINMENU = 2;
    public static final int LOBBI = 3;
    public static final int GAME = 4;
    public static final int SINGLE = 5;

    public StatusMainWindow(int status) {
        set(status);
    }

    public void set(int status) {
        this.status = status;

    }

    public int get() {
        return status;
    }
}
