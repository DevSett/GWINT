package app.config;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by kills on 05.11.2016.
 */
class jsonConfigLobbi {
    private JSONArray array;

    public jsonConfigLobbi() {
        array = new JSONArray();
    }

    public static void main(String[] args) throws IOException, ParseException {
        jsonConfigLobbi base = new jsonConfigLobbi();
    }

    public boolean add(String id, String nickName, int lobbiCreate, int lobbiConnection) {
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

    public boolean update(String id, int lobbiCreate, int lobbiConnection) {
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

    public boolean findId(String id) {
        for (int indexParseJs = 0; indexParseJs < array.size(); indexParseJs++) {
            JSONObject obs = (JSONObject) array.get(indexParseJs);
            if (id.equals(obs.get("id"))) return true;
        }
        return false;
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
                if (obs.get("id").equals(id)) return indexParseJs;
            }
        }
        return -1;
    }

    public String getJson() {
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

    public boolean checkLobbi(int i) {
        for (int indexParseJs = 0; indexParseJs < array.size(); indexParseJs++) {
            JSONObject obs = (JSONObject) array.get(indexParseJs);
            if (i == HelpClass.toInt(obs.get("lobbiConnection")) && HelpClass.toInt(obs.get("lobbiCreate")) != i) {
                return true;
            }
        }
        return false;
    }

    public String getConnectedLobbi(Object id) {
        for (int indexParseJs = 0; indexParseJs < array.size(); indexParseJs++) {
            JSONObject obs = (JSONObject) array.get(indexParseJs);
            if (id.equals(obs.get("id"))) {
                for (int indexParseJs2 = 0; indexParseJs2 < array.size(); indexParseJs2++) {
                    JSONObject obs2 = (JSONObject) array.get(indexParseJs2);
                    if (!obs.equals(obs2))
                        if (obs2.get("lobbiConnection").equals(obs.get("lobbiCreate"))) {
                            return (String) obs2.get("id");
                        }
                }
            }
        }
        return null;
    }
}


