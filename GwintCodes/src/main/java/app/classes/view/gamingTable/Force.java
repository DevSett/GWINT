package app.classes.view.gamingTable;

import app.classes.MainApp;
import app.classes.other.HelpClass;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

/**
 * Created by killsett on 11.05.17.
 */
public class Force extends Label {
    private Integer force = 0;
    private StringProperty property = new SimpleStringProperty("00");

    public Force() {
        super("00");
        textProperty().bind(property);
        setStyle("-fx-text-fill: whitesmoke;");
        setFont(HelpClass.getFont(50 / MainApp.getSingleton().getDel()));
    }

    public void fire() {
            if (force != 0) {
                property.setValue(String.valueOf(force));
            } else property.setValue("00");
    }

    public void fire(CardsBoxFromPane... cardsBoxes) {
        Platform.runLater(()->{
            clear();
            for (CardsBoxFromPane cardsBox : cardsBoxes) {
                for (GamingCard gamingCard : cardsBox.getArrays()) {
                    add(gamingCard.getDamage());
                }
            }
        });

    }

    public void setForce(int force) {
        this.force = force;
        fire();
    }

    public Integer getForce() {
        return force;
    }

    public void clear() {
        force = 0;
        fire();

    }

    public void add(int force) {
        this.force += force;
        fire();
    }

    public void minus(int force) {
        this.force -= force;
        fire();
    }
}

