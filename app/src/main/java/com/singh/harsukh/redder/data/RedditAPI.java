package com.singh.harsukh.redder.data;

import com.singh.harsukh.redder.model.Listing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Henry on 3/4/2016.
 */
public interface RedditAPI {

    @GET("r/{subreddit}/.json")
    Call<Listing> getPostsFromSubreddit(
            @Path("subreddit") String subreddit
    );
}
