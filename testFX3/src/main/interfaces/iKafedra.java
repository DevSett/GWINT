package main.interfaces;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by kills on 23.01.2017.
 */
public interface iKafedra {
    void setId(int id);

    void setIdFakult(int idFakult);

    void setName(String name);

    void setAddresses(String addresses);

    void setMail(String mail);

    void setPhoneNumber(String phoneNumber);

    void setLink(String link);

    void setMainMan(String mainMan);

    int getId();
    IntegerProperty getIdProperty();

    int getIdFakult();
    IntegerProperty getIdFakultProperty();

    String getName();
    StringProperty getNameProperty();

    String getAddresses();
    StringProperty getAddressesProperty();

    String getMail();
    StringProperty getMailProperty();

    String getPhoneNumber();
    StringProperty getPhoneNumberProperty();

    String getLink();
    StringProperty getLinkProperty();

    String getMainMan();
    StringProperty getMainManProperty();


            /*
            id INTEGER
            id_Факультета INTEGER
            Наименование TEXT
            Адресс TEXT
            Почта TEXT
            Телефон TEXT
            Ссылка TEXT
            Заведущий TEXT
            */
}
