package com.flian.gstoremusic.model;

import com.google.gson.annotations.SerializedName;

public class Music {
    private long id;
    @SerializedName("s")
    private Entry subject;
    @SerializedName("p")
    private Entry predicate;
    @SerializedName("o")
    private Entry object;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Entry getSubject() {
        return subject;
    }

    public void setSubject(Entry subject) {
        this.subject = subject;
    }

    public Entry getPredicate() {
        return predicate;
    }

    public void setPredicate(Entry predicate) {
        this.predicate = predicate;
    }

    public Entry getObject() {
        return object;
    }

    public void setObject(Entry object) {
        this.object = object;
    }
}
