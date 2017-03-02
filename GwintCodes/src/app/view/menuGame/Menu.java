package app.view.menuGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by kills on 01.03.2017.
 */
public class Menu {

    private Stage stage;
    private BorderPane pane;
    private double del;
    private Button singlePlay;
    private Button multyPlay;
    private Button exit;
    private VBox vBox;

    public Menu(double del, boolean fullscreen, Image image) {
        this.stage = new Stage();
        this.del = del;
        stage.setMaxHeight(1080 / this.del);
        stage.setMaxWidth(1920 / this.del);
        stage.setFullScreen(fullscreen);
        stage.setResizable(false);
        stage.getIcons().add(image);
        mainPane();
    }

    private void mainPane() {
        pane = new BorderPane();
        pane.setPrefSize(stage.getMaxWidth(), stage.getMaxHeight());

        Image image = new Image(getClass().getResource("images/background.jpg").toExternalForm());
        pane.setBackground(new Background(
                new BackgroundImage(
                        image,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(
                                1920 / del,
                                1080 / del,
                                true,
                                true,
                                true,
                                true))));


        showButton();


        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void showButton() {
        singlePlay = singleButton();
        multyPlay = multyButton();
        exit = exitButton();

        vBox = new VBox(10);
        vBox.getChildren().addAll(singlePlay, multyPlay, exit);
        vBox.setAlignment(Pos.CENTER);

        pane.setCenter(vBox);
    }

    private Button exitButton() {
        Button exit = new Button("Выход");
        exit.setPrefSize(500d/del, 50d/del);
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        return exit;
    }
    private Button multyButton() {
        Button multy = new Button("Сетевая игра");
        multy.setPrefSize(500d/del, 100d/del);
        multy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        return multy;
    }
    private Button singleButton() {
        Button single = new Button("Одиночная");
        single.setPrefSize(500d/del, 100d/del);
        single.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        return single;
    }

}
