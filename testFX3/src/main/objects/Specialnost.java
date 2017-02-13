package main.objects;

import main.interfaces.iSpecialnost;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by kills on 23.01.2017.
 */
public class Specialnost implements iSpecialnost {

    private IntegerProperty id;
    private IntegerProperty idKafedr;
    private StringProperty name;
    private StringProperty kvalif;

    public Specialnost(int id, int idKafedr, String name, String kvalif) {

        setId(id);

        setIdKafedr(idKafedr);

        setName(name);

        setKvalif(kvalif);

    }

    public Specialnost(int id, String name) {
        setId(id);
        setName(name);
    }

    @Override
    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    @Override
    public void setIdKafedr(int idKafedr) {
        this.idKafedr = new SimpleIntegerProperty(idKafedr);
    }

    @Override
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    @Override
    public void setKvalif(String kvalif) {
        this.kvalif = new SimpleStringProperty(kvalif);
    }

    @Override
    public int getId() {
        return id.get();
    }

    @Override
    public IntegerProperty getIdProperty() {
        return id;
    }

    @Override
    public int getIdKafedr() {
        return idKafedr.get();
    }

    @Override
    public IntegerProperty getIdKaferProperty() {
        return idKafedr;
    }

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public StringProperty getNameProperty() {
        return name;
    }

    @Override
    public String getKvalif() {
        return kvalif.get();
    }

    @Override
    public StringProperty getKvalifProperty() {
        return kvalif;
    }
}
