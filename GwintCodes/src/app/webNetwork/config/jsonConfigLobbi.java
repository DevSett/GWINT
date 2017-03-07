package app.webNetwork.config;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kills on 05.11.2016.
 */
class jsonConfigLobbi {
    private JSONArray array;

    protected jsonConfigLobbi() {
        array = new JSONArray();
    }

    protected static void main(String[] args) throws IOException, ParseException {
        jsonConfigLobbi base = new jsonConfigLobbi();
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
        System.out.println("json:" + array.toJSONString());

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

    public HashMap parseText(String text) {
        HashMap map = new HashMap();
        try {

            String read = text;
            if (read == null) {
                array = new JSONArray();
            } else {
                JSONParser parser = new JSONParser();
                array = (JSONArray) parser.parse(read);
            }

            map.put("createdLobbi", checkCreateLobbi());
            map.put("connectedLobbi", checkConnectedLobbi());
            return map;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object[] checkConnectedLobbi() {
        ArrayList list = new ArrayList();
        for (int index = 0; index < array.size(); index++) {
            JSONObject obs = (JSONObject) array.get(index);
            if (!obs.get("lobbiCreate").equals("-1")) list.add(obs.get("lobbiCreate"));
        }
        return list.toArray();
    }

    private Object[] checkCreateLobbi() {
        ArrayList list = new ArrayList();
        for (int index = 0; index < array.size(); index++) {
            JSONObject obs = (JSONObject) array.get(index);
            if (!obs.get("lobbiConnection").equals("-1")) list.add(obs.get("lobbiConnection"));
        }
        return list.toArray();
    }

    public int checksCreateLobbi(Object o) {

        for (int index = 0; index < array.size(); index++) {
            JSONObject obs = (JSONObject) array.get(index);
            if (obs.get("id").equals(o)) {
                return HelpClass.toInt(obs.get("lobbiCreate"));
            }
        }
        return -1;
    }

    public int checksConnectedLobbi(Object o) {

        for (int index = 0; index < array.size(); index++) {
            JSONObject obs = (JSONObject) array.get(index);
            if (obs.get("id").equals(o)) {
                return HelpClass.toInt(obs.get("lobbiConnection"));
            }
        }
        return -1;
    }
}


