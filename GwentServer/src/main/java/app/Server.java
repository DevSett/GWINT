package app;


import javax.websocket.*;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/gwent")
public class Server {
    private static CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<Session>();

    @OnOpen
    public void onOpen(Session session) {

        sessions.add(session);
        System.out.println("[open] " + session);

    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("[" + message + "] " + session);
        for(Session s : sessions)
            if(!session.equals(s))s.getAsyncRemote().sendText(message);
    }

    @OnClose
    public void onClose(Session session) {

        sessions.remove(session);
        System.out.println("[close] " + session);
    }
}