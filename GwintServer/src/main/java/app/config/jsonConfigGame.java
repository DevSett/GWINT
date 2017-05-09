package app.config;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;


class jsonConfigGame {
    private JSONArray array;

    public static void main(String[] args) throws IOException, ParseException {
        jsonConfigGame base = new jsonConfigGame();
    }

    public jsonConfigGame() {
        array = new JSONArray();
    }


    public boolean add(String id1, String id2
                          /*int life, int strong1, int strong2, int strong3,
                          int lifeWall1, int lifeWall2, int lifeWall3,
                          int idCardsHand[], int idCardsTrash[], int idCardsPack[]*/) {
        boolean checkRep = findId(id1);
        boolean checkRep2 = findId(id2);
        if (!checkRep && !checkRep2) {
            JSONObject obs = new JSONObject();
            obs.put("id1", id1);
            obs.put("id2", id2);
            /*obs.put("nickname1", nickName1);
            obs.put("nickname2", nickName2);
            obs.put("life1", life1);
            obs.put("life2", life2);
            obs.put("Strong1F", strong1E);
            obs.put("Strong2F", strong2E);
            obs.put("Strong3F", strong3E);
            obs.put("Strong1E", strong1F);
            obs.put("Strong2E", strong2F);
            obs.put("Strong3E", strong3F);

            obs.put("LifeWall1", lifeWall1E);
            obs.put("LifeWall2", lifeWall2E);
            obs.put("LifeWall3", lifeWall3E);

            obs.put("LifeWall1", lifeWall1F);
            obs.put("LifeWall2", lifeWall2F);
            obs.put("LifeWall3", lifeWall3F);

            obs.put("cardsHand", idCardsHandE);
            obs.put("cardsTrash", idCardsTrashE);
            obs.put("cardsPack", idCardsPackE);

            obs.put("cardsHand", idCardsHandF);
            obs.put("cardsTrash", idCardsTrashF);
            obs.put("cardsPack", idCardsPackF);*/


            array.add(obs);

            return true;
        }
//            update(id, nickName, life, strong1, strong2, strong3, lifeWall1, lifeWall2, lifeWall3, idCardsHand, idCardsTrash, idCardsPack);
        return false;

    }

 /*   public boolean update(String id, String nickName,
                           int life, int strong1, int strong2, int strong3, int lifeWall1, int lifeWall2, int lifeWall3,
                           int idCardsHand[], int idCardsTrash[], int idCardsPack[]) throws IOException {
        boolean checkIs = findId(id);
        if (checkIs) {
            for (int indexParseJs = 0; indexParseJs < array.size(); indexParseJs++) {
                JSONObject obs = (JSONObject) array.get(indexParseJs);
                if (id.equals(obs.get("id"))) {
                    obs.replace("nickname", nickName);
                    obs.replace("life", life);

                    obs.replace("Strong1", strong1);
                    obs.replace("Strong2", strong2);
                    obs.replace("Strong3", strong3);

                    obs.replace("LifeWall1",lifeWall1);
                    obs.replace("LifeWall2",lifeWall2);
                    obs.replace("LifeWall3",lifeWall3);

                    obs.replace("idCardsHand",idCardsHand);
                    obs.replace("idCardsTrash",idCardsTrash);
                    obs.replace("idCardsPack",idCardsPack);

                    array.set(indexParseJs, obs);
                }
            }
            return true;
        } else {
            add(id, nickName,life, strong1,strong2,strong3,lifeWall1,lifeWall2,lifeWall3,idCardsHand,idCardsTrash,idCardsPack);
            return false;
        }
    }*/

    public boolean findId(String id) {
        for (int indexParseJs = 0; indexParseJs < array.size(); indexParseJs++) {
            JSONObject obs = (JSONObject) array.get(indexParseJs);
            if (id.equals(obs.get("id1"))) return true;
            if (id.equals(obs.get("id2"))) return true;

        }
        return false;
    }

    public String findIdEnemy(String id) {
        for (int indexParseJs = 0; indexParseJs < array.size(); indexParseJs++) {
            JSONObject obs = (JSONObject) array.get(indexParseJs);
            if (id.equals(obs.get("id1"))) return (String) obs.get("id2");
            if (id.equals(obs.get("id2"))) return (String) obs.get("id1");
        }
        return null;
    }

    public boolean remove(String id) {
        boolean check = findId(id);
        if (check) {
            array.remove(indexFind(id));
            return true;
        }
        return false;
    }

    public int indexFind(String id) {
        boolean checkIs = findId(id);
        if (checkIs) {
            for (int indexParseJs = 0; indexParseJs < array.size(); indexParseJs++) {
                JSONObject obs = (JSONObject) array.get(indexParseJs);
                if (obs.get("id1").equals(id)) return indexParseJs;
                if (obs.get("id2").equals(id)) return indexParseJs;
            }
        }
        return -1;
    }

    public String getJson() {
        return array.toJSONString();
    }
}


