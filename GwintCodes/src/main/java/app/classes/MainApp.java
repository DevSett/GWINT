package app.classes;

import app.classes.other.Messager;
import app.classes.view.StatusWindow;
import app.classes.view.gamingTable.Logic;
import app.classes.view.lobbiGame.classes.Lobbi;
import app.classes.view.lobbiGame.classes.LobbiItems;
import app.classes.view.menuGame.classes.Menu;
import app.classes.view.optionGame.classes.OptionController;
import app.classes.webNetwork.StartClient;
import app.classes.webNetwork.config.RootConfig;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import javax.websocket.DeploymentException;
import java.io.IOException;

public class MainApp extends Application {


    private Logger logger = Logger.getLogger(MainApp.class);

    private StatusWindow status = StatusWindow.UNCNOWN;


    public ObservableList<LobbiItems> data = FXCollections.observableArrayList();

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

        Thread thread = new Thread(() -> {
            int i;
            try {
                while ((i = System.in.read()) != 100) {
                    if (i == 97) {
                        System.out.println(rootConfig.getConfigLobbi());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
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

        status = StatusWindow.OPTION;

    }

    public static void main(String[] args) {
        launch(args);
    }


    public void playGame(double del, boolean selected) {
        setDel(del);
        setFullscreen(selected);

        menuGame(stage.getIcons().get(0));
    }

    public void playGameTable() {
        logic.initGamingTable(stage);
    }

    public void menuGame(Stage stage) {
        menu = new Menu(stage);

        status = StatusWindow.MAIN_MENU;

    }

    public void menuGame() {
        menu = new Menu(stage);

        status = StatusWindow.MAIN_MENU;

    }

    public void menuGame(Image image) {
        menu = new Menu(image);

        status = StatusWindow.MAIN_MENU;

    }

    public StartClient client;

    public void startClient(String fieldIp, String fieldPort, String fieldName, Stage stage) {
        status = StatusWindow.LOBBI;

        this.stage = stage;
        setNickname(fieldName);
        Thread threadClient = new Thread(() -> {
            client = new StartClient(fieldIp, fieldPort);
            try {
                client.start();

            } catch (DeploymentException e) {
                logger.error("Error DE :" + e);
                e.printStackTrace();
                new Messager(stage).showPopupMessage("Ошибка подключения к серверу!", 0, 2);
            }
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
        else status = StatusWindow.MAIN_MENU;
    }

    public void lobbi() {
        status = StatusWindow.LOBBI;
        setLobbiRooms(new Lobbi(stage));
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

    public StatusWindow getStatus() {
        return status;
    }

    public void setStatus(StatusWindow status) {
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
