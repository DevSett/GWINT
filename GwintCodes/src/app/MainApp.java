package app;

import app.view.lobbiGame.classes.Lobbi;
import app.view.menuGame.classes.Menu;
import app.view.optionGame.classes.OptionController;
import app.webNetwork.classes.StartClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by kills on 01.03.2017.
 */
public class MainApp extends Application {

    private Stage stage;
    private Menu menu;
    public static boolean fullscreen;
    public static double del;
    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;
        try {
            optionPane();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void optionPane() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/optionGame/classes/option.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        OptionController opt = loader.getController();
        Image image = new Image(getClass().getResource("view/gamingTable/images/tableTimes.png").toExternalForm());
        opt.setMainApp(this, image);

        stage = new Stage();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/hearts.png")));
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void playGame(double del, boolean selected) {
        this.del = del;
        fullscreen=selected;
        Menu menu = new Menu(stage.getIcons().get(0));
        menu.setMainApp(this);
    }

    public void menuGame()
    {
        Menu menu = new Menu(stage.getIcons().get(0));
        menu.setMainApp(this);
    }
    public void lobbi(String fieldIp, String fieldPort, String fieldName,Stage stage) {
        Lobbi lobbi = new Lobbi(stage);
        StartClient client = new StartClient(fieldIp,fieldPort,fieldName);
    }
}
