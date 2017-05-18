package app.classes.webNetwork;

import app.classes.MainApp;
import app.classes.view.gamingTable.GamingCard;
import app.classes.webNetwork.config.CommandGwent;
import org.apache.log4j.Logger;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;


public class ControlClient {

    private Logger logger = Logger.getLogger(ControlClient.class);
    private String url;
    private Session session;

    public ControlClient(String fieldIp, String fieldPort) {
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
        logger.info("(O_O) Получил => " + text);
        session.getAsyncRemote().sendText(text);
    }


    public void CreateLobbi() {
        send(CommandGwent.CREATE_LOBBI.toString());
    }

    public void removeLobbi() {
        send(CommandGwent.REMOVE_LOBBI.toString());
        MainApp.getSingleton().getRootConfig().removeLobbi();
    }

    public void ConnecteLobbi(int id) {
        send(CommandGwent.CONNECTED_LOBBI.toString() + "|" + id);
    }

    public void disconnectedLobbi() {
        send(CommandGwent.DISCONNECTED_LOBBI.toString());
        MainApp.getSingleton().getRootConfig().disconnectedLobbi();
    }

    public void startGame() {
        send(CommandGwent.START_GAME.toString());
    }

    public void step(GamingCard card) {
        send(CommandGwent.STEP + "|" + card.toString() + "|-1|-1|-1|-1|-1");
    }


    public void step(GamingCard gamingCard, GamingCard gamingCard2, int finalI1, int i) {
        if (gamingCard2 != null)
            send(CommandGwent.STEP + "|" + gamingCard.toString() + "|" + gamingCard2.toString() + "|" + finalI1 + "|" + i);
        else {
            send(CommandGwent.STEP + "|" + gamingCard.toString() + "|-1|-1|-1|" + finalI1 + "|" + i);

        }
    }

    public void surrender() {
        send(CommandGwent.SURRENDER.toString());
    }

    public void endGame() {
        send(CommandGwent.END_GAME.toString());
    }
}
