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
        System.out.println("до: " + getJson());
        if (check) {
            array.remove(indexFind(id));
            System.out.println("после: " + getJson());
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
        System.out.println("Отправляет: " + array.toJSONString());
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


