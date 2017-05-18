package app.classes.webNetwork.config;


import app.classes.MainApp;
import app.classes.other.HelpClass;
import app.classes.view.StatusWindow;
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
                break;
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
                configLobbi.update(
                        getId(),
                        HelpClass.StringToInt(arrayMessage[0]),
                        HelpClass.StringToInt(arrayMessage[0])
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
                Platform.runLater(() -> {
                    MainApp.getSingleton().getLogic().step(arrayMessage[2], arrayMessage[3], arrayMessage[4], arrayMessage[5], arrayMessage[6], arrayMessage[7], arrayMessage[8], arrayMessage[9]);
                });

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
                configLobbi.update((String) arrayMessage[0], HelpClass.StringToInt(arrayMessage[2]), HelpClass.StringToInt(arrayMessage[2]));
                lobbiUpdate();
                break;

            case CONNECTED_LOBBI:
                MainApp.getSingleton().data.forEach(t -> {
                    if (t.getId() == HelpClass.StringToInt(arrayMessage[2])) {
                        t.setSecondName((String) configLobbi.getNickName(arrayMessage[0]));
                        t.setIdSecondName((String) arrayMessage[0]);
                        t.setColor(Color.YELLOW);
                        configLobbi.update((String) arrayMessage[0], -1, t.getId());
                    }
                });
                lobbiUpdate();
                break;
            case DISCONNECTED_LOBBI:
                MainApp.getSingleton().data.forEach(t -> {
                    if (t.getIdSecondName().equals(arrayMessage[0])) {
                        t.setSecondName("");
                        t.setIdSecondName((String) "");
                        t.setColor(Color.GREEN);
                        configLobbi.update((String) arrayMessage[0], -1, -1);
                    }
                });
                lobbiUpdate();
                break;
            case DISCONNECTION:
                if (MainApp.getSingleton().data.size() != 0)
                    MainApp.getSingleton().data.forEach(lobbiItems -> {
                        if (arrayMessage[0].equals(lobbiItems.getIdName())) {
                            Platform.runLater(() -> {
                                MainApp.getSingleton().data.remove(lobbiItems);
                            });
                        }
                        if (arrayMessage[0].equals(lobbiItems.getIdSecondName())) {
                            lobbiItems.setIdSecondName("");
                            lobbiItems.setSecondName("");
                            lobbiItems.setColor(Color.GREEN);
                        }
                    });
                if (MainApp.getSingleton().getStatus() == StatusWindow.MULTI) {
                    if (configLobbi.checkConnection((String) arrayMessage[0])) {
                        HelpClass.alert(
                                "Соперник отключился!",
                                event -> {
                                    MainApp.getSingleton().getLogic().backMenu();
                                    MainApp.getSingleton().client.stop();
                                },
                                null, MainApp.getSingleton().getLogic().getGamingTable());
                    }
                }

                configLobbi.remove(String.valueOf(arrayMessage[0]));
                lobbiUpdate();
                break;
            case REMOVE_LOBBI:
                MainApp.getSingleton().data.forEach(t ->
                {
                    if (t.getIdName().equals(arrayMessage[0])) Platform.runLater(() -> {
                        if (getId().equals(t.getIdSecondName())) {
                            configLobbi.update(getId(), -1, -1);
                        }
                        configLobbi.update(t.getIdName(), -1, -1);
                        MainApp.getSingleton().data.remove(t);
                    });
                });
                MainApp.getSingleton().getLobbiRooms().animatedDeletedLobbi();
                lobbiUpdate();
                break;
            case SUCCESS_CONNECTED_LOBBI:
                MainApp.getSingleton().data.forEach(lobbiItems -> {
                    if (lobbiItems.getId() == HelpClass.StringToInt(arrayMessage[0])) {
                        lobbiItems.setIdSecondName(getId());
                        lobbiItems.setSecondName("Я");
                        lobbiItems.setColor(Color.YELLOW);
                        configLobbi.update(getId(), -1, lobbiItems.getId());
                    }
                });
                MainApp.getSingleton().getLobbiRooms().animateConnectedLobbi();
                lobbiUpdate();
                break;
            case START_GAME:
                if (arrayMessage[2].equals("true")) {
                    MainApp.getSingleton().getLobbiRooms().startGame(true);
                    MainApp.getSingleton().getLogic().step();
                } else {
                    MainApp.getSingleton().getLobbiRooms().startGame(false);
                    MainApp.getSingleton().getLogic().stepEnemy();
                }
                break;
            case ERROR_CONNECTED_LOBBI:
                break;
            case SURRENDER:
                MainApp.getSingleton().getLogic().surrender();
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

    public void removeLobbi() {
        configLobbi.removeLobbi(id);
    }

    public void disconnectedLobbi() {
        configLobbi.disconnectedLobbi(id);
    }

    public void clearJson() {
        configLobbi.clear();
    }
}
