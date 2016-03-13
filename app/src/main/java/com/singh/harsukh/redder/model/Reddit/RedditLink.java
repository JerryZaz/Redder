package com.singh.harsukh.redder.model.Reddit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Henry on 3/6/2016.
 */
@SuppressWarnings("unused")
public class RedditLink extends RedditSubmission implements Parcelable {
    public static final Creator<RedditLink> CREATOR = new Creator<RedditLink>() {
        @Override
        public RedditLink createFromParcel(Parcel in) {
            return new RedditLink(in);
        }

        @Override
        public RedditLink[] newArray(int size) {
            return new RedditLink[size];
        }
    };
    private String domain;
    private String selftext_html;
    private String selftext;
    private String link_flair_text;
    private boolean clicked;
    private boolean hidden;
    private String thumbnail;
    private boolean is_self;
    private String permalink;
    private boolean stickied;
    private String url;
    private String title;
    private int num_comments;
    private boolean visited;

    protected RedditLink(Parcel in) {
        super(in);
        domain = in.readString();
        selftext_html = in.readString();
        selftext = in.readString();
        link_flair_text = in.readString();
        clicked = in.readByte() != 0;
        hidden = in.readByte() != 0;
        thumbnail = in.readString();
        is_self = in.readByte() != 0;
        permalink = in.readString();
        stickied = in.readByte() != 0;
        url = in.readString();
        title = in.readString();
        num_comments = in.readInt();
        visited = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(domain);
        dest.writeString(selftext_html);
        dest.writeString(selftext);
        dest.writeString(link_flair_text);
        dest.writeByte((byte) (clicked ? 1 : 0));
        dest.writeByte((byte) (hidden ? 1 : 0));
        dest.writeString(thumbnail);
        dest.writeByte((byte) (is_self ? 1 : 0));
        dest.writeString(permalink);
        dest.writeByte((byte) (stickied ? 1 : 0));
        dest.writeString(url);
        dest.writeString(title);
        dest.writeInt(num_comments);
        dest.writeByte((byte) (visited ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSelftext_html() {
        return selftext_html;
    }

    public void setSelftext_html(String selftext_html) {
        this.selftext_html = selftext_html;
    }

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    public String getLink_flair_text() {
        return link_flair_text;
    }

    public void setLink_flair_text(String link_flair_text) {
        this.link_flair_text = link_flair_text;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean is_self() {
        return is_self;
    }

    public void setIs_self(boolean is_self) {
        this.is_self = is_self;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public boolean isStickied() {
        return stickied;
    }

    public void setStickied(boolean stickied) {
        this.stickied = stickied;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(int num_comments) {
        this.num_comments = num_comments;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
