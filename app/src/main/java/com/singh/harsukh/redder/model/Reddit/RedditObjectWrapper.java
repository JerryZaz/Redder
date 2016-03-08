package com.singh.harsukh.redder.model.Reddit;

import com.google.gson.JsonElement;
import com.singh.harsukh.redder.data.RedditType;

/**
 * Created by Henry on 3/6/2016.
 */
public class RedditObjectWrapper {
    RedditType kind;
    JsonElement data;

    public JsonElement getData() {
        return data;
    }

    public RedditType getKind() {
        return kind;
    }
}
