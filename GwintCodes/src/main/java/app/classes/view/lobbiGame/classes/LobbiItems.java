package app.classes.view.lobbiGame.classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by killsett on 09.03.17.
 */
public class LobbiItems {
    public int id;
    public String idName;
    public String idSecondName;

    StringProperty name;
    StringProperty secondName;
    Circle statusCircle;

    public LobbiItems(String name,String idF, String secondName,String idS, Color color, int id) {

        this.name = new SimpleStringProperty(name);
        this.secondName = new SimpleStringProperty(secondName);
        this.statusCircle = new Circle(5, 5, 5, color);
        this.id = id;
        this.idName=idF;
        this.idSecondName=idS;
    }

    public void setColor(Color color) {
        statusCircle.setFill(color);
    }

    public Circle getStatusCircle() {
        return statusCircle;
    }

    public void setStatusCircle(Circle statusCircle) {
        this.statusCircle = statusCircle;
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public StringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }


}
