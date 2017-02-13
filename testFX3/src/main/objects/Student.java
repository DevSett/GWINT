package main.objects;

import main.interfaces.iStudent;
import javafx.beans.property.*;

/**
 * Created by kills on 23.01.2017.
 */
public class Student implements iStudent {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty firstName;
    private StringProperty secondName;
    private StringProperty phoneNumber;
    private StringProperty addresses;
    private BooleanProperty sex;

    public Student(int id, String name, String firstName, String secondName) {

        setId(id);

        setName(name);

        setFirstName(firstName);

        setSecondName(secondName);

    }
    public Student(int id, String name, String firstName, String secondName, String phoneNumber, String addresses, boolean sex) {

        setId(id);

        setName(name);

        setFirstName(firstName);

        setSecondName(secondName);

        setPhoneNumber(phoneNumber);

        setAddresses(addresses);

        setSex(sex);

    }

    @Override
    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    @Override
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    @Override
    public void setSecondName(String secondName) {
        this.secondName = new SimpleStringProperty(secondName);
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    @Override
    public void setAddresses(String addresses) {
        this.addresses = new SimpleStringProperty(addresses);
    }

    @Override
    public void setSex(boolean sex) {
        this.sex = new SimpleBooleanProperty(sex);
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
    public String getName() {
        return name.get();
    }

    @Override
    public StringProperty getNameProperty() {
        return name;
    }

    @Override
    public String getFirstName() {
        return firstName.get();
    }

    @Override
    public StringProperty getFirstNameProperty() {
        return firstName;
    }

    @Override
    public String getSecondName() {
        return secondName.get();
    }

    @Override
    public StringProperty getSecondNameProperty() {
        return secondName;
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
    public String getAddresses() {
        return addresses.get();
    }

    @Override
    public StringProperty getAddressesProperty() {
        return addresses;
    }

    @Override
    public boolean getSex() {
        return sex.get();
    }

    @Override
    public BooleanProperty getSexProperty() {
        return sex;
    }
}
