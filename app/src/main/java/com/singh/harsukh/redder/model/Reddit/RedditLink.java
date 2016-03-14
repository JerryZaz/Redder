package com.singh.harsukh.redder.model.Reddit;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henry on 3/6/2016.
 */
@SuppressWarnings("unused")
public class RedditLink extends RedditSubmission implements Parcelable{

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
    private PreviewEntity preview;


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
        preview = in.readParcelable(PreviewEntity.class.getClassLoader());
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
        dest.writeParcelable(preview, flags);
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

    public PreviewEntity getPreview() {
        return preview;
    }

    public void setPreview(PreviewEntity preview) {
        this.preview = preview;
    }


    public static class PreviewEntity implements Parcelable{
        public static final Creator<PreviewEntity> CREATOR = new Creator<PreviewEntity>() {
            @Override
            public PreviewEntity createFromParcel(Parcel in) {
                return new PreviewEntity(in);
            }

            @Override
            public PreviewEntity[] newArray(int size) {
                return new PreviewEntity[size];
            }
        };
        private List<ImagesEntity> images;

        /**
         * source : {"url":"https://i.redditmedia.com/V71rE3q4oKUgqapTfhBiq_x6AsEI-qHZ_pFYHbWMjb8.jpg?s=fef53eee198e1b720a7360219800b4d2","width":460,"height":276}
         * resolutions : [{"url":"https://i.redditmedia.com/V71rE3q4oKUgqapTfhBiq_x6AsEI-qHZ_pFYHbWMjb8.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=108&amp;s=6e2347f2acd2c8359f963f2a8b7416e0","width":108,"height":64},{"url":"https://i.redditmedia.com/V71rE3q4oKUgqapTfhBiq_x6AsEI-qHZ_pFYHbWMjb8.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=216&amp;s=515f9976797fe13f016db4152eeac8cb","width":216,"height":129},{"url":"https://i.redditmedia.com/V71rE3q4oKUgqapTfhBiq_x6AsEI-qHZ_pFYHbWMjb8.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=320&amp;s=5ce71747bbe8cc3687c4e5806da0242e","width":320,"height":192}]
         * variants : {}
         * id : odCLnvsXyAU97qyT2xw4Hyq6oWKyK08wfAnGxq4LuAE
         */

        protected PreviewEntity(Parcel in) {
            images = new ArrayList<>();
            in.readTypedList(images, ImagesEntity.CREATOR);
        }

        public List<ImagesEntity> getImages() {
            return images;
        }

        public void setImages(List<ImagesEntity> images) {
            this.images = images;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(images);
        }

        public static class ImagesEntity implements Parcelable {
            public static final Creator<ImagesEntity> CREATOR = new Creator<ImagesEntity>() {
                @Override
                public ImagesEntity createFromParcel(Parcel in) {
                    return new ImagesEntity(in);
                }

                @Override
                public ImagesEntity[] newArray(int size) {
                    return new ImagesEntity[size];
                }
            };
            /**
             * url : https://i.redditmedia.com/V71rE3q4oKUgqapTfhBiq_x6AsEI-qHZ_pFYHbWMjb8.jpg?s=fef53eee198e1b720a7360219800b4d2
             * width : 460
             * height : 276
             */

            private SourceEntity source;
            private VariantsEntity variants;
            private String id;
            /**
             * url : https://i.redditmedia.com/V71rE3q4oKUgqapTfhBiq_x6AsEI-qHZ_pFYHbWMjb8.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=108&amp;s=6e2347f2acd2c8359f963f2a8b7416e0
             * width : 108
             * height : 64
             */

            private List<ResolutionsEntity> resolutions;

            protected ImagesEntity(Parcel in) {
                id = in.readString();
                resolutions = new ArrayList<>();
                in.readTypedList(resolutions, ResolutionsEntity.CREATOR);
                source = in.readParcelable(SourceEntity.class.getClassLoader());
            }

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeTypedList(resolutions);
                dest.writeParcelable(source, flags);
            }

            public static class SourceEntity implements Parcelable {

                public static final Creator<SourceEntity> CREATOR = new Creator<SourceEntity>() {
                    @Override
                    public SourceEntity createFromParcel(Parcel in) {
                        return new SourceEntity(in);
                    }

                    @Override
                    public SourceEntity[] newArray(int size) {
                        return new SourceEntity[size];
                    }
                };
                private String url;
                private int width;
                private int height;

                protected SourceEntity(Parcel in) {
                    url = in.readString();
                    width = in.readInt();
                    height = in.readInt();
                }

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(url);
                    dest.writeInt(width);
                    dest.writeInt(height);

                }
            }

            public static class VariantsEntity {
            }

            public static class ResolutionsEntity implements Parcelable {
                public static final Creator<ResolutionsEntity> CREATOR = new Creator<ResolutionsEntity>() {
                    @Override
                    public ResolutionsEntity createFromParcel(Parcel in) {
                        return new ResolutionsEntity(in);
                    }

                    @Override
                    public ResolutionsEntity[] newArray(int size) {
                        return new ResolutionsEntity[size];
                    }
                };
                private String url;
                private int width;
                private int height;

                protected ResolutionsEntity(Parcel in) {
                    url = in.readString();
                    width = in.readInt();
                    height = in.readInt();
                }

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(url);
                    dest.writeInt(width);
                    dest.writeInt(height);
                }
            }
        }
    }

}
