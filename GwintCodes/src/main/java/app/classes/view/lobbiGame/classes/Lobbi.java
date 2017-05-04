package app.classes.view.lobbiGame.classes;

import app.classes.MainApp;
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

    /*  private final ObservableList<LobbiItems> data =
              FXCollections.observableArrayList(
                      new LobbiItems("Jacob", "", "Smith", "", Color.GREEN, 0),
                      new LobbiItems("Jacob1", "", "Smith", "", Color.RED, 1),
                      new LobbiItems("Jacob2", "", "Smith", "", Color.YELLOW, 2),
                      new LobbiItems("Jacob3", "", "Smith", "", Color.YELLOW, 3),
                      new LobbiItems("Jacob4", "", "Smith", "", Color.YELLOW, 4)
              );
  */
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

    public void lobbi() {
        tableView = new TableView();
        TableColumn tableColumnHost = new TableColumn("Хост");
        TableColumn tableColumnEnemy = new TableColumn("Соперник");
        TableColumn tableColumnStatus = new TableColumn("Статус");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableColumnHost.setCellValueFactory(new PropertyValueFactory<LobbiItems, String>("name"));
        tableColumnEnemy.setCellValueFactory(new PropertyValueFactory<LobbiItems, String>("secondName"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<LobbiItems, Circle>("statusCircle"));
        tableView.getColumns().addAll(tableColumnHost, tableColumnEnemy, tableColumnStatus);

        BorderPane pane = new BorderPane();

        Button buttonExit = new Button("Выход");
        Button buttonJoin = new Button("Подключится");
        Button buttonCreate = new Button("Создать");

        buttonCreate.setOnAction(event -> {
            MainApp.getSingleton().client.send("команда создания");
        });
        buttonExit.setOnAction(event -> {
            MainApp.getSingleton().client.stop();
            MainApp.getSingleton().menuGame(stage);
        });
        buttonJoin.setOnAction(event -> {
            if (tableView.getSelectionModel().getSelectedIndex() == -1)
                return;
            //
        });
        pane.setCenter(buttonCreate);
        pane.setLeft(buttonExit);
        pane.setRight(buttonJoin);

        panel.setCenter(tableView);
        panel.setBottom(pane);


    }


    public void updateRooms() {
        tableView.setItems(MainApp.getSingleton().data);
    }

    public void removeLobbi(int lobbi) {

    }

    public void freeLobbi(int lobbi) {

    }

    public void createLobbi() {

    }
}
