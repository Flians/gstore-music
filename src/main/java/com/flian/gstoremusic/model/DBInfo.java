package com.flian.gstoremusic.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Unaware
 * @Description: ${description}
 * @Title: DBInfo
 * @ProjectName gstore-music
 * @date 2019/8/8 21:40
 */
public class DBInfo {
    @SerializedName("database")
    private String db_name;
    @SerializedName("creator")
    private String creator;
    @SerializedName("built_time")
    private String built_time;
    @SerializedName("triple num")
    private long triple_num;
    @SerializedName("entity num")
    private long entity_num;
    @SerializedName("literal num")
    private long literal_num;
    @SerializedName("subject num")
    private long subject_num;
    @SerializedName("predicate num")
    private long predicate_num;
    @SerializedName("connection num")
    private long connection_num;

    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getDb_name() {
        return db_name;
    }

    public DBInfo setDb_name(String db_name) {
        this.db_name = db_name;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public DBInfo setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public String getBuilt_time() {
        return built_time;
    }

    public DBInfo setBuilt_time(Date built_time) {
        this.built_time = sdFormat.format(built_time);
        return this;
    }

    public DBInfo setBuilt_time(String built_time) {
            this.built_time = built_time;
        return this;
    }

    public long getTriple_num() {
        return triple_num;
    }

    public DBInfo setTriple_num(long triple_num) {
        this.triple_num = triple_num;
        return this;
    }

    public long getEntity_num() {
        return entity_num;
    }

    public DBInfo setEntity_num(long entity_num) {
        this.entity_num = entity_num;
        return this;
    }

    public long getLiteral_num() {
        return literal_num;
    }

    public DBInfo setLiteral_num(long literal_num) {
        this.literal_num = literal_num;
        return this;
    }

    public long getSubject_num() {
        return subject_num;
    }

    public DBInfo setSubject_num(long subject_num) {
        this.subject_num = subject_num;
        return this;
    }

    public long getPredicate_num() {
        return predicate_num;
    }

    public DBInfo setPredicate_num(long predicate_num) {
        this.predicate_num = predicate_num;
        return this;
    }

    public long getConnection_num() {
        return connection_num;
    }

    public void setConnection_num(long connection_num) {
        this.connection_num = connection_num;
    }
}
