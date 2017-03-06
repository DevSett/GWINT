package app;

/**
 * Created by killsett on 06.03.17.
 */
public class StatusMainWindow {
/*    private boolean option = false;
    private boolean mainMenu = false;
    private boolean lobbi = false;
    private boolean game = false;
    private boolean single = false;*/

    private int status;
    public static final int UNCNOWN = -1;
    public static final int OPTION = 1;
    public static final int MAINMENU = 2;
    public static final int LOBBI = 3;
    public static final int GAME = 4;
    public static final int SINGLE = 5;

/*    private void allFalse() {
        option = false;
        mainMenu = false;
        lobbi = false;
        game = false;
        single = false;
    }*/

    public StatusMainWindow(int status) {
        set(status);
    }

    public void set(int status) {
        this.status = status;
        /*//        allFalse();
        switch (status) {
            case OPTION: {
//                option = true;
                status=OPTION;
                break;
            }
            case MAINMENU: {
//                mainMenu = true;
                status=MAINMENU;
                break;
            }
            case LOBBI: {
//                lobbi = true;
                status=LOBBI;
                break;
            }
            case GAME: {
//                game = true;
                status=GAME;
                break;
            }
            case SINGLE: {
//                single = true;
                status=SINGLE;
                break;
            }*/
    }

    public int get() {
        return status;
    }
}
