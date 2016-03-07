package com.singh.harsukh.redder.model.Reddit;

import org.joda.time.DateTime;

/**
 * Created by Henry on 3/6/2016.
 */
@SuppressWarnings("unused")
public class RedditSubmission extends RedditObject {
    private String banned_by;
    private String subreddit;
    private boolean saved;
    private String id;
    private int gilded;
    private String author;
    private int score;
    private String name;
    private long created;
    private String author_flair_text;
    private DateTime created_utc;
    private int ups;

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public DateTime getCreated_utc() {
        return created_utc;
    }

    public void setCreated_utc(DateTime created_utc) {
        this.created_utc = created_utc;
    }

    public String getAuthor_flair_text() {
        return author_flair_text;
    }

    public void setAuthor_flair_text(String author_flair_text) {
        this.author_flair_text = author_flair_text;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getGilded() {
        return gilded;
    }

    public void setGilded(int gilded) {
        this.gilded = gilded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getBanned_by() {
        return banned_by;
    }

    public void setBanned_by(String banned_by) {
        this.banned_by = banned_by;
    }
}
