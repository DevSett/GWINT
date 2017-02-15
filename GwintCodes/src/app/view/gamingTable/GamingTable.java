package app.view.gamingTable;
/**
 * Created by kills on 06.02.2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class GamingTable extends Application {
    private Pane pane;
    private Stage stage;
    private GamingCard enemyLider;
    private GamingCard frendlyLider;

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
        String css = this.getClass().getResource("GamingTable.css").toExternalForm();
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

        enemyLider = new GamingCard(image, 290, 533, 2);

        setAnimationOnCard(enemyLider,true);
        enemyLider.setLayoutX(10);
        enemyLider.setLayoutY(10);

        pane.getChildren().add(enemyLider);

        frendlyLider = new GamingCard(image, 290, 533, 2);

        frendlyLider.setLayoutX(10);
        frendlyLider.setLayoutY(pane.getPrefHeight() - frendlyLider.getPrefHeight() - 10);
        setAnimationOnCard(frendlyLider,false);

        pane.getChildren().addAll(frendlyLider);


        //колода(тестирование)
        GamingCard a = new GamingCard(image, 290, 533, 2);
        GamingCard b = new GamingCard(image, 290, 533, 2);
        GamingCard c = new GamingCard(image, 290, 533, 2);
        GamingCard a1 = new GamingCard(image, 290, 533, 2);
        GamingCard b1 = new GamingCard(image, 290, 533, 2);
        GamingCard c1 = new GamingCard(image, 290, 533, 2);
        GamingCard a2 = new GamingCard(image, 290, 533, 2);
        GamingCard b2 = new GamingCard(image, 290, 533, 2);
        GamingCard c2 = new GamingCard(image, 290, 533, 2);
        GamingCard a3 = new GamingCard(image, 290, 533, 2);
        GamingCard b3 = new GamingCard(image, 290, 533, 2);
        GamingCard c3 = new GamingCard(image, 290, 533, 2);

        a.setTranslateX(100);
        CardsHand cardsHand = new CardsHand(700, 200);
        AnchorPane.setBottomAnchor(cardsHand, 10.0);
        double y = (1366 - 700) / 2;
        AnchorPane.setLeftAnchor(cardsHand, y);
        AnchorPane.setRightAnchor(cardsHand, y);
        cardsHand.add(a);
        cardsHand.add(b);
        cardsHand.add(c);
        cardsHand.add(a1);
        cardsHand.add(b1);
        cardsHand.add(c1);
        cardsHand.add(a2);
        cardsHand.add(b2);
        cardsHand.add(c2);
        cardsHand.add(a3);
        cardsHand.add(b3);
        cardsHand.add(c3);
        pane.getChildren().add(cardsHand);
        //        rectangle.setLayoutX(pane.getPrefWidth()/2);
//        rectangle.setLayoutY(pane.getPrefHeight()-a.getPrefHeight()-10);
//        rectangle.getChildren().addAll(a,b,c,a1,b1,c1,a2,b2,c2,a3,b3,c3);
//        pane.getChildren().add(rectangle);

        return pane;
    }
    private void setAnimationOnCard(Pane pane,boolean up)
    {
        AnimationCard enemyAnimationCard = new AnimationCard(
                pane,
                pane.getWidth(),
                pane.getHeight(),
                up,
                2,
                0.7);
       pane.setOnMouseClicked(event -> enemyAnimationCard.animationStart());
    }

}
