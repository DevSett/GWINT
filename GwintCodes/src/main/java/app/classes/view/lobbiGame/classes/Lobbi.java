package app.classes.view.lobbiGame.classes;

import app.classes.MainApp;
import app.classes.other.Alerts;
import app.classes.other.HelpClass;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * Created by kills on 03.03.2017.
 */
public class Lobbi {
    private Stage stage;
    private BorderPane panel;
    private TableView<LobbiItems> tableView;

    public Lobbi(Stage stage) {

        this.stage = stage;
        Node node = stage.getScene().getFocusOwner();
        while (node.getParent() != null) {
            node = node.getParent();

        }
        panel = (BorderPane) node;
        panel.getChildren().clear();
        panel.getStylesheets().add(getClass().getResource("/css/button.css").toExternalForm());
        stage.getScene().getStylesheets().add(getClass().getResource("/css/lobbi.css").toExternalForm());
        lobbi();
    }

    private Button buttonFirst;
    private Button buttonSecond;
    private Button buttonThird;

    public void lobbi() {

        TableColumn<LobbiItems, String> tableColumnHost = new TableColumn<>("Хост");
        TableColumn<LobbiItems, String> tableColumnEnemy = new TableColumn<>("Соперник");
        TableColumn<LobbiItems, Circle> tableColumnStatus = new TableColumn<>("Статус");

        tableColumnHost.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnEnemy.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("statusCircle"));

        tableView = new TableView<>();
        tableView.getColumns().addAll(tableColumnHost, tableColumnEnemy, tableColumnStatus);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        buttonFirst = buttonBack(new Button());
        buttonSecond = buttonCreate(new Button());
        buttonThird = buttonJoin(new Button());


        BorderPane pane = new BorderPane();
        pane.setCenter(buttonSecond);
        pane.setLeft(buttonFirst);
        pane.setRight(buttonThird);
        pane.setPadding(new Insets(10,10,10,10));
        panel.setCenter(tableView);
        panel.setBottom(pane);


    }

    public Button buttonBack(Button button) {
        return HelpClass.customButton(button, 200, 40, "button-back", event -> {
            MainApp.getSingleton().client.stop();
            MainApp.getSingleton().menuGame(stage);
        });
    }


    private Button buttonJoin(Button button) {
        return HelpClass.customButton(button, 200, 40, "button-accept", event -> {
            int selectionIndex = tableView.getSelectionModel().getSelectedIndex();
            if (selectionIndex == -1) {
                HelpClass.alert("Выберете лобби или создайте!",null,null,panel);

//                new Messager(stage).showPopupMessage("Выберете лобби или создайте!", Messager.ERROR, 2, false);
                return;
            }
            if (tableView.getItems().get(selectionIndex).getIdName().equals(MainApp.getSingleton().getRootConfig().getId())) {
                HelpClass.alert("Невозможно подключится к себе!",null,null,panel,Alerts.ERROR);
//                new Messager(stage).showPopupMessage("Невозможно подключится к себе", Messager.ERROR, 2, false);
                return;
            }
            if (tableView.getItems().get(selectionIndex).getIdSecondName().isEmpty()) {
                MainApp.getSingleton().client.ConnecteLobbi(tableView.getItems().get(selectionIndex).getId());
                HelpClass.alert("Успешное подключение!",null,null,panel,Alerts.SUCCESS);
//                new Messager(stage).showPopupMessage("Подключение", Messager.INFO, 2, true);
            } else {
                HelpClass.alert("Лобби занято!",null,null,panel,Alerts.ERROR);
//                new Messager(stage).showPopupMessage("Лобби занято!", Messager.ERROR, 2, false);
            }
        });
    }

    private Button buttonCreate(Button button) {
        return HelpClass.customButton(button, 200, 40, "button-create", event -> {
            createLobbi();
            HelpClass.alert("Создание и подключение к лобби",null,null,panel);

//            new Messager(stage).showPopupMessage("Создание и подключение к лобби", Messager.INFO, 2);
        });
    }

    private Button buttonDisconnect(Button button) {
        return HelpClass.customButton(button, 200, 40, "button-disconnect", event -> actionDisconnect());
    }

    private Button buttonDisconnect2(Button button) {
        return HelpClass.customButton(button, 200, 40, "button-disconnect", event -> {
            actionDisconnectLobbi();
            HelpClass.alert("Отключение",null,null,panel,Alerts.ERROR);
//            new Messager(stage).showPopupMessage("Отключение", Messager.INFO, 2, true);
        });
    }

    private Button buttonStart(Button button) {
        return HelpClass.customButton(button, 200, 40, "button-start", event -> {
            for (LobbiItems lobbiItems : tableView.getItems()) {
                if (lobbiItems.getIdName().equals(MainApp.getSingleton().getRootConfig().getId())) {
                    if (lobbiItems.getStatusCircle().getFill().equals(Color.GREEN)) {
                        HelpClass.alert("Лобби пустое",null,null,panel, Alerts.ERROR);
//                        new Messager(stage).showPopupMessage("Лобби пустое", Messager.ERROR, 2, false);
                    } else {
                        MainApp.getSingleton().client.startGame();
                    }
                }
            }
        });
    }

    private void actionDisconnectLobbi() {
        MainApp.getSingleton().client.disconnectedLobbi();
        for (LobbiItems lobbiItems : tableView.getItems()) {
            if (lobbiItems.getIdSecondName().equals(MainApp.getSingleton().getRootConfig().getId())) {
                lobbiItems.setSecondName("");
                lobbiItems.setIdSecondName("");
                lobbiItems.setColor(Color.GREEN);
            }
        }
        animatedDeletedLobbi();
    }

    private void actionDisconnect() {
        MainApp.getSingleton().client.removeLobbi();
        for (LobbiItems lobbiItems : tableView.getItems()) {
            if (lobbiItems.getIdName().equals(MainApp.getSingleton().getRootConfig().getId())) {
                tableView.getItems().remove(lobbiItems);
                break;
            }
        }
        buttonCreate(buttonSecond);
        buttonJoin(buttonThird);
    }


    public void backToMenu() {
        MainApp.getSingleton().menuGame(stage);
    }

    public void updateRooms() {
        tableView.setItems(MainApp.getSingleton().data);

    }

    public void removeLobbi(int lobbi) {
        for (LobbiItems lobbiItems : tableView.getItems()) {
            if (lobbiItems.getId() == lobbi) {
                tableView.getItems().remove(lobbiItems);
            }
        }
    }


    public void animateCreateLobbi() {
        Platform.runLater(() -> {
            buttonDisconnect(buttonSecond);
            buttonStart(buttonThird);
        });
    }

    public void createLobbi() {
        MainApp.getSingleton().client.CreateLobbi();

    }

    public void animateConnectedLobbi() {

        Platform.runLater(() -> {
            buttonSecond.setVisible(false);
            buttonDisconnect2(buttonThird);
        });
    }

    public void animatedDeletedLobbi() {
        if (!buttonThird.getId().equals("button-start"))
            Platform.runLater(() -> {
                buttonSecond.setVisible(true);
                buttonJoin(buttonThird);
            });
    }

    public void startGame(boolean check) {
        MainApp.getSingleton().getLogic().initGamingTable(stage, check);
    }
}
