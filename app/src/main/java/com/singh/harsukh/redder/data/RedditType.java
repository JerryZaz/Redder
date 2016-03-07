package com.singh.harsukh.redder.data;

import com.singh.harsukh.redder.model.Reddit.RedditComment;
import com.singh.harsukh.redder.model.Reddit.RedditLink;
import com.singh.harsukh.redder.model.Reddit.RedditListing;
import com.singh.harsukh.redder.model.Reddit.RedditMore;

/**
 * Created by Henry on 3/6/2016.
 */
public enum RedditType {
    t1(RedditComment.class),
    t3(RedditLink.class),
    Listing(RedditListing.class),
    more(RedditMore.class);

    private final Class mCls;

    RedditType(Class mCls) {
        this.mCls = mCls;
    }

    public Class getDerivedClass() {
        return mCls;
    }
}
