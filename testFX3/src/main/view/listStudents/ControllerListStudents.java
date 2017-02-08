package main.view.listStudents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import main.objects.*;
import main.otherClass.DialogLib;

/**
 * Created by kills on 23.01.2017.
 */
public class ControllerListStudents {

    @FXML
    private ChoiceBox<String> choiceBoxFakultet;
    @FXML
    private ChoiceBox<String> choiceBoxKafedra;
    @FXML
    private ChoiceBox<String> choiceBoxSpecialnost;
    @FXML
    private ChoiceBox<String> choiceBoxKurs;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TextField fieldID;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldFirstName;
    @FXML
    private TextField fieldSecondName;
    @FXML
    private TextField fieldPhoneNumber;
    @FXML
    private TextField fieldAddresses;
    @FXML
    private ChoiceBox boxSex;
    @FXML
    private TextField fieldFakultet;
    @FXML
    private TextField fieldKafedra;
    @FXML
    private TextField fieldKurs;
    @FXML
    private TextField fieldFormReal;
    @FXML
    private ChoiceBox<String> menuLearning;
    @FXML
    private Button menuItemFakultel;
    @FXML
    private Button menuItemKafedra;
    @FXML
    private Button menuItemSpecialnost;
    //

    @FXML
    private Menu menuTest;
    //

    @FXML
    private VBox vbox;
    @FXML
    private Button itemEditStudent;
    @FXML
    private Button itemCreateStudent;
    @FXML
    private Button itemDeleteStudent;
    @FXML
    private Button itemSaveStudent;
    @FXML
    private Button itemCancel;

    @FXML
    private Button itemEditLearning;
    @FXML
    private Button itemCreateLearning;
    @FXML
    private Button itemDeleteLearning;
    @FXML
    private Button itemSaveLearning;
    @FXML
    private Button itemCancelLearning;

    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> secondNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    private String charsFilterForNumbers = "1234567890-";
    private Main mainApp;
    private ObservableList<Learning> learningData;
    private ObservableList<Fakultet> fakultetData;
    @FXML
    private HBox hBox;

    public void setMainApp(Main mainApp) {

        this.mainApp = mainApp;

        tableView.setItems(mainApp.getStudentsData());

        updateListFilters();
    }

    public void updateListFilters() {

        fakultetData = mainApp.getListFakults();
        choiceBoxFakultet.getItems().clear();
        choiceBoxFakultet.getItems().add("Факультет");

        for (Fakultet fakultet : fakultetData) {
            choiceBoxFakultet.getItems().add(fakultet.getName());
        }

        choiceBoxFakultet.getSelectionModel().select("Факультет");

        choiceBoxKafedra.getItems().add("Кафедра");
        choiceBoxKafedra.getSelectionModel().selectNext();

        choiceBoxSpecialnost.getItems().add("Специальность");
        choiceBoxSpecialnost.getSelectionModel().selectNext();

        choiceBoxKurs.getItems().add("Курс");
        choiceBoxKurs.getSelectionModel().selectNext();

    }

    @FXML
    private void initialize() {
        hBox.getStyleClass().add("hbox");

        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNameProperty());
        secondNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getFirstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getSecondNameProperty());

        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));


        menuLearning.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showLearningDetails(newValue));

        boxSex.getItems().clear();
        boxSex.getItems().add("Мужской");
        boxSex.getItems().add("Женский");

        choiceBoxFakultet.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showFakultsDetails(newValue));
        choiceBoxKafedra.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showKafedraDetails(newValue));
        choiceBoxSpecialnost.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSpecDetails(newValue));
        choiceBoxKurs.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showKursDetails(newValue));
        fieldKurs.setOnKeyPressed(event -> {
            if (charsFilterForNumbers.indexOf(event.getText()) == -1) {
                new DialogLib.Warning("Ошибка!", "Некорректный символ!", "Разрешен ввод только цифр");
                if (fieldKurs.getText().length() > 0)
                    fieldKurs.setText(fieldKurs.getText());
                else fieldKurs.clear();
            }
        });
        fieldPhoneNumber.setOnKeyPressed(event -> {
            if (charsFilterForNumbers.indexOf(event.getText()) == -1) {
                new DialogLib.Warning("Ошибка!", "Некорректный символ!", "Разрешен ввод только цифр");
                if (fieldPhoneNumber.getText().length() > 0)
                    fieldPhoneNumber.setText(fieldPhoneNumber.getText());
                else fieldPhoneNumber.clear();
            }
        });

    }


    private void showFakultsDetails(String newValue) {
        if (newValue == null)
            return;
        Fakultet fakultetB = null;
        for (Fakultet fakultet : fakultetData) {
            if (fakultet.getName().equals(newValue))
                fakultetB = fakultet;
        }
        if (newValue.equals("Факультет")) {
            tableView.setItems(mainApp.getStudentsData());
            choiceBoxKafedraClear();
            choiceBoxKursClear();
            setChoiceBoxSpecialnostClear();
            return;
        }
        choiceBoxKafedraClear();
        choiceBoxKursClear();
        setChoiceBoxSpecialnostClear();
        if (fakultetB != null) {
            showKafedraChooser(fakultetB.getId());
            tableView.setItems(mainApp.getFilterFakultsForStudents(fakultetB.getId()));
        }
    }

    private void showKafedraDetails(String newValue) {
        if (newValue == null || kafedras == null)
            return;
        Kafedra kafedraB = null;
        for (Kafedra kafedra : kafedras) {
            if (kafedra.getName().equals(newValue))
                kafedraB = kafedra;
        }
        if (newValue.equals("Кафедра")) {
            Fakultet fakultetB = null;
            for (Fakultet fakultet : fakultetData) {
                if (fakultet.getName().equals(choiceBoxFakultet.getSelectionModel().getSelectedItem()))
                    fakultetB = fakultet;
            }
            if (fakultetB != null)
                tableView.setItems(mainApp.getFilterFakultsForStudents(fakultetB.getId()));
            choiceBoxKursClear();
            setChoiceBoxSpecialnostClear();
            return;
        }
        tableView.setItems(mainApp.getFilterKafedrsForStudents(kafedraB.getId()));

        choiceBoxKursClear();
        setChoiceBoxSpecialnostClear();
        showSpecChooser(kafedraB.getId());
    }

    private void choiceBoxKafedraClear() {
        choiceBoxKafedra.getItems().clear();
        choiceBoxKafedra.getItems().add("Кафедра");
        choiceBoxKafedra.getSelectionModel().select(0);
    }

    private void choiceBoxKursClear() {
        choiceBoxKurs.getItems().clear();
        choiceBoxKurs.getItems().add("Курс");
        choiceBoxKurs.getSelectionModel().select(0);
    }

    private void setChoiceBoxSpecialnostClear() {
        choiceBoxSpecialnost.getItems().clear();
        choiceBoxSpecialnost.getItems().add("Специальность");
        choiceBoxSpecialnost.getSelectionModel().select(0);
    }

    ObservableList<Kafedra> kafedras;

    private void showKafedraChooser(int idFakult) {
        kafedras = mainApp.getListKafedra(idFakult);
        ObservableList<String> kafData = FXCollections.observableArrayList();
        kafData.add("Кафедра");
        if (kafedras != null)
            for (Kafedra kafedra : kafedras) {
                kafData.add(kafedra.getName());
            }
        choiceBoxKafedra.getItems().clear();
        choiceBoxKafedra.setItems(kafData);
        choiceBoxKafedra.getSelectionModel().select(0);

    }

    ObservableList<Specialnost> specialnosts;

    private void showSpecDetails(String newValue) {
        if (newValue == null || specialnosts == null)
            return;
        Specialnost specialnostB = null;
        for (Specialnost specialnost : specialnosts) {
            if (specialnost.getName().equals(newValue))
                specialnostB = specialnost;
        }
        if (newValue.equals("Специальность")) {
            Kafedra kafedra = null;
            if (kafedras != null)
                for (Kafedra kafedra1 : kafedras) {
                    if (kafedra1.getName().equals(choiceBoxKafedra.getSelectionModel().getSelectedItem()))
                        kafedra = kafedra1;
                }
            if (kafedra != null) tableView.setItems(mainApp.getFilterKafedrsForStudents(kafedra.getId()));
            choiceBoxKursClear();
            return;
        }
        choiceBoxKursClear();
        tableView.setItems(mainApp.getFilterSpecialnostForStudents(specialnostB.getId()));
        showKursChooser(specialnostB.getId());

    }

    private void showSpecChooser(int idKafedr) {
        specialnosts = mainApp.getListSpecialnost(idKafedr);
        if (specialnosts == null) return;
        ObservableList<String> specData = FXCollections.observableArrayList();
        specData.add("Специальность");
        for (Specialnost specialnost : specialnosts) {
            specData.add(specialnost.getName());
        }
        choiceBoxSpecialnost.getItems().clear();
        choiceBoxSpecialnost.setItems(specData);
        choiceBoxSpecialnost.getSelectionModel().select(0);
    }

    ObservableList<String> kurs;

    private void showKursDetails(String newValue) {
        if (newValue == null || specialnosts == null)
            return;
        if (newValue.equals("Курс")) {
            Specialnost specialnost = null;
            for (Specialnost specialnost1 : specialnosts) {
                if (specialnost1.getName().equals(choiceBoxSpecialnost.getSelectionModel().getSelectedItem()))
                    specialnost = specialnost1;
            }

            if (specialnost != null)
                tableView.setItems(mainApp.getFilterSpecialnostForStudents(specialnost.getId()));
            return;
        }
        Specialnost specialnost = null;
        for (Specialnost specialnost1 : specialnosts) {
            if (specialnost1.getName().equals(choiceBoxSpecialnost.getSelectionModel().getSelectedItem()))
                specialnost = specialnost1;
        }
        if (specialnost == null) return;
//        showKursChooser(specialnost.getId());
        tableView.setItems(mainApp.getFilterKursForStudents(newValue, specialnost.getId()));

    }

    private void showKursChooser(int idSpec) {
        kurs = mainApp.getListKurs(idSpec);
        choiceBoxKurs.getItems().clear();
        if (kurs == null) {
            choiceBoxKurs.getItems().add("Курс");
            choiceBoxKurs.getSelectionModel().select(0);
            return;
        }
        choiceBoxKurs.getItems().add("Курс");
        choiceBoxKurs.getItems().addAll(kurs);
        choiceBoxKurs.getSelectionModel().select(0);
    }

    private void studentFieldsEnable() {
        fieldAddresses.setDisable(false);
        fieldFirstName.setDisable(false);
        fieldName.setDisable(false);
        fieldPhoneNumber.setDisable(false);
        fieldSecondName.setDisable(false);
        boxSex.setDisable(false);
    }

    private void studentFieldsDisable() {
        fieldAddresses.setDisable(true);
        fieldFirstName.setDisable(true);
        fieldName.setDisable(true);
        fieldPhoneNumber.setDisable(true);
        fieldSecondName.setDisable(true);
        boxSex.setDisable(true);
    }

    @FXML
    private void handlerEditStudent() {
        if (ErrorIfFieldsClear(new TextField[]{fieldName,
                fieldPhoneNumber,
                fieldAddresses,
                fieldSecondName,
                fieldSecondName})) return;
        vbox.setDisable(true);
        studentFieldsEnable();
        menuLearning.setDisable(true);
        itemDeleteStudent.setDisable(true);
        itemCreateStudent.setDisable(true);
        itemEditStudent.setDisable(true);
        itemCancel.setDisable(false);
        itemSaveStudent.setDisable(false);
        itemCreateLearning.setDisable(true);
        itemDeleteLearning.setDisable(true);
        itemEditLearning.setDisable(true);
        itemCancelLearning.setDisable(true);
        itemSaveLearning.setDisable(true);
    }

    @FXML
    private void handleCancleStudent() {
        tableView.setItems(mainApp.getStudentsData());
        itemCancel.setDisable(true);
        vbox.setDisable(false);
        studentFieldsDisable();
        menuLearning.setDisable(false);
        itemDeleteStudent.setDisable(false);
        itemCreateStudent.setDisable(false);
        itemEditStudent.setDisable(false);
        itemSaveStudent.setDisable(true);

        fakultetData = mainApp.getListFakults();
        ObservableList<String> fakultetDataName = FXCollections
                .observableArrayList();
        for (Fakultet fakultet : fakultetData) {
            fakultetDataName.add(fakultet.getName());
        }
        fakultetDataName.add("Все");
        choiceBoxFakultet.setItems(fakultetDataName);
        choiceBoxKafedra.getItems().clear();
        choiceBoxKurs.getItems().clear();
        choiceBoxSpecialnost.getItems().clear();

        itemCreateLearning.setDisable(false);
        itemDeleteLearning.setDisable(false);
        itemEditLearning.setDisable(false);
        itemCancelLearning.setDisable(true);
        itemSaveLearning.setDisable(true);
    }

    @FXML
    private void handlerDeleteStudent() {
        if (ErrorIfFieldsClear(new TextField[]{fieldName,
                fieldPhoneNumber,
                fieldAddresses,
                fieldSecondName,
                fieldSecondName})) return;

        boolean chose = new DialogLib.Confirm("Предупреждение!", null, "Если вы удалите студента все обучения удалятся!").checkConfirm();
        if (chose) {
            learningData = mainApp.getLearningsData(Integer.parseInt(fieldID.getText()));
            for (Learning learning : learningData) {
                mainApp.deleteLearning(learning.getId());
            }
            mainApp.deleteStudent(fieldID.getText());

        }
        tableView.setItems(mainApp.getStudentsData());
    }

    @FXML
    private void handlerCreateStudent() {

        menuLearning.setDisable(true);
        vbox.setDisable(true);

        itemCancel.setDisable(false);
        itemDeleteStudent.setDisable(true);
        itemCreateStudent.setDisable(true);
        itemEditStudent.setDisable(true);
        itemSaveStudent.setDisable(false);

        itemCreateLearning.setDisable(true);
        itemDeleteLearning.setDisable(true);
        itemEditLearning.setDisable(true);
        itemCancelLearning.setDisable(true);
        itemSaveLearning.setDisable(true);

        studentFieldsEnable();
        fieldsStudentsClear();
    }

    @FXML
    private void handlerSaveStudent() {
        if (ErrorIfFieldsClear(
                new TextField[]{
                        fieldName,
                        fieldPhoneNumber,
                        fieldAddresses,
                        fieldSecondName,
                        fieldSecondName})) return;

        if (fieldID.getText().isEmpty()) {
            mainApp.createNewStudent(
                    fieldName.getText(),
                    fieldFirstName.getText(),
                    fieldSecondName.getText(),
                    fieldAddresses.getText(),
                    fieldPhoneNumber.getText(),
                    String.valueOf(boxSex.getSelectionModel().getSelectedItem()));


        } else {
            mainApp.updateStudent(fieldID.getText(), fieldName.getText(), fieldFirstName.getText(), fieldSecondName.getText(), fieldAddresses.getText(), fieldPhoneNumber.getText(), String.valueOf(boxSex.getSelectionModel().getSelectedItem()));
        }
        tableView.setItems(mainApp.getStudentsData());
        menuLearning.setDisable(false);
        studentFieldsDisable();
        vbox.setDisable(false);
        itemEditStudent.setDisable(false);
        itemDeleteStudent.setDisable(false);
        itemCreateStudent.setDisable(false);
        itemCancel.setDisable(true);
        itemSaveStudent.setDisable(true);

        itemCreateLearning.setDisable(false);
        itemDeleteLearning.setDisable(false);
        itemEditLearning.setDisable(false);
        itemCancelLearning.setDisable(true);
        itemSaveLearning.setDisable(true);

    }


    private void showLearningDetails(String learningS) {
        if (learningS == null || learningS.isEmpty()) {
            setFieldLearnClear();
            return;
        }

        Learning learning = null;

        for (Learning learningA : learningData) {
            if (mainApp.getSpecialnostName(learningA.getIdSpecialnost()).equals(learningS))
                learning = learningA;
        }

        if (learning != null) {
            fieldFakultet.setText(mainApp.getFakultetNameFromIdSpecialnost(learning.getIdSpecialnost()));
            fieldKafedra.setText(mainApp.getKafedraNameFromIdSpecialnost(learning.getIdSpecialnost()));
            fieldKurs.setText(String.valueOf(learning.getKurs()));
            fieldFormReal.setText(learning.getFormReal());
            learningGL = learning;
        }
    }

    private Learning learningGL;
    private int idSelected = -1;

    @FXML
    private void handleEditLearning() {

        if (fieldFakultet.getText().isEmpty() ||
                fieldFormReal.getText().isEmpty()) {
            new DialogLib.Warning("Ошибка!", "Не выбрана специальность!", "Выберете специальность!");
            return;

        }
        setEnabledLeanrFields();
        idSelected = 1;

        vbox.setDisable(true);
        menuLearning.setDisable(true);

        itemCreateLearning.setDisable(true);
        itemDeleteLearning.setDisable(true);
        itemEditLearning.setDisable(true);
        itemCancelLearning.setDisable(false);
        itemSaveLearning.setDisable(false);


        itemSaveStudent.setDisable(true);
        itemCreateStudent.setDisable(true);
        itemCancel.setDisable(true);
        itemDeleteStudent.setDisable(true);
        itemEditStudent.setDisable(true);

    }

    @FXML
    private void handleDeleteLearning() {
        if (menuLearning.getSelectionModel().getSelectedIndex() == -1) {
            new DialogLib.Error("Ошибка!", null, "Выберете специальность для удаления");
            return;
        }
        boolean chose = new DialogLib.Confirm("Предупреждение!", null, "Вы точно хотите удалить специальность?").checkConfirm();
        if (chose) {

            mainApp.deleteLearning(learningGL.getId());
            setFieldLearnClear();
            detailsLearning();

            learningGL = null;

            fakultetData = mainApp.getListFakults();
            ObservableList<String> fakultetDataName = FXCollections
                    .observableArrayList();
            fakultetDataName.add("Факультет");
            if (fakultetData != null)
                for (Fakultet fakultet : fakultetData)
                    fakultetDataName.add(fakultet.getName());

            choiceBoxFakultet.setItems(fakultetDataName);
            choiceBoxFakultet.getSelectionModel().select(0);
            choiceBoxKursClear();
            choiceBoxKafedraClear();
            setChoiceBoxSpecialnostClear();
        }
    }

    @FXML
    private void handleSaveLearning() {
        if (ErrorIfFieldsClear(new TextField[]{fieldKurs, fieldFormReal})) return;

        if (idSelected == 1) {
            if (learningGL == null) new DialogLib.Error("Крит.Ошибка!", null, "Ошибка выбора специальности!");
            else mainApp.updateLearning(learningGL.getId(), fieldKurs.getText(), fieldFormReal.getText());
        }
        if (idSelected == 2) {
            String nameSpecial = menuLearning.getSelectionModel().getSelectedItem();
            int idSpecial = mainApp.findSpecial(nameSpecial);
            mainApp.createNewLearning(idSpecial, Integer.parseInt(fieldID.getText()), fieldKurs.getText(), fieldFormReal.getText());
        }
        menuLearning.setDisable(false);

        itemCancelLearning.setDisable(true);
        itemCreateLearning.setDisable(false);
        itemDeleteLearning.setDisable(false);
        itemEditLearning.setDisable(false);
        itemSaveLearning.setDisable(true);


        itemSaveStudent.setDisable(true);
        itemCreateStudent.setDisable(false);
        itemCancel.setDisable(true);
        itemDeleteStudent.setDisable(false);
        itemEditStudent.setDisable(false);

        detailsLearning();
        setDisabledLearnFields();
        vbox.setDisable(false);

    }

    @FXML
    private void handleCreateLearning() {

        ObservableList<Specialnost> NamesSpecData = mainApp.getListSpecialnost();
        if (NamesSpecData != null)
            for (Specialnost data : NamesSpecData) {
                menuLearning.getItems().add(data.getName());
            }
        setEnabledLeanrFields();

        itemCancelLearning.setDisable(false);
        itemCreateLearning.setDisable(true);
        itemDeleteLearning.setDisable(true);
        itemEditLearning.setDisable(true);
        itemSaveLearning.setDisable(false);

        itemSaveStudent.setDisable(false);
        itemCreateStudent.setDisable(true);
        itemCancel.setDisable(true);
        itemDeleteStudent.setDisable(true);
        itemEditStudent.setDisable(true);

        vbox.setDisable(true);
        idSelected = 2;
    }

    @FXML
    private void handleCancelLearning()
    {
        vbox.setDisable(false);
        detailsLearning();
        setDisabledLearnFields();
        setFieldLearnClear();

        menuLearning.setDisable(false);
        itemCancelLearning.setDisable(true);
        itemCreateLearning.setDisable(false);
        itemDeleteLearning.setDisable(false);
        itemEditLearning.setDisable(false);
        itemSaveLearning.setDisable(true);


        itemSaveStudent.setDisable(true);
        itemCreateStudent.setDisable(false);
        itemCancel.setDisable(true);
        itemDeleteStudent.setDisable(false);
        itemEditStudent.setDisable(false);

    }


    private void setEnabledLeanrFields() {

        fieldKurs.setDisable(false);
        fieldFormReal.setDisable(false);
    }

    private void setDisabledLearnFields() {

        fieldKurs.setDisable(true);
        fieldFormReal.setDisable(true);
    }

    private void setFieldLearnClear() {
        fieldFakultet.clear();
        fieldKafedra.clear();
        fieldKurs.clear();
        fieldFormReal.clear();
    }

    private void detailsLearning() {
        learningData = mainApp.getLearningsData(Integer.parseInt(fieldID.getText()));
        ObservableList<String> NamegData = FXCollections
                .observableArrayList();

        if(learningData!=null)
        for (Learning learning : learningData) {
            NamegData.add(mainApp.getSpecialnostName(learning.getIdSpecialnost()));
        }
        menuLearning.setItems(NamegData);

        setDisabledLearnFields();
        setFieldLearnClear();
        learningGL = null;
    }


    private void showPersonDetails(Student student) {
        if (student != null) {
            student = mainApp.getStudentData(student.getId());
            // Fill the labels with info from the person object.@FXML

            fieldID.setText(String.valueOf(student.getId()));
            fieldName.setText(student.getName());
            fieldFirstName.setText(student.getFirstName());
            fieldSecondName.setText(student.getSecondName());
            fieldPhoneNumber.setText(student.getPhoneNumber());
            fieldAddresses.setText(student.getAddresses());
            if (student.getSex())
                boxSex.getSelectionModel().select(0);
            else
                boxSex.getSelectionModel().select(1);

            //если загрузился студент, загружаем список обучений.
            detailsLearning();
            //
        } else {
            // Person is null, remove all the text.
            fieldsStudentsClear();
        }
    }

    private void fieldsStudentsClear() {
        fieldID.clear();
        fieldName.clear();
        fieldFirstName.clear();
        fieldSecondName.clear();
        fieldPhoneNumber.clear();
        fieldAddresses.clear();
        boxSex.getSelectionModel().select(-1);
    }

    private boolean ErrorIfFieldsClear(TextField[] textFields) {
        for (TextField textField : textFields)
            if (textField.getText().isEmpty()) {
                new DialogLib.Warning("Ошибка!", "Поля пустые!", "Заполните поля!!!");
                return true;
            }
        return false;
    }

    @FXML
    private void handlerShowListFakultets() {
        mainApp.showListFakult(itemCancel.getScene().getWindow());
        Stage stage = (Stage) itemCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlerShowListKafedra() {
        mainApp.showListKafedr(itemCancel.getScene().getWindow());
        Stage stage = (Stage) itemCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlerShowListSpecialnost() {
        mainApp.showListSpecial(itemCancel.getScene().getWindow());
        Stage stage = (Stage) itemCancel.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handlerShowExit() {
        System.exit(0);
    }

    public void guest() {
        menuItemFakultel.setDisable(true);
        menuItemKafedra.setDisable(true);
        menuItemSpecialnost.setDisable(true);
    }
}
