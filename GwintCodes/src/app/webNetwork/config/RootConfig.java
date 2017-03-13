package app.webNetwork.config;


import app.MainApp;
import app.StatusMainWindow;
import app.view.lobbiGame.classes.LobbiItems;
import javafx.scene.paint.Color;

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
                HashMap[] objectsLobbiCreated = (HashMap[]) mapConfigLobbi.get("createdLobbi");
                HashMap[] objectsLobbiConnected = (HashMap[]) mapConfigLobbi.get("connectedLobbi");
                if (objectsLobbiCreated != null)
                    for (HashMap objectCreat : objectsLobbiCreated) {
                        mainApp.data.addAll(
                                new LobbiItems(
                                        (String) objectCreat.get("nickname"),
                                        (String) objectCreat.get("idF"),
                                        "",
                                        "",
                                        Color.GREEN,
                                        HelpClass.toInt(objectCreat.get("lobbiCreate")))
                        );
                    }
                if (objectsLobbiConnected != null)
                    for (HashMap objectConn : objectsLobbiConnected) {
                        mainApp.data.forEach(l ->
                        {
                            if (l.id == HelpClass.toInt(objectConn.get("lobbiConnection"))) {
                                l.setSecondName((String) objectConn.get("nickname"));
                                l.idSecondName = (String) objectConn.get("idS");
                            }
                        });
                    }

                lobbiUpdate();

            }
            case CommandGwent.DISCONNECTED: {
                int lobbi;
                if ((lobbi = configLobbi.checksCreateLobbi(arrayMessage[0])) != -1) {
                    int finalLobbi = lobbi;
                    mainApp.data.forEach(t ->
                    {
                        if (t.id == finalLobbi) mainApp.data.remove(t);
                    });
                }
                String name;
                if ((name = configLobbi.getNickName(arrayMessage[0])) != null) {
                    mainApp.data.forEach(t ->
                    {
                        if (t.idSecondName.equals(arrayMessage[0])) {
                            t.setSecondName("");
                            t.idSecondName = "";
                        }
                    });
                }
                lobbiUpdate();
            }
            case CommandGwent.STEP: {
            }
            case CommandGwent.CREATE_LOBBI: {
                mainApp.data.add(new LobbiItems(configLobbi.getNickName(arrayMessage[2]), (String) arrayMessage[2], "", "", Color.GREEN, HelpClass.toInt(arrayMessage[0])));
                lobbiUpdate();
            }
            case CommandGwent.CONNECTED_LOBBI: {
                mainApp.data.forEach(t -> {
                    if (t.id == HelpClass.toInt(arrayMessage[0])) {
                        t.setSecondName((String) configLobbi.getNickName(arrayMessage[2]));
                        t.idSecondName = (String) arrayMessage[2];
                    }
                });
                lobbiUpdate();
            }
            case CommandGwent.DISCONNECTED_LOBBI: {
                mainApp.data.forEach(t -> {
                    if (t.idSecondName == arrayMessage[0]) {
                        t.setSecondName("");
                        t.idSecondName = (String) "";
                    }
                });
                lobbiUpdate();
            }
            case CommandGwent.REMOVE_LOBBI: {
                mainApp.data.forEach(t ->
                {
                    if (t.idName.equals(arrayMessage[0])) mainApp.data.remove(t);
                });
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
