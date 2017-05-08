package app.classes.webNetwork;

import app.classes.MainApp;
import app.classes.view.StatusWindow;
import app.classes.other.Messager;
import javafx.application.Platform;
import org.apache.log4j.Logger;

import javax.websocket.*;
import java.util.HashMap;

import static java.lang.System.*;

@ClientEndpoint
public class Client {
    Logger logger = Logger.getLogger(Client.class);

    private static Session session;


    @OnOpen
    public void onOpen(Session session) {
        out.println("Open new session: " + session.getId());
        logger.info("Open new session: " + session.getId());
        setSession(session);

        session.getAsyncRemote().sendText("Nickname|" + MainApp.getSingleton().getNickname());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("(O_O) Получил => " + message);

        HashMap map = (HashMap) MainApp.getSingleton().getRootConfig().checkCommands(message);
        if (map != null) {
            if (map.get("message") != null) System.out.println("(O_O) Oтправляет (message) => " + map.get("message"));
            session.getAsyncRemote().sendText((String) map.get("message"));
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        out.println("Close");
        Platform.runLater(() -> {
            MainApp.getSingleton().getRootConfig().clearJson();
            if (MainApp.getSingleton().getStatus().equals(StatusWindow.LOBBI)) {
                new Messager(MainApp.getSingleton().getStage()).showPopupMessage("Сервер не доступен!", 0, 2);
                MainApp.getSingleton().data.clear();
                MainApp.getSingleton().getLobbiRooms().backToMenu();
            }
            if (MainApp.getSingleton().getStatus().equals(StatusWindow.MULTI)) {
                new Messager(MainApp.getSingleton().getStage()).showPopupMessage("Сервер не доступен!", 0, 2);
                MainApp.getSingleton().data.clear();
                MainApp.getSingleton().getLogic().backToMenu();
                //не тестировалось
            }
        });
        logger.info("Close session: " + session.getId() + "\nReason: " + closeReason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable thr) {
        out.println("Error");
        thr.printStackTrace(out);
        logger.error("Error session: " + session.getId(), thr);
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        Client.session = session;
    }
}