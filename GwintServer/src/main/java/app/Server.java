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
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("(O_O) Получил [" + message + "] " + session.getId());


//
        message = session.getId() + "|" + message;
        HashMap map = main.rootConfig.checkCommands(message);
        System.out.println("(O_O) Oтправляет (message) => " + map.get("message"));
        if (map.get("message-1") != null) System.out.println("(O_O) Oтправляет (message-1) => " + map.get("message-1"));
        if (map.get("message-2") != null) System.out.println("(O_O) Oтправляет (message-2) => " + map.get("message-2"));

        else System.out.println();
        if (map == null) return;
        if (map.get("id").equals("all")) {
            for (Session s : sessions)
                if (!session.equals(s)) {
                    s.getAsyncRemote().sendText((String) map.get("message"));
                }
        } else if (map.get("id").equals("all-each")) {
            for (Session s : sessions)
                if (!session.equals(s)) s.getAsyncRemote().sendText((String) map.get("message"));
                else s.getAsyncRemote().sendText((String) map.get("message-2"));
        } else if (map.get("id").equals("all-each2")) {
            for (Session s : sessions)
                if (!session.equals(s)) s.getAsyncRemote().sendText((String) map.get("message"));
                else {
                    s.getAsyncRemote().sendText((String) map.get("message-1"));
                    s.getAsyncRemote().sendText((String) map.get("message-2"));
                }
        } else {
            for (Session s : sessions) {
                if (s.getId().equals(map.get("id"))) s.getAsyncRemote().sendText((String) map.get("message"));
            }
        }
    }


    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        String message = (String) main.rootConfig.checkCommands(session.getId() + "|Disconnection").get("message");
        System.out.println("[close] " + session);
        for (Session s : sessions)
            s.getAsyncRemote().sendText(
                    message
            );
    }

}