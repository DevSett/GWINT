package main.objects;

import main.interfaces.iKafedra;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by kills on 23.01.2017.
 */
public class Kafedra implements iKafedra {

    private IntegerProperty id;
    private IntegerProperty idFakult;
    private StringProperty name;
    private StringProperty addresses;
    private StringProperty mail;
    private StringProperty phoneNumber;
    private StringProperty link;
    private StringProperty mainMan;

    public Kafedra(int id, int idFakult, String name, String addresses, String mail, String phoneNumber, String link, String mainMan) {

        setId(id);

        setIdFakult(idFakult);

        setName(name);

        setAddresses(addresses);

        setMail(mail);

        setPhoneNumber(phoneNumber);

        setLink(link);

        setMainMan(mainMan);

    }

    public Kafedra(int id, String name) {
        setId(id);
        setName(name);
    }

    @Override
    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    @Override
    public void setIdFakult(int idFakult) {
        this.idFakult = new SimpleIntegerProperty(idFakult);
    }

    @Override
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    @Override
    public void setAddresses(String addresses) {
        this.addresses = new SimpleStringProperty(addresses);
    }

    @Override
    public void setMail(String mail) {
        this.mail = new SimpleStringProperty(mail);
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    @Override
    public void setLink(String link) {
        this.link = new SimpleStringProperty(link);
    }

    @Override
    public void setMainMan(String mainMan) {
        this.mainMan = new SimpleStringProperty(mainMan);
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
    public int getIdFakult() {
        return idFakult.get();
    }

    @Override
    public IntegerProperty getIdFakultProperty() {
        return idFakult;
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
    public String getAddresses() {
        return addresses.get();
    }

    @Override
    public StringProperty getAddressesProperty() {
        return addresses;
    }

    @Override
    public String getMail() {
        return mail.get();
    }

    @Override
    public StringProperty getMailProperty() {
        return mail;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    @Override
    public StringProperty getPhoneNumberProperty() {
        return phoneNumber;
    }

    @Override
    public String getLink() {
        return link.get();
    }

    @Override
    public StringProperty getLinkProperty() {
        return link;
    }

    @Override
    public String getMainMan() {
        return mainMan.get();
    }

    @Override
    public StringProperty getMainManProperty() {
        return mainMan;
    }
}
