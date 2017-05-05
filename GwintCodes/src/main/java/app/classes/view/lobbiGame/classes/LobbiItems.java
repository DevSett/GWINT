package app.classes.view.lobbiGame.classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by killsett on 09.03.17.
 */
public class LobbiItems {
    private int id;
    private String idName;
    private String idSecondName;

    private StringProperty name;
    private StringProperty secondName;
    private Circle statusCircle;

    public LobbiItems(String name, String idF, String secondName, String idS, Color color, int idLobbi) {

        this.name = new SimpleStringProperty(name);
        this.secondName = new SimpleStringProperty(secondName);
        this.statusCircle = new Circle(5, 5, 5, color);
        this.id = idLobbi;
        this.idName = idF;
        this.idSecondName = idS;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdSecondName() {
        return idSecondName;
    }

    public void setIdSecondName(String idSecondName) {
        this.idSecondName = idSecondName;
    }
}
