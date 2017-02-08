package main.view.listFakult;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import main.objects.Fakultet;
import main.otherClass.DialogLib;

/**
 * Created by kills on 23.01.2017.
 */
public class ControllerListFakult {
    @FXML
    private Button itemDeleteFakultet;
    @FXML
    private Button itemCreateFakultet;
    @FXML
    private Button itemEditFakultet;
    @FXML
    private Button itemSaveFakultet;
    @FXML
    private Button itemCancelFakultet;

    @FXML
    private TextField fieltName;
    @FXML
    private TextField fieltID;
    @FXML
    private TextField fieltAddresses;
    @FXML
    private TextField fieltMail;
    @FXML
    private TextField fieltPhoneNumber;
    @FXML
    private TextField fieltLink;
    @FXML
    private TextField fieltMainMan;
    @FXML
    private VBox vBox;
    @FXML
    private TableView<Fakultet> tableView;
    @FXML
    private TableColumn<Fakultet, String> tableColumnName;

    Main mainApp;

    private String charsFilterForNumbers = "1234567890-";
    @FXML
    private HBox hBox;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        tableView.setItems(mainApp.getListFakults());
    }

    @FXML
    private void initialize() {
        hBox.getStyleClass().add("hbox");
        tableColumnName.setCellValueFactory(
                cellData -> cellData.getValue().getNameProperty());

        showFakultetsDetails(null);

        // Listen for selection changes and show the person details when changed.
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showFakultetsDetails(newValue));


        fieltPhoneNumber.setOnKeyPressed(event -> {
            if (charsFilterForNumbers.indexOf(event.getText()) != -1)
                return;

            new DialogLib.Warning(
                    "Ошибка!",
                    "Некорректный символ!",
                    "Разрешен ввод только цифр"
            );

            if (fieltPhoneNumber.getText().length() > 0)
                fieltPhoneNumber.setText(fieltPhoneNumber.getText());
            else fieltPhoneNumber.clear();

        });

    }

    private void showFakultetsDetails(Fakultet value) {
        if (value == null) {
            fakultetFieldsClear();
            return;
        }
        value = mainApp.getFakultetData(value.getId());

        fieltPhoneNumber.setText(value.getPhoneNumber());
        fieltAddresses.setText(value.getAddresses());
        fieltID.setText(String.valueOf(value.getId()));
        fieltLink.setText(value.getLink());
        fieltMail.setText(value.getMail());
        fieltMainMan.setText(value.getMainMan());
        fieltName.setText(value.getName());
    }

    @FXML
    private void handleCancelFakultet() {
        vBox.setDisable(false);
        fakultetFieldDisabled();
        itemDeleteFakultet.setDisable(false);
        itemCreateFakultet.setDisable(false);
        itemEditFakultet.setDisable(false);
        itemSaveFakultet.setDisable(true);
        tableView.setItems(mainApp.getListFakults());

    }

    @FXML
    private void handleSaveFakultet() {

        if (ErrorIfFieldsClear(new TextField[]{fieltMainMan, fieltMail, fieltLink, fieltPhoneNumber, fieltName, fieltAddresses}))
            return;
        if (fieltID.getText().isEmpty()) {
            mainApp.createNewFakultet(
                    fieltName.getText(),
                    fieltAddresses.getText(),
                    fieltPhoneNumber.getText(),
                    fieltMail.getText(),
                    fieltLink.getText(),
                    fieltMainMan.getText()
            );
        } else {
            mainApp.updateFakultet(
                    fieltID.getText(),
                    fieltName.getText(),
                    fieltAddresses.getText(),
                    fieltPhoneNumber.getText(),
                    fieltMail.getText(),
                    fieltLink.getText(),
                    fieltMainMan.getText());
        }
        tableView.setItems(mainApp.getListFakults());
        fakultetFieldDisabled();
        vBox.setDisable(false);
        itemEditFakultet.setDisable(false);
        itemDeleteFakultet.setDisable(false);
        itemCreateFakultet.setDisable(false);
        itemCancelFakultet.setDisable(true);
        itemSaveFakultet.setDisable(true);
    }

    @FXML
    private void handleEditFakultet() {
        if (ErrorIfFieldsClear(new TextField[]{fieltMainMan, fieltMail, fieltLink, fieltPhoneNumber, fieltName, fieltAddresses}))
            return;
        itemsAndVboxDisabledAndFieldsEnabled();
    }

    @FXML
    private void handleCreateFakultet() {
        itemsAndVboxDisabledAndFieldsEnabled();
        fakultetFieldsClear();
    }

    @FXML
    private void handleDeleteFakultet() {
        if (ErrorIfFieldsClear(new TextField[]{fieltMainMan, fieltMail, fieltLink, fieltPhoneNumber, fieltName, fieltAddresses}))
            return;
        boolean chose = new DialogLib.Confirm(
                "Предупреждение!",
                null,
                "Если вы удалите Факультет все студенты, специальности и кафедры будут удалены!!!"
        ).checkConfirm();
        if (chose) {
            //удаление обучения, студентов, специальностей,кафедр,факультетов!
            mainApp.deleteLearningAndStudentAndSpecialnostAndKafersAndFakults(fieltID.getText());
            tableView.setItems(mainApp.getListFakults());
        }

    }

    private void fakultetFieldEnabled() {
        fieltPhoneNumber.setDisable(false);
        fieltAddresses.setDisable(false);
        fieltLink.setDisable(false);
        fieltMail.setDisable(false);
        fieltMainMan.setDisable(false);
        fieltName.setDisable(false);
    }

    private void fakultetFieldDisabled() {
        fieltPhoneNumber.setDisable(true);
        fieltAddresses.setDisable(true);
        fieltLink.setDisable(true);
        fieltMail.setDisable(true);
        fieltMainMan.setDisable(true);
        fieltName.setDisable(true);
    }

    private void fakultetFieldsClear() {
        fieltPhoneNumber.clear();
        fieltAddresses.clear();
        fieltID.clear();
        fieltLink.clear();
        fieltMail.clear();
        fieltMainMan.clear();
        fieltName.clear();
    }

    private void itemsAndVboxDisabledAndFieldsEnabled() {
        vBox.setDisable(true);
        fakultetFieldEnabled();
        itemDeleteFakultet.setDisable(true);
        itemCreateFakultet.setDisable(true);
        itemEditFakultet.setDisable(true);
        itemCancelFakultet.setDisable(false);
        itemSaveFakultet.setDisable(false);
    }

    private boolean ErrorIfFieldsClear(TextField[] textFields) {
        for (TextField textField : textFields)
            if (textField.getText().isEmpty()) {
                new DialogLib.Warning("Ошибка!", "Поля пустые!", "Заполните поля!!!");
                return true;
            }
        return false;
    }


    //меню бар
    @FXML
    private void handlerShowListKafedra() {
        mainApp.showListKafedr(fieltName.getScene().getWindow());
        Stage stage = (Stage) fieltName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlerShowListStudents() {
        mainApp.showMainWindowsAdmin(fieltName.getScene().getWindow());
        Stage stage = (Stage) fieltName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlerShowListSpecialnost() {
        mainApp.showListSpecial(fieltName.getScene().getWindow());
        Stage stage = (Stage) fieltName.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handlerShowExit() {
        System.exit(0);
    }
}

