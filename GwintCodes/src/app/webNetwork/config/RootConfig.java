package app.webNetwork.config;


import app.MainApp;
import app.StatusMainWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by kills on 27.02.2017.
 */
public class RootConfig {

    private jsonConfigLobbi configLobbi;
    private MainApp mainApp;

    public String getConfigLobbi() {
        return configLobbi.getJson();
    }

    public RootConfig() {
        configLobbi = new jsonConfigLobbi();
    }

    public void setConfig(String text) {
        configLobbi.parseText(text);
    }

    public HashMap checkCommands(String message) {

        Object[] arrayMessage = getToArrayMessage(message);

        HashMap map = new HashMap();

        switch ((String) arrayMessage[1]) {
            case CommandGwent.NEW_CONNECTED: {

            }
            case CommandGwent.INFO_USERS: {
                HashMap mapConfigLobbi = configLobbi.parseText((String) arrayMessage[0]);
                Object[] objectsLobbiCreated = (Object[]) mapConfigLobbi.get("createdLobbi");
                Object[] objectsLobbiConnected = (Object[]) mapConfigLobbi.get("connectedLobbi");

                for (Object objectCreat : objectsLobbiCreated) {
                    mainApp.listCreatedLobbi.add(objectCreat);
                }
                for (Object objectConn : objectsLobbiConnected) {
                    mainApp.listConnectedLobbi.add(objectConn);
                }

                lobbiUpdate();

            }
            case CommandGwent.DISCONNECTED: {
                int lobbi;
                if (mainApp.status.equals(StatusMainWindow.LOBBI)) {
                    if ((lobbi = configLobbi.checksCreateLobbi(arrayMessage[0])) != -1) {
                        mainApp.lobbiRooms.removeLobbi(lobbi);
                        mainApp.listCreatedLobbi.remove(lobbi);
                    }
                    if ((lobbi = configLobbi.checksConnectedLobbi(arrayMessage[0])) != -1) {
                        mainApp.lobbiRooms.freeLobbi(lobbi);
                        mainApp.listConnectedLobbi.remove(lobbi);
                    }
                }
            }
            case CommandGwent.STEP: {
            }
            case CommandGwent.CREATE_LOBBI: {

                mainApp.listCreatedLobbi.add(arrayMessage[0]);

                lobbiUpdate();
            }
            case CommandGwent.CONNECTED_LOBBI: {
                mainApp.listConnectedLobbi.add(arrayMessage[0]);

                lobbiUpdate();
            }
            case CommandGwent.DISCONNECTED_LOBBI: {
                mainApp.listConnectedLobbi.remove(arrayMessage[0]);

                lobbiUpdate();
            }
            case CommandGwent.REMOVE_LOBBI: {
                mainApp.listCreatedLobbi.remove(arrayMessage[0]);

                lobbiUpdate();
            }
            case CommandGwent.START_GAME: {
//                mainApp.startGame();
            }
        }
        return null;
    }

    private void lobbiUpdate() {
        if (mainApp.status.equals(StatusMainWindow.OPTION)) {
            mainApp.lobbiRooms.updateRooms();
        }
    }

    private Object[] getToArrayMessage(String message) {
        ArrayList listMessage = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(message, "|");

        while (stringTokenizer.hasMoreElements()) {
            listMessage.add(stringTokenizer.nextElement());
        }
        return listMessage.toArray();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
