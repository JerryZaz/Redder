package com.singh.harsukh.redder.model;

import java.util.List;

/**
 * Created by Henry on 3/6/2016.
 */
@SuppressWarnings("unused")
public class Thing {

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
        private Object after;
        private Object before;

        private List<ChildrenEntity> children;

        public String getModhash() {
            return modhash;
        }

        public void setModhash(String modhash) {
            this.modhash = modhash;
        }

        public Object getAfter() {
            return after;
        }

        public void setAfter(Object after) {
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

                private String subreddit_id;
                private String banned_by;
                private String removal_reason;
                private String link_id;
                private String likes;
                private RepliesEntity replies;
                private String saved;
                private String id;
                private int gilded;
                private String archived;
                private String report_reasons;
                private String author;
                private String parent_id;
                private int score;
                private String approved_by;
                private int controversiality;
                private String body;
                private String edited;
                private String author_flair_css_class;
                private int downs;
                private String body_html;
                private String subreddit;
                private String name;
                private boolean score_hidden;
                private boolean stickied;
                private int created;
                private String author_flair_text;
                private int created_utc;
                private String distinguished;
                private String num_reports;
                private int ups;
                private List<?> user_reports;
                private List<?> mod_reports;
                private PreviewEntity preview;

                public String getBanned_by() {
                    return banned_by;
                }

                public void setBanned_by(String banned_by) {
                    this.banned_by = banned_by;
                }

                public String getRemoval_reason() {
                    return removal_reason;
                }

                public void setRemoval_reason(String removal_reason) {
                    this.removal_reason = removal_reason;
                }

                public String getLikes() {
                    return likes;
                }

                public void setLikes(String likes) {
                    this.likes = likes;
                }

                public String getSaved() {
                    return saved;
                }

                public void setSaved(String saved) {
                    this.saved = saved;
                }

                public String getArchived() {
                    return archived;
                }

                public void setArchived(String archived) {
                    this.archived = archived;
                }

                public String getReport_reasons() {
                    return report_reasons;
                }

                public void setReport_reasons(String report_reasons) {
                    this.report_reasons = report_reasons;
                }

                public String getDistinguished() {
                    return distinguished;
                }

                public void setDistinguished(String distinguished) {
                    this.distinguished = distinguished;
                }

                public String getNum_reports() {
                    return num_reports;
                }

                public void setNum_reports(String num_reports) {
                    this.num_reports = num_reports;
                }

                public String getApproved_by() {
                    return approved_by;
                }

                public void setApproved_by(String approved_by) {
                    this.approved_by = approved_by;
                }

                public String getEdited() {
                    return edited;
                }

                public void setEdited(String edited) {
                    this.edited = edited;
                }

                public PreviewEntity getPreview() {
                    return preview;
                }

                public void setPreview(PreviewEntity preview) {
                    this.preview = preview;
                }

                public String getSubreddit_id() {
                    return subreddit_id;
                }

                public void setSubreddit_id(String subreddit_id) {
                    this.subreddit_id = subreddit_id;
                }

                public String getLink_id() {
                    return link_id;
                }

                public void setLink_id(String link_id) {
                    this.link_id = link_id;
                }

                public RepliesEntity getReplies() {
                    return replies;
                }

                public void setReplies(RepliesEntity replies) {
                    this.replies = replies;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public int getGilded() {
                    return gilded;
                }

                public void setGilded(int gilded) {
                    this.gilded = gilded;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public String getParent_id() {
                    return parent_id;
                }

                public void setParent_id(String parent_id) {
                    this.parent_id = parent_id;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
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

                public String getBody_html() {
                    return body_html;
                }

                public void setBody_html(String body_html) {
                    this.body_html = body_html;
                }

                public String getSubreddit() {
                    return subreddit;
                }

                public void setSubreddit(String subreddit) {
                    this.subreddit = subreddit;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public boolean isScore_hidden() {
                    return score_hidden;
                }

                public void setScore_hidden(boolean score_hidden) {
                    this.score_hidden = score_hidden;
                }

                public boolean isStickied() {
                    return stickied;
                }

                public void setStickied(boolean stickied) {
                    this.stickied = stickied;
                }

                public int getCreated() {
                    return created;
                }

                public void setCreated(int created) {
                    this.created = created;
                }

                public String getAuthor_flair_text() {
                    return author_flair_text;
                }

                public void setAuthor_flair_text(String author_flair_text) {
                    this.author_flair_text = author_flair_text;
                }

                public int getCreated_utc() {
                    return created_utc;
                }

                public void setCreated_utc(int created_utc) {
                    this.created_utc = created_utc;
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

                public static class PreviewEntity {

                    private List<ImagesEntity> images;

                    public List<ImagesEntity> getImages() {
                        return images;
                    }

                    public void setImages(List<ImagesEntity> images) {
                        this.images = images;
                    }

                    public static class ImagesEntity {

                        private SourceEntity source;
                        private VariantsEntity variants;
                        private String id;

                        private List<ResolutionsEntity> resolutions;

                        public SourceEntity getSource() {
                            return source;
                        }

                        public void setSource(SourceEntity source) {
                            this.source = source;
                        }

                        public VariantsEntity getVariants() {
                            return variants;
                        }

                        public void setVariants(VariantsEntity variants) {
                            this.variants = variants;
                        }

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }

                        public List<ResolutionsEntity> getResolutions() {
                            return resolutions;
                        }

                        public void setResolutions(List<ResolutionsEntity> resolutions) {
                            this.resolutions = resolutions;
                        }

                        public static class SourceEntity {
                            private String url;
                            private int width;
                            private int height;

                            public String getUrl() {
                                return url;
                            }

                            public void setUrl(String url) {
                                this.url = url;
                            }

                            public int getWidth() {
                                return width;
                            }

                            public void setWidth(int width) {
                                this.width = width;
                            }

                            public int getHeight() {
                                return height;
                            }

                            public void setHeight(int height) {
                                this.height = height;
                            }
                        }

                        public static class VariantsEntity {
                        }

                        public static class ResolutionsEntity {
                            private String url;
                            private int width;
                            private int height;

                            public String getUrl() {
                                return url;
                            }

                            public void setUrl(String url) {
                                this.url = url;
                            }

                            public int getWidth() {
                                return width;
                            }

                            public void setWidth(int width) {
                                this.width = width;
                            }

                            public int getHeight() {
                                return height;
                            }

                            public void setHeight(int height) {
                                this.height = height;
                            }
                        }
                    }
                }

                public static class RepliesEntity {
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

                }
            }
        }
    }
}
