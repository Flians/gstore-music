package com.flian.gstoremusic.service.impl;

import com.flian.gstoremusic.model.DBInfo;
import com.flian.gstoremusic.model.Entry;
import com.flian.gstoremusic.model.Music;
import com.flian.gstoremusic.service.MusicService;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import jgsc.GstoreConnector;

import java.util.*;

public class MusicServiceImpl implements MusicService {
    // connect to gStore
    GstoreConnector gc = new GstoreConnector("127.0.0.1", 9000, "root", "123456");
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Override
    public List<Music> getAllMusic(int pageNum, int pageSize) throws Exception{
        String sparql= String .format("SELECT * WHERE {?s ?p ?o} LIMIT %s OFFSET %s", pageSize, (pageNum-1)*pageSize);
        String res = gc.query("music", "json", sparql);

        // System.out.println(res);

        JsonElement jsonElement = new JsonParser().parse(res);
        if (jsonElement.isJsonNull()) {
            return null;
        } else if (jsonElement.isJsonObject()) {
            JsonElement results = jsonElement.getAsJsonObject().get("results").getAsJsonObject().get("bindings");
            List<Music> MusicList = gson.fromJson(results, new TypeToken<List<Music>>() {}.getType());
            for (int i=0; i < MusicList.size(); i++)
                MusicList.get(i).setId(i);
            return MusicList;
        } else if (jsonElement.isJsonArray()) {
            return null;
        } else if (jsonElement.isJsonPrimitive()) {
            // App app = new Gson().fromJson(primitive.getAsJsonPrimitive().getAsString(), App.class);
            // App app = new Gson().fromJson(primitive.getAsJsonPrimitive().toString(), App.class);
            // System.out.println(app);
            return null;
        }
        return null;
    }

    @Override
    public DBInfo getDBInfo(String DBName) {
        String res = gc.monitor(DBName);
        if (res.isEmpty())
            return null;
        return new Gson().fromJson(res, new TypeToken<DBInfo>() {}.getType());
    }

    @Override
    public Map<String, List<Map<String,Entry>>>  findMusicBySparql(String sparql, int pageNum, int pageSize) {
        String res = gc.query("music", "json", sparql);
        // System.out.println(res);

        Map<String, List<Map<String,Entry>>> result = new LinkedHashMap<String, List<Map<String,Entry>>>();
        ArrayList<Map<String,Entry>> status = new ArrayList<Map<String,Entry>>();
        List<Map<String,Entry>> musics = new ArrayList<Map<String,Entry>>();

        JsonElement jsonElement = new JsonParser().parse(res);
        if (jsonElement.isJsonNull()) {
            return null;
        } else if (jsonElement.isJsonObject()) {
            String statusCode = jsonElement.getAsJsonObject().get("StatusCode").getAsString();
            String statusMsg = jsonElement.getAsJsonObject().get("StatusMsg").getAsString();

            Map<String,Entry> tempM = new LinkedHashMap<String, Entry>();
            tempM.put("statusCode", new Entry("statusCode", statusCode));
            tempM.put("statusMsg", new Entry("statusMsg", statusMsg));
            status.add(tempM);
            result.put("status", status);

            // error
            if(!statusCode.equals("0")) {
                result.put("musicList", musics);
                return result;
            }

            JsonArray attName = jsonElement.getAsJsonObject().get("head").getAsJsonObject().get("vars").getAsJsonArray();
            JsonArray results = jsonElement.getAsJsonObject().get("results").getAsJsonObject().get("bindings").getAsJsonArray();
            for (JsonElement music:results) {
                Map<String,Entry> tempMusic = new LinkedHashMap<String, Entry>();
                for (JsonElement att:attName) {
                    JsonObject item = music.getAsJsonObject().get(att.getAsString()).getAsJsonObject();
                    tempMusic.put(att.getAsString(), new Entry(item.get("type").getAsString(), item.get("value").getAsString()));
                }
                musics.add(tempMusic);
            }
            result.put("musicList", musics);
            return result;
            // List<Music> MusicList = gson.fromJson(results, new TypeToken<List<Map<String,Entry>>>() {}.getType());
        } else if (jsonElement.isJsonArray()) {
            return null;
        } else if (jsonElement.isJsonPrimitive()) {
            return null;
        }
        return null;
    }
}