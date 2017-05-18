package app.classes.view.gamingTable;


import app.classes.MainApp;
import app.classes.view.optionGame.classes.ScreenResolution;
import javafx.scene.layout.Region;

/**
 * Created by killsett on 15.05.17.
 */
public class Field extends Region {
    public Field() {
        setVisible(false);
        setPrefSize(ScreenResolution.SIZE.GAME.WIDTH / MainApp.getSingleton().getDel(), ScreenResolution.SIZE.GAME.HEIGHT / MainApp.getSingleton().getDel());
        setStyle("-fx-effect: dropshadow(three-pass-box, yellow, 10, 0.8, 0, 0); -fx-border-color:  rgba(215, 226, 44, 0.3); -fx-border-radius: 10");
    }
}

