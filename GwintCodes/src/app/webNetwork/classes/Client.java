package app.webNetwork.classes;

import javax.websocket.*;

@ClientEndpoint
public class Client {

    static Session session;


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open");
        this.session = session;
        session.getAsyncRemote().sendText("Nickname|KillSett1");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.printf("Response: %s%n", message);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("Close");
    }

    @OnError
    public void onError(Session session, Throwable thr) {
        System.out.println("Error");
        thr.printStackTrace(System.out);
    }
}