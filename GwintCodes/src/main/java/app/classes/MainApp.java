package app.classes;

import app.classes.view.lobbiGame.classes.Lobbi;
import app.classes.view.lobbiGame.classes.LobbiItems;
import app.classes.view.menuGame.classes.Menu;
import app.classes.view.optionGame.classes.OptionController;
import app.classes.webNetwork.StartClient;
import app.classes.webNetwork.config.RootConfig;
import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class MainApp extends Application {


    private Logger logger = Logger.getLogger(MainApp.class);

    private StatusMainWindow status = new StatusMainWindow(StatusMainWindow.UNCNOWN);


    public ObservableList<LobbiItems> data = new SimpleListProperty<>();

    private Stage stage;
    private Menu menu;
    private Lobbi lobbiRooms;

    private static MainApp singleton;

    private RootConfig rootConfig = new RootConfig();
    private double del;
    private String nickname;
    private boolean fullscreen;
    private Logic logic;

    @Override
    public void start(Stage primaryStage) throws IOException, ParseException {
        logic = new Logic();
        setSingleton(this);
        stage = primaryStage;
        try {
            optionPane();
        } catch (IOException e) {
            logger.error("Ошибка старта", e);
            e.printStackTrace();
        }

    }

    public void optionPane() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/option.fxml"));

        AnchorPane anchorPane = (AnchorPane) loader.load();

        OptionController opt = loader.getController();
        Image image = new Image(getClass().getResource("/images/gamingTable/tableTimes.png").toExternalForm());
        opt.setImage(image);

        stage = new Stage();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/ico.png")));
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();

        getStatus().set(StatusMainWindow.OPTION);
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void playGame(double del, boolean selected) {
        setDel(del);
        setFullscreen(selected);

        menuGame();

    }

    public void playGameTable() {
        logic.initGamingTable(stage);
    }

    public void menuGame() {
        menu = new Menu(stage.getIcons().get(0));

        getStatus().set(StatusMainWindow.MAINMENU);

    }

    public StartClient client;

    public void startClient(String fieldIp, String fieldPort, String fieldName, Stage stage) {
        this.stage = stage;
        setNickname(fieldName);
        Thread threadClient = new Thread(() -> {
            client = new StartClient(fieldIp, fieldPort);
            client.start();
        });
        threadClient.start();

        //экран загрузки
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error("Ошибка задержки", e);
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        //
        if (client.isAlive()) lobbi();
    }

    public void lobbi() {
        setLobbiRooms(new Lobbi(stage));
        getStatus().set(StatusMainWindow.LOBBI);
    }

    public static MainApp getSingleton() {
        return singleton;
    }

    public void setSingleton(MainApp singleton) {
        MainApp.singleton = singleton;
    }

    public RootConfig getRootConfig() {
        return rootConfig;
    }

    public void setRootConfig(RootConfig rootConfig) {
        this.rootConfig = rootConfig;
    }

    public double getDel() {
        return del;
    }

    public void setDel(double del) {
        this.del = del;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    public Lobbi getLobbiRooms() {
        return lobbiRooms;
    }

    public void setLobbiRooms(Lobbi lobbiRooms) {
        this.lobbiRooms = lobbiRooms;
    }

    public StatusMainWindow getStatus() {
        return status;
    }

    public void setStatus(StatusMainWindow status) {
        this.status = status;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Logic getLogic() {
        return logic;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }
}
