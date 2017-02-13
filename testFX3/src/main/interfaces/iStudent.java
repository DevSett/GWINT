package main.interfaces;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by kills on 23.01.2017.
 */
public interface iStudent {
    void setId(int id);

    void setName(String name);

    void setFirstName(String firstName);

    void setSecondName(String secondName);

    void setPhoneNumber(String phoneNumber);

    void setAddresses(String addresses);

    void setSex(boolean sex);

    int getId();
    IntegerProperty getIdProperty();

    String getName();
    StringProperty getNameProperty();

    String getFirstName();
    StringProperty getFirstNameProperty();

    String getSecondName();
    StringProperty getSecondNameProperty();

    String getPhoneNumber();
    StringProperty getPhoneNumberProperty();

    String getAddresses();
    StringProperty getAddressesProperty();

    boolean getSex();
    BooleanProperty getSexProperty();

            /*
            id INTEGER
            Имя TEXT
            Фамилия TEXT
            Отчество TEXT
            Телефон TEXT
            Адресс TEXT
            Пол BOOLEAN
            */

}
