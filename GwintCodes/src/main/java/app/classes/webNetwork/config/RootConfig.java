package app.classes.webNetwork.config;


import app.classes.MainApp;
import app.classes.StatusMainWindow;
import app.classes.other.HelpClass;
import app.classes.view.lobbiGame.classes.LobbiItems;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import static app.classes.webNetwork.config.CommandGwent.*;

/**
 * Created by kills on 27.02.2017.
 */
public class RootConfig {

    private JsonConfigLobbi configLobbi;

    public String getConfigLobbi() {
        return configLobbi.getJson();
    }

    public RootConfig() {
        configLobbi = new JsonConfigLobbi();
    }

    public void setConfig(String text) {
        configLobbi.parseText(text);
    }

    public Map checkCommands(String message) {

        Object[] arrayMessage = getToArrayMessage(message);


        switch ((String) arrayMessage[1]) {
//            case NEW_CONNECTED: {
//
//            }
            case INFO_USERS:
                HashMap mapConfigLobbi = (HashMap) configLobbi.parseText((String) arrayMessage[0]);
                HashMap[] objectsLobbiCreated = (HashMap[]) mapConfigLobbi.get("createdLobbi");
                HashMap[] objectsLobbiConnected = (HashMap[]) mapConfigLobbi.get("connectedLobbi");
                if (objectsLobbiCreated != null)
                    for (HashMap objectCreat : objectsLobbiCreated) {
                        MainApp.getSingleton().data.addAll(
                                new LobbiItems(
                                        (String) objectCreat.get("nickname"),
                                        (String) objectCreat.get("idF"),
                                        "",
                                        "",
                                        Color.GREEN,
                                        HelpClass.StringToInt(objectCreat.get("lobbiCreate")))
                        );
                    }
                if (objectsLobbiConnected != null)
                    for (HashMap objectConn : objectsLobbiConnected) {
                        MainApp.getSingleton().data.forEach(l ->
                        {
                            if (l.id == HelpClass.StringToInt(objectConn.get("lobbiConnection"))) {
                                l.setSecondName((String) objectConn.get("nickname"));
                                l.idSecondName = (String) objectConn.get("idS");
                            }
                        });
                    }

                lobbiUpdate();
                break;

            case DISCONNECTED:
                int lobbi;
                if ((lobbi = configLobbi.checksCreateLobbi(arrayMessage[0])) != -1) {
                    int finalLobbi = lobbi;
                    MainApp.getSingleton().data.forEach(t ->
                    {
                        if (t.id == finalLobbi) MainApp.getSingleton().data.remove(t);
                    });
                }
                if ((configLobbi.getNickName(arrayMessage[0])) != null) {
                    MainApp.getSingleton().data.forEach(t ->
                    {
                        if (t.idSecondName.equals(arrayMessage[0])) {
                            t.setSecondName("");
                            t.idSecondName = "";
                        }
                    });
                }
                lobbiUpdate();
                break;

            case STEP: {
                break;
            }
            case CREATE_LOBBI:
                MainApp.getSingleton().data.add(
                        new LobbiItems(
                                configLobbi.getNickName(arrayMessage[2]),
                                (String) arrayMessage[2],
                                "",
                                "",
                                Color.GREEN,
                                HelpClass.StringToInt(arrayMessage[0])
                        )
                );

                lobbiUpdate();
                break;

            case CONNECTED_LOBBI:
                MainApp.getSingleton().data.forEach(t -> {
                    if (t.id == HelpClass.StringToInt(arrayMessage[0])) {
                        t.setSecondName((String) configLobbi.getNickName(arrayMessage[2]));
                        t.idSecondName = (String) arrayMessage[2];
                    }
                });
                lobbiUpdate();
                break;
            case DISCONNECTED_LOBBI:
                MainApp.getSingleton().data.forEach(t -> {
                    if (t.idSecondName == arrayMessage[0]) {
                        t.setSecondName("");
                        t.idSecondName = (String) "";
                    }
                });
                lobbiUpdate();
                break;

            case REMOVE_LOBBI:
                MainApp.getSingleton().data.forEach(t ->
                {
                    if (t.idName.equals(arrayMessage[0])) MainApp.getSingleton().data.remove(t);
                });
                lobbiUpdate();
                break;
//            case START_GAME: {
//                mainApp.startGame();
//            }
        }
        return null;
    }

    private void lobbiUpdate() {
        if (MainApp.getSingleton().getStatus().equals(StatusMainWindow.OPTION)) {
            MainApp.getSingleton().getLobbiRooms().updateRooms();
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

}
