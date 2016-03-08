package com.singh.harsukh.redder.model.Reddit;

import java.util.List;

/**
 * Created by Henry on 3/6/2016.
 */
public class RedditListing extends RedditObject {
    private String modhash;
    private String after;
    private String before;
    private List<RedditObject> children;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public List<RedditObject> getChildren() {
        return children;
    }

    public void setChildren(List<RedditObject> children) {
        this.children = children;
    }
}
