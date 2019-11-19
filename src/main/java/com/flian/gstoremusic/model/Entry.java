package com.flian.gstoremusic.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Unaware
 * @Description: ${description}
 * @Title: Entry
 * @ProjectName gstore-music
 * @date 2019/8/8 22:13
 */
public class Entry {
    @SerializedName("type")
    private String type;
    @SerializedName("value")
    private String value;

    public Entry(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.type + ": " + this.value;
    }
}
