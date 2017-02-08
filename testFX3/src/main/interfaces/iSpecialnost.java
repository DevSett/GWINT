package main.interfaces;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by kills on 23.01.2017.
 */
public interface iSpecialnost {
    void setId(int id);

    void setIdKafedr(int idKafedr);

    void setName(String name);

    void setKvalif(String kvalif);

    int getId();
    IntegerProperty getIdProperty();

    int getIdKafedr();
    IntegerProperty getIdKaferProperty();

    String getName();
    StringProperty getNameProperty();

    String getKvalif();
    StringProperty getKvalifProperty();



                    /*
            id INTEGER
            id_Кафедры INTEGER
            Наименование TEXT
            Квалификация TEXT
                    */
}
