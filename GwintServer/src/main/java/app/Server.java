package app;


import org.apache.log4j.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/gwent")
public class Server {
    private static CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<Session>();
    private Logger logger = Logger.getLogger(Server.class);


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("[open] " + session);
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("(O_O) Получил [" + message + "] " + session.getId());
        logger.info("(O_O) Получил [" + message + "] " + session.getId());

//
        message = session.getId() + "|" + message;
        HashMap map = main.rootConfig.checkCommands(message);
        if (map == null) return;
        System.out.println("(O_O) Oтправляет (message) => " + map.get("message"));
        logger.info("(O_O) Oтправляет (message) => " + map.get("message"));
        if (map.get("message-1") != null) {
            System.out.println("(O_O) Oтправляет (message-1) => " + map.get("message-1"));
            logger.info("(O_O) Oтправляет (message-1) => " + map.get("message-1"));

        }
        if (map.get("message-2") != null) {
            System.out.println("(O_O) Oтправляет (message-2) => " + map.get("message-2"));
            logger.info("(O_O) Oтправляет (message-2) => " + map.get("message-2"));

        } else System.out.println();
        if (map == null) return;
        if (map.get("id").equals("all")) {
            for (Session s : sessions)
                if (!session.equals(s)) {
                    s.getAsyncRemote().sendText((String) map.get("message"));
                }
            return;
        }
        if (map.get("id").equals("all-each")) {
            for (Session s : sessions)
                if (!session.equals(s)) s.getAsyncRemote().sendText((String) map.get("message"));
                else s.getAsyncRemote().sendText((String) map.get("message-2"));
            return;
        }
        if (map.get("id").equals("all-each2")) {
            for (Session s : sessions)
                if (!session.equals(s)) s.getAsyncRemote().sendText((String) map.get("message"));
                else {
                    s.getAsyncRemote().sendText((String) map.get("message-1"));
                    s.getAsyncRemote().sendText((String) map.get("message-2"));
                }
            return;
        }
        if (map.get("id").equals("first")) {
            for (Session s : sessions) {
                if (s.getId().equals(map.get("id-1"))) s.getAsyncRemote().sendText((String) map.get("message"));
            }
            return;
        }
        boolean firsted = new Random().nextBoolean();

        if (map.get("id").equals("double")) {
            for (Session s : sessions) {
                if (s.getId().equals(map.get("id-1"))) s.getAsyncRemote().sendText(message + "|" + firsted);
                if (s.getId().equals(map.get("id-2"))) s.getAsyncRemote().sendText(message + "|" + !firsted);
            }
        }
    }


    @OnClose
    public void onClose(Session session) {
        System.out.println("[close] " + session);
        logger.info("[close] " + session);
        String message = (String) main.rootConfig.checkCommands(session.getId() + "|Disconnection").get("message");
        System.out.println("(O_O) Oтправляет об отключении(message) => " + message);
        logger.info("(O_O) Oтправляет об отключении(message) => " + message);

        sessions.remove(session);
        for (Session s : sessions)
            s.getAsyncRemote().sendText(
                    message
            );
    }

}