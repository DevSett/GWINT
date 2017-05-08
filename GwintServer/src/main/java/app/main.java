package app;


import app.config.RootConfig;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;


public class main {
    static RootConfig rootConfig = new RootConfig();

    public static void main(String[] args) throws DeploymentException,
            IOException {
        Map<String, Object> properties = Collections.emptyMap();
        org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost", Integer.parseInt(args[1]), "/ws", properties,
                Server.class);
        try {
            server.start();
            //d
            int i = -1;
            while ((i = System.in.read()) != 100) {
                //a
                if (i == 97) {
                    System.out.println(rootConfig.getConfigLobbi());
                }
                //b
                if (i == 98) {
                    System.out.println(rootConfig.getConfigGame());
                }
            }
            ;

        } finally {
            server.stop();
        }
    }
}
