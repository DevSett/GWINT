package app.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by kills on 27.02.2017.
 */
public class RootConfig {

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

        switch ((String) arrayMessage[1]) {
            case CommandGwent.NICKNAME:
            {
                configLobbi.updateNickname((String )arrayMessage[0],(String)arrayMessage[2]);
                map.put("id","all");
                map.put("message",message);
                return map;
            }
            case CommandGwent.CONNECTION: {

                addLobbi(
                        (String) arrayMessage[0],
                        null
                );
                map.put("id", arrayMessage[0]);
                map.put("message", getConfigLobbi());
                return map;

            }
            case CommandGwent.DISCONNECTION: {

                removeElementLobbi((String) arrayMessage[0]);
                map.put("id","all");
                map.put("message",message);
                return map;
            }
            case CommandGwent.CONNECTEDLOBBI: {

                updateLobbi(
                        (String) arrayMessage[0],
                        -1,
                        HelpClass.toInt(arrayMessage[2])
                );
                map.put("id","all");
                map.put("message",message);
                return map;
            }
            case CommandGwent.CREATELOBBI: {

                updateLobbi(
                        (String) arrayMessage[0],
                        HelpClass.toInt(arrayMessage[2]),
                        HelpClass.toInt(arrayMessage[2])
                );
                map.put("id","all");
                map.put("message",message);
                return map;
            }

            case CommandGwent.STARTGAME: {
                configGame.add((String) arrayMessage[0], (String) arrayMessage[2]);
                return null;
            }
            case CommandGwent.STEP: {
                map.put("id",configGame.findIdEnemy((String) arrayMessage[0]));
                map.put("message",message);
                return map;
            }
            case CommandGwent.REMOVELOBBI:
            {
               updateLobbi((String) arrayMessage[0],-1,-1);
                map.put("id","all");
                map.put("message",message);
                return map;
            }
            case CommandGwent.DISCONNECTEDLOBBI:
            {
                updateLobbi((String) arrayMessage[0],-1,-1);
                map.put("id","all");
                map.put("message",message);
                return map;
            }
            /*case CommandGwent.DAMAGE: {
                return CommandGwent.damage(arrayMessage);
            }*/
            /*case CommandGwent.EFFECT: {
                return CommandGwent.effect(arrayMessage);
            }*/

            case CommandGwent.IDCARDSHAND: {
//                return CommandGwent.idCardsHand(arrayMessage);
            }
            case CommandGwent.IDCARDSPACK: {
//                return CommandGwent.idCardsPack(arrayMessage);
            }
            case CommandGwent.IDCARDTRASH: {
//                return CommandGwent.idCardsPack(arrayMessage);
            }
            case CommandGwent.READYTOLOBBI: {
//                return CommandGwent.readyToLobbi(arrayMessage);
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

    private void updateLobbi(String id, int lobbiCo, int lobbiCr) {
        configLobbi.update(
                id,
                lobbiCo,
                lobbiCr);
    }
}
