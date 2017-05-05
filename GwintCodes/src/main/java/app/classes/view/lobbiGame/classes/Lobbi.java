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
            if (selectionIndex == -1)
                return;
            if (tableView.getItems().get(selectionIndex).getIdName().equals(MainApp.getSingleton().getRootConfig().getId())) {
                new Messager(stage).showPopupMessage("Невозможно подключится к себе", Messager.ERROR, 2, false);
                return;
            }
            if (tableView.getItems().get(selectionIndex).getIdSecondName().isEmpty()){
                MainApp.getSingleton().client.ConnecteLobbi();
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

    private void actionDisconnect() {
        MainApp.getSingleton().client.RemoveLobbi();
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

    public void freeLobbi(int lobbi) {

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
}
