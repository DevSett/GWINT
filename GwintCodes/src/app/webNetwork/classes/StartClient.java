package app.webNetwork.classes;

import app.webNetwork.config.RootConfig;

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
public class StartClient implements Runnable {
    static final CountDownLatch latch = new CountDownLatch(1);

    private String fieldIp, fieldPort, fieldName;
    private String url;
    private Session session;

    public StartClient(String fieldIp, String fieldPort, String fieldName) {
        RootConfig rootConfig = new RootConfig();

        this.fieldIp = fieldIp;
        this.fieldPort = fieldPort;
        this.fieldName = fieldName;
        url = "ws://" + fieldIp + ":" + fieldPort + "/ws/gwent";

        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {
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

    public void send(String text)
    {
        session.getAsyncRemote().sendText(text);
    }

}
