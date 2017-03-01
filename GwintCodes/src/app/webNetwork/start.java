package app.webNetwork;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * Created by kills on 25.02.2017.
 */
public class start {
    static final CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args)
            throws IOException, DeploymentException, InterruptedException {
        Thread client = new Thread(new Runnable() {
            @Override
            public void run() {
                WebSocketContainer container = ContainerProvider
                        .getWebSocketContainer();
                try (Session session = container.connectToServer(Client.class,
                        URI.create("ws://localhost:8765/ws/gwent"))) {
                    latch.await();
                } catch (DeploymentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread send = new Thread(new Runnable() {
            @Override
            public void run() {
                String text="";
                while (!text.equals("close")) {
                    text= new Scanner(System.in).nextLine();
                    Client.session.getAsyncRemote().sendText(text);
                }
                }
        });
        client.start();
        send.start();
    }
}
