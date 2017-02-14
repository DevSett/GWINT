package app.view.gamingTable;
/**
 * Created by kills on 06.02.2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;



public class gamingTable extends Application {
    private Pane pane;
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
        pane = new AnchorPane();

        Scene scene = new Scene(drawingMainElementsGamingTable(pane));

      /*
        String css = this.getClass().getResource("gamingTable.css").toExternalForm();
        scene.getStylesheets().add(css);
        pane.getStyleClass().add("anchorrpane");
        */

        //тестовый фон для построения макета!
        Image image = new Image(getClass().getResource("images/tableTimes.png").toExternalForm());

        pane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1366.0, 768.0, true, true, true, false))));

        stage.setScene(scene);
    }

    double size;

    private Pane drawingMainElementsGamingTable(Pane pane) {
        pane.setPrefSize(1366, 768);

        //создание лидеров
        Image image = new Image("http://vignette3.wikia.nocookie.net/witcher/images/a/a0/290px-Tw3_gwent_card_face_Mysterious_Elf.png/revision/latest?cb=20151026174236");

        enemyLider = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),false);

        enemyLider.setLayoutX(10);
        enemyLider.setLayoutY(10);

        pane.getChildren().add(enemyLider);

        frendlyLider = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);

        frendlyLider.setLayoutX(10);
        frendlyLider.setLayoutY(pane.getPrefHeight() - frendlyLider.getPrefHeight() - 10);

        pane.getChildren().add(frendlyLider);

        //колода(тестирование)
       /* HBox rectangle = new HBox();
        Pane a = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);
        Pane b = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);
        Pane c = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);
        Pane a1 = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);
        Pane b1 = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);
        Pane c1 = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);
        Pane a2 = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);
        Pane b2 = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);
        Pane c2 = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);
        Pane a3 = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);
        Pane b3 = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);
        Pane c3 = new card(image,290,533,0.7,2,pane.getPrefWidth(),pane.getPrefHeight(),true);

        rectangle.setLayoutX(pane.getPrefWidth()/2);
        rectangle.setLayoutY(pane.getPrefHeight()-a.getPrefHeight()-10);
        rectangle.getChildren().addAll(a,b,c,a1,b1,c1,a2,b2,c2,a3,b3,c3);
        pane.getChildren().add(rectangle);*/

        return pane;
    }

}
