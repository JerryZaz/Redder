package com.singh.harsukh.redder.model.Reddit;

/**
 * Created by Henry on 3/6/2016.
 */
@SuppressWarnings("unused")
public class RedditComment extends RedditSubmission {
    private RedditObject replies;
    private String subreddit_id;
    private String parent_id;
    private int controversiality;
    private String body;
    private String body_html;
    private String link_id;
    private int depth;

    public RedditObject getReplies() {
        return replies;
    }

    public void setReplies(RedditObject replies) {
        this.replies = replies;
    }

    public String getSubreddit_id() {
        return subreddit_id;
    }

    public void setSubreddit_id(String subreddit_id) {
        this.subreddit_id = subreddit_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public int getControversiality() {
        return controversiality;
    }

    public void setControversiality(int controversiality) {
        this.controversiality = controversiality;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getLink_id() {
        return link_id;
    }

    public void setLink_id(String link_id) {
        this.link_id = link_id;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
