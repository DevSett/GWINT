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

                if (mainApp.status.equals(StatusMainWindow.OPTION)) {
                    //
                } else {
                    //
                }
            }
            case CommandGwent.DISCONNECTED: {
                //
            }
            case CommandGwent.STEP: {
                //
            }
            case CommandGwent.CREATE_LOBBI: {

                //

                if (mainApp.status.equals(StatusMainWindow.OPTION)) {
                    //
                } else {
                    //
                }
            }
            case CommandGwent.CONNECTED_LOBBI: {
                //
                if (mainApp.status.equals(StatusMainWindow.OPTION)) {
                    //
                } else {
                    //
                }
            }
            case CommandGwent.DISCONNECTED_LOBBI: {
                //
                if (mainApp.status.equals(StatusMainWindow.OPTION)) {
                    //
                } else {
                    //
                }
            }
            case CommandGwent.REMOVE_LOBBI: {
                //
                if (mainApp.status.equals(StatusMainWindow.OPTION)) {
                    //
                } else {
                    //
                }
            }
            case CommandGwent.START_GAME: {
                //
            }
        }
        return null;
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
