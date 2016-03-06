package com.singh.harsukh.redder.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Henry on 3/4/2016.
 */
@SuppressWarnings("unused")
public class Listing {

    private String kind;

    private DataEntity data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        private String modhash;
        private String after;
        private Object before;

        private List<ChildrenEntity> children;

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

        public Object getBefore() {
            return before;
        }

        public void setBefore(Object before) {
            this.before = before;
        }

        public List<ChildrenEntity> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenEntity> children) {
            this.children = children;
        }

        public static class ChildrenEntity {
            private String kind;

            private ChildrenDataEntity data;

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            public ChildrenDataEntity getData() {
                return data;
            }

            public void setData(ChildrenDataEntity data) {
                this.data = data;
            }

            public static class ChildrenDataEntity {
                private String domain;
                private Object banned_by;
                private String subreddit;

                @SerializedName("body_html")
                private String selftext_html;
                @SerializedName("body")
                private String selftext;
                private Object likes;
                private Object suggested_sort;
                private Object secure_media;
                private Object link_flair_text;
                private String id;
                private Object from_kind;
                private int gilded;
                private boolean archived;
                private boolean clicked;
                private Object report_reasons;
                private String author;
                private Object media;
                private int score;
                private Object approved_by;
                private boolean over_18;
                private boolean hidden;
                private int num_comments;
                private String thumbnail;
                private String subreddit_id;
                private boolean hide_score;
                private String edited;
                private Object link_flair_css_class;
                private String author_flair_css_class;
                private int downs;
                private SecureMediaEmbedEntity secure_media_embed;
                private boolean saved;
                private Object removal_reason;
                private boolean stickied;
                private Object from;
                private boolean is_self;
                private Object from_id;
                private String permalink;
                private boolean locked;
                private String name;
                private int created;
                private String url;
                private String author_flair_text;
                private boolean quarantine;
                private String title;
                private int created_utc;
                private Object distinguished;
                private boolean visited;
                private Object num_reports;
                private int ups;
                private List<?> user_reports;
                private List<?> mod_reports;

                public String getEdited() {
                    return edited;
                }

                public void setEdited(String edited) {
                    this.edited = edited;
                }

                public String getDomain() {
                    return domain;
                }

                public void setDomain(String domain) {
                    this.domain = domain;
                }

                public Object getBanned_by() {
                    return banned_by;
                }

                public void setBanned_by(Object banned_by) {
                    this.banned_by = banned_by;
                }

                public String getSubreddit() {
                    return subreddit;
                }

                public void setSubreddit(String subreddit) {
                    this.subreddit = subreddit;
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

                public Object getLikes() {
                    return likes;
                }

                public void setLikes(Object likes) {
                    this.likes = likes;
                }

                public Object getSuggested_sort() {
                    return suggested_sort;
                }

                public void setSuggested_sort(Object suggested_sort) {
                    this.suggested_sort = suggested_sort;
                }

                public Object getSecure_media() {
                    return secure_media;
                }

                public void setSecure_media(Object secure_media) {
                    this.secure_media = secure_media;
                }

                public Object getLink_flair_text() {
                    return link_flair_text;
                }

                public void setLink_flair_text(Object link_flair_text) {
                    this.link_flair_text = link_flair_text;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public Object getFrom_kind() {
                    return from_kind;
                }

                public void setFrom_kind(Object from_kind) {
                    this.from_kind = from_kind;
                }

                public int getGilded() {
                    return gilded;
                }

                public void setGilded(int gilded) {
                    this.gilded = gilded;
                }

                public boolean isArchived() {
                    return archived;
                }

                public void setArchived(boolean archived) {
                    this.archived = archived;
                }

                public boolean isClicked() {
                    return clicked;
                }

                public void setClicked(boolean clicked) {
                    this.clicked = clicked;
                }

                public Object getReport_reasons() {
                    return report_reasons;
                }

                public void setReport_reasons(Object report_reasons) {
                    this.report_reasons = report_reasons;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public Object getMedia() {
                    return media;
                }

                public void setMedia(Object media) {
                    this.media = media;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public Object getApproved_by() {
                    return approved_by;
                }

                public void setApproved_by(Object approved_by) {
                    this.approved_by = approved_by;
                }

                public boolean isOver_18() {
                    return over_18;
                }

                public void setOver_18(boolean over_18) {
                    this.over_18 = over_18;
                }

                public boolean isHidden() {
                    return hidden;
                }

                public void setHidden(boolean hidden) {
                    this.hidden = hidden;
                }

                public int getNum_comments() {
                    return num_comments;
                }

                public void setNum_comments(int num_comments) {
                    this.num_comments = num_comments;
                }

                public String getThumbnail() {
                    return thumbnail;
                }

                public void setThumbnail(String thumbnail) {
                    this.thumbnail = thumbnail;
                }

                public String getSubreddit_id() {
                    return subreddit_id;
                }

                public void setSubreddit_id(String subreddit_id) {
                    this.subreddit_id = subreddit_id;
                }

                public boolean isHide_score() {
                    return hide_score;
                }

                public void setHide_score(boolean hide_score) {
                    this.hide_score = hide_score;
                }

                public Object getLink_flair_css_class() {
                    return link_flair_css_class;
                }

                public void setLink_flair_css_class(Object link_flair_css_class) {
                    this.link_flair_css_class = link_flair_css_class;
                }

                public String getAuthor_flair_css_class() {
                    return author_flair_css_class;
                }

                public void setAuthor_flair_css_class(String author_flair_css_class) {
                    this.author_flair_css_class = author_flair_css_class;
                }

                public int getDowns() {
                    return downs;
                }

                public void setDowns(int downs) {
                    this.downs = downs;
                }

                public SecureMediaEmbedEntity getSecure_media_embed() {
                    return secure_media_embed;
                }

                public void setSecure_media_embed(SecureMediaEmbedEntity secure_media_embed) {
                    this.secure_media_embed = secure_media_embed;
                }

                public boolean isSaved() {
                    return saved;
                }

                public void setSaved(boolean saved) {
                    this.saved = saved;
                }

                public Object getRemoval_reason() {
                    return removal_reason;
                }

                public void setRemoval_reason(Object removal_reason) {
                    this.removal_reason = removal_reason;
                }

                public boolean isStickied() {
                    return stickied;
                }

                public void setStickied(boolean stickied) {
                    this.stickied = stickied;
                }

                public Object getFrom() {
                    return from;
                }

                public void setFrom(Object from) {
                    this.from = from;
                }

                public boolean isIs_self() {
                    return is_self;
                }

                public void setIs_self(boolean is_self) {
                    this.is_self = is_self;
                }

                public Object getFrom_id() {
                    return from_id;
                }

                public void setFrom_id(Object from_id) {
                    this.from_id = from_id;
                }

                public String getPermalink() {
                    return permalink;
                }

                public void setPermalink(String permalink) {
                    this.permalink = permalink;
                }

                public boolean isLocked() {
                    return locked;
                }

                public void setLocked(boolean locked) {
                    this.locked = locked;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getCreated() {
                    return created;
                }

                public void setCreated(int created) {
                    this.created = created;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getAuthor_flair_text() {
                    return author_flair_text;
                }

                public void setAuthor_flair_text(String author_flair_text) {
                    this.author_flair_text = author_flair_text;
                }

                public boolean isQuarantine() {
                    return quarantine;
                }

                public void setQuarantine(boolean quarantine) {
                    this.quarantine = quarantine;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getCreated_utc() {
                    return created_utc;
                }

                public void setCreated_utc(int created_utc) {
                    this.created_utc = created_utc;
                }

                public Object getDistinguished() {
                    return distinguished;
                }

                public void setDistinguished(Object distinguished) {
                    this.distinguished = distinguished;
                }

                public boolean isVisited() {
                    return visited;
                }

                public void setVisited(boolean visited) {
                    this.visited = visited;
                }

                public Object getNum_reports() {
                    return num_reports;
                }

                public void setNum_reports(Object num_reports) {
                    this.num_reports = num_reports;
                }

                public int getUps() {
                    return ups;
                }

                public void setUps(int ups) {
                    this.ups = ups;
                }

                public List<?> getUser_reports() {
                    return user_reports;
                }

                public void setUser_reports(List<?> user_reports) {
                    this.user_reports = user_reports;
                }

                public List<?> getMod_reports() {
                    return mod_reports;
                }

                public void setMod_reports(List<?> mod_reports) {
                    this.mod_reports = mod_reports;
                }

                public static class SecureMediaEmbedEntity {
                }
            }
        }
    }
}
