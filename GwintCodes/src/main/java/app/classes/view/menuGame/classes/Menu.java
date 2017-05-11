package app.classes.view.menuGame.classes;

import app.classes.MainApp;
import app.classes.other.HelpClass;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


/**
 * Created by kills on 01.03.2017.
 */
public class Menu {

    private Stage stage;
    private BorderPane pane;
    private Button singlePlay;
    private Button multyPlay;
    private Button exit;
    private VBox vBox;
    private TextField fieldIp;
    private TextField fieldPort;
    private TextField fieldName;

    public Menu(Stage stage) {
        this.stage = stage;
        stage.setMaxHeight(1080 / MainApp.getSingleton().getDel());
        stage.setMaxWidth(1920 / MainApp.getSingleton().getDel());
        stage.setFullScreen(MainApp.getSingleton().isFullscreen());
        stage.setResizable(false);
        stage.getIcons().add(stage.getIcons().get(0));
        mainPane();
    }

    public Menu(Image image) {
        this.stage = new Stage();
        stage.setMaxHeight(1080 / MainApp.getSingleton().getDel());
        stage.setMaxWidth(1920 / MainApp.getSingleton().getDel());
        stage.setFullScreen(MainApp.getSingleton().isFullscreen());
        stage.setResizable(false);
        stage.getIcons().add(image);

        mainPane();
    }

    private void mainPane() {
        pane = new BorderPane();
        pane.setPrefSize(stage.getMaxWidth(), stage.getMaxHeight());

        Image image = new Image(MainApp.class.getResource("/images/menuGame/background.jpg").toExternalForm());
        pane.setBackground(new Background(
                new BackgroundImage(
                        image,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(
                                1920 / MainApp.getSingleton().getDel(),
                                1080 / MainApp.getSingleton().getDel(),
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
        pane.getChildren().clear();
        pane.getStylesheets().add(MainApp.class.getResource("/css/button.css").toExternalForm());

        singlePlay = HelpClass.customButton(null, 500, 100, "single-button", event -> {
            MainApp.getSingleton().setStage(stage);
            MainApp.getSingleton().playGameTable();
        });

        multyPlay = HelpClass.customButton(null, 500, 100, "multy-button", event -> actionMultyButton());

        exit = HelpClass.customButton(null, 500, 50, "exit-button", event -> stage.close());


        vBox = new VBox(20);
        vBox.getChildren().addAll(singlePlay, multyPlay, exit);
        vBox.setAlignment(Pos.CENTER);
        pane.setCenter(vBox);

    }


    private void actionMultyButton() {
        pane.getChildren().clear();

        VBox vBox = new VBox(100);

        HBox hBox = new HBox(30);
        HBox hBox2 = new HBox(30);
        HBox hBox3 = new HBox(30);

        Button acceptMultyButton = HelpClass.customButton(null,500, 100, "button-accept", event -> actionAcceptButton());
        Button backMultyButton = HelpClass.customButton(null,500, 100, "button-back", event -> actionBackButton());
        //ждут отрисовки книпоки

        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(backMultyButton, acceptMultyButton);


        fieldIp = HelpClass.customField("Введите ip адресс", "field", 200, 40);
        fieldPort = HelpClass.customField("Введите порт", "field", 200, 40);
        fieldName = HelpClass.customField("Никнейм", "field", 200, 40);

        hBox3.getChildren().addAll(fieldName);
        hBox3.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(fieldIp, fieldPort);
        hBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(hBox, hBox3, hBox2);
        pane.setCenter(vBox);

        vBox.setAlignment(Pos.CENTER);
    }


    private void actionBackButton() {
        showButton();
    }


    private void actionAcceptButton() {
        MainApp.getSingleton().startClient(fieldIp.getText(), fieldPort.getText(), fieldName.getText(), stage);
    }

}
