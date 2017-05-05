package app.classes.webNetwork;

import app.classes.webNetwork.config.CommandGwent;
import org.apache.log4j.Logger;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;


public class StartClient {

    private Logger logger = Logger.getLogger(StartClient.class);
    private String url;
    private Session session;

    public StartClient(String fieldIp, String fieldPort) {
        url = "ws://" + fieldIp + ":" + fieldPort + "/ws/gwent";
    }

    public void start() throws DeploymentException {
        WebSocketContainer container = ContainerProvider
                .getWebSocketContainer();
        try {
            session = container.connectToServer(Client.class,
                    URI.create(url));
        } catch (IOException e) {
            logger.error("start IO", e);
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            session.close();
        } catch (IOException e) {
            logger.error("stop IO", e);
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


    public void CreateLobbi() {
        send(CommandGwent.CREATE_LOBBI.toString());
    }

    public void RemoveLobbi() {
        send(CommandGwent.REMOVE_LOBBI.toString());
    }

    public void ConnecteLobbi() {
        send(CommandGwent.CONNECTED_LOBBI.toString());
    }
}
