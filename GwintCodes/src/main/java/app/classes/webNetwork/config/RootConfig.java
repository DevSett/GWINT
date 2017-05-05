package app.classes.webNetwork.config;


import app.classes.MainApp;
import app.classes.view.StatusWindow;
import app.classes.other.HelpClass;
import app.classes.view.lobbiGame.classes.LobbiItems;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by kills on 27.02.2017.
 */
public class RootConfig {

    private JsonConfigLobbi configLobbi;
    private String id;

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

        CommandGwent commandGwent = null;
        for (CommandGwent commandGwentT : CommandGwent.values()) {
            if (commandGwentT.toString().equals(arrayMessage[1])) commandGwent = commandGwentT;
        }

        if (commandGwent == null) return null;

        switch (commandGwent) {
            case NICKNAME: {
                configLobbi.add((String) arrayMessage[0], (String) arrayMessage[2], -1, -1);
                return null;
            }
            case INFO_USERS:
                HashMap mapConfigLobbi = (HashMap) configLobbi.parseText((String) arrayMessage[0]);
                HashMap[] objectsLobbiCreated = (HashMap[]) mapConfigLobbi.get("createdLobbi");
                HashMap[] objectsLobbiConnected = (HashMap[]) mapConfigLobbi.get("connectedLobbi");
                if (objectsLobbiCreated != null)
                    for (HashMap objectCreat : objectsLobbiCreated) {
                        Platform.runLater(() -> {
                            MainApp.getSingleton().data.addAll(
                                    new LobbiItems(
                                            (String) objectCreat.get("nickname"),
                                            (String) objectCreat.get("idF"),
                                            "",
                                            "",
                                            Color.GREEN,
                                            HelpClass.LongToInt(objectCreat.get("lobbiCreate")))
                            );
                        });
                    }
                if (objectsLobbiConnected != null)
                    for (HashMap objectConn : objectsLobbiConnected) {
                        MainApp.getSingleton().data.forEach(l ->
                        {
                            if (l.getId() == HelpClass.StringToInt(objectConn.get("lobbiConnection"))) {
                                l.setSecondName((String) objectConn.get("nickname"));
                                l.setIdSecondName((String) objectConn.get("idS"));
                            }
                        });
                    }

                lobbiUpdate();
                break;
            case ID_USER:
                setId((String) arrayMessage[0]);
                break;
            case SUCCESS_CREATE_LOBBI:
                System.out.println(getId());
                MainApp.getSingleton()
                        .data
                        .addAll(
                                new LobbiItems(
                                        "Я",
                                        getId(),
                                        "",
                                        "",
                                        Color.GREEN,
                                        HelpClass.StringToInt(arrayMessage[0])
                                )
                        );
                MainApp.getSingleton().getLobbiRooms().animateCreateLobbi();
                lobbiUpdate();
                break;
            case DISCONNECTED:
                int lobbi;
                if ((lobbi = configLobbi.checksCreateLobbi(arrayMessage[0])) != -1) {
                    int finalLobbi = lobbi;
                    MainApp.getSingleton().data.forEach(t ->
                    {
                        if (t.getId() == finalLobbi) MainApp.getSingleton().data.remove(t);
                    });
                }
                if ((configLobbi.getNickName(arrayMessage[0])) != null) {
                    MainApp.getSingleton().data.forEach(t ->
                    {
                        if (t.getIdSecondName().equals(arrayMessage[0])) {
                            t.setSecondName("");
                            t.setIdSecondName("");
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
                                configLobbi.getNickName(arrayMessage[0]),
                                (String) arrayMessage[0],
                                "",
                                "",
                                Color.GREEN,
                                HelpClass.StringToInt(arrayMessage[2])
                        )
                );

                lobbiUpdate();
                break;

            case CONNECTED_LOBBI:
                MainApp.getSingleton().data.forEach(t -> {
                    if (t.getId() == HelpClass.StringToInt(arrayMessage[0])) {
                        t.setSecondName((String) configLobbi.getNickName(arrayMessage[2]));
                        t.setIdSecondName((String) arrayMessage[2]);
                    }
                });
                lobbiUpdate();
                break;
            case DISCONNECTED_LOBBI:
                MainApp.getSingleton().data.forEach(t -> {
                    if (t.getIdSecondName() == arrayMessage[0]) {
                        t.setSecondName("");
                        t.setIdSecondName((String) "");
                    }
                });
                lobbiUpdate();
                break;

            case REMOVE_LOBBI:
                System.out.println(arrayMessage[0]);
                MainApp.getSingleton().data.forEach(t ->
                {
                    System.out.println(t.getIdName());
                    if (t.getIdName().equals(arrayMessage[0])) Platform.runLater(() -> {
                        MainApp.getSingleton().data.remove(t);
                    });
                });
                lobbiUpdate();
                break;

            default:
                System.out.println("(O_O)!uncnown command!(O_O) ==> " + commandGwent);
        }
        return null;
    }


    private void lobbiUpdate() {
        if (MainApp.getSingleton().getStatus().equals(StatusWindow.LOBBI)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
