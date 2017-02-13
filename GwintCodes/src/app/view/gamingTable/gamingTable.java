package app.view.gamingTable;
/**
 * Created by kills on 06.02.2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class gamingTable extends Application {
    private AnchorPane anchorPane;
    private Stage stage;
    private Pane enemyLider;
    private Pane frendlyLider;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);
        stage.show();
        mainScene();

    }

    private void mainScene() {
        anchorPane = new AnchorPane();

        String css = this.getClass().getResource("gamingTable.css").toExternalForm();
        Scene scene = new Scene(drawingMainElementsGamingTable(anchorPane));
        scene.getStylesheets().add(css);

        anchorPane.getStyleClass().add("anchorrpane");

        stage.setScene(scene);
    }

    private AnchorPane drawingMainElementsGamingTable(AnchorPane anchorPane) {
        anchorPane.setPrefSize(1366, 768);

        enemyLider = new Pane();
        
        //пока тестирую буду использовать прямые ссылки, в будущем будет изображения из проекта.
        enemyLider.setStyle("-fx-background-image: url(\"http://vignette3.wikia.nocookie.net/witcher/images/a/a0/290px-Tw3_gwent_card_face_Mysterious_Elf.png/revision/latest?cb=20151026174236\"); fx-background-repeat: no-repeat;-fx-background-size: contain;");
        enemyLider.setPrefSize(290 / 2, 533 / 2);


        /* p.setEventDispatcher(new EventDispatcher() {
            @Override
            public Event dispatchEvent(Event event, EventDispatchChain tail) {
                System.out.println(event.getTarget());
                return event;
            }
        });*/

        AnchorPane.setTopAnchor(enemyLider, 10.0);
        AnchorPane.setLeftAnchor(enemyLider, 10.0);
        anchorPane.getChildren().add(enemyLider);

        frendlyLider = new Pane();
        frendlyLider.setStyle("-fx-background-image: url(\"http://vignette3.wikia.nocookie.net/witcher/images/a/a0/290px-Tw3_gwent_card_face_Mysterious_Elf.png/revision/latest?cb=20151026174236\"); fx-background-repeat: no-repeat;-fx-background-size: contain;");
        frendlyLider.setPrefSize(290 / 2, 533 / 2);

        AnchorPane.setBottomAnchor(frendlyLider, 10.0);
        AnchorPane.setLeftAnchor(frendlyLider, 10.0);
        anchorPane.getChildren().add(frendlyLider);

        return anchorPane;
    }

}
