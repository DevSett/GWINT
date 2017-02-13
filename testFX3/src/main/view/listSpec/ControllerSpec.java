package main.view.listSpec;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import main.objects.Fakultet;
import main.objects.Kafedra;
import main.objects.Specialnost;
import main.otherClass.DialogLib;

/**
 * Created by kills on 23.01.2017.
 */
public class ControllerSpec {
    Main mainApp;

    @FXML
    private ChoiceBox<String> choiceBoxFakultet;
    @FXML
    private ChoiceBox<String> choiceBoxKafedra;
    @FXML
    private ChoiceBox<String> choiceBoxFakultetFilter;
    @FXML
    private ChoiceBox<String> choiceBoxKafedraFilter;
    //fields
    @FXML
    private TextField fieldId;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldKvalif;

    @FXML
    private Button menuItemDelete;
    @FXML
    private Button menuItemCreate;
    @FXML
    private Button menuItemSave;
    @FXML
    private Button menuItemEdit;
    @FXML
    private Button menuItemCancel;

    @FXML
    private TableView<Specialnost> tableView;
    @FXML
    private TableColumn<Specialnost, String> tableColumnName;
    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        tableView.setItems(mainApp.getListSpecialnost());
        updateListFilterForFakultets();
    }

    private void updateListFilterForFakultets() {
        choiceBoxFakultetFilter.getItems().clear();
        choiceBoxFakultetFilter.getItems().add("Факультет");
        for (Fakultet fakultet : mainApp.getListFakults()) {
            choiceBoxFakultetFilter.getItems().add(fakultet.getName());
        }
        choiceBoxFakultetFilter.getSelectionModel().select(0);

        choiceBoxKafedraFilter.getItems().clear();
        choiceBoxKafedraFilter.getItems().add("Кафедра");
        choiceBoxKafedraFilter.getSelectionModel().select(0);
    }

    @FXML
    private void initialize() {
        hBox.getStyleClass().add("hbox");
        menuItemCancel.setDisable(true);
        menuItemSave.setDisable(true);
        tableColumnName.setCellValueFactory(
                cellData -> cellData.getValue().getNameProperty());

        showSpecialnostDetails(null);

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSpecialnostDetails(newValue));

        choiceBoxFakultet.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSpecialnostBoxFakultetDetails(newValue));
        choiceBoxFakultetFilter.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBoxFakultetDetails(newValue));
        choiceBoxKafedraFilter.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBoxKafedraDetails(newValue));
    }

    @FXML
    private void handlerSaveB() {
        if (ErrorIfFieldsClear(new TextField[]{fieldName, fieldKvalif})) return;
        if (choiceBoxFakultet.getSelectionModel().getSelectedIndex() == -1 ||
                choiceBoxKafedra.getSelectionModel().getSelectedIndex() == -1) {
            new DialogLib.Warning(
                    "Ошибка!",
                    null,
                    "Невыбрана факультет или кафедра!!");
            return;
        }
        if (fieldId.getText().isEmpty()) {
            for (Kafedra kafedra : mainApp.getListKafedra()) {
                if (kafedra.getName().equals(choiceBoxKafedra.getSelectionModel().getSelectedItem())) {
                    mainApp.createNewSpecialnost(
                            kafedra.getId(),
                            fieldName.getText(),
                            fieldKvalif.getText()
                    );
                }
            }
        } else {
            for (Kafedra kafedra : mainApp.getListKafedra()) {
                if (kafedra.getName().equals(choiceBoxKafedra.getSelectionModel().getSelectedItem())) {
                    mainApp.updateSpecialnost(
                            Integer.parseInt(fieldId.getText()),
                            kafedra.getId(),
                            fieldName.getText(),
                            fieldKvalif.getText()
                    );
                }
            }
        }
        handlerCancelB();
    }

    @FXML
    private void handlerDeleteB() {
        if (ErrorIfFieldsClear(new TextField[]{fieldName, fieldKvalif})) return;
        boolean chose = new DialogLib.Confirm(
                "Предупреждение!",
                null,
                "Если вы удалите Специальность! Все студенты, специальности будут удалены!!!"
        ).checkConfirm();
        if (chose) {
            //удаление обучения, студентов, специальностей,кафедр!
            mainApp.deleteLearningAndStudentAndSpecialnost(fieldId.getText());
            tableView.setItems(mainApp.getListSpecialnost());
        }
    }

    @FXML
    private void handlerEditB() {
        if (ErrorIfFieldsClear(new TextField[]{fieldName, fieldKvalif})) return;
        //
        vBox.setDisable(true);
        specialnostFieldsEnabled();
        //
        String nameFakultet = choiceBoxFakultet.getSelectionModel().getSelectedItem();
        String nameKafedra = choiceBoxKafedra.getSelectionModel().getSelectedItem();
        //
        choiceBoxKafedra.getItems().clear();
        choiceBoxFakultet.getItems().clear();
        choiceBoxFakultet.setDisable(false);
        choiceBoxKafedra.setDisable(false);
        //
        for (Fakultet fakultet : mainApp.getListFakults()) {
            choiceBoxFakultet.getItems().add(fakultet.getName());
        }
        choiceBoxFakultet.getSelectionModel().select(nameFakultet);
        //
        for (Kafedra kafedra : mainApp.getListKafedra()) {
            choiceBoxKafedra.getItems().add(kafedra.getName());
        }
        choiceBoxKafedra.getSelectionModel().select(nameKafedra);
        //
        menuItemCreate.setDisable(true);
        menuItemDelete.setDisable(true);
        menuItemEdit.setDisable(true);
        menuItemCancel.setDisable(false);
        menuItemSave.setDisable(false);
    }

    @FXML
    private void handlerCreateB() {
        vBox.setDisable(true);
        specialnostFieldsEnabled();
        specialnostFieldsClear();

        choiceBoxFakultet.setDisable(false);
        choiceBoxFakultet.getItems().clear();
        for (Fakultet fakultet : mainApp.getListFakults()) {
            choiceBoxFakultet.getItems().add(fakultet.getName());
        }

        menuItemCreate.setDisable(true);
        menuItemDelete.setDisable(true);
        menuItemEdit.setDisable(true);
        menuItemCancel.setDisable(false);
        menuItemSave.setDisable(false);
    }

    @FXML
    private void handlerCancelB() {

        vBox.setDisable(false);
        specialnostFieldsDisabled();

        choiceBoxKafedra.setDisable(true);
        choiceBoxFakultet.setDisable(true);

        menuItemSave.setDisable(true);
        menuItemCancel.setDisable(true);
        menuItemCreate.setDisable(false);
        menuItemDelete.setDisable(false);
        menuItemEdit.setDisable(false);

        choiceBoxFakultetFilter.getItems().clear();
        choiceBoxFakultetFilter.getItems().add("Факультет");
        for (Fakultet fakultet : mainApp.getListFakults()) {
            choiceBoxFakultetFilter.getItems().add(fakultet.getName());
        }
        choiceBoxFakultetFilter.getSelectionModel().select(0);

        choiceBoxKafedraFilter.getItems().clear();
        choiceBoxKafedraFilter.getItems().add("Кафедра");
        choiceBoxKafedraFilter.getSelectionModel().select(0);

        tableView.setItems(mainApp.getListSpecialnost());

    }

    private void showBoxKafedraDetails(String newValue) {
        if (newValue == null) return;
        if (newValue.equals("Кафедра")) {
            for (Fakultet fakultet : mainApp.getListFakults()) {
                if (choiceBoxFakultetFilter.getSelectionModel().getSelectedItem().equals(fakultet.getName())) {
                    tableView.setItems(mainApp.getListSpecialnostFromFakultet(fakultet.getId()));
                }
            }
            return;
        }
        for (Kafedra kafedra : mainApp.getListKafedra())
            if (newValue.equals(kafedra.getName())) {
                tableView.setItems(mainApp.getListSpecialnost(kafedra.getId()));
            }
    }

    private void showBoxFakultetDetails(String newValue) {
        if (newValue == null) return;
        if (newValue.equals("Факультет")) {
            tableView.setItems(mainApp.getListSpecialnost());
            choiceBoxKafedraFilter.getItems().clear();
            choiceBoxKafedraFilter.getItems().add("Кафедра");
            choiceBoxKafedraFilter.getSelectionModel().select(0);
            return;
        }
        for (Fakultet fakultet : mainApp.getListFakults()) {
            if (newValue.equals(fakultet.getName())) {
                tableView.setItems(mainApp.getListSpecialnostFromFakultet(fakultet.getId()));
                choiceBoxKafedraFilter.getItems().clear();
                choiceBoxKafedraFilter.getItems().add("Кафедра");
                ObservableList<Kafedra> kafs = mainApp.getListKafedra(fakultet.getId());
                if (kafs != null)
                    for (Kafedra kafedra : kafs) {
                        choiceBoxKafedraFilter.getItems().add(kafedra.getName());
                    }
                choiceBoxKafedraFilter.getSelectionModel().select(0);
            }
        }
    }

    private void showSpecialnostBoxFakultetDetails(String newValue) {
        if (newValue == null) return;
        choiceBoxKafedra.getItems().clear();
        ObservableList<Kafedra> kData = null;
        for (Fakultet fakultet : mainApp.getListFakults()) {
            if (choiceBoxFakultet.getSelectionModel().getSelectedItem().equals(fakultet.getName()))
                kData = mainApp.getListKafedra(fakultet.getId());
        }
        if (kData == null) return;
        for (Kafedra kafedra : kData)
            choiceBoxKafedra.getItems().add(kafedra.getName());

    }

    private void showSpecialnostDetails(Specialnost newValue) {
        if (newValue == null) {
            specialnostFieldsClear();
            return;
        }

        newValue = mainApp.getSpecialnostData(newValue.getId());

        fieldId.setText(String.valueOf(newValue.getId()));
        fieldName.setText(newValue.getName());
        fieldKvalif.setText(newValue.getKvalif());

        choiceBoxFakultet.getItems().clear();
        choiceBoxFakultet.getItems().add(mainApp.getFakultetNameFromIdSpecialnost(newValue.getId()));
        choiceBoxFakultet.getSelectionModel().select(0);
        choiceBoxKafedra.getItems().clear();
        choiceBoxKafedra.getItems().add(mainApp.getKafedraData(newValue.getIdKafedr()).getName());
        choiceBoxKafedra.getSelectionModel().select(0);
    }

    private void specialnostFieldsEnabled() {
        fieldName.setDisable(false);
        fieldKvalif.setDisable(false);
        choiceBoxKafedra.setDisable(false);
        choiceBoxFakultet.setDisable(false);
    }

    private void specialnostFieldsDisabled() {
        fieldName.setDisable(true);
        fieldKvalif.setDisable(true);
        choiceBoxKafedra.setDisable(true);
        choiceBoxFakultet.setDisable(true);
    }

    private void specialnostFieldsClear() {
        fieldId.clear();
        fieldName.clear();
        fieldKvalif.clear();
        choiceBoxFakultet.getItems().clear();
        choiceBoxKafedra.getItems().clear();
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
        mainApp.showListFakult(fieldId.getScene().getWindow());
        Stage stage = (Stage) fieldId.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlerShowListKafedra() {
        mainApp.showListKafedr(fieldId.getScene().getWindow());
        Stage stage = (Stage) fieldId.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlerShowListStudents() {
        mainApp.showMainWindowsAdmin(fieldId.getScene().getWindow());
        Stage stage = (Stage) fieldId.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handlerShowExit() {
        System.exit(0);
    }
}
