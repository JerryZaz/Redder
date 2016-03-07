package com.singh.harsukh.redder.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.singh.harsukh.redder.BuildConfig;
import com.singh.harsukh.redder.model.Listing;
import com.singh.harsukh.redder.model.Reddit.RedditListing;
import com.singh.harsukh.redder.model.Reddit.RedditObject;
import com.singh.harsukh.redder.model.Reddit.RedditResponse;
import com.singh.harsukh.redder.model.Thing;

import org.joda.time.DateTime;

import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Henry on 3/4/2016.
 */
public interface RedditService {

    @GET("r/{subreddit}.json")
    Call<RedditResponse<RedditListing>> getSubreddit(
            @Path("subreddit") String subreddit
    );

    @GET("r/{subreddit}/comments/{link_id}.json")
    Call<List<RedditResponse<RedditListing>>> getComments(
            @Path("subreddit") String subreddit,
            @Path("link_id") String id
    );

    /**
     * Fetches a specific comment. This endpoint can be used to draw focus on a single t1
     * @param name format (full name, not just id. Sample: t1_d0ooweb )
     * @return t1
     */
    @GET("api/info.json")
    Call<Listing> fetchComment(
            @Query("id") String name
    );

    /**
     * Uses a search param to query a specified subreddit
     * @param subreddit t5 thing
     * @param queryParam search query
     * @return t3 things
     */
    @GET("r/{subreddit}/search.json")
    Call<Thing> searchListingsWithQueryParam(
            @Path("subreddit") String subreddit,
            @Query("q") String queryParam
    );

    /**
     *
     * @param queryParam
     * @param sortOrder
     * @return
     */
    @GET("r/{subreddit}/search.json")
    Call<Listing> searchListingsWithQueryParamAndSort(
            @Query("q") String queryParam,
            @Query("sort") String sortOrder
    );

    class Implementation{

        public static RedditService get(){
            return getBuilder()
                    .build()
                    .create(RedditService.class);
        }

        static Retrofit.Builder getBuilder(){
            return new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_REDDIT_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()));
        }

        private static Gson getGson() {
            return new GsonBuilder()
                    .registerTypeAdapter(RedditObject.class, new RedditObjectDeserializer())
                    .registerTypeAdapter(DateTime.class, new DateTimeDeserializer())
                    .create();
        }
    }

}
