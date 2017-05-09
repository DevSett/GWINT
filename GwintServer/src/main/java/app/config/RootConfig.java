package app.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by kills on 27.02.2017.
 */
public class RootConfig {
    int idCreateLobbi = 0;
    private jsonConfigGame configGame;
    private jsonConfigLobbi configLobbi;

    public String getConfigGame() {
        return configGame.getJson();
    }

    public String getConfigLobbi() {
        return configLobbi.getJson();
    }

    public RootConfig() {
        configLobbi = new jsonConfigLobbi();
        configGame = new jsonConfigGame();
    }


    public HashMap checkCommands(String message) {

        Object[] arrayMessage = getToArrayMessage(message);

        HashMap map = new HashMap();

        CommandGwent commandGwent = null;
        for (CommandGwent commandGwentT : CommandGwent.values()) {
            if (commandGwentT.toString().equals(arrayMessage[1])) commandGwent = commandGwentT;
        }
        if (commandGwent == null) return null;

        switch (commandGwent) {

            case NICKNAME: {

                addLobbi(
                        (String) arrayMessage[0],
                        (String) arrayMessage[2]
                );
                map.put("id", "all-each2");
                map.put("message", message);
                map.put("message-1", arrayMessage[0] + "|IdUser");
                map.put("message-2", getConfigLobbi() + "|InfoUsers");
                return map;

            }
            case DISCONNECTION: {

                removeElementLobbi((String) arrayMessage[0]);
                removeElementGame((String) arrayMessage[0]);
                map.put("id", "all");
                map.put("message", message);
                return map;
            }
            case CONNECTED_LOBBI: {
                if (configLobbi.checkLobbi(HelpClass.toInt(arrayMessage[2]))) {
                    map.put("id", "first");
                    map.put("id-1", arrayMessage[0]);
                    map.put("message", arrayMessage[2] + "|" + CommandGwent.ERROR_CONNECTED_LOBBI);
                    return map;
                }

                updateLobbi(
                        (String) arrayMessage[0],
                        -1,
                        HelpClass.toInt(arrayMessage[2])
                );

                map.put("id", "all-each");
                map.put("message", message);
                map.put("message-2", arrayMessage[2] + "|" + CommandGwent.SUCCESS_CONNECTED_LOBBI);
                return map;
            }
            case CREATE_LOBBI: {
                updateLobbi(
                        (String) arrayMessage[0],
                        idCreateLobbi,
                        idCreateLobbi);
                map.put("id", "all-each");
                map.put("message", message + "|" + idCreateLobbi);
                map.put("message-2", idCreateLobbi++ + "|" + CommandGwent.SUCCESS_CREATE_LOBBI);
                return map;
            }
            case REMOVE_LOBBI: {
                updateLobbi((String) arrayMessage[0], -1, -1);
                map.put("id", "all");
                map.put("message", message);
                return map;
            }
            case DISCONNECTED_LOBBI: {
                updateLobbi((String) arrayMessage[0], -1, -1);
                map.put("id", "all");
                map.put("message", message);
                return map;
            }

            case START_GAME: {
                configGame.add((String) arrayMessage[0], configLobbi.getConnectedLobbi(arrayMessage[0]));
                map.put("id", "double");
                map.put("id-1", arrayMessage[0]);
                map.put("id-2", configLobbi.getConnectedLobbi(arrayMessage[0]));
                map.put("message", message);
                return map;
            }


            case STEP: {
                map.put("id", "first");
                map.put("id-1", configGame.findIdEnemy((String) arrayMessage[0]));
                map.put("message", message);
                return map;
            }

            /*case CommandGwent.DAMAGE: {
                return CommandGwent.damage(arrayMessage);
            }*/
            /*case CommandGwent.EFFECT: {
                return CommandGwent.effect(arrayMessage);
            }*/

            case IDCARDSHAND: {
//                return CommandGwent.idCardsHand(arrayMessage);
            }
            case IDCARDSPACK: {
//                return CommandGwent.idCardsPack(arrayMessage);
            }
            case IDCARDTRASH: {
//                return CommandGwent.idCardsPack(arrayMessage);
            }
            case READYTOLOBBI: {
//                return CommandGwent.readyToLobbi(arrayMessage);
            }
            default:
                System.out.println("(O_O)!uncnown command!(O_O) ==> " + commandGwent);

        }
        return null;
    }

    private void removeElementGame(String id) {
        configGame.remove(id);
    }

    private Object[] getToArrayMessage(String message) {
        ArrayList listMessage = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(message, "|");

        while (stringTokenizer.hasMoreElements()) {
            listMessage.add(stringTokenizer.nextElement());
        }
        return listMessage.toArray();
    }

    private void addLobbi(String id, String nickName) {
        configLobbi.add(
                id,
                nickName,
                -1,
                -1
        );
    }

    private void removeElementLobbi(String id) {
        configLobbi.remove(id);
    }

    private void updateLobbi(String id, int lobbiCreate, int lobbiConnection) {
        configLobbi.update(
                id,
                lobbiCreate,
                lobbiConnection);
    }
}
