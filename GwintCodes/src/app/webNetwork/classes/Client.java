package app.webNetwork.classes;

import app.MainApp;

import javax.websocket.*;
import java.util.HashMap;

@ClientEndpoint
public class Client {

    static Session session;


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open");
        this.session = session;
        session.getAsyncRemote().sendText("Nickname|"+MainApp.nickname);
    }

    @OnMessage
    public void onMessage(String message) {
        HashMap map = MainApp.rootConfig.checkCommands(message);
        if (map!=null)
        session.getAsyncRemote().sendText((String) map.get("message"));
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