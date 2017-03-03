package app;


import app.config.RootConfig;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * Created by kills on 25.02.2017.
 */
public class main {
static RootConfig rootConfig = new RootConfig();

    public static void main(String[] args) throws DeploymentException,
            IOException {
        Map<String, Object> properties = Collections.emptyMap();
        org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost", Integer.parseInt(args[1]), "/ws", properties,
                Server.class);
        try {
            server.start();
            System.in.read();
        } finally {
            server.stop();
        }
    }
}
