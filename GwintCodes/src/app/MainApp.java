package app;

import app.view.optionGame.OptionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by kills on 01.03.2017.
 */
public class MainApp extends Application{



    @Override
    public void start(Stage primaryStage) throws Exception {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/optionGame/option.fxml"));

             AnchorPane anchorPane = (AnchorPane) loader.load();

            OptionController opt = loader.getController();
        Image image = new Image(getClass().getResource("view/gamingTable/images/tableTimes.png").toExternalForm());
        opt.setMainApp(this,image);

            primaryStage = new Stage();
            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


    public void playGame(double del) {
    }
}
