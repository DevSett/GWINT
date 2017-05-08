package app.classes.view.lobbiGame.classes;

import app.classes.MainApp;
import app.classes.other.Messager;
import javafx.application.Platform;
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

        buttonFirst = buttonExit(new Button());
        buttonSecond = buttonCreate(new Button());
        buttonThird = buttonJoin(new Button());

        BorderPane pane = new BorderPane();
        pane.setCenter(buttonSecond);
        pane.setLeft(buttonFirst);
        pane.setRight(buttonThird);

        panel.setCenter(tableView);
        panel.setBottom(pane);


    }

    private Button buttonExit(Button button) {
        button.setText("Выход");
        button.setOnAction(event -> {
            MainApp.getSingleton().client.stop();
            MainApp.getSingleton().menuGame(stage);
        });
        return button;
    }

    private Button buttonJoin(Button button) {
        button.setText("Подключится");
        button.setOnAction(event -> {
            int selectionIndex = tableView.getSelectionModel().getSelectedIndex();
            if (selectionIndex == -1) {
                new Messager(stage).showPopupMessage("Выберете лобби или создайте!", Messager.ERROR, 2, false);
                return;
            }
            if (tableView.getItems().get(selectionIndex).getIdName().equals(MainApp.getSingleton().getRootConfig().getId())) {
                new Messager(stage).showPopupMessage("Невозможно подключится к себе", Messager.ERROR, 2, false);
                return;
            }
            if (tableView.getItems().get(selectionIndex).getIdSecondName().isEmpty()) {
                MainApp.getSingleton().client.ConnecteLobbi(tableView.getItems().get(selectionIndex).getId());
                new Messager(stage).showPopupMessage("Подключение", Messager.INFO, 2, true);
            } else {
                new Messager(stage).showPopupMessage("Лобби занято!", Messager.ERROR, 2, false);
                return;
            }
        });
        return button;
    }

    private Button buttonCreate(Button button) {
        button.setText("Создать");
        button.setOnAction(event -> {
            createLobbi();
            new Messager(stage).showPopupMessage("Создание и подключение к лобби", Messager.INFO, 2);
        });
        return button;
    }

    private Button buttonDisconnect(Button button) {
        button.setText("Отключится");
        button.setOnAction(event -> {
            actionDisconnect();
        });
        return button;
    }

    private Button buttonDisconnect2(Button button) {
        button.setText("Отключится");
        button.setOnAction(event -> {
            actionDisconnectLobbi();
            new Messager(stage).showPopupMessage("Отключение", Messager.INFO, 2, true);
        });
        return button;
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

    private Button buttonStart(Button button) {
        button.setText("Старт");
        button.setOnAction(event -> {
            for (LobbiItems lobbiItems : tableView.getItems()) {
                if (lobbiItems.getIdName().equals(MainApp.getSingleton().getRootConfig().getId())) {
                    if (lobbiItems.getStatusCircle().getFill().equals(Color.GREEN)) {
                        new Messager(stage).showPopupMessage("Лобби пустое", Messager.ERROR, 2, false);
                    } else {
                        MainApp.getSingleton().client.startGame();
                        MainApp.getSingleton().getLogic().initGamingTable(stage);
                    }
                }
            }
        });
        return button;
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
        Platform.runLater(() -> {
            buttonSecond.setVisible(true);
            buttonJoin(buttonThird);
        });
    }

    public void startGame() {
        MainApp.getSingleton().getLogic().initGamingTable(stage);
    }
}
