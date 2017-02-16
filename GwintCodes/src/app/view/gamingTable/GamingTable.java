package app.view.gamingTable;
/**
 * Created by kills on 06.02.2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;


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
        drawingMainElementsGamingTable();
        Scene scene = new Scene(pane);

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

    private void drawingMainElementsGamingTable() {
        pane.setPrefSize(1366, 768);

        //создание лидеров
        Image image = new Image("http://vignette3.wikia.nocookie.net/witcher/images/a/a0/290px-Tw3_gwent_card_face_Mysterious_Elf.png/revision/latest?cb=20151026174236");

        enemyLider = new GamingCard(image, 290, 533, 2);

        enemyLider.setLayoutX(10);
        enemyLider.setLayoutY(10);

        pane.getChildren().add(enemyLider);

        frendlyLider = new GamingCard(image, 290, 533, 2);

        frendlyLider.setLayoutX(10);
        frendlyLider.setLayoutY(pane.getPrefHeight() - frendlyLider.getPrefHeight() - 10);

        pane.getChildren().addAll(frendlyLider);


        //колода(тестирование)
        ArrayList<GamingCard> cards = new ArrayList<>();
        int locale = 0;
        while (cards.size() != 11) {
            cards.add(new GamingCard(image, 290, 533, 4));
            if (cards.size() != 1) {
                GamingCard card = cards.get(cards.size() - 1);
                card.setLayoutX(locale += 50);

            }
        }
        double y = (1366 - 600) / 2;
        CardsHand cardsHand = new CardsHand(600, 200);
        AnchorPane.setBottomAnchor(cardsHand, 10.0);
        AnchorPane.setLeftAnchor(cardsHand, y);
        AnchorPane.setRightAnchor(cardsHand, y);
        cardsHand.addAll(cards);

        AnimationCardTest test = new AnimationCardTest(enemyLider,2,pane.getPrefWidth(),pane.getPrefHeight(),0.15);
        enemyLider.setOnMouseClicked(event -> test.animationStart());
        pane.getChildren().add(cardsHand);
        pane.getChildren().add(test);
    }

    private void setAnimationOnCard(Pane pane) {
        AnimationCard enemyAnimationCard = new AnimationCard(
                pane,
                this.pane.getPrefWidth(),
                this.pane.getPrefHeight(),
                2,
                0.7);
        pane.setOnMouseClicked(event -> {
            if (event.getButton().name().equals("SECONDARY")) enemyAnimationCard.animationStart();
        });
    }
}
