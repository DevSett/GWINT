package app;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/gwent")
public class Server {
    private static CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<Session>();



    @OnOpen
    public void onOpen(Session session) {
        System.out.println("[open] " + session);
        sessions.add(session);
        session.getAsyncRemote().sendText(
                String.valueOf(main.rootConfig.checkCommands(session.getId() + "|Connection").get("message")+"|InfoUsers")
        );
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("[" + message + "] " + session);


//
        message = session.getId() + "|" + message;
        HashMap map = main.rootConfig.checkCommands(message);
        if (map==null) return;
        if (map.get("id").equals("all")) {
            for (Session s : sessions)
                if (!session.equals(s)) s.getAsyncRemote().sendText((String) map.get("message"));
        } else {
            for (Session s : sessions) {
                if (s.getId().equals(map.get("id"))) s.getAsyncRemote().sendText((String) map.get("message"));
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("[close] " + session);
        for (Session s : sessions)
            s.getAsyncRemote().sendText(
                    String.valueOf(main.rootConfig.checkCommands(session.getId() + "|Disconnection").get("message"))
            );
    }

}