package com.singh.harsukh.redder.data;

import com.singh.harsukh.redder.model.Listing;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Henry on 3/4/2016.
 */
public interface RedditAPI {

    @GET("r/{subreddit}/.json")
    Call<Listing> getPostsFromSubreddit(
            @Path("subreddit") String subreddit
    );

    /**
     * Retrieves the comments tree for the passed article
     * @param nameOfSubreddit must be the full name prefix_id (i.e. t5_2cneq)
     * @param articleID field "id" of the ChildrenDataEntity class (i.e. 492viu)
     * @return parsed object of the callback type
     */
    @GET("r/{subreddit}/comments/{articleID}.json")
    Call<List<Listing>> getCommentsFromPost(
            @Path("subreddit") String nameOfSubreddit,
            @Path("articleID") String articleID
    );

    @GET("r/{subreddit}/search.json")
    Call<String> searchListingsWithQueryParamAndSort(
            @Query("q") String queryParam,
            @Query("sort") String sortOrder
    );
}
