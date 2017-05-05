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
        System.out.println("до json: " + getJson());
        if (check) {
            array.remove(indexFind(id));
            System.out.println("после json: " + getJson());
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
}


