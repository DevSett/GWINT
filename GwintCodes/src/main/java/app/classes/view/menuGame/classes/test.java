package app.classes.view.menuGame.classes;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by kills on 02.03.2017.
 */
public class test extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        pane.setPrefSize(600,400);
        VBox hBox = new VBox(10);

        Button button = multyButton();
        button.setAlignment(Pos.CENTER);
        Button button1 = singleButton();
        button.setAlignment(Pos.CENTER);
        Button button2 = exitButton();
        button2.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(button,button1,button2);
        hBox.setAlignment(Pos.CENTER);

        pane.setCenter(hBox);
        Image image = new Image(getClass().getResource("/images/menuGame/background.jpg").toExternalForm());
        pane.setBackground(new Background(
                new BackgroundImage(
                        image,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(
                                1920 ,
                                1080 ,
                                true,
                                true,
                                true,
                                true))));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/css/button.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private Button singleButton() {
        Button single = new Button();
        single.setId("single-button");
        single.setPrefSize(300d, 60d);
        single.setOnAction(event -> {
        });
        return single;
    }
    private Button exitButton() {
        Button exit = new Button();
        exit.setId("exit-button");
        exit.setPrefSize(300d, 30d );
        return exit;
    }
    public static void main(String[] args) {
        launch(args);
    }
    private Button multyButton() {
        Button single = new Button();
        single.setId("multy-button");
//        single.setStyle("-fx-background-size: " +300d+ " "+ 60d + ";");
        single.setPrefSize(300d, 60d);
        single.setOnAction(event -> {
            System.out.println(1);
        });
        return single;
    }
}
