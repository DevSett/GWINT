package main.view.listKaf;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import main.objects.Fakultet;
import main.objects.Kafedra;
import main.otherClass.DialogLib;

/**
 * Created by kills on 23.01.2017.
 */
public class ControllerListKaf {
    Main mainApp;
    @FXML
    private TableView<Kafedra> tableView;
    @FXML
    private TableColumn<Kafedra, String> tableColumnName;
    @FXML
    private TextField fieldId;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldAddresses;
    @FXML
    private TextField fieldMail;
    @FXML
    private TextField fieldPhoneNumber;
    @FXML
    private TextField fieldLink;
    @FXML
    private TextField fieldMainMan;
    @FXML
    private ChoiceBox<String> choiceBoxFakult;
    @FXML
    private ChoiceBox<String> choiceBoxFakultFromId;

    @FXML
    private Button menuItemSave;
    @FXML
    private Button menuItemEdit;
    @FXML
    private Button menuItemDelete;
    @FXML
    private Button menuItemCreate;
    @FXML
    private Button menuItemCancel;
    @FXML
    private VBox vBox;

    private ObservableList<Fakultet> fakultetsData;

    private String charsFilterForNumbers = "1234567890-";
    @FXML
    private HBox hBox;


    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        //Добавление элиментов в таблицу
        tableView.setItems(mainApp.getListKafedra());
        //Добавление в фильтр факультетов
        updateListFilterForFakultets();
    }

    private void updateListFilterForFakultets() {
        choiceBoxFakult.getItems().clear();
        choiceBoxFakult.getItems().add("Факультет");
        fakultetsData = mainApp.getListFakults();
        for (Fakultet fakultet : fakultetsData)
            choiceBoxFakult.getItems().add(fakultet.getName());
        choiceBoxFakult.getSelectionModel().select(0);
    }

    @FXML
    private void initialize() {
        hBox.getStyleClass().add("hbox");
        choiceBoxFakultFromId.setStyle("-fx-opacity: 1.0;");
        tableColumnName.setCellValueFactory(
                cellData -> cellData.getValue().getNameProperty());

        showKafedrsDetails(null);

        // Listen for selection changes and show the person details when changed.
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showKafedrsDetails(newValue));

        fieldPhoneNumber.setOnKeyPressed(event -> {
            if (charsFilterForNumbers.indexOf(event.getText()) == -1) {
                new DialogLib.Warning("Ошибка!", "Некорректный символ!", "Разрешен ввод только цифр");
                if (fieldPhoneNumber.getText().length() > 0)
                    fieldPhoneNumber.setText(fieldPhoneNumber.getText());
                else fieldPhoneNumber.clear();
            }
        });

        choiceBoxFakult.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showFakultsDetails(newValue));
    }

    @FXML
    private void handleCreate() {
        vBox.setDisable(true);
        kafedraFieldEnabled();
        kafedraFieldClear();
        choiceBoxFakultFromId.setDisable(false);
        choiceBoxFakultFromId.getItems().clear();
        for (Fakultet fakultet : mainApp.getListFakults()) {
            choiceBoxFakultFromId.getItems().add(fakultet.getName());
        }
        menuItemCreate.setDisable(true);
        menuItemDelete.setDisable(true);
        menuItemEdit.setDisable(true);
        menuItemCancel.setDisable(false);
        menuItemSave.setDisable(false);
    }

    @FXML
    private void handleEdit() {
        if (ErrorIfFieldsClear(new TextField[]{fieldPhoneNumber, fieldLink, fieldMail, fieldMainMan, fieldAddresses, fieldName}))
            return;
        vBox.setDisable(true);
        kafedraFieldEnabled();
        String name = choiceBoxFakultFromId.getSelectionModel().getSelectedItem();
        choiceBoxFakultFromId.getItems().clear();
        choiceBoxFakultFromId.setDisable(false);
        for (Fakultet fakultet : mainApp.getListFakults()) {
            choiceBoxFakultFromId.getItems().add(fakultet.getName());
        }
        choiceBoxFakultFromId.getSelectionModel().select(name);
        menuItemCreate.setDisable(true);
        menuItemDelete.setDisable(true);
        menuItemEdit.setDisable(true);
        menuItemCancel.setDisable(false);
        menuItemSave.setDisable(false);
    }

    @FXML
    private void handleCancel() {

        vBox.setDisable(false);
        kafedraFieldDisbled();
        menuItemCreate.setDisable(false);
        menuItemDelete.setDisable(false);
        menuItemEdit.setDisable(false);
        menuItemCancel.setDisable(true);
        menuItemSave.setDisable(true);
        choiceBoxFakultFromId.setDisable(true);
        tableView.setItems(mainApp.getListKafedra());

    }

    @FXML
    private void handleDelete() {
        if (ErrorIfFieldsClear(new TextField[]{fieldPhoneNumber, fieldLink, fieldMail, fieldMainMan, fieldAddresses, fieldName}))
            return;
        boolean chose = new DialogLib.Confirm(
                "Предупреждение!",
                null,
                "Если вы удалите Кафедру все студенты, специальности и кафедра будут удалены!!!"
        ).checkConfirm();
        if (chose) {
            //удаление обучения, студентов, специальностей,кафедр!
            mainApp.deleteLearningAndStudentAndSpecialnostAndKafedra(fieldId.getText());
            tableView.setItems(mainApp.getListKafedra());
        }
    }

    @FXML
    private void handleSave() {
        if (ErrorIfFieldsClear(new TextField[]{fieldPhoneNumber, fieldLink, fieldMail, fieldMainMan, fieldAddresses, fieldName}))
            return;
        if (choiceBoxFakultFromId.getSelectionModel().getSelectedIndex() == -1) {
            new DialogLib.Warning(
                    "Ошибка!",
                    null,
                    "Невыбран факультет!!"
            );
            return;
        }
        menuItemCancel.setDisable(true);
        menuItemSave.setDisable(true);
        if (fieldId.getText().isEmpty()) {
            for (Fakultet fakultet : mainApp.getListFakults()) {
                if (fakultet.getName().equals(choiceBoxFakultFromId.getSelectionModel().getSelectedItem())) {
                    mainApp.createNewKafedra(
                            fakultet.getId(),
                            fieldName.getText(),
                            fieldAddresses.getText(),
                            fieldPhoneNumber.getText(),
                            fieldMail.getText(),
                            fieldLink.getText(),
                            fieldMainMan.getText()
                    );
                }
            }
        } else {
            for (Fakultet fakultet : mainApp.getListFakults()) {
                if (fakultet.getName().equals(choiceBoxFakultFromId.getSelectionModel().getSelectedItem())) {
                    mainApp.updateKafedra(
                            Integer.parseInt(fieldId.getText()),
                            fakultet.getId(),
                            fieldName.getText(),
                            fieldAddresses.getText(),
                            fieldPhoneNumber.getText(),
                            fieldMail.getText(),
                            fieldLink.getText(),
                            fieldMainMan.getText());
                }
            }
        }
        tableView.setItems(mainApp.getListKafedra());
        vBox.setDisable(false);
        kafedraFieldDisbled();
        choiceBoxFakultFromId.setDisable(true);
        menuItemCreate.setDisable(false);
        menuItemDelete.setDisable(false);
        menuItemEdit.setDisable(false);
    }

    private void showFakultsDetails(String newValue) {
        if (newValue == null)
            return;

        for (Fakultet fakultet : fakultetsData)
            if (newValue.equals(fakultet.getName()))
                tableView.setItems(mainApp.getListKafedra(fakultet.getId()));

        if (newValue.equals("Факультет"))
            tableView.setItems(mainApp.getListKafedra());

        kafedraFieldClear();
    }

    private void showKafedrsDetails(Kafedra value) {
        if (value == null) {
            kafedraFieldClear();
            return;
        }
        value = mainApp.getKafedraData(value.getId());
        fieldId.setText(String.valueOf(value.getId()));
        fieldName.setText(value.getName());
        fieldAddresses.setText(value.getAddresses());
        fieldMainMan.setText(value.getMainMan());
        fieldMail.setText(value.getMail());
        fieldLink.setText(value.getLink());
        fieldPhoneNumber.setText(value.getPhoneNumber());
        choiceBoxFakultFromId.setDisable(true);
        choiceBoxFakultFromId.getItems().clear();
        choiceBoxFakultFromId.getItems().add(mainApp.getFakultetData(value.getIdFakult()).getName());
        choiceBoxFakultFromId.getSelectionModel().select(0);
    }

    private void kafedraFieldEnabled() {
        fieldName.setDisable(false);
        fieldMail.setDisable(false);
        fieldLink.setDisable(false);
        fieldMainMan.setDisable(false);
        fieldAddresses.setDisable(false);
        fieldPhoneNumber.setDisable(false);
    }

    private void kafedraFieldDisbled() {
        choiceBoxFakultFromId.getSelectionModel().select(-1);
        fieldName.setDisable(true);
        fieldMail.setDisable(true);
        fieldLink.setDisable(true);
        fieldMainMan.setDisable(true);
        fieldAddresses.setDisable(true);
        fieldPhoneNumber.setDisable(true);
    }

    private void kafedraFieldClear() {
        choiceBoxFakultFromId.getItems().clear();
        fieldId.clear();
        fieldName.clear();
        fieldMail.clear();
        fieldLink.clear();
        fieldMainMan.clear();
        fieldAddresses.clear();
        fieldPhoneNumber.clear();
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
        mainApp.showListFakult(hBox.getScene().getWindow());
        Stage stage = (Stage) hBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlerShowListStudents() {
        mainApp.showMainWindowsAdmin(hBox.getScene().getWindow());
        Stage stage = (Stage) hBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlerShowListSpecialnost() {
        mainApp.showListSpecial(hBox.getScene().getWindow());
        Stage stage = (Stage) hBox.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handlerShowExit() {
        System.exit(0);
    }
}
