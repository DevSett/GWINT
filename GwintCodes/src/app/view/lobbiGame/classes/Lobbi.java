package app.view.lobbiGame.classes;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * Created by kills on 03.03.2017.
 */
public class Lobbi {
    private Stage stage;
    BorderPane panel;

    public Lobbi(Stage stage) {

        this.stage = stage;
        Node node = stage.getScene().getFocusOwner();
        while (node.getParent()!=null)
        {
            node=node.getParent();

        }
        panel = (BorderPane) node;
        panel.getChildren().clear();

        stage.getScene().getStylesheets().add(getClass().getResource("lobbi.css").toExternalForm());
        lobbi();
    }

    public void lobbi() {
        TableView tableView = new TableView();
        TableColumn<String, Object> tableColumnHost = new TableColumn("Хост");
        TableColumn<String, Object> tableColumnEnemy = new TableColumn("Соперник");
        TableColumn<Circle, Object> tableColumnStatus = new TableColumn("Статус");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(tableColumnHost,tableColumnEnemy,tableColumnStatus);

        BorderPane pane = new BorderPane();

        Button buttonExit = new Button("Выход");
        Button buttonJoin = new Button("Подключится");
        Button buttonCreate = new Button("Создать");
        pane.setCenter(buttonCreate);
        pane.setLeft(buttonExit);
        pane.setRight(buttonJoin);

        panel.setCenter(tableView);
        panel.setBottom(pane);


    }


    public void updateRooms() {

    }

    public void removeLobbi(int lobbi) {

    }

    public void freeLobbi(int lobbi) {

    }
}
