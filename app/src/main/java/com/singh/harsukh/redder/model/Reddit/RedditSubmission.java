package com.singh.harsukh.redder.model.Reddit;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Henry on 3/6/2016.
 */
@SuppressWarnings("unused")
public class RedditSubmission extends RedditObject implements Parcelable {
    public static final Creator<RedditSubmission> CREATOR = new Creator<RedditSubmission>() {
        @Override
        public RedditSubmission createFromParcel(Parcel in) {
            return new RedditSubmission(in);
        }

        @Override
        public RedditSubmission[] newArray(int size) {
            return new RedditSubmission[size];
        }
    };
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

    protected RedditSubmission(Parcel in) {
        banned_by = in.readString();
        subreddit = in.readString();
        saved = in.readByte() != 0;
        id = in.readString();
        gilded = in.readInt();
        author = in.readString();
        score = in.readInt();
        name = in.readString();
        created = in.readLong();
        author_flair_text = in.readString();
        ups = in.readInt();
    }

    public static String getDiff(Date date) {
        Date date2 = new Date();
        long diff = getDateDiff(date, date2, TimeUnit.HOURS);
        if (diff > 24) {
            diff = getDateDiff(date, date2, TimeUnit.DAYS);
            return String.valueOf(diff) + "d";
        } else if (diff < 1) {
            diff = getDateDiff(date, date2, TimeUnit.MINUTES);
            if (diff < 1){
                diff = getDateDiff(date,date2,TimeUnit.SECONDS);
                return String.valueOf(diff) + "s";
            }
            return String.valueOf(diff) + "m";
        } else {
            return String.valueOf(diff) + "h";
        }
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public String getCreated_utc() {
        DateTime dataTime = created_utc;
        Date date = created_utc.toDate();
        return getDiff(date);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(banned_by);
        dest.writeString(subreddit);
        dest.writeByte((byte) (saved ? 1 : 0));
        dest.writeString(id);
        dest.writeInt(gilded);
        dest.writeString(author);
        dest.writeInt(score);
        dest.writeString(name);
        dest.writeLong(created);
        dest.writeString(author_flair_text);
        dest.writeInt(ups);
    }
}
