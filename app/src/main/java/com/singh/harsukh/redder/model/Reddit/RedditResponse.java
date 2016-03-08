package com.singh.harsukh.redder.model.Reddit;

/**
 * Created by Henry on 3/6/2016.
 */
@SuppressWarnings("unused")
public class RedditResponse<T> {

    T data;

    public RedditResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
