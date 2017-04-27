package app.classes.webNetwork;

import app.classes.MainApp;
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
        HashMap map = (HashMap) MainApp.getSingleton().getRootConfig().checkCommands(message);
        if (map != null)
            session.getAsyncRemote().sendText((String) map.get("message"));
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        out.println("Close");
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