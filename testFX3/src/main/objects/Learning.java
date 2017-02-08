package main.objects;

import main.interfaces.iLearning;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by kills on 23.01.2017.
 */
public class Learning implements iLearning {

    private IntegerProperty id;
    private IntegerProperty idSpecialnost;
    private IntegerProperty idStudent;
    private IntegerProperty kurs;
    private StringProperty formReal;

    public Learning(int id, int idSpecialnost, int idStudent, int kurs, String formReal) {

        setId(id);

        setIdSpecialnost(idSpecialnost);

        setIdStudent(idStudent);

        setKurs(kurs);

        setFormReal(formReal);

    }

    @Override
    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    @Override
    public void setIdSpecialnost(int idSpecialnost) {
        this.idSpecialnost = new SimpleIntegerProperty(idSpecialnost);
    }

    @Override
    public void setIdStudent(int idStudent) {
        this.idStudent = new SimpleIntegerProperty(idStudent);
    }

    @Override
    public void setKurs(int kurs) {
        this.kurs = new SimpleIntegerProperty(kurs);
    }

    @Override
    public void setFormReal(String formReal) {
        this.formReal = new SimpleStringProperty(formReal);
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
    public int getIdSpecialnost() {
        return idSpecialnost.get();
    }

    @Override
    public IntegerProperty getIdSpecialnostProperty() {
        return idSpecialnost;
    }

    @Override
    public int getIdStudent() {
        return idStudent.get();
    }

    @Override
    public IntegerProperty getIdStudentProperty() {
        return idStudent;
    }

    @Override
    public int getKurs() {
        return kurs.get();
    }

    @Override
    public IntegerProperty getKursProperty() {
        return kurs;
    }

    @Override
    public String getFormReal() {
        return formReal.get();
    }

    @Override
    public StringProperty getFormRealProperty() {
        return formReal;
    }
}
