package main.interfaces;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by kills on 23.01.2017.
 */
public interface iLearning {

    void setId(int id);

    void setIdSpecialnost(int idSpecialnost);

    void setIdStudent(int idStudent);

    void setKurs(int kurs);

    void setFormReal(String formReal);

    int getId();
    IntegerProperty getIdProperty();

    int getIdSpecialnost();
    IntegerProperty getIdSpecialnostProperty();

    int getIdStudent();
    IntegerProperty getIdStudentProperty();

    int getKurs();
    IntegerProperty getKursProperty();

    String getFormReal();
    StringProperty getFormRealProperty();

    /*
    id INTEGER
    id_Специальности INTEGER
    id_Студента INTEGER
    Курс INTEGER
    Форма реализаци TEXT
    */

}
