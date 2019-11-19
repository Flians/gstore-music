package com.flian.gstoremusic.service;

import com.flian.gstoremusic.model.DBInfo;
import com.flian.gstoremusic.model.Entry;
import com.flian.gstoremusic.model.Music;

import java.util.List;
import java.util.Map;

public interface MusicService {

    public List<Music> getAllMusic(int pageNum, int pageSize) throws Exception;

    public Map<String, List<Map<String,Entry>>> findMusicBySparql(String sparql, int pageNum, int pageSize);

    public DBInfo getDBInfo(String DBName);

}
