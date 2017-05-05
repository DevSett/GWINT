package app.classes.webNetwork.config;


import app.classes.other.HelpClass;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * Created by kills on 05.11.2016.
 */


class JsonConfigLobbi {
    Logger logger = Logger.getLogger(JsonConfigLobbi.class);
    private JSONArray array;

    protected JsonConfigLobbi() {
        array = new JSONArray();
    }

    protected boolean add(String id, String nickName, int lobbiCreate, int lobbiConnection) {
        boolean checkRep = findId(id);
        if (!checkRep) {
            JSONObject obs = new JSONObject();
            obs.put("id", id);
            obs.put("nickname", nickName);
            obs.put("lobbiCreate", lobbiCreate);
            obs.put("lobbiConnection", lobbiConnection);
            array.add(obs);

            return true;
        } else {
            update(id, lobbiCreate, lobbiConnection);
            return false;
        }
    }

    protected boolean update(String id, int lobbiCreate, int lobbiConnection) {
        boolean checkIs = findId(id);
        if (checkIs) {
            for (int indexParseJs = 0; indexParseJs < array.size(); indexParseJs++) {
                JSONObject obs = (JSONObject) array.get(indexParseJs);
                if (id.equals(obs.get("id"))) {
                    obs.replace("lobbiCreate", lobbiCreate);
                    obs.replace("lobbiConnection", lobbiConnection);
                    array.set(indexParseJs, obs);
                }
            }
            return true;
        }

        return false;

    }

    protected boolean findId(String id) {
        for (int indexParseJs = 0; indexParseJs < array.size(); indexParseJs++) {
            JSONObject obs = (JSONObject) array.get(indexParseJs);
            if (id.equals(obs.get("id"))) return true;
        }
        return false;
    }

    protected boolean remove(String id) {
        boolean check = findId(id);
        if (check) {
            array.remove(indexFind(id));
            return true;
        }
        return false;
    }

    protected int indexFind(String id) {
        boolean checkIs = findId(id);
        if (checkIs) {
            for (int indexParseJs = 0; indexParseJs < array.size(); indexParseJs++) {
                JSONObject obs = (JSONObject) array.get(indexParseJs);
                if (obs.get("id").equals(id)) return indexParseJs;
            }
        }
        return -1;
    }

    protected String getJson() {
        out.println("json:" + array.toJSONString());
        return array.toJSONString();
    }

    public boolean updateNickname(String id, String nickName) {
        for (int indexParseJs = 0; indexParseJs < array.size(); indexParseJs++) {
            JSONObject obs = (JSONObject) array.get(indexParseJs);
            if (id.equals(obs.get("id"))) {
                obs.replace("nickname", nickName);
                return true;
            }
        }
        return false;
    }

    public Map parseText(String text) {
        HashMap map = new HashMap();
        try {

            String read = text;
            if (read == null) {
                array = new JSONArray();
            } else {
                JSONParser parser = new JSONParser();
                array = (JSONArray) parser.parse(read);
            }

            map.put("connectedLobbi", checkConnectionsLobbi());
            map.put("createdLobbi", checkCreatedLobbi());
            return map;
        } catch (ParseException e) {
            logger.error("Parse Text", e);
            e.printStackTrace();
            return null;
        }
    }

    private HashMap[] checkCreatedLobbi() {
        ArrayList list = new ArrayList();
        ArrayList list2 = new ArrayList();
        ArrayList list3 = new ArrayList();
        for (int index = 0; index < array.size(); index++) {
            JSONObject obs = (JSONObject) array.get(index);
            if (!"-1".equals(obs.get("lobbiCreate").toString())) {
                list.add(obs.get("lobbiCreate"));
                list2.add(obs.get("nickname"));
                list3.add(obs.get("id"));
            }
        }
        HashMap[] maps = new HashMap[list.size()];
        for (int index = 0; index < maps.length; index++) {
            maps[index] = new HashMap();
            maps[index].put("lobbiCreate", list.get(index));
            maps[index].put("nickname", list2.get(index));
            maps[index].put("idF", list3.get(index));
        }
        return maps;
    }

    private HashMap[] checkConnectionsLobbi() {
        ArrayList list = new ArrayList();
        ArrayList list2 = new ArrayList();
        ArrayList list3 = new ArrayList();
        for (int index = 0; index < array.size(); index++) {
            JSONObject obs = (JSONObject) array.get(index);
            if (!"-1".equals(obs.get("lobbiConnection").toString()))
                list.add(obs.get("lobbiConnection"));
            list2.add(obs.get("nickname"));
            list3.add(obs.get("id"));
        }
        HashMap[] maps = new HashMap[list.size()];
        for (int index = 0; index < maps.length; index++) {
            maps[index] = new HashMap();
            maps[index].put("lobbiConnection", list.get(index));
            maps[index].put("nickname", list2.get(index));
            maps[index].put("idS", list3.get(index));
        }
        return maps;
    }

    public int checksCreateLobbi(Object id) {

        for (int index = 0; index < array.size(); index++) {
            JSONObject obs = (JSONObject) array.get(index);
            if (obs.get("id").equals(id)) {
                return HelpClass.StringToInt(obs.get("lobbiCreate"));
            }
        }
        return -1;
    }


    public String getNickName(Object id) {
        for (int index = 0; index < array.size(); index++) {
            JSONObject obs = (JSONObject) array.get(index);
            if (obs.get("id").equals(id)) {
                return (String) obs.get("nickname");
            }
        }
        return null;
    }


}



