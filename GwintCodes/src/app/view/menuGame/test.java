package app.view.menuGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by kills on 02.03.2017.
 */
public class test extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        pane.setPrefSize(600,400);
        Button button = singleButton();
        button.setAlignment(Pos.CENTER);
        pane.setCenter(button);
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    private Button singleButton() {
        Button single = new Button("Одиночная");
        single.setPrefSize(500d, 100d);
        single.setOnAction(event -> {
            System.out.println(1);
        });
        return single;
    }
}
