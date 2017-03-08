package app.webNetwork.classes;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

/**
 * Created by kills on 25.02.2017.
 */
public class StartClient {
    static final CountDownLatch latch = new CountDownLatch(1);

    public static String fieldIp, fieldPort, fieldName;
    private String url;
    private Session session;

    public StartClient(String fieldIp, String fieldPort, String fieldName) {

        this.fieldIp = fieldIp;
        this.fieldPort = fieldPort;
        this.fieldName = fieldName;
        url = "ws://" + fieldIp + ":" + fieldPort + "/ws/gwent";


    }

    public void start() {
        WebSocketContainer container = ContainerProvider
                .getWebSocketContainer();
        try {
            session = container.connectToServer(Client.class,
                    URI.create(url));
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isAlive() {
        if (session == null)
            return false;
        if (!session.isOpen())
            return false;
        return true;
    }

    public void send(String text) {
        session.getAsyncRemote().sendText(text);
    }


}
